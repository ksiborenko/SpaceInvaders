package com.space.sprites;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.space.App;

public class BigInvader {

    public static final int INVADER_LIGHT_DISTANCE = 190;
    private float speed;

    private final Texture bigInvaderTexture;
    private final Vector2 bigInvaderPosition;
    private final Light bigInvaderLight;


    public BigInvader(RayHandler rayHandler, int x) {
        this.speed = 150f;
        this.bigInvaderTexture = new Texture("bigInvader.png");
        int invaderFixedYPosition = App.HEIGHT - 100;
        this.bigInvaderPosition = new Vector2(x, invaderFixedYPosition);
        this.bigInvaderLight = new PointLight(rayHandler, 15, Color.RED, BigInvader.INVADER_LIGHT_DISTANCE, this.bigInvaderPosition.x +
                (float) this.bigInvaderTexture.getWidth() / 2, this.bigInvaderPosition.y + (float) this.bigInvaderTexture.getHeight() / 4);
    }

    public void render(SpriteBatch batch) {
        batch.draw(this.bigInvaderTexture, this.bigInvaderPosition.x, this.bigInvaderPosition.y);
    }

    public void update(float delta) {
        this.bigInvaderPosition.x += this.speed * delta;
        if (this.bigInvaderPosition.x >= App.WIDTH - this.bigInvaderTexture.getWidth()) {
            this.bigInvaderPosition.x = App.WIDTH - this.bigInvaderTexture.getWidth();
            this.reverseSpeed();
        }
        if (this.bigInvaderPosition.x <= 0) {
            this.bigInvaderPosition.x = 0;
            this.reverseSpeed();
        }
    }

    public Vector2 getBigInvaderPosition() {
        return bigInvaderPosition;
    }

    public void lightUpdate() {
        this.bigInvaderLight.setPosition(this.bigInvaderPosition.x +
                (float) this.bigInvaderTexture.getWidth() / 2, this.bigInvaderPosition.y + (float) this.bigInvaderTexture.getHeight() / 2);
    }

    public void reverseSpeed() {
        this.speed = -this.speed;
    }

    public void setBrighterLight(Light light) {
        this.bigInvaderLight.setDistance(light.getDistance() * 1.018f);
    }

    public void setSPeed(float speed) {
        this.speed = speed;
    }

    public float returnSpeed () {
        return this.speed;
    }

    public Texture getBigInvaderTexture() {
        return bigInvaderTexture;
    }

    public Light getBigInvaderLight() {
        return bigInvaderLight;
    }
}
