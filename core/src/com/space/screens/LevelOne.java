package com.space.screens;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.space.App;
import com.space.gamemanagers.DefenderManager;
import com.space.gamemanagers.InvaderManager;
import com.space.gamemanagers.bullets.InvaderBulletManager;
import com.space.gamemanagers.bullets.ShipBulletManager;
import com.space.sprites.Ship;
import com.space.utils.CollisionDetector;
import com.space.utils.Hud;
import com.space.utils.Lights;
import com.space.utils.SaveData;

public class LevelOne implements Screen {

    protected App app;
    protected SaveData saveData;
    protected InvaderBulletManager invaderBulletManager;
    protected InvaderManager invaderManager;
    protected RayHandler rayHandler;
    protected CollisionDetector collisionDetector;
    protected Ship ship;
    protected Lights lights;

    private final Texture background;

    private final Vector2 backgroundPosition1;
    private final Vector2 backgroundPosition2;

    private final World world;
    private final Box2DDebugRenderer renderer;
    private final OrthographicCamera camera;
    private final ShipBulletManager shipBulletManager;
    private final DefenderManager defenderManager;
    private final Hud hud;

    public LevelOne(App app) {
        this.app = app;
        this.background = new Texture("starsBackground.png");
        this.backgroundPosition1 = new Vector2(0, 0);
        this.backgroundPosition2 = new Vector2(0, App.HEIGHT);
        this.app.levelMusic.play();
        this.app.levelMusic.setLooping(true);
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, App.WIDTH, App.HEIGHT);
        this.renderer = new Box2DDebugRenderer();
        this.world = new World(new Vector2(0, 0), false);
        this.rayHandler = new RayHandler(this.world);
        this.rayHandler.setCombinedMatrix(this.camera);
        this.lights = new Lights(this.rayHandler);
        this.ship = new Ship(this.rayHandler);
        this.shipBulletManager = new ShipBulletManager();
        this.invaderManager = new InvaderManager(this.rayHandler);
        this.collisionDetector = new CollisionDetector(this.app);
        this.defenderManager = new DefenderManager(this.rayHandler);
        this.invaderBulletManager = new InvaderBulletManager();
        this.invaderBulletManager.setInvadersBulletsAmount(0);
        this.hud = new Hud(this.app.batch, this.collisionDetector.getInvadersLeft(), this.app);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.app.batch.setProjectionMatrix(this.camera.combined);
        this.app.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        this.app.batch.begin();
        this.app.batch.draw(this.background, this.backgroundPosition1.x, this.backgroundPosition1.y, App.WIDTH, App.HEIGHT);
        this.app.batch.draw(this.background, this.backgroundPosition2.x, this.backgroundPosition2.y, App.WIDTH, App.HEIGHT);
        this.shipBulletManager.render(app.batch, this.ship, this.rayHandler);
        this.ship.render(app.batch);
        this.invaderManager.render(app.batch);
        this.defenderManager.render(app.batch);
        this.shootingUpdate();
        this.bigInvaderUpdate();
        this.app.batch.end();
        this.renderer.render(this.world, this.camera.combined);
        this.rayHandler.updateAndRender();
        this.hud.stage.draw();
        this.hud.updateScore(this.collisionDetector, this.shipBulletManager);

        this.collisionDetector.invaderDefender(this.invaderManager.getInvaders(), this.defenderManager.getDefenders());
        this.collisionDetector.ShipBulletInvader(this.invaderManager.getInvaders(), this.shipBulletManager.getBullets());
        this.collisionDetector.ShipBulletDefender(this.shipBulletManager.getBullets(), this.defenderManager.getDefenders());
        this.collisionDetector.invaderBulletDefender1(this.invaderBulletManager.getInvaderBullets1(), this.defenderManager.getDefenders());
        this.collisionDetector.invaderBulletDefender2(this.invaderBulletManager.getInvaderBullets2(), this.defenderManager.getDefenders());
        this.collisionDetector.invaderBulletDefender3(this.invaderBulletManager.getInvaderBullets3(), this.defenderManager.getDefenders());
        this.collisionDetector.invaderBulletShip1(this.invaderBulletManager.getInvaderBullets1(), this.ship);
        this.collisionDetector.invaderBulletShip2(this.invaderBulletManager.getInvaderBullets2(), this.ship);
        this.collisionDetector.invaderBulletShip3(this.invaderBulletManager.getInvaderBullets3(), this.ship);
        if (collisionDetector.isGameOver()) {
            this.app.levelMusic.dispose();
            app.setScreen(new Laugh(app));
        }
        if (this.collisionDetector.getInvadersLeft() == 0) {
            this.app.setScreen(new MainMenu(this.app));
            this.app.saveData.getSaveData().flush();
            this.app.levelMusic.dispose();
            this.app.charge.dispose();
            this.app.horn.dispose();
            this.levelVictoryMusic();
            this.gameSave();
        }
        this.update();
        this.reposition();


    }

    protected void levelVictoryMusic() {
        this.app.levelVictoryMusic.play();
    }

    private void update() {
        float backgroundSpeed = -0.5f;
        this.backgroundPosition1.y += backgroundSpeed;
        this.backgroundPosition2.y += backgroundSpeed;

    }

    private void reposition() {
        if (this.backgroundPosition1.y < -App.HEIGHT) {
            this.backgroundPosition1.y = App.HEIGHT;
        }
        if (this.backgroundPosition2.y < -App.HEIGHT) {
            this.backgroundPosition2.y = App.HEIGHT;
        }
    }

    protected void gameSave() {
        this.app.saveData.getSaveData().putBoolean("levelTwo", true);
    }

    protected void shootingUpdate() {
        this.invaderBulletManager.renderLevelOne(app.batch, this.invaderManager.getInvaders(), this.rayHandler);
    }

    protected void bigInvaderUpdate() {

    }

    @Override
    public void dispose() {
        this.background.dispose();
        this.rayHandler.dispose();
        this.world.dispose();
        this.lights.getLeftFirst().dispose();
        this.lights.getLeftSecond().dispose();
        this.lights.getRight().dispose();
        this.renderer.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
