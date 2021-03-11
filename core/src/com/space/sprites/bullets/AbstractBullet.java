package com.space.sprites.bullets;

import box2dLight.Light;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractBullet {

    protected final int bulletSpeed;
    protected Texture bulletTexture;
    protected Light bulletLight;
    protected Vector2 bulletPosition;
    protected Rectangle bulletRectangle;


    public AbstractBullet() {
        this.bulletSpeed = 300;
        this.bulletTexture = new Texture("laser.png");
        this.bulletPosition = new Vector2();
        this.bulletRectangle = new Rectangle();
    }

    protected abstract void bulletPositionUpdate(float delta);
    protected abstract void render(SpriteBatch batch);
    protected abstract void bulletRectangleUpdate();
    protected abstract void bulletLightUpdate();

    public Texture getBulletTexture() {
        return bulletTexture;
    }

    public Light getBulletLight() {
        return bulletLight;
    }

    public Vector2 getBulletPosition() {
        return bulletPosition;
    }

    public Rectangle getBulletRectangle() {
        return bulletRectangle;
    }
}
