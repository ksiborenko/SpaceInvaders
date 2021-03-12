package com.space.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.space.App;
import com.space.gamemanagers.BigInvaderManager;
import com.space.gamemanagers.bullets.BigInvaderBulletManager;

public class LevelSeven extends LevelThree implements Screen {

    protected BigInvaderManager bigInvaderManager;
    protected BigInvaderBulletManager bigInvaderBulletManager;

    public LevelSeven(App app) {
        super(app);
        this.invaderBulletManager.setInvadersBulletsAmount(3);
        this.bigInvaderManager = new BigInvaderManager(this.rayHandler);
        this.bigInvaderBulletManager = new BigInvaderBulletManager(app);
    }

    @Override
    protected void gameSave() {
        this.app.saveData.getSaveData().putBoolean("levelEight", true);
    }

    @Override
    protected void bigInvaderUpdate() {
        this.bigInvaderManager.render(app.batch, Gdx.graphics.getDeltaTime());
        this.bigInvaderBulletManager.render(app.batch, Gdx.graphics.getDeltaTime());
        this.collisionDetector.bigLaserShip(this.bigInvaderBulletManager.getBullets(), this.ship);
        this.shootingLevels();
    }
    protected void shootingLevels () {
        this.bigInvaderBulletManager.shooting(1, this.rayHandler, this.bigInvaderManager.getInvaders());
    }
}
