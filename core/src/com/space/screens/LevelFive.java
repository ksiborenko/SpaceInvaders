package com.space.screens;

import com.badlogic.gdx.Screen;
import com.space.App;

public class LevelFive extends LevelThree implements Screen {

    public LevelFive(App app) {
        super(app);
        this.invaderBulletManager.setInvadersBulletsAmount(2);
    }

    @Override
    protected void gameSave() {
        this.app.saveData.getSaveData().putBoolean("levelSix", true);
    }
}
