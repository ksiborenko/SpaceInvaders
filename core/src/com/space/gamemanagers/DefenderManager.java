package com.space.gamemanagers;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.space.App;
import com.space.sprites.Defender;

public class DefenderManager {


    private int defenderSpeed;
    private final Array<Defender> defenders;


    public DefenderManager(RayHandler rayHandler) {
        defenderSpeed = 1;
        this.defenders = new Array<>();
        int defenderSize = 4;
        for (int defenderIndex = 0; defenderIndex < defenderSize; defenderIndex++) {
            int defenderSpacing = 300;
            this.defenders.add(new Defender(rayHandler, defenderIndex * defenderSpacing));
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
            if (defender.getDefenderPosition().x > App.WIDTH - defender.getDEFENDER_TEXTURE().getWidth() ||
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
