package com.space.sprites;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Defender {

    private final Texture DEFENDER_TEXTURE;
    private final Vector2 defenderPosition;
    private final PointLight defenderLight;
    private final Rectangle rectangle;

    public Defender(RayHandler rayHandler, int x) {
        int defenderFixedYPosition = 200;
        this.DEFENDER_TEXTURE = new Texture("defender.png");
        this.defenderPosition = new Vector2(x, defenderFixedYPosition);
        this.defenderLight = new PointLight(rayHandler, 5, Color.WHITE, 220,
                this.defenderPosition.x + (float) this.DEFENDER_TEXTURE.getWidth() / 2,
                this.defenderPosition.y + (float) this.DEFENDER_TEXTURE.getHeight() / 2 - 15);
        this.rectangle = new Rectangle(this.defenderPosition.x, this.defenderPosition.y,
                this.DEFENDER_TEXTURE.getWidth(), this.DEFENDER_TEXTURE.getHeight());
    }

    public void render(SpriteBatch batch) {
        batch.draw(this.DEFENDER_TEXTURE, this.defenderPosition.x, this.defenderPosition.y);
        this.lightUpdate();
        this.rectangleUpdate();
    }

    public void lightUpdate() {
        this.defenderLight.setPosition(this.defenderPosition.x + (float) this.DEFENDER_TEXTURE.getWidth() / 2,
                this.defenderPosition.y + (float) this.DEFENDER_TEXTURE.getHeight() / 2 - 15);
    }

    public void rectangleUpdate() {
        this.rectangle.setPosition(this.defenderPosition);
    }

    public Vector2 getDefenderPosition() {
        return defenderPosition;
    }

    public Texture getDEFENDER_TEXTURE() {
        return DEFENDER_TEXTURE;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
