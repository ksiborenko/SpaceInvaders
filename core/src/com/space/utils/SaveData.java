package com.space.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


public class SaveData {

    private final Preferences saveData;

    public SaveData() {
        this.saveData = Gdx.app.getPreferences("SpaceInvadersSaveData");
    }

    public void invadersKilledCount(int invadersKilled) {
        this.saveData.putInteger("overallInvadersKilled", invadersKilled);
        this.saveData.flush();
    }

    public int getInvadersKilledCount() {
        return this.saveData.getInteger("overallInvadersKilled");
    }

    public Preferences getSaveData() {
        return saveData;
    }
}
