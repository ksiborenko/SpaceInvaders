package com.space.screens;

import com.badlogic.gdx.Screen;
import com.space.App;

public class LevelSix extends LevelThree implements Screen {

    public LevelSix(App app) {
        super(app);
        this.invaderBulletManager.setInvadersBulletsAmount(3);
    }

    @Override
    protected void gameSave() {
        this.app.saveData.getSaveData().putBoolean("levelSeven", true);
    }
}
