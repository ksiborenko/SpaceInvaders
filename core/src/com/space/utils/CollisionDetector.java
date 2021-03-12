package com.space.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.space.App;
import com.space.gamemanagers.InvaderManager;
import com.space.sprites.Defender;
import com.space.sprites.Invader;
import com.space.sprites.Ship;
import com.space.sprites.bullets.BigInvaderBullet;
import com.space.sprites.bullets.InvaderBullet;
import com.space.sprites.bullets.ShipBullet;

public class CollisionDetector {

    private final App app;
    private final Sound explosion;
    private final float explosionVolume;
    private boolean gameOver = false;
    private int invadersLeft;
    private int invadersKilled;


    public CollisionDetector(App app) {
        this.app = app;
        this.explosionVolume = 0.4f;
        this.explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
        this.invadersLeft = InvaderManager.INVADERS_ROWS * InvaderManager.INVADERS_COLUMNS;
        this.invadersKilled = this.app.saveData.getInvadersKilledCount();

    }

    public void ShipBulletInvader(Invader[][] invaders, Array<ShipBullet> bullets) {
        for (int row = 0; row < invaders.length; row++) {
            for (int column = 0; column < invaders[row].length; column++) {
                for (int bulletIndex = 0; bulletIndex < bullets.size; bulletIndex++) {
                    if (invaders[row][column] != null &&
                            bullets.get(bulletIndex).getBulletRectangle().overlaps(invaders[row][column].getRectangle())) {
                        this.explosion.play(this.explosionVolume);
                        bullets.get(bulletIndex).getBulletLight().remove();
                        invaders[row][column].getInvaderLight().remove();
                        bullets.removeIndex(bulletIndex);
                        invaders[row][column] = null;
                        this.invadersLeft--;
                        this.invadersKilled++;
                        this.app.saveData.invadersKilledCount(this.invadersKilled);
                        break;
                    }
                }
            }
        }
    }

    public void ShipBulletDefender(Array<ShipBullet> bullets, Array<Defender> defenders) {
        for (int bulletIndex = 0; bulletIndex < bullets.size; bulletIndex++) {
            for (int defenderIndex = 0; defenderIndex < defenders.size; defenderIndex++) {
                if (bullets.get(bulletIndex).getBulletRectangle().overlaps(defenders.get(defenderIndex).getRectangle())) {
                    bullets.get(bulletIndex).getBulletLight().remove();
                    bullets.removeIndex(bulletIndex);
                    break;
                }
            }
        }
    }

    public void invaderBulletDefender1(Array<InvaderBullet> bullets1, Array<Defender> defenders) {
        for (int bulletIndex = 0; bulletIndex < bullets1.size; bulletIndex++) {
            for (int defenderIndex = 0; defenderIndex < defenders.size; defenderIndex++) {
                if (bullets1.get(bulletIndex).getBulletRectangle().overlaps(defenders.get(defenderIndex).getRectangle())) {
                    bullets1.get(bulletIndex).getBulletLight().remove();
                    bullets1.removeIndex(bulletIndex);
                    break;
                }
            }
        }
    }

    public void invaderBulletDefender2(Array<InvaderBullet> bullets2, Array<Defender> defenders) {
        for (int bulletIndex = 0; bulletIndex < bullets2.size; bulletIndex++) {
            for (int defenderIndex = 0; defenderIndex < defenders.size; defenderIndex++) {
                if (bullets2.get(bulletIndex).getBulletRectangle().overlaps(defenders.get(defenderIndex).getRectangle())) {
                    bullets2.get(bulletIndex).getBulletLight().remove();
                    bullets2.removeIndex(bulletIndex);
                    break;
                }
            }
        }
    }

    public void invaderBulletDefender3(Array<InvaderBullet> bullets3, Array<Defender> defenders) {
        for (int bulletIndex = 0; bulletIndex < bullets3.size; bulletIndex++) {
            for (int defenderIndex = 0; defenderIndex < defenders.size; defenderIndex++) {
                if (bullets3.get(bulletIndex).getBulletRectangle().overlaps(defenders.get(defenderIndex).getRectangle())) {
                    bullets3.get(bulletIndex).getBulletLight().remove();
                    bullets3.removeIndex(bulletIndex);
                    break;
                }
            }
        }
    }

    public void invaderBulletShip1(Array<InvaderBullet> bullets1, Ship ship) {
        for (int bulletIndex = 0; bulletIndex < bullets1.size; bulletIndex++) {
            if (bullets1.get(bulletIndex).getBulletRectangle().overlaps(ship.getRectangle())) {
                this.explosion.play(this.explosionVolume);
                this.gameOver = true;
                break;
            }
        }
    }

    public void invaderBulletShip2(Array<InvaderBullet> bullets2, Ship ship) {
        for (int bulletIndex = 0; bulletIndex < bullets2.size; bulletIndex++) {
            if (bullets2.get(bulletIndex).getBulletRectangle().overlaps(ship.getRectangle())) {
                this.explosion.play(this.explosionVolume);
                this.gameOver = true;
                break;
            }
        }
    }

    public void invaderBulletShip3(Array<InvaderBullet> bullets3, Ship ship) {
        for (int bulletIndex = 0; bulletIndex < bullets3.size; bulletIndex++) {
            if (bullets3.get(bulletIndex).getBulletRectangle().overlaps(ship.getRectangle())) {
                this.explosion.play(this.explosionVolume);
                this.gameOver = true;
                break;
            }
        }
    }

    public void bigLaserShip(Array<BigInvaderBullet> bullets, Ship ship) {
        for (int bulletIndex = 0; bulletIndex < bullets.size; bulletIndex++) {
            if (bullets.get(bulletIndex).getBulletRectangle().overlaps(ship.getRectangle())) {
                this.explosion.play(this.explosionVolume);
                this.gameOver = true;
            }
        }
    }

    public void invaderDefender(Invader[][] invaders, Array<Defender> defenders) {
        for (Invader[] invader : invaders) {
            for (Invader value : invader) {
                for (int defenderIndex = 0; defenderIndex < defenders.size; defenderIndex++) {
                    if (value != null &&
                            value.getRectangle().overlaps(defenders.get(defenderIndex).getRectangle())) {
                        this.explosion.play(this.explosionVolume);
                        this.gameOver = true;
                    }
                }
            }
        }

    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getInvadersLeft() {
        return invadersLeft;
    }
}
