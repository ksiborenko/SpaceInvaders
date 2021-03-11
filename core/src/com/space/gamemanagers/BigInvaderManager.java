package com.space.gamemanagers;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.space.sprites.BigInvader;


public class BigInvaderManager {

    public static final int INVADERS_COUNT = 4;
    public static final int INVADERS_SPACING = 300;
    private final Array<BigInvader> invaders;

    public BigInvaderManager(RayHandler rayHandler) {
        this.invaders = new Array<>();
        for (int invadersIndex = 0; invadersIndex < BigInvaderManager.INVADERS_COUNT; invadersIndex++) {
            this.invaders.add(new BigInvader(rayHandler, invadersIndex * BigInvaderManager.INVADERS_SPACING));
        }
    }

    public void render(SpriteBatch batch, float delta) {
        for (int invaderIndex = 0; invaderIndex < invaders.size; invaderIndex++) {
            this.invaders.get(invaderIndex).render(batch);
            this.invaders.get(invaderIndex).update(delta);
            this.invaders.get(invaderIndex).lightUpdate();
        }
    }

    public Array<BigInvader> getInvaders() {
        return invaders;
    }
}
