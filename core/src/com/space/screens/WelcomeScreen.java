package com.space.screens;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.space.App;

public class WelcomeScreen implements Screen {

    private float playButtonSoundTimer;
    private float exitButtonSoundTimer;
    private final Texture background;
    private final Texture beginBtnTexture;
    private final Texture beginBtnInactiveTexture;
    private final Texture leaveBtnTexture;
    private final Texture leaveBtnInactiveTexture;
    private final Texture logo;
    private final Vector2 beginBtnPosition;
    private final Vector2 leaveBtnPosition;
    private final Vector2 logoPosition;
    private final App app;
    private final World world;
    private final OrthographicCamera camera;
    private final RayHandler rayHandler;
    private final Box2DDebugRenderer renderer;
    private final Light beginBtnLight;
    private final Light leaveBtnLight;
    private final Light logoLight;


    public WelcomeScreen(App app) {
        this.playButtonSoundTimer = 0;
        this.exitButtonSoundTimer = 0;
        this.app = app;
        this.background = new Texture("background1.png");
        this.beginBtnTexture = new Texture("begin.png");
        this.beginBtnInactiveTexture = new Texture("beginInactive.png");
        this.leaveBtnTexture = new Texture("leave.png");
        this.leaveBtnInactiveTexture = new Texture("leaveInactive.png");
        this.logo = new Texture("logo3.png");
        this.beginBtnPosition = new Vector2((float) App.WIDTH / 20, App.HEIGHT * 2f);
        this.leaveBtnPosition = new Vector2((float) App.WIDTH / 20, App.HEIGHT);
        this.logoPosition = new Vector2(App.WIDTH / 20f, -logo.getHeight());
        if (!this.app.welcomeMusic.isPlaying()) {
            this.app.welcomeMusic.play();
        }
        this.app.welcomeMusic.setLooping(true);
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, App.WIDTH, App.HEIGHT);
        this.world = new World(new Vector2(0, 0), false);
        this.renderer = new Box2DDebugRenderer();
        this.rayHandler = new RayHandler(this.world);
        this.rayHandler.setCombinedMatrix(this.camera);
        this.beginBtnLight = new PointLight(rayHandler, 80, Color.RED, 150, this.beginBtnPosition.x + (float) this.beginBtnTexture.getWidth() / 2,
                this.beginBtnPosition.y + this.beginBtnTexture.getHeight());
        this.leaveBtnLight = new PointLight(rayHandler, 80, Color.RED, 150, this.leaveBtnPosition.x + (float) this.leaveBtnTexture.getWidth() / 2,
                this.leaveBtnPosition.y + this.leaveBtnTexture.getHeight());
        this.logoLight = new PointLight(rayHandler, 80, Color.BLACK, 1000, this.logoPosition.x + (float) this.logo.getWidth() / 2,
                this.logoPosition.y + this.logo.getHeight());
        new PointLight(rayHandler, 80, Color.BLACK, 1000, (float) App.WIDTH / 2, (float) App.HEIGHT / 2);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.app.batch.setProjectionMatrix(camera.combined);
        this.app.batch.begin();
        this.app.batch.draw(this.background, 0, 0, App.WIDTH, App.HEIGHT);
        if (Gdx.input.getX() >= this.beginBtnPosition.x &&
                Gdx.input.getX() <= this.beginBtnPosition.x + this.beginBtnTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.beginBtnPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.beginBtnPosition.y + this.beginBtnTexture.getHeight()) {
            this.app.batch.draw(this.beginBtnInactiveTexture, this.beginBtnPosition.x, this.beginBtnPosition.y);
        } else {
            this.app.batch.draw(this.beginBtnTexture, this.beginBtnPosition.x, this.beginBtnPosition.y);
        }
        if (Gdx.input.getX() >= this.leaveBtnPosition.x &&
                Gdx.input.getX() <= this.leaveBtnPosition.x + this.leaveBtnTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.leaveBtnPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.leaveBtnPosition.y + this.leaveBtnTexture.getHeight()) {
            this.app.batch.draw(this.leaveBtnInactiveTexture, this.leaveBtnPosition.x, this.leaveBtnPosition.y);
        } else {
            this.app.batch.draw(this.leaveBtnTexture, this.leaveBtnPosition.x, this.leaveBtnPosition.y);
        }
        this.app.batch.draw(this.logo, this.logoPosition.x, this.logoPosition.y, 500, 250);
        this.app.batch.end();
        this.rayHandler.updateAndRender();
        this.renderer.render(world, camera.combined);
        this.moveButtonsAndLogo();
        this.playBtnUpdate();
        this.exitBtnUpdate();
        this.logoLightUpdate();

    }

    private void moveButtonsAndLogo() {
        this.beginBtnPosition.y -= 3f;
        this.leaveBtnPosition.y -= 3f;
        this.logoPosition.y += 1.55;
        if (beginBtnPosition.y <= 250) {
            this.beginBtnPosition.y = 250;
        }
        if (leaveBtnPosition.y <= 50) {
            this.leaveBtnPosition.y = 50;
        }
        if (logoPosition.y >= App.HEIGHT - this.logo.getHeight() - 100) {
            this.logoPosition.y = App.HEIGHT - this.logo.getHeight() - 100;
        }
    }

    public void logoLightUpdate() {
        this.logoLight.setPosition(this.logoPosition.x + (float) this.logo.getWidth() / 2,
                this.logoPosition.y + this.logo.getHeight());
    }

    public void playBtnUpdate() {
        this.beginBtnLight.setPosition(this.beginBtnPosition.x + (float) this.beginBtnTexture.getWidth() / 2,
                this.beginBtnPosition.y + (float) this.beginBtnTexture.getHeight() / 2);
        if (Gdx.input.getX() <= this.beginBtnPosition.x + this.beginBtnTexture.getWidth() &&
                Gdx.input.getX() >= this.beginBtnPosition.x && App.HEIGHT - Gdx.input.getY() >= this.beginBtnPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.beginBtnPosition.y + this.beginBtnTexture.getHeight()) {
            this.beginBtnLight.setColor(Color.RED);
            this.playButtonSoundTimer += Gdx.graphics.getDeltaTime();
            this.app.buttonSound.play();
            if (playButtonSoundTimer >= 0.6f) {
                this.app.buttonSound.stop();
            }
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                this.app.setScreen(new MainMenu(this.app));
                this.app.accessGranted.play();

            }
        } else {
            this.beginBtnLight.setColor(Color.BLACK);
            this.playButtonSoundTimer = 0;
        }
    }

    public void exitBtnUpdate() {
        this.leaveBtnLight.setPosition(this.leaveBtnPosition.x + (float) this.leaveBtnTexture.getWidth() / 2,
                this.leaveBtnPosition.y + (float) this.leaveBtnTexture.getHeight() / 2);
        if (Gdx.input.getX() <= this.leaveBtnPosition.x + this.leaveBtnTexture.getWidth() &&
                Gdx.input.getX() >= this.leaveBtnPosition.x && App.HEIGHT - Gdx.input.getY() >= this.leaveBtnPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.leaveBtnPosition.y + this.leaveBtnTexture.getHeight()) {
            this.leaveBtnLight.setColor(Color.RED);
            this.exitButtonSoundTimer += Gdx.graphics.getDeltaTime();
            this.app.buttonSound.play();
            if (exitButtonSoundTimer >= 0.6f) {
                this.app.buttonSound.stop();
            }
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                Gdx.app.exit();
            }
        } else {
            this.leaveBtnLight.setColor(Color.BLACK);
            this.exitButtonSoundTimer = 0;
        }
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

    @Override
    public void dispose() {

    }
}
