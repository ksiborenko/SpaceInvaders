package com.space.gamemanagers.bullets;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.space.gamemanagers.InvaderManager;
import com.space.sprites.Invader;
import com.space.sprites.bullets.InvaderBullet;

import java.util.Random;


public class InvaderBulletManager {


    private int invadersBulletsAmount;

    private final Array<InvaderBullet> invaderBullets1;
    private final Array<InvaderBullet> invaderBullets2;
    private final Array<InvaderBullet> invaderBullets3;
    private final Random random;

    public InvaderBulletManager() {
        this.invaderBullets1 = new Array<>();
        this.invaderBullets2 = new Array<>();
        this.invaderBullets3 = new Array<>();
        this.random = new Random();
    }

    public void shootingLevelOne(Invader[][] invaders, RayHandler rayHandler) {
        for (int row = 0; row < invaders.length; row++) {
            int invadersFull = InvaderManager.INVADERS_COLUMNS;
            for (int column = this.random.nextInt(invadersFull + 1); column < invaders[row].length; column++) {
                if (invaders[row][column] == null && invaders[row][column] != invaders[InvaderManager.INVADERS_ROWS - 1][column]) {
                    row++;
                }
                if (this.invaderBullets1.size <= this.invadersBulletsAmount && invaders[row][column] != null) {
                    Invader tempInvader = invaders[row][column];
                    this.invaderBullets1.add(new InvaderBullet(rayHandler, tempInvader));
                    break;
                }
            }
        }
    }

    public void renderLevelOne(SpriteBatch batch, Invader[][] invaders, RayHandler rayHandler) {
        for (int bulletIndex = 0; bulletIndex < this.invaderBullets1.size; bulletIndex++) {
            this.invaderBullets1.get(bulletIndex).render(batch);
        }
        this.shootingLevelOne(invaders, rayHandler);
        this.destroyBulletList1();
    }


    public void shootingLevelTwo(Invader[][] invaders, RayHandler rayHandler) {
        int invadersHalf = InvaderManager.INVADERS_COLUMNS / 2;
        for (int row = 0; row < invaders.length; row++) {
            for (int column = random.nextInt(invadersHalf + 1); column < invadersHalf; column++) {
                if (invaders[row][column] == null && invaders[row][column] != invaders[InvaderManager.INVADERS_ROWS - 1][column]) {
                    row++;
                }
                if (invaders[row][column] != null && this.invaderBullets1.size <= this.invadersBulletsAmount) {
                    Invader tempInvader = invaders[row][column];
                    this.invaderBullets1.add(new InvaderBullet(rayHandler, tempInvader));
                    break;
                }
            }
        }
        for (int row = 0; row < invaders.length; row++) {
            for (int column = invadersHalf + this.random.nextInt(invadersHalf);
                 column < invaders[row].length; column++) {
                if (invaders[row][column] == null && invaders[row][column] != invaders[InvaderManager.INVADERS_ROWS - 1][column]) {
                    row++;
                }
                if (invaders[row][column] != null && this.invaderBullets2.size <= this.invadersBulletsAmount) {
                    Invader tempInvader = invaders[row][column];
                    this.invaderBullets2.add(new InvaderBullet(rayHandler, tempInvader));
                    break;
                }

            }
        }
    }

    public void renderLevelTwo(SpriteBatch batch, Invader[][] invaders, RayHandler rayHandler) {
        for (int bulletIndex = 0; bulletIndex < this.invaderBullets1.size; bulletIndex++) {
            this.invaderBullets1.get(bulletIndex).render(batch);
        }
        for (int bulletIndex = 0; bulletIndex < this.invaderBullets2.size; bulletIndex++) {
            this.invaderBullets2.get(bulletIndex).render(batch);
        }
        this.shootingLevelTwo(invaders, rayHandler);
        this.destroyBulletList1();
        this.destroyBulletList2();
    }


    public void renderLevelThree(SpriteBatch batch, Invader[][] invaders, RayHandler rayHandler) {
        for (int invaderBulletIndex = 0; invaderBulletIndex < invaderBullets1.size; invaderBulletIndex++) {
            invaderBullets1.get(invaderBulletIndex).render(batch);
        }
        for (int invaderBulletIndex = 0; invaderBulletIndex < invaderBullets2.size; invaderBulletIndex++) {
            invaderBullets2.get(invaderBulletIndex).render(batch);
        }
        for (int invaderBulletIndex = 0; invaderBulletIndex < invaderBullets3.size; invaderBulletIndex++) {
            invaderBullets3.get(invaderBulletIndex).render(batch);
        }
        this.destroyBulletList1();
        this.shootingLevelThree(invaders, rayHandler);
        this.destroyBulletList2();
        this.destroyBulletList3();
    }

    public void shootingLevelThree(Invader[][] invaders, RayHandler rayHandler) {
        int invadersFirstPart = InvaderManager.INVADERS_COLUMNS / 3;
        for (int row = 0; row < invaders.length; row++) {
            for (int column = random.nextInt(invadersFirstPart); column < invadersFirstPart; column++) {
                if (invaders[row][column] == null && invaders[row][column] != invaders[InvaderManager.INVADERS_ROWS - 1][column]) {
                    row++;
                }
                if (invaders[row][column] != null && invaderBullets1.size <= this.invadersBulletsAmount) {
                    Invader invader = invaders[row][column];
                    this.invaderBullets1.add(new InvaderBullet(rayHandler, invader));
                    break;
                }
            }
        }
        int invadersSecondPart = InvaderManager.INVADERS_COLUMNS / 3 * 2;
        for (int row = 0; row < invaders.length; row++) {
            int invadersMiddlePart = invadersSecondPart - invadersFirstPart;
            for (int column = (int) (Math.random() * invadersMiddlePart) + invadersFirstPart; column < invadersSecondPart; column++) {
                if (invaders[row][column] == null && invaders[row][column] != invaders[InvaderManager.INVADERS_ROWS - 1][column]) {
                    row++;
                }
                if (invaders[row][column] != null && invaderBullets2.size <= this.invadersBulletsAmount) {
                    Invader invader = invaders[row][column];
                    this.invaderBullets2.add(new InvaderBullet(rayHandler, invader));
                    break;
                }
            }
        }
        for (int row = 0; row < invaders.length; row++) {
            int invadersLastPart = InvaderManager.INVADERS_COLUMNS - invadersSecondPart;
            for (int column = (int) (Math.random() * invadersLastPart) + invadersSecondPart; column < invaders[row].length; column++) {
                if (invaders[row][column] == null && invaders[row][column] != invaders[InvaderManager.INVADERS_ROWS - 1][column]) {
                    row++;
                }
                if (invaders[row][column] != null && invaderBullets3.size <= this.invadersBulletsAmount) {
                    Invader invader = invaders[row][column];
                    this.invaderBullets3.add(new InvaderBullet(rayHandler, invader));
                    break;
                }
            }
        }
    }

    public void destroyBulletList1() {
        for (int invaderBulletIndex = 0; invaderBulletIndex < invaderBullets1.size; invaderBulletIndex++) {
            if (this.invaderBullets1.get(invaderBulletIndex).getBulletPosition().y <
                    -this.invaderBullets1.get(invaderBulletIndex).getBulletTexture().getHeight()) {
                this.invaderBullets1.get(invaderBulletIndex).getBulletLight().remove();
                this.invaderBullets1.removeIndex(invaderBulletIndex);
            }
        }
    }

    public void destroyBulletList2() {
        for (int invaderBulletIndex = 0; invaderBulletIndex < invaderBullets2.size; invaderBulletIndex++) {
            if (this.invaderBullets2.get(invaderBulletIndex).getBulletPosition().y <
                    -this.invaderBullets2.get(invaderBulletIndex).getBulletTexture().getHeight()) {
                this.invaderBullets2.get(invaderBulletIndex).getBulletLight().remove();
                this.invaderBullets2.removeIndex(invaderBulletIndex);
            }
        }
    }

    public void destroyBulletList3() {
        for (int invaderBulletIndex = 0; invaderBulletIndex < invaderBullets3.size; invaderBulletIndex++) {
            if (this.invaderBullets3.get(invaderBulletIndex).getBulletPosition().y <
                    -this.invaderBullets3.get(invaderBulletIndex).getBulletTexture().getHeight()) {
                this.invaderBullets3.get(invaderBulletIndex).getBulletLight().remove();
                this.invaderBullets3.removeIndex(invaderBulletIndex);
            }
        }
    }

    public Array<InvaderBullet> getInvaderBullets1() {
        return invaderBullets1;
    }

    public Array<InvaderBullet> getInvaderBullets2() {
        return invaderBullets2;
    }

    public Array<InvaderBullet> getInvaderBullets3() {
        return invaderBullets3;
    }

    public void setInvadersBulletsAmount(int invadersBulletsAmount) {
        this.invadersBulletsAmount = invadersBulletsAmount;
    }
}