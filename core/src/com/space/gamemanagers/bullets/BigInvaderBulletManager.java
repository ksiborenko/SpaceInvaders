package com.space.gamemanagers.bullets;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.space.App;
import com.space.gamemanagers.BigInvaderManager;
import com.space.sprites.BigInvader;
import com.space.sprites.bullets.BigInvaderBullet;
import com.space.utils.States;

import java.util.Random;

public class BigInvaderBulletManager {

    private final App app;
    private final Array<BigInvaderBullet> bullets;
    private float timeStart;
    private BigInvader invaderFirst;
    private BigInvader invaderSecond;
    private BigInvader invaderThird;
    private BigInvader invaderFourth;
    private float tempSpeedFirstInvader;
    private float tempSpeedSecondInvader;
    private float tempSpeedThirdInvader;
    private float tempSpeedFourthInvader;
    private States invaderState;

    private final Random random;


    public BigInvaderBulletManager(App app) {
        timeStart = 0;
        invaderFirst = null;
        invaderSecond = null;
        invaderThird = null;
        invaderFourth = null;
        tempSpeedFirstInvader = 0;
        tempSpeedSecondInvader = 0;
        tempSpeedThirdInvader = 0;
        tempSpeedFourthInvader = 0;
        this.app = app;
        this.bullets = new Array<>();

        this.random = new Random();
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

    public void shootingLevelSeven(RayHandler rayHandler, Array<BigInvader> invaders) {
        this.timeStart += Gdx.graphics.getDeltaTime();
        float timeEvent = 5f;
        if (this.invaderState == States.NEUTRAL && this.timeStart >= timeEvent) {
            this.invaderState = States.PICKING_INVADER;
        }
        if (this.invaderState == States.PICKING_INVADER) {
            this.invaderFirst = invaders.get(this.random.nextInt(BigInvaderManager.INVADERS_COUNT));
            this.tempSpeedFirstInvader = this.invaderFirst.returnSpeed();
            this.invaderState = States.CHARGING;
            this.app.charge.play();
        }
        if (this.invaderState == States.CHARGING && this.app.charge.isPlaying()) {
            this.invaderFirst.setSPeed(0);
            this.app.charge.setLooping(false);
            this.app.charge.setVolume(0.8f);
            this.invaderFirst.setBrighterLight(this.invaderFirst.getBigInvaderLight());

        }
        if (this.invaderState == States.CHARGING && !this.app.charge.isPlaying()) {
            this.app.horn.play();
            this.app.horn.setVolume(0.6f);
            this.invaderState = States.SHOOTING;
        }
        if (this.invaderState == States.SHOOTING && this.app.horn.isPlaying()) {
            this.invaderFirst.getBigInvaderLight().setDistance(BigInvader.INVADER_LIGHT_DISTANCE);
            this.bullets.add(new BigInvaderBullet(rayHandler, this.invaderFirst));
        }
        if (this.invaderState == States.SHOOTING && !this.app.horn.isPlaying()) {
            this.invaderFirst.setSPeed(this.tempSpeedFirstInvader);
            this.invaderState = States.NEUTRAL;
            this.timeStart = 0;
        }
    }

    public void shootingLevelEight(RayHandler rayHandler, Array<BigInvader> invaders) {
        this.timeStart += Gdx.graphics.getDeltaTime();
        BigInvader tempInvader;
        float timeEvent = 5f;
        if (this.invaderState == States.NEUTRAL && this.timeStart >= timeEvent) {
            this.invaderState = States.PICKING_INVADER;
        }
        if (this.invaderState == States.PICKING_INVADER) {
            this.invaderFirst = invaders.get(this.random.nextInt(BigInvaderManager.INVADERS_COUNT));
            tempInvader = invaders.get(this.random.nextInt(BigInvaderManager.INVADERS_COUNT));
            if (tempInvader != invaderFirst) {
                this.invaderSecond = tempInvader;
            }
            if (this.invaderFirst != null && this.invaderSecond != null) {
                this.tempSpeedFirstInvader = this.invaderFirst.returnSpeed();
                this.tempSpeedSecondInvader = this.invaderSecond.returnSpeed();
                this.invaderState = States.CHARGING;
            }
            this.app.charge.play();
        }
        if (this.invaderState == States.CHARGING && this.app.charge.isPlaying()) {
            this.invaderFirst.setSPeed(0);
            this.invaderSecond.setSPeed(0);
            this.app.charge.setLooping(false);
            this.app.charge.setVolume(0.8f);
            this.invaderFirst.setBrighterLight(this.invaderFirst.getBigInvaderLight());
            this.invaderSecond.setBrighterLight(this.invaderSecond.getBigInvaderLight());

        }
        if (this.invaderState == States.CHARGING && !this.app.charge.isPlaying()) {
            this.app.horn.play();
            this.app.horn.setVolume(0.6f);
            this.invaderState = States.SHOOTING;
        }
        if (this.invaderState == States.SHOOTING && this.app.horn.isPlaying()) {
            this.invaderFirst.getBigInvaderLight().setDistance(BigInvader.INVADER_LIGHT_DISTANCE);
            this.invaderSecond.getBigInvaderLight().setDistance(BigInvader.INVADER_LIGHT_DISTANCE);
            this.bullets.add(new BigInvaderBullet(rayHandler, this.invaderFirst));
            this.bullets.add(new BigInvaderBullet(rayHandler, this.invaderSecond));
        }
        if (this.invaderState == States.SHOOTING && !this.app.horn.isPlaying()) {
            this.invaderFirst.setSPeed(this.tempSpeedFirstInvader);
            this.invaderSecond.setSPeed(this.tempSpeedSecondInvader);
            this.invaderState = States.NEUTRAL;
            this.timeStart = 0;
            this.invaderFirst = null;
            this.invaderSecond = null;
        }
    }

    public void shootingLevelNine(RayHandler rayHandler, Array<BigInvader> invaders) {
        this.timeStart += Gdx.graphics.getDeltaTime();
        BigInvader tempInvaderSecond;
        BigInvader tempInvaderThird;
        float timeEvent = 5f;
        if (this.invaderState == States.NEUTRAL && this.timeStart >= timeEvent) {
            this.invaderState = States.PICKING_INVADER;
        }
        if (this.invaderState == States.PICKING_INVADER) {
            this.invaderFirst = invaders.get(this.random.nextInt(BigInvaderManager.INVADERS_COUNT));
            tempInvaderSecond = invaders.get(this.random.nextInt(BigInvaderManager.INVADERS_COUNT));
            if (tempInvaderSecond != invaderFirst) {
                this.invaderSecond = tempInvaderSecond;
            }
            tempInvaderThird = invaders.get(this.random.nextInt(BigInvaderManager.INVADERS_COUNT));
            if (tempInvaderThird != this.invaderFirst && tempInvaderThird != this.invaderSecond) {
                this.invaderThird = tempInvaderThird;
            }
            if (this.invaderFirst != null && this.invaderSecond != null && this.invaderThird != null) {
                this.tempSpeedFirstInvader = this.invaderFirst.returnSpeed();
                this.tempSpeedSecondInvader = this.invaderSecond.returnSpeed();
                this.tempSpeedThirdInvader = this.invaderThird.returnSpeed();
                this.invaderState = States.CHARGING;
            }
            this.app.charge.play();
        }
        if (this.invaderState == States.CHARGING && this.app.charge.isPlaying()) {
            this.invaderFirst.setSPeed(0);
            this.invaderSecond.setSPeed(0);
            this.invaderThird.setSPeed(0);
            this.app.charge.setLooping(false);
            this.app.charge.setVolume(0.8f);
            this.invaderFirst.setBrighterLight(this.invaderFirst.getBigInvaderLight());
            this.invaderSecond.setBrighterLight(this.invaderSecond.getBigInvaderLight());
            this.invaderThird.setBrighterLight(this.invaderThird.getBigInvaderLight());

        }
        if (this.invaderState == States.CHARGING && !this.app.charge.isPlaying()) {
            this.app.horn.play();
            this.app.horn.setVolume(0.6f);
            this.invaderState = States.SHOOTING;
        }
        if (this.invaderState == States.SHOOTING && this.app.horn.isPlaying()) {
            this.invaderFirst.getBigInvaderLight().setDistance(BigInvader.INVADER_LIGHT_DISTANCE);
            this.invaderSecond.getBigInvaderLight().setDistance(BigInvader.INVADER_LIGHT_DISTANCE);
            this.invaderThird.getBigInvaderLight().setDistance(BigInvader.INVADER_LIGHT_DISTANCE);
            this.bullets.add(new BigInvaderBullet(rayHandler, this.invaderFirst));
            this.bullets.add(new BigInvaderBullet(rayHandler, this.invaderSecond));
            this.bullets.add(new BigInvaderBullet(rayHandler, this.invaderThird));
        }
        if (this.invaderState == States.SHOOTING && !this.app.horn.isPlaying()) {
            this.invaderFirst.setSPeed(this.tempSpeedFirstInvader);
            this.invaderSecond.setSPeed(this.tempSpeedSecondInvader);
            this.invaderThird.setSPeed(this.tempSpeedThirdInvader);
            this.invaderState = States.NEUTRAL;
            this.timeStart = 0;
            this.invaderFirst = null;
            this.invaderSecond = null;
            this.invaderThird = null;
        }
    }

    public void shootingLevelFinal(RayHandler rayHandler, Array<BigInvader> invaders) {
        this.timeStart += Gdx.graphics.getDeltaTime();
        float timeEvent = 5f;
        if (this.invaderState == States.NEUTRAL && this.timeStart >= timeEvent) {
            this.invaderState = States.PICKING_INVADER;
        }
        if (this.invaderState == States.PICKING_INVADER) {
            this.invaderFirst = invaders.get(0);
            this.invaderSecond = invaders.get(1);
            this.invaderThird = invaders.get(2);
            this.invaderFourth = invaders.get(3);
            this.tempSpeedFirstInvader = this.invaderFirst.returnSpeed();
            this.tempSpeedSecondInvader = this.invaderSecond.returnSpeed();
            this.tempSpeedThirdInvader = this.invaderThird.returnSpeed();
            this.tempSpeedFourthInvader = this.invaderFourth.returnSpeed();
            this.invaderState = States.CHARGING;
            this.app.charge.play();
        }
        if (this.invaderState == States.CHARGING && this.app.charge.isPlaying()) {
            this.invaderFirst.setSPeed(0);
            this.invaderSecond.setSPeed(0);
            this.invaderThird.setSPeed(0);
            this.invaderFourth.setSPeed(0);
            this.app.charge.setLooping(false);
            this.app.charge.setVolume(0.9f);
            this.invaderFirst.setBrighterLight(this.invaderFirst.getBigInvaderLight());
            this.invaderSecond.setBrighterLight(this.invaderSecond.getBigInvaderLight());
            this.invaderThird.setBrighterLight(this.invaderThird.getBigInvaderLight());
            this.invaderFourth.setBrighterLight(this.invaderFourth.getBigInvaderLight());

        }
        if (this.invaderState == States.CHARGING && !this.app.charge.isPlaying()) {
            this.app.horn.play();
            this.app.horn.setVolume(0.6f);
            this.invaderState = States.SHOOTING;
        }
        if (this.invaderState == States.SHOOTING && this.app.horn.isPlaying()) {
            this.invaderFirst.getBigInvaderLight().setDistance(BigInvader.INVADER_LIGHT_DISTANCE);
            this.invaderSecond.getBigInvaderLight().setDistance(BigInvader.INVADER_LIGHT_DISTANCE);
            this.invaderThird.getBigInvaderLight().setDistance(BigInvader.INVADER_LIGHT_DISTANCE);
            this.invaderFourth.getBigInvaderLight().setDistance(BigInvader.INVADER_LIGHT_DISTANCE);
            this.bullets.add(new BigInvaderBullet(rayHandler, this.invaderFirst));
            this.bullets.add(new BigInvaderBullet(rayHandler, this.invaderSecond));
            this.bullets.add(new BigInvaderBullet(rayHandler, this.invaderThird));
            this.bullets.add(new BigInvaderBullet(rayHandler, this.invaderFourth));
        }
        if (this.invaderState == States.SHOOTING && !this.app.horn.isPlaying()) {
            this.invaderFirst.setSPeed(this.tempSpeedFirstInvader);
            this.invaderSecond.setSPeed(this.tempSpeedSecondInvader);
            this.invaderThird.setSPeed(this.tempSpeedThirdInvader);
            this.invaderFourth.setSPeed(this.tempSpeedFourthInvader);
            this.invaderState = States.NEUTRAL;
            this.timeStart = 0;
            this.invaderFirst = null;
            this.invaderSecond = null;
            this.invaderThird = null;
            this.invaderFourth = null;
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
