package com.space.sprites;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Invader {

    private float invaderSpeedX;
    private final Texture invaderTexture;
    private final Vector2 invaderPosition;
    private final Light invaderLight;
    private final Rectangle rectangle;

    public Invader(RayHandler rayHandler, int x, int y) {
        this.invaderSpeedX = 0.8f;
        this.invaderTexture = new Texture("enemy.png");
        this.invaderPosition = new Vector2(x, y);
        this.invaderLight = new PointLight(rayHandler, 5, Color.BROWN, 100,
                this.invaderPosition.x, this.invaderPosition.y);

        this.rectangle = new Rectangle(this.invaderPosition.x, this.invaderPosition.y,
                this.invaderTexture.getWidth(), this.invaderTexture.getHeight());
    }

    public void render(SpriteBatch batch) {
        batch.draw(this.invaderTexture, this.invaderPosition.x, this.invaderPosition.y);
        this.updateLight();
        this.updateRectangle();
    }

    public void invaderReverseSpeed() {
        this.invaderSpeedX = -40;
        int invaderSpeedY = 50;
        this.invaderPosition.y -= invaderSpeedY;
    }

    public void invaderMovementRight() {
        this.invaderPosition.x += this.invaderSpeedX;
    }

    public void invaderMovementLeft() {
        this.invaderSpeedX = 0.8f;
    }

    private void updateLight() {
        this.invaderLight.setPosition(this.invaderPosition.x + (float) this.invaderTexture.getWidth() / 2,
                this.invaderPosition.y + (float) this.invaderTexture.getHeight() / 2);
    }

    private void updateRectangle() {
        this.rectangle.setPosition(this.invaderPosition);
    }

    public Vector2 getInvaderPosition() {
        return invaderPosition;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Light getInvaderLight() {
        return invaderLight;
    }

    public Texture getInvaderTexture() {
        return invaderTexture;
    }

}
