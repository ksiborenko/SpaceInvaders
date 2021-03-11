package com.space.screens;

import com.badlogic.gdx.Screen;
import com.space.App;

public class LevelThree extends LevelOne implements Screen {


    public LevelThree(App app) {
        super(app);
    }

    @Override
    protected void gameSave() {
        this.app.saveData.getSaveData().putBoolean("levelFour", true);
    }

    @Override
    protected void shootingUpdate() {
        this.invaderBulletManager.renderLevelThree(app.batch, this.invaderManager.getInvaders(), this.rayHandler);
    }
}
