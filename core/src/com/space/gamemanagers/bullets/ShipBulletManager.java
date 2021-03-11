package com.space.gamemanagers.bullets;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.space.App;
import com.space.sprites.bullets.ShipBullet;
import com.space.sprites.Ship;

public class ShipBulletManager {

    private final Array<ShipBullet> bullets;
    private final Sound shot;
    private int bulletsShot;

    public ShipBulletManager() {
        this.bullets = new Array<>();
        this.shot = Gdx.audio.newSound(Gdx.files.internal("laser1.mp3"));
        this.bulletsShot = 0;
    }

    public void render(SpriteBatch batch, Ship ship, RayHandler rayHandler) {
        for (int bulletIndex = 0; bulletIndex < this.bullets.size; bulletIndex++) {
            this.bullets.get(bulletIndex).render(batch);
        }
        this.addBullets(ship, rayHandler);
        this.cleaningBullets();
    }

    public void addBullets(Ship ship, RayHandler rayHandler) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && this.bullets.size<=10) {
            this.bullets.add(new ShipBullet(rayHandler, ship));
            this.bulletsShot++;
            this.shot.play(0.3f);
        }
    }

    public void cleaningBullets() {
        for (int bulletIndex = 0; bulletIndex < this.bullets.size; bulletIndex++) {
            if (bullets.get(bulletIndex).getBulletPosition().y > App.HEIGHT) {
                this.bullets.get(bulletIndex).getBulletLight().remove();
                this.bullets.removeIndex(bulletIndex);
            }
        }
    }

    public Array<ShipBullet> getBullets() {
        return bullets;
    }

    public int getBulletsShot() {
        return bulletsShot;
    }
}
