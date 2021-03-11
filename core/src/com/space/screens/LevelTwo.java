package com.space.screens;

import com.badlogic.gdx.Screen;
import com.space.App;

public class LevelTwo extends LevelOne implements Screen {


    public LevelTwo(App app) {
        super(app);
    }

    @Override
    protected void gameSave() {
        this.app.saveData.getSaveData().putBoolean("levelThree", true);
    }

    @Override
    protected void shootingUpdate() {
        this.invaderBulletManager.renderLevelTwo(app.batch, this.invaderManager.getInvaders(), this.rayHandler);
    }
}
