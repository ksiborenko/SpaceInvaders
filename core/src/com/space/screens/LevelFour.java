package com.space.screens;

import com.badlogic.gdx.Screen;
import com.space.App;

public class LevelFour extends LevelThree implements Screen {

    public LevelFour(App app) {
        super(app);
        this.invaderBulletManager.setInvadersBulletsAmount(1);
    }

    @Override
    protected void gameSave() {
        this.app.saveData.getSaveData().putBoolean("levelFive", true);
    }
}
