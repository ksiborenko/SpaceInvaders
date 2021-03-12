package com.space.screens;

import com.badlogic.gdx.Screen;
import com.space.App;

public class LevelNine extends LevelSeven implements Screen {

    public LevelNine(App app) {
        super(app);
    }

    @Override
    protected void shootingLevels() {
        this.bigInvaderBulletManager.shooting(3, this.rayHandler, this.bigInvaderManager.getInvaders());
    }

    @Override
    protected void gameSave() {
        this.app.saveData.getSaveData().putBoolean("levelFinal", true);
    }
}
