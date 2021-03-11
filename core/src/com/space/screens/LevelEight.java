package com.space.screens;

import com.badlogic.gdx.Screen;
import com.space.App;

public class LevelEight extends LevelSeven implements Screen {

    public LevelEight(App app) {
        super(app);

    }

    @Override
    protected void shootingLevels() {
        this.bigInvaderBulletManager.shootingLevelEight(this.rayHandler, this.bigInvaderManager.getInvaders());
    }

    @Override
    protected void gameSave() {
        this.app.saveData.getSaveData().putBoolean("levelNine", true);
    }
}
