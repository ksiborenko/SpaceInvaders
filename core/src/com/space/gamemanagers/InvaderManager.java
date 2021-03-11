package com.space.gamemanagers;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.App;
import com.space.sprites.Invader;

public class InvaderManager {

    public static final int INVADERS_ROWS = 5;
    public static final int INVADERS_COLUMNS = 16;
    public static final int INVADERS_SPACING = 50;
    private final Invader[][] invaders;


    public InvaderManager(RayHandler rayHandler) {
        this.invaders = new Invader[InvaderManager.INVADERS_ROWS][InvaderManager.INVADERS_COLUMNS];
        for (int row = 0; row < this.invaders.length; row++) {
            for (int column = 0; column < this.invaders[row].length; column++) {
                int invadersLeftBeginning = 100;
                int invadersTopPositionCorrection = 80;
                this.invaders[row][column] = new Invader(rayHandler,
                        invadersLeftBeginning + column * InvaderManager.INVADERS_SPACING,
                        App.HEIGHT / 3 * 2 - invadersTopPositionCorrection + row * InvaderManager.INVADERS_SPACING);
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Invader[] invader : this.invaders) {
            for (Invader value : invader) {
                if (value != null) {
                    value.render(batch);
                }
            }
        }
        this.invadersMovement();
    }

    public void invadersMovement() {
        for (Invader[] invader : this.invaders) {
            for (Invader value : invader) {
                if (value != null) {
                    value.invaderMovementRight();
                }
                int invadersWidth = 50;
                if (value != null &&
                        value.getINVADER_POSITION().x > App.WIDTH - invadersWidth) {
                    value.invaderReverseSpeed();
                }
                if (value != null && value.getINVADER_POSITION().x < 0) {
                    value.invaderMovementLeft();
                }
            }
        }
    }

    public Invader[][] getInvaders() {
        return invaders;
    }
}
