package com.space.screens;

import com.badlogic.gdx.Screen;
import com.space.App;

public class LevelFinal extends LevelSeven implements Screen {

    public LevelFinal(App app) {
        super(app);
        this.invaderBulletManager.setInvadersBulletsAmount(4);
    }

    @Override
    protected void levelVictoryMusic() {
        this.app.finalVictoryMusic.play();
    }

    @Override
    protected void shootingLevels() {
        this.bigInvaderBulletManager.shooting(4,this.rayHandler, this.bigInvaderManager.getInvaders());
    }
}
