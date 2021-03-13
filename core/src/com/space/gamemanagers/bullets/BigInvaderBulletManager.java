package com.space.gamemanagers.bullets;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.space.App;
import com.space.sprites.BigInvader;
import com.space.sprites.bullets.BigInvaderBullet;
import com.space.utils.States;

public class BigInvaderBulletManager {

    private final App app;
    private final Array<BigInvaderBullet> bullets;
    private float timeStart;

    private final BigInvader[] invaders;
    private final float[] invaderSpeedCache;
    private final float timeEvent;
    private final float chargeVolume;
    private final float hornVolume;
    private States invaderState;


    public BigInvaderBulletManager(App app) {
        this.timeStart = 0;
        this.invaders = new BigInvader[4];
        this.invaderSpeedCache = new float[this.invaders.length];
        this.chargeVolume = 1f;
        this.hornVolume = 0.7f;
        this.timeEvent = 5f;
        this.app = app;
        this.bullets = new Array<>();
        this.invaderState = States.NEUTRAL;
    }

    public void render(SpriteBatch batch, float delta) {
        for (int bulletIndex = 0; bulletIndex < this.bullets.size; bulletIndex++) {
            this.bullets.get(bulletIndex).render(batch);
            this.bullets.get(bulletIndex).bulletPositionUpdate(delta);
            this.bullets.get(bulletIndex).bulletLightUpdate();
            this.bullets.get(bulletIndex).bulletRectangleUpdate();
            this.remove();
        }
    }

    public void shooting(int numOfShootingInvaders, RayHandler rayHandler, Array<BigInvader> invaders) {
        if (numOfShootingInvaders > this.invaders.length) {
            throw new AssertionError("numOfShootingInvaders value exceeded invaders length");
        }

        this.timeStart += Gdx.graphics.getDeltaTime();
        if (this.invaderState == States.NEUTRAL && this.timeStart >= timeEvent) {
            this.invaderState = States.PICKING_INVADER;
        }
        if (this.invaderState == States.PICKING_INVADER) {
            for (int index = 0; index < numOfShootingInvaders; index++) {
                BigInvader invader = invaders.get(index);
                this.invaders[index] = invader;
                this.invaderSpeedCache[index] = invader.returnSpeed();
            }
            this.invaderState = States.CHARGING;
            this.app.charge.play();
        }

        if (this.invaderState == States.CHARGING && this.app.charge.isPlaying()) {
            for (int index = 0; index < numOfShootingInvaders; index++) {
                BigInvader invader = this.invaders[index];
                invader.setSPeed(0);
                invader.setBrighterLight(invader.getBigInvaderLight());
            }
            this.app.charge.setLooping(false);
            this.app.charge.setVolume(this.chargeVolume);
        }

        if (this.invaderState == States.CHARGING && !this.app.charge.isPlaying()) {
            this.app.horn.play();
            this.app.horn.setVolume(this.hornVolume);
            this.invaderState = States.SHOOTING;
        }

        if (this.invaderState == States.SHOOTING && this.app.horn.isPlaying()) {
            for (int index = 0; index < numOfShootingInvaders; index++) {
                BigInvader invader = this.invaders[index];
                invader.getBigInvaderLight().setDistance(BigInvader.INVADER_LIGHT_DISTANCE);
                this.bullets.add(new BigInvaderBullet(rayHandler, invader));
            }
        }

        if (this.invaderState == States.SHOOTING && !this.app.horn.isPlaying()) {
            for (int index = 0; index < numOfShootingInvaders; index++) {
                this.invaders[index].setSPeed(this.invaderSpeedCache[index]);
                this.invaders[index] = null;
                this.invaderSpeedCache[index] = 0;
            }
            this.invaderState = States.NEUTRAL;
            this.timeStart = 0;
        }
    }

    private void remove() {
        for (int bulletIndex = 0; bulletIndex < this.bullets.size; bulletIndex++) {
            if (this.bullets.get(bulletIndex).getBulletPosition().y + this.bullets.get(bulletIndex).getBulletTexture().getHeight() <
                    0) {
                this.bullets.get(bulletIndex).getBulletLight().remove();
                this.bullets.removeIndex(bulletIndex);
            }
        }
    }

    public Array<BigInvaderBullet> getBullets() {
        return bullets;
    }
}
