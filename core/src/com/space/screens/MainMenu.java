package com.space.screens;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.space.App;
import com.space.utils.States;

public class MainMenu implements Screen {

    private final App app;
    private final World world;
    private final Box2DDebugRenderer renderer;
    private final OrthographicCamera camera;
    private final RayHandler rayHandler;
    private States states;

    private float levelOneButtonSoundTimer;
    private float levelTwoButtonSoundTimer;
    private float levelThreeButtonSoundTimer;
    private float levelFourButtonSoundTimer;
    private float levelFiveButtonSoundTimer;
    private float levelSixButtonSoundTimer;
    private float levelSevenButtonSoundTimer;
    private float levelEightButtonSoundTimer;
    private float levelNineButtonSoundTimer;
    private float levelFinalButtonSoundTimer;
    private float resetInvadersButtonSoundTimer;
    private float resetSavesButtonSoundTimer;

    private final Texture backgroundTexture;

    private final Texture levelOneButtonTexture;
    private final Texture levelOneButtonTextureInactive;
    private final Texture levelTwoButtonTexture;
    private final Texture levelTwoButtonTextureInactive;
    private final Texture levelThreeButtonTexture;
    private final Texture levelThreeButtonTextureInactive;
    private final Texture levelFourButtonTexture;
    private final Texture levelFourButtonTextureInactive;
    private final Texture levelFiveButtonTexture;
    private final Texture levelFiveButtonTextureInactive;
    private final Texture levelSixButtonTexture;
    private final Texture levelSixButtonTextureInactive;
    private final Texture levelSevenButtonTexture;
    private final Texture levelSevenButtonTextureInactive;
    private final Texture levelEightButtonTexture;
    private final Texture levelEightButtonTextureInactive;
    private final Texture levelNineButtonTexture;
    private final Texture levelNineButtonTextureInactive;
    private final Texture levelFinalButtonTexture;
    private final Texture levelFinalButtonTextureInactive;


    private final Texture resetKillsButtonTexture;
    private final Texture resetKillsButtonTextureInactive;
    private final Texture resetSavesButtonTexture;
    private final Texture resetSavesButtonTextureInactive;

    private final Vector2 levelOneButtonPosition;
    private final Vector2 levelTwoButtonPosition;
    private final Vector2 levelThreeButtonPosition;
    private final Vector2 levelFourButtonPosition;
    private final Vector2 levelFiveButtonPosition;
    private final Vector2 levelSixButtonPosition;
    private final Vector2 levelSevenButtonPosition;
    private final Vector2 levelEightButtonPosition;
    private final Vector2 levelNineButtonPosition;
    private final Vector2 resetKillsButtonPosition;
    private final Vector2 resetSavesButtonPosition;
    private final Vector2 levelFinalButtonPosition;

    private final Light resetInvadersLight;
    private final Light resetISavesLight;
    private final Light levelOneLight;
    private final Light levelTwoLight;
    private final Light levelThreeLight;
    private final Light levelFourLight;
    private final Light levelFiveLight;
    private final Light levelSixLight;
    private final Light levelSevenLight;
    private final Light levelEightLight;
    private final Light levelNineLight;
    private final Light levelFinalLight;


    public MainMenu(App app) {
        this.levelOneButtonSoundTimer = 0;
        this.levelTwoButtonSoundTimer = 0;
        this.levelThreeButtonSoundTimer = 0;
        this.levelFourButtonSoundTimer = 0;
        this.levelFiveButtonSoundTimer = 0;
        this.levelSixButtonSoundTimer = 0;
        this.levelSevenButtonSoundTimer = 0;
        this.levelEightButtonSoundTimer = 0;
        this.levelNineButtonSoundTimer = 0;
        this.levelFinalButtonSoundTimer = 0;
        this.resetInvadersButtonSoundTimer = 0;
        this.resetSavesButtonSoundTimer = 0;

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, App.WIDTH, App.HEIGHT);
        this.world = new World(new Vector2(0, 0), false);
        this.rayHandler = new RayHandler(this.world);
        this.rayHandler.setCombinedMatrix(this.camera);
        new PointLight(rayHandler, 200, Color.BLACK, 4000, (float) App.WIDTH / 2, (float) App.HEIGHT / 2);
        this.renderer = new Box2DDebugRenderer();
        this.app = app;

        this.backgroundTexture = new Texture("levelOneTexture.jpg");
        this.levelOneButtonTexture = new Texture("levelOne.png");
        this.levelOneButtonTextureInactive = new Texture("levelOneInactive.png");
        this.levelTwoButtonTexture = new Texture("levelTwo.png");
        this.levelTwoButtonTextureInactive = new Texture("levelTwoInactive.png");
        this.levelThreeButtonTexture = new Texture("levelThree.png");
        this.levelThreeButtonTextureInactive = new Texture("levelThreeInactive.png");
        this.levelFourButtonTexture = new Texture("levelFour.png");
        this.levelFourButtonTextureInactive = new Texture("levelFourInactive.png");
        this.levelFiveButtonTexture = new Texture("levelFive.png");
        this.levelFiveButtonTextureInactive = new Texture("levelFiveInactive.png");
        this.levelSixButtonTexture = new Texture("levelSix.png");
        this.levelSixButtonTextureInactive = new Texture("levelSixInactive.png");
        this.levelSevenButtonTexture = new Texture("levelSeven.png");
        this.levelSevenButtonTextureInactive = new Texture("levelSevenInactive.png");
        this.levelEightButtonTexture = new Texture("levelEight.png");
        this.levelEightButtonTextureInactive = new Texture("levelEightInactive.png");
        this.levelNineButtonTexture = new Texture("levelNine.png");
        this.levelNineButtonTextureInactive = new Texture("levelNineInactive.png");
        this.levelFinalButtonTexture = new Texture("levelFinal.png");
        this.levelFinalButtonTextureInactive = new Texture("levelFinalInactive.png");

        this.resetKillsButtonTexture = new Texture("resetInvadersKilled.png");
        this.resetKillsButtonTextureInactive = new Texture("resetInvadersKilledInactive.png");
        this.resetSavesButtonTexture = new Texture("resetSaves.png");
        this.resetSavesButtonTextureInactive = new Texture("resetSavesInactive.png");

        int yFixedButtonsPosition = 110;
        this.levelOneButtonPosition = new Vector2((float) App.WIDTH / 4 - (float) this.levelOneButtonTexture.getWidth() / 2,
                App.HEIGHT - yFixedButtonsPosition);
        this.levelTwoButtonPosition = new Vector2((float) App.WIDTH / 2 - (float) this.levelTwoButtonTexture.getWidth() / 2,
                App.HEIGHT - yFixedButtonsPosition);
        this.levelThreeButtonPosition = new Vector2((float) App.WIDTH / 4 * 3 - (float) this.levelThreeButtonTexture.getWidth() / 2,
                App.HEIGHT - yFixedButtonsPosition);
        this.levelFourButtonPosition = new Vector2((float) App.WIDTH / 4 - (float) this.levelFourButtonTexture.getWidth() / 2,
                App.HEIGHT - yFixedButtonsPosition * 2);
        this.levelFiveButtonPosition = new Vector2((float) App.WIDTH / 2 - (float) this.levelFiveButtonTexture.getWidth() / 2,
                App.HEIGHT - yFixedButtonsPosition * 2);
        this.levelSixButtonPosition = new Vector2((float) App.WIDTH / 4 * 3 - (float) this.levelSixButtonTexture.getWidth() / 2,
                App.HEIGHT - yFixedButtonsPosition * 2);
        this.levelSevenButtonPosition = new Vector2((float) App.WIDTH / 4 - (float) this.levelSevenButtonTexture.getWidth() / 2,
                App.HEIGHT - yFixedButtonsPosition * 3);
        this.levelEightButtonPosition = new Vector2((float) App.WIDTH / 2 - (float) this.levelEightButtonTexture.getWidth() / 2,
                App.HEIGHT - yFixedButtonsPosition * 3);
        this.levelNineButtonPosition = new Vector2((float) App.WIDTH / 4 * 3 - (float) this.levelNineButtonTexture.getWidth() / 2,
                App.HEIGHT - yFixedButtonsPosition * 3);
        this.levelFinalButtonPosition = new Vector2((float) App.WIDTH / 2 - (float) this.levelFinalButtonTexture.getWidth() / 2,
                App.HEIGHT - yFixedButtonsPosition * 4);


        int xButtonsOffset = 150;
        this.resetKillsButtonPosition = new Vector2(xButtonsOffset,
                yFixedButtonsPosition - this.resetKillsButtonTexture.getHeight());
        this.resetSavesButtonPosition = new Vector2(App.WIDTH - xButtonsOffset - this.resetSavesButtonTexture.getWidth(),
                yFixedButtonsPosition - this.resetKillsButtonTexture.getHeight());

        int rays = 80;
        int distance = 150;
        this.resetInvadersLight = new PointLight(rayHandler, rays, Color.BLACK, distance,
                this.resetKillsButtonPosition.x + (float) this.resetKillsButtonTexture.getWidth() / 2,
                this.resetKillsButtonPosition.y + (float) this.resetKillsButtonTexture.getHeight() / 2);
        this.resetISavesLight = new PointLight(rayHandler, rays, Color.BLACK, distance,
                this.resetSavesButtonPosition.x + (float) this.resetSavesButtonTexture.getWidth() / 2,
                this.resetSavesButtonPosition.y + (float) this.resetSavesButtonTexture.getHeight() / 2);
        this.levelOneLight = new PointLight(rayHandler, rays, Color.BLACK, distance,
                this.levelOneButtonPosition.x + (float) this.levelOneButtonTexture.getWidth() / 2,
                this.levelOneButtonPosition.y + (float) this.levelOneButtonTexture.getHeight() / 2);
        this.levelTwoLight = new PointLight(rayHandler, rays, Color.BLACK, distance,
                this.levelTwoButtonPosition.x + (float) this.levelTwoButtonTexture.getWidth() / 2,
                this.levelTwoButtonPosition.y + (float) this.levelTwoButtonTexture.getHeight() / 2);
        this.levelThreeLight = new PointLight(rayHandler, rays, Color.BLACK, distance,
                this.levelThreeButtonPosition.x + (float) this.levelThreeButtonTexture.getWidth() / 2,
                this.levelThreeButtonPosition.y + (float) this.levelThreeButtonTexture.getHeight() / 2);
        this.levelFourLight = new PointLight(rayHandler, rays, Color.BLACK, distance,
                this.levelFourButtonPosition.x + (float) this.levelFourButtonTexture.getWidth() / 2,
                this.levelFourButtonPosition.y + (float) this.levelFourButtonTexture.getHeight() / 2);
        this.levelFiveLight = new PointLight(rayHandler, rays, Color.BLACK, distance,
                this.levelFiveButtonPosition.x + (float) this.levelFiveButtonTexture.getWidth() / 2,
                this.levelFiveButtonPosition.y + (float) this.levelFiveButtonTexture.getHeight() / 2);
        this.levelSixLight = new PointLight(rayHandler, rays, Color.BLACK, distance,
                this.levelSixButtonPosition.x + (float) this.levelSixButtonTexture.getWidth() / 2,
                this.levelSixButtonPosition.y + (float) this.levelSixButtonTexture.getHeight() / 2);
        this.levelSevenLight = new PointLight(rayHandler, rays, Color.BLACK, distance,
                this.levelSevenButtonPosition.x + (float) this.levelSevenButtonTexture.getWidth() / 2,
                this.levelSevenButtonPosition.y + (float) this.levelSevenButtonTexture.getHeight() / 2);
        this.levelEightLight = new PointLight(rayHandler, rays, Color.BLACK, distance,
                this.levelEightButtonPosition.x + (float) this.levelEightButtonTexture.getWidth() / 2,
                this.levelEightButtonPosition.y + (float) this.levelEightButtonTexture.getHeight() / 2);
        this.levelNineLight = new PointLight(rayHandler, rays, Color.BLACK, distance,
                this.levelNineButtonPosition.x + (float) this.levelNineButtonTexture.getWidth() / 2,
                this.levelNineButtonPosition.y + (float) this.levelNineButtonTexture.getHeight() / 2);
        this.levelFinalLight = new PointLight(rayHandler, rays, Color.BLACK, distance,
                this.levelFinalButtonPosition.x + (float) this.levelFinalButtonTexture.getWidth() / 2,
                this.levelFinalButtonPosition.y + (float) this.levelFinalButtonTexture.getHeight() / 2);

    }

    @Override
    public void render(float delta) {
        this.app.batch.setProjectionMatrix(this.camera.combined);
        this.app.batch.begin();
        this.app.batch.draw(this.backgroundTexture, 0, 0, App.WIDTH, App.HEIGHT);

        if (Gdx.input.getX() >= this.levelOneButtonPosition.x &&
                Gdx.input.getX() <= this.levelOneButtonPosition.x + this.levelOneButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelOneButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelOneButtonPosition.y + this.levelOneButtonTexture.getHeight()) {
            this.app.batch.draw(this.levelOneButtonTextureInactive, this.levelOneButtonPosition.x, this.levelOneButtonPosition.y);
        } else {
            this.app.batch.draw(this.levelOneButtonTexture, this.levelOneButtonPosition.x, this.levelOneButtonPosition.y);
        }

        if (Gdx.input.getX() >= this.levelTwoButtonPosition.x &&
                Gdx.input.getX() <= this.levelTwoButtonPosition.x + this.levelTwoButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelTwoButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelTwoButtonPosition.y + this.levelTwoButtonTexture.getHeight()) {
            this.app.batch.draw(this.levelTwoButtonTextureInactive, this.levelTwoButtonPosition.x, this.levelTwoButtonPosition.y);
        } else {
            this.app.batch.draw(this.levelTwoButtonTexture, this.levelTwoButtonPosition.x, this.levelTwoButtonPosition.y);
        }

        if (Gdx.input.getX() >= this.levelThreeButtonPosition.x &&
                Gdx.input.getX() <= this.levelThreeButtonPosition.x + this.levelThreeButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelTwoButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelThreeButtonPosition.y + this.levelThreeButtonTexture.getHeight()) {
            this.app.batch.draw(this.levelThreeButtonTextureInactive, this.levelThreeButtonPosition.x, this.levelThreeButtonPosition.y);
        } else {
            this.app.batch.draw(this.levelThreeButtonTexture, this.levelThreeButtonPosition.x, this.levelThreeButtonPosition.y);
        }

        if (Gdx.input.getX() >= this.levelFourButtonPosition.x &&
                Gdx.input.getX() <= this.levelFourButtonPosition.x + this.levelFourButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelFourButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelFourButtonPosition.y + this.levelFourButtonTexture.getHeight()) {
            this.app.batch.draw(this.levelFourButtonTextureInactive, this.levelFourButtonPosition.x, this.levelFourButtonPosition.y);
        } else {
            this.app.batch.draw(this.levelFourButtonTexture, this.levelFourButtonPosition.x, this.levelFourButtonPosition.y);
        }

        if (Gdx.input.getX() >= this.resetKillsButtonPosition.x &&
                Gdx.input.getX() <= this.resetKillsButtonPosition.x + this.resetKillsButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.resetKillsButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.resetKillsButtonPosition.y + this.resetKillsButtonTexture.getHeight()) {
            this.app.batch.draw(this.resetKillsButtonTextureInactive, this.resetKillsButtonPosition.x, this.resetKillsButtonPosition.y);
        } else {
            this.app.batch.draw(this.resetKillsButtonTexture, this.resetKillsButtonPosition.x, this.resetKillsButtonPosition.y);
        }

        if (Gdx.input.getX() >= this.resetSavesButtonPosition.x &&
                Gdx.input.getX() <= this.resetSavesButtonPosition.x + this.resetSavesButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.resetSavesButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.resetSavesButtonPosition.y + this.resetSavesButtonTexture.getHeight()) {
            this.app.batch.draw(this.resetSavesButtonTextureInactive, this.resetSavesButtonPosition.x, this.resetSavesButtonPosition.y);
        } else {
            this.app.batch.draw(this.resetSavesButtonTexture, this.resetSavesButtonPosition.x, this.resetSavesButtonPosition.y);
        }

        if (Gdx.input.getX() >= this.levelFiveButtonPosition.x &&
                Gdx.input.getX() <= this.levelFiveButtonPosition.x + this.levelFiveButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelFiveButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelFiveButtonPosition.y + this.levelFiveButtonTexture.getHeight()) {
            this.app.batch.draw(this.levelFiveButtonTextureInactive, this.levelFiveButtonPosition.x, this.levelFiveButtonPosition.y);
        } else {
            this.app.batch.draw(this.levelFiveButtonTexture, this.levelFiveButtonPosition.x, this.levelFiveButtonPosition.y);
        }

        if (Gdx.input.getX() >= this.levelSixButtonPosition.x &&
                Gdx.input.getX() <= this.levelSixButtonPosition.x + this.levelSixButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelSixButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelSixButtonPosition.y + this.levelSixButtonTexture.getHeight()) {
            this.app.batch.draw(this.levelSixButtonTextureInactive, this.levelSixButtonPosition.x, this.levelSixButtonPosition.y);
        } else {
            this.app.batch.draw(this.levelSixButtonTexture, this.levelSixButtonPosition.x, this.levelSixButtonPosition.y);
        }

        if (Gdx.input.getX() >= this.levelSevenButtonPosition.x &&
                Gdx.input.getX() <= this.levelSevenButtonPosition.x + this.levelSevenButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelSevenButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelSevenButtonPosition.y + this.levelSevenButtonTexture.getHeight()) {
            this.app.batch.draw(this.levelSevenButtonTextureInactive, this.levelSevenButtonPosition.x, this.levelSevenButtonPosition.y);
        } else {
            this.app.batch.draw(this.levelSevenButtonTexture, this.levelSevenButtonPosition.x, this.levelSevenButtonPosition.y);
        }

        if (Gdx.input.getX() >= this.levelEightButtonPosition.x &&
                Gdx.input.getX() <= this.levelEightButtonPosition.x + this.levelEightButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelEightButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelEightButtonPosition.y + this.levelEightButtonTexture.getHeight()) {
            this.app.batch.draw(this.levelEightButtonTextureInactive, this.levelEightButtonPosition.x, this.levelEightButtonPosition.y);
        } else {
            this.app.batch.draw(this.levelEightButtonTexture, this.levelEightButtonPosition.x, this.levelEightButtonPosition.y);
        }

        if (Gdx.input.getX() >= this.levelNineButtonPosition.x &&
                Gdx.input.getX() <= this.levelNineButtonPosition.x + this.levelNineButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelNineButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelNineButtonPosition.y + this.levelNineButtonTexture.getHeight()) {
            this.app.batch.draw(this.levelNineButtonTextureInactive, this.levelNineButtonPosition.x, this.levelNineButtonPosition.y);
        } else {
            this.app.batch.draw(this.levelNineButtonTexture, this.levelNineButtonPosition.x, this.levelNineButtonPosition.y);
        }

        if (Gdx.input.getX() >= this.levelFinalButtonPosition.x &&
                Gdx.input.getX() <= this.levelFinalButtonPosition.x + this.levelFinalButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelFinalButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelFinalButtonPosition.y + this.levelFinalButtonTexture.getHeight()) {
            this.app.batch.draw(this.levelFinalButtonTextureInactive, this.levelFinalButtonPosition.x, this.levelFinalButtonPosition.y);
        } else {
            this.app.batch.draw(this.levelFinalButtonTexture, this.levelFinalButtonPosition.x, this.levelFinalButtonPosition.y);
        }

        this.app.batch.end();
        this.renderer.render(this.world, this.camera.combined);
        this.rayHandler.updateAndRender();
        this.resetInvadersKilledBtnUpdate();
        this.resetSavesBtnUpdate();
        this.levelOneBtnUpdate();
        this.levelTwoBtnUpdate();
        this.levelThreeBtnUpdate();
        this.levelFourBtnUpdate();
        this.levelFiveBtnUpdate();
        this.levelSixBtnUpdate();
        this.levelSevenBtnUpdate();
        this.levelEightBtnUpdate();
        this.levelNineBtnUpdate();
        this.levelFinalBtnUpdate();

    }

    private void levelOneBtnUpdate() {
        if (Gdx.input.getX() >= this.levelOneButtonPosition.x &&
                Gdx.input.getX() <= this.levelOneButtonPosition.x + this.levelOneButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelOneButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelOneButtonPosition.y + this.levelOneButtonTexture.getHeight()) {
            this.levelOneLight.setColor(Color.RED);
            this.levelOneButtonSoundTimer += Gdx.graphics.getDeltaTime();
            this.app.buttonSound.play();
            if (levelOneButtonSoundTimer >= 0.6f) {
                this.app.buttonSound.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                this.states = States.LOADING_LEVEL1;
                this.app.accessMusic.play();
                this.app.welcomeMusic.stop();
            }
        } else {
            this.levelOneLight.setColor(Color.BLACK);
            this.levelOneButtonSoundTimer = 0;
        }
        if (this.states == States.LOADING_LEVEL1 && !this.app.accessMusic.isPlaying()) {
            this.app.setScreen(new LevelOne(this.app));
        }
    }

    private void levelTwoBtnUpdate() {
        if (Gdx.input.getX() >= this.levelTwoButtonPosition.x &&
                Gdx.input.getX() <= this.levelTwoButtonPosition.x + this.levelTwoButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelTwoButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelTwoButtonPosition.y + this.levelTwoButtonTexture.getHeight()) {
            this.levelTwoLight.setColor(Color.RED);
            this.levelTwoButtonSoundTimer += Gdx.graphics.getDeltaTime();
            this.app.buttonSound.play();
            if (levelTwoButtonSoundTimer >= 0.6f) {
                this.app.buttonSound.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && this.app.saveData.getSaveData().getBoolean("levelTwo")) {
                this.states = States.LOADING_LEVEL2;
                this.app.accessMusic.play();
                this.app.levelOneVictoryMusic.stop();
                this.app.welcomeMusic.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !this.app.saveData.getSaveData().getBoolean("levelTwo")) {
                this.app.accessDenied.play();
            }
        } else {
            this.levelTwoLight.setColor(Color.BLACK);
            this.levelTwoButtonSoundTimer = 0;
        }
        if (this.states == States.LOADING_LEVEL2 && !this.app.accessMusic.isPlaying())
            this.app.setScreen(new LevelTwo(this.app));
    }

    private void levelThreeBtnUpdate() {
        if (Gdx.input.getX() >= this.levelThreeButtonPosition.x &&
                Gdx.input.getX() <= this.levelThreeButtonPosition.x + this.levelThreeButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelThreeButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelThreeButtonPosition.y + this.levelThreeButtonTexture.getHeight()) {
            this.levelThreeLight.setColor(Color.RED);
            this.levelThreeButtonSoundTimer += Gdx.graphics.getDeltaTime();
            this.app.buttonSound.play();
            if (levelThreeButtonSoundTimer >= 0.6f) {
                this.app.buttonSound.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && this.app.saveData.getSaveData().getBoolean("levelThree")) {
                this.states = States.LOADING_LEVEL3;
                this.app.accessMusic.play();
                this.app.levelOneVictoryMusic.stop();
                this.app.welcomeMusic.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !this.app.saveData.getSaveData().getBoolean("levelThree")) {
                this.app.accessDenied.play();
            }
        } else {
            this.levelThreeLight.setColor(Color.BLACK);
            this.levelThreeButtonSoundTimer = 0;
        }
        if (this.states == States.LOADING_LEVEL3 && !this.app.accessMusic.isPlaying())
            this.app.setScreen(new LevelThree(this.app));
    }

    private void levelFourBtnUpdate() {
        if (Gdx.input.getX() >= this.levelFourButtonPosition.x &&
                Gdx.input.getX() <= this.levelFourButtonPosition.x + this.levelFourButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelFourButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelFourButtonPosition.y + this.levelFourButtonTexture.getHeight()) {
            this.levelFourLight.setColor(Color.RED);
            this.levelFourButtonSoundTimer += Gdx.graphics.getDeltaTime();
            this.app.buttonSound.play();
            if (levelFourButtonSoundTimer >= 0.6f) {
                this.app.buttonSound.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && this.app.saveData.getSaveData().getBoolean("levelFour")) {
                this.states = States.LOADING_LEVEL4;
                this.app.accessMusic.play();
                this.app.levelOneVictoryMusic.stop();
                this.app.welcomeMusic.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !this.app.saveData.getSaveData().getBoolean("levelFour")) {
                this.app.accessDenied.play();
            }
        } else {
            this.levelFourLight.setColor(Color.BLACK);
            this.levelFourButtonSoundTimer = 0;
        }
        if (this.states == States.LOADING_LEVEL4 && !this.app.accessMusic.isPlaying())
            this.app.setScreen(new LevelFour(this.app));
    }

    private void levelFiveBtnUpdate() {
        if (Gdx.input.getX() >= this.levelFiveButtonPosition.x &&
                Gdx.input.getX() <= this.levelFiveButtonPosition.x + this.levelFiveButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelFiveButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelFiveButtonPosition.y + this.levelFiveButtonTexture.getHeight()) {
            this.levelFiveLight.setColor(Color.RED);
            this.levelFiveButtonSoundTimer += Gdx.graphics.getDeltaTime();
            this.app.buttonSound.play();
            if (levelFiveButtonSoundTimer >= 0.6f) {
                this.app.buttonSound.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && this.app.saveData.getSaveData().getBoolean("levelFive")) {
                this.states = States.LOADING_LEVEL5;
                this.app.welcomeMusic.stop();
                this.app.levelOneVictoryMusic.stop();
                this.app.accessMusic.play();
                this.app.welcomeMusic.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !this.app.saveData.getSaveData().getBoolean("levelFive")) {
                this.app.accessDenied.play();
            }
        } else {
            this.levelFiveLight.setColor(Color.BLACK);
            this.levelFiveButtonSoundTimer = 0;
        }
        if (this.states == States.LOADING_LEVEL5 && !this.app.accessMusic.isPlaying()) {
            this.app.setScreen(new LevelFive(this.app));
        }
    }

    private void levelSixBtnUpdate() {
        if (Gdx.input.getX() >= this.levelSixButtonPosition.x &&
                Gdx.input.getX() <= this.levelSixButtonPosition.x + this.levelSixButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelSixButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelSixButtonPosition.y + this.levelSixButtonTexture.getHeight()) {
            this.levelSixLight.setColor(Color.RED);
            this.levelSixButtonSoundTimer += Gdx.graphics.getDeltaTime();
            this.app.buttonSound.play();
            if (levelSixButtonSoundTimer >= 0.6f) {
                this.app.buttonSound.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && this.app.saveData.getSaveData().getBoolean("levelSix")) {
                this.states = States.LOADING_LEVEL6;
                this.app.welcomeMusic.stop();
                this.app.levelOneVictoryMusic.stop();
                this.app.accessMusic.play();
                this.app.welcomeMusic.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !this.app.saveData.getSaveData().getBoolean("levelSix")) {
                this.app.accessDenied.play();
            }
        } else {
            this.levelSixLight.setColor(Color.BLACK);
            this.levelSixButtonSoundTimer = 0;
        }
        if (this.states == States.LOADING_LEVEL6 && !this.app.accessMusic.isPlaying()) {
            this.app.setScreen(new LevelSix(this.app));
        }
    }

    private void levelSevenBtnUpdate() {
        if (Gdx.input.getX() >= this.levelSevenButtonPosition.x &&
                Gdx.input.getX() <= this.levelSevenButtonPosition.x + this.levelSevenButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelSevenButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelSevenButtonPosition.y + this.levelSevenButtonTexture.getHeight()) {
            this.levelSevenLight.setColor(Color.RED);
            this.levelSevenButtonSoundTimer += Gdx.graphics.getDeltaTime();
            this.app.buttonSound.play();
            if (levelSevenButtonSoundTimer >= 0.6f) {
                this.app.buttonSound.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && this.app.saveData.getSaveData().getBoolean("levelSeven")) {
                this.states = States.LOADING_LEVEL7;
                this.app.welcomeMusic.stop();
                this.app.levelOneVictoryMusic.stop();
                this.app.accessMusic.play();
                this.app.welcomeMusic.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !this.app.saveData.getSaveData().getBoolean("levelSeven")) {
                this.app.accessDenied.play();
            }
        } else {
            this.levelSevenLight.setColor(Color.BLACK);
            this.levelSevenButtonSoundTimer = 0;
        }
        if (this.states == States.LOADING_LEVEL7 && !this.app.accessMusic.isPlaying()) {
            this.app.setScreen(new LevelSeven(this.app));
        }
    }

    private void levelEightBtnUpdate() {
        if (Gdx.input.getX() >= this.levelEightButtonPosition.x &&
                Gdx.input.getX() <= this.levelEightButtonPosition.x + this.levelEightButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelEightButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelEightButtonPosition.y + this.levelEightButtonTexture.getHeight()) {
            this.levelEightLight.setColor(Color.RED);
            this.levelEightButtonSoundTimer += Gdx.graphics.getDeltaTime();
            this.app.buttonSound.play();
            if (levelEightButtonSoundTimer >= 0.6f) {
                this.app.buttonSound.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && this.app.saveData.getSaveData().getBoolean("levelEight")) {
                this.states = States.LOADING_LEVEL8;
                this.app.welcomeMusic.stop();
                this.app.levelOneVictoryMusic.stop();
                this.app.accessMusic.play();
                this.app.welcomeMusic.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !this.app.saveData.getSaveData().getBoolean("levelEight")) {
                this.app.accessDenied.play();
            }
        } else {
            this.levelEightLight.setColor(Color.BLACK);
            this.levelEightButtonSoundTimer = 0;
        }
        if (this.states == States.LOADING_LEVEL8 && !this.app.accessMusic.isPlaying()) {
            this.app.setScreen(new LevelEight(this.app));
        }
    }

    private void levelNineBtnUpdate() {
        if (Gdx.input.getX() >= this.levelNineButtonPosition.x &&
                Gdx.input.getX() <= this.levelNineButtonPosition.x + this.levelNineButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelNineButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelNineButtonPosition.y + this.levelNineButtonTexture.getHeight()) {
            this.levelNineLight.setColor(Color.RED);
            this.levelNineButtonSoundTimer += Gdx.graphics.getDeltaTime();
            this.app.buttonSound.play();
            if (levelNineButtonSoundTimer >= 0.6f) {
                this.app.buttonSound.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && this.app.saveData.getSaveData().getBoolean("levelNine")) {
                this.states = States.LOADING_LEVEL9;
                this.app.welcomeMusic.stop();
                this.app.levelOneVictoryMusic.stop();
                this.app.accessMusic.play();
                this.app.welcomeMusic.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !this.app.saveData.getSaveData().getBoolean("levelNine")) {
                this.app.accessDenied.play();
            }
        } else {
            this.levelNineLight.setColor(Color.BLACK);
            this.levelNineButtonSoundTimer = 0;
        }
        if (this.states == States.LOADING_LEVEL9 && !this.app.accessMusic.isPlaying()) {
            this.app.setScreen(new LevelNine(this.app));
        }
    }

    private void levelFinalBtnUpdate() {
        if (Gdx.input.getX() >= this.levelFinalButtonPosition.x &&
                Gdx.input.getX() <= this.levelFinalButtonPosition.x + this.levelFinalButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.levelFinalButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.levelFinalButtonPosition.y + this.levelFinalButtonTexture.getHeight()) {
            this.levelFinalLight.setColor(Color.RED);
            this.levelFinalButtonSoundTimer += Gdx.graphics.getDeltaTime();
            this.app.buttonSound.play();
            if (levelFinalButtonSoundTimer >= 0.6f) {
                this.app.buttonSound.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && this.app.saveData.getSaveData().getBoolean("levelFinal")) {
                this.states = States.LOADING_LEVEL_FINAL;
                this.app.welcomeMusic.stop();
                this.app.levelOneVictoryMusic.stop();
                this.app.accessMusic.play();
                this.app.welcomeMusic.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !this.app.saveData.getSaveData().getBoolean("levelFinal")) {
                this.app.accessDenied.play();
            }
        } else {
            this.levelFinalLight.setColor(Color.BLACK);
            this.levelFinalButtonSoundTimer = 0;
        }
        if (this.states == States.LOADING_LEVEL_FINAL && !this.app.accessMusic.isPlaying()) {
            this.app.setScreen(new LevelFinal(this.app));
        }
    }

    private void resetInvadersKilledBtnUpdate() {
        if (Gdx.input.getX() >= this.resetKillsButtonPosition.x &&
                Gdx.input.getX() <= this.resetKillsButtonPosition.x + this.resetKillsButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.resetKillsButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.resetKillsButtonPosition.y + this.resetKillsButtonTexture.getHeight()) {
            this.resetInvadersLight.setColor(Color.RED);
            this.resetInvadersButtonSoundTimer += Gdx.graphics.getDeltaTime();
            this.app.buttonSound.play();
            if (resetInvadersButtonSoundTimer >= 0.6f) {
                this.app.buttonSound.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                this.app.resetDataVoice.play();
                this.app.saveData.getSaveData().remove("overallInvadersKilled");
                this.app.saveData.getSaveData().flush();
            }
        } else {
            this.resetInvadersLight.setColor(Color.BLACK);
            this.resetInvadersButtonSoundTimer = 0;
        }
    }

    private void resetSavesBtnUpdate() {
        if (Gdx.input.getX() >= this.resetSavesButtonPosition.x &&
                Gdx.input.getX() <= this.resetSavesButtonPosition.x + this.resetSavesButtonTexture.getWidth() &&
                App.HEIGHT - Gdx.input.getY() >= this.resetSavesButtonPosition.y &&
                App.HEIGHT - Gdx.input.getY() <= this.resetSavesButtonPosition.y + this.resetSavesButtonTexture.getHeight()) {
            this.resetISavesLight.setColor(Color.RED);
            this.resetSavesButtonSoundTimer += Gdx.graphics.getDeltaTime();
            this.app.buttonSound.play();
            if (resetSavesButtonSoundTimer >= 0.6f) {
                this.app.buttonSound.stop();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                this.app.resetDataVoice.play();
                this.app.saveData.getSaveData().putBoolean("levelTwo", false);
                this.app.saveData.getSaveData().putBoolean("levelThree", false);
                this.app.saveData.getSaveData().putBoolean("levelFour", false);
                this.app.saveData.getSaveData().putBoolean("levelFive", false);
                this.app.saveData.getSaveData().putBoolean("levelSix", false);
                this.app.saveData.getSaveData().putBoolean("levelSeven", false);
                this.app.saveData.getSaveData().putBoolean("levelEight", false);
                this.app.saveData.getSaveData().putBoolean("levelNine", false);
                this.app.saveData.getSaveData().putBoolean("levelFinal", false);
            }
        } else {
            this.resetISavesLight.setColor(Color.BLACK);
            this.resetSavesButtonSoundTimer = 0;
        }
    }


    @Override
    public void dispose() {
        this.world.dispose();
        this.rayHandler.dispose();
        this.levelOneButtonTexture.dispose();
        this.levelOneButtonTextureInactive.dispose();
        this.levelTwoButtonTexture.dispose();
        this.levelTwoButtonTextureInactive.dispose();
        this.levelThreeButtonTexture.dispose();
        this.levelThreeButtonTextureInactive.dispose();
        this.levelFourButtonTexture.dispose();
        this.levelFourButtonTextureInactive.dispose();
        this.levelFiveButtonTexture.dispose();
        this.levelFiveButtonTextureInactive.dispose();
        this.levelSixButtonTexture.dispose();
        this.levelSixButtonTextureInactive.dispose();
        this.levelSevenButtonTexture.dispose();
        this.levelSevenButtonTextureInactive.dispose();
        this.levelEightButtonTexture.dispose();
        this.levelEightButtonTextureInactive.dispose();
        this.levelNineButtonTexture.dispose();
        this.levelNineButtonTextureInactive.dispose();
        this.levelFinalButtonTexture.dispose();
        this.levelFinalButtonTextureInactive.dispose();
        this.resetKillsButtonTexture.dispose();
        this.resetKillsButtonTextureInactive.dispose();
        this.resetSavesButtonTexture.dispose();
        this.resetSavesButtonTextureInactive.dispose();
        this.resetInvadersLight.dispose();
        this.resetISavesLight.dispose();
        this.levelOneLight.dispose();
        this.levelTwoLight.dispose();
        this.levelThreeLight.dispose();
        this.levelFourLight.dispose();
        this.levelFiveLight.dispose();
        this.levelSixLight.dispose();
        this.levelSevenLight.dispose();
        this.levelEightLight.dispose();
        this.levelNineLight.dispose();
        this.levelFinalLight.dispose();
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
