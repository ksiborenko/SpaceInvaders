package com.space.sprites;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.space.App;

public class Ship {


    private final Texture shipTexture;
    private final Vector2 shipPosition;
    private final Light shipLight;
    private final Rectangle rectangle;

    public Ship(RayHandler rayHandler) {
        this.shipTexture = new Texture("ship.png");
        float shipFixedYPosition = 30;
        this.shipPosition = new Vector2((float) App.WIDTH / 2 - (float) this.shipTexture.getWidth() / 2, shipFixedYPosition);
        this.shipLight = new PointLight(rayHandler, 5, Color.WHITE, 150, this.shipPosition.x +
                (float) this.shipTexture.getWidth() / 2, this.shipPosition.y + (float) this.shipTexture.getHeight() / 2);
        this.rectangle = new Rectangle(this.shipPosition.x, this.shipPosition.y,
                this.shipTexture.getWidth(), this.shipTexture.getHeight());
    }

    public void render(SpriteBatch batch) {
        batch.draw(this.shipTexture, this.shipPosition.x, this.shipPosition.y);
        this.shipMovement(Gdx.graphics.getDeltaTime());
        this.shipLightUpdate();
        this.shipRectangleUpdate();
    }

    public void shipMovement(float delta) {
        float shipSpeed = 200;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.shipPosition.x += shipSpeed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.shipPosition.x -= shipSpeed * delta;
        }
        if (this.shipPosition.x <= 0) {
            this.shipPosition.x = 0;
        }
        if (this.shipPosition.x + this.shipTexture.getWidth() >= App.WIDTH) {
            this.shipPosition.x = App.WIDTH - this.shipTexture.getWidth();
        }
    }

    public void shipLightUpdate() {
        this.shipLight.setPosition(this.shipPosition.x +
                (float) this.shipTexture.getWidth() / 2, this.shipPosition.y + (float) this.shipTexture.getHeight() / 2);
    }

    public void shipRectangleUpdate() {
        this.rectangle.setPosition(this.shipPosition);
    }

    public Vector2 getShipPosition() {
        return shipPosition;
    }

    public Texture getShipTexture() {
        return shipTexture;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
