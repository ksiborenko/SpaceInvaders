package com.space.gamemanagers;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.space.App;
import com.space.sprites.Defender;

public class DefenderManager {

    private static final int DEFENDER_SIZE = 4;
    private static final int DEFENDER_SPACING = 300;
    private int defenderSpeed;
    private final Array<Defender> defenders;


    public DefenderManager(RayHandler rayHandler) {
        this.defenders = new Array<>();
        this.defenderSpeed = 1;
        for (int defenderIndex = 0; defenderIndex < DEFENDER_SIZE; defenderIndex++) {
            this.defenders.add(new Defender(rayHandler, defenderIndex * DEFENDER_SPACING));
        }
    }

    public void render(SpriteBatch batch) {
        for (Defender defender : defenders) {
            defender.render(batch);
        }
        this.defenderMovement();
    }

    public void defenderMovement() {
        for (Defender defender : defenders) {
            defender.getDefenderPosition().x += this.defenderSpeed;
            if (defender.getDefenderPosition().x > App.WIDTH - defender.getDefenderTexture().getWidth() ||
                    defender.getDefenderPosition().x < 0) {
                reverseSpeed();
            }
        }
    }

    public void reverseSpeed() {
        this.defenderSpeed = -this.defenderSpeed;
    }

    public Array<Defender> getDefenders() {
        return defenders;
    }
}
