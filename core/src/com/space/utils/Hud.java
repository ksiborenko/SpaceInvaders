package com.space.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.space.App;
import com.space.gamemanagers.bullets.ShipBulletManager;

public class Hud {

    public Stage stage;
    private final Label scoreCount;
    private final Label bulletsCount;
    private final Label invadersCount;
    private final App app;

    public Hud(SpriteBatch batch, int invadersLeft, App app) {
        this.app = app;
        FitViewport viewport = new FitViewport(App.WIDTH, App.HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewport, batch);
        Label scoreTitle = new Label("Invaders left: ",
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal("font.fnt")), Color.RED));
        this.scoreCount = new Label(String.format("%02d", invadersLeft),
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal("font.fnt")), Color.RED));
        Label bulletsTitle = new Label("Bullets shot: ",
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal("font.fnt")), Color.RED));
        this.bulletsCount = new Label(String.format("%03d", invadersLeft),
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal("font.fnt")), Color.RED));
        Label invadersTitle = new Label("Invaders overall: ",
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal("font.fnt")), Color.RED));
        this.invadersCount = new Label(String.format("%05d", invadersLeft),
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal("font.fnt")), Color.RED));
        Table table = new Table();
        table.top();

        table.setFillParent(true);
        table.add(bulletsTitle);
        table.add(this.bulletsCount).width(80);
        table.add(scoreTitle);
        table.add(this.scoreCount).width(80);
        table.add(invadersTitle);
        table.add(this.invadersCount);
        this.stage.addActor(table);
    }

    public void updateScore(CollisionDetector collisionDetector, ShipBulletManager shipBulletManager) {
        this.scoreCount.setText(collisionDetector.getInvadersLeft());
        this.bulletsCount.setText(shipBulletManager.getBulletsShot());
        this.invadersCount.setText(this.app.saveData.getSaveData().getInteger("overallInvadersKilled", 0));
    }
}
