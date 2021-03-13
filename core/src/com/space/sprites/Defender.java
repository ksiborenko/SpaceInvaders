package com.space.sprites;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Defender {

    private final Texture defenderTexture;
    private final Vector2 defenderPosition;
    private final PointLight defenderLight;
    private final Rectangle rectangle;

    public Defender(RayHandler rayHandler, int x) {
        int defenderFixedYPosition = 200;
        this.defenderTexture = new Texture("defender.png");
        this.defenderPosition = new Vector2(x, defenderFixedYPosition);
        this.defenderLight = new PointLight(rayHandler, 5, Color.WHITE, 220,
                this.defenderPosition.x + (float) this.defenderTexture.getWidth() / 2,
                this.defenderPosition.y + (float) this.defenderTexture.getHeight() / 2 - 15);
        this.rectangle = new Rectangle(this.defenderPosition.x, this.defenderPosition.y,
                this.defenderTexture.getWidth(), this.defenderTexture.getHeight());
    }

    public void render(SpriteBatch batch) {
        batch.draw(this.defenderTexture, this.defenderPosition.x, this.defenderPosition.y);
        this.lightUpdate();
        this.rectangleUpdate();
    }

    public void lightUpdate() {
        this.defenderLight.setPosition(this.defenderPosition.x + (float) this.defenderTexture.getWidth() / 2,
                this.defenderPosition.y + (float) this.defenderTexture.getHeight() / 2 - 15);
    }

    public void rectangleUpdate() {
        this.rectangle.setPosition(this.defenderPosition);
    }

    public Vector2 getDefenderPosition() {
        return defenderPosition;
    }

    public Texture getDefenderTexture() {
        return defenderTexture;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
