package com.space;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.screens.LevelFinal;
import com.space.utils.SaveData;


public class App extends Game {
    public static final int HEIGHT = 800;
    public static final int WIDTH = 1200;
    public static final int FPS = 60;
    public SpriteBatch batch;
    public SaveData saveData;
    public Music accessGranted;
    public Music resetDataVoice;
    public Music accessDenied;
    public Music accessMusic;
    public Music welcomeMusic;
    public Music buttonSound;
    public Music levelMusic;
    public Music levelVictoryMusic;
    public Music finalVictoryMusic;
    public Music horn;
    public Music charge;


    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.saveData = new SaveData();
        this.accessGranted = Gdx.audio.newMusic(Gdx.files.internal("music/accessGrantedVoice.mp3"));
        this.resetDataVoice = Gdx.audio.newMusic(Gdx.files.internal("music/resetData.mp3"));
        this.accessDenied = Gdx.audio.newMusic(Gdx.files.internal("music/accessDenied.mp3"));
        this.accessMusic = Gdx.audio.newMusic(Gdx.files.internal("music/accessMusic.mp3"));
        this.welcomeMusic = Gdx.audio.newMusic(Gdx.files.internal("music/welcomeMusic1.mp3"));
        this.buttonSound = Gdx.audio.newMusic(Gdx.files.internal("music/sound2.mp3"));
        this.levelMusic = Gdx.audio.newMusic(Gdx.files.internal("music/levelMusic.mp3"));
        this.levelVictoryMusic = Gdx.audio.newMusic(Gdx.files.internal("music/victoryMusic.mp3"));
        this.finalVictoryMusic = Gdx.audio.newMusic(Gdx.files.internal("music/finalVictoryMusic.mp3"));
        this.horn = Gdx.audio.newMusic(Gdx.files.internal("horn.mp3"));
        this.charge = Gdx.audio.newMusic(Gdx.files.internal("charge.mp3"));

        this.setScreen(new LevelFinal(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        this.accessGranted.dispose();
        this.resetDataVoice.dispose();
        this.accessDenied.dispose();
        this.accessMusic.dispose();
        this.welcomeMusic.dispose();
        this.buttonSound.dispose();
        this.levelMusic.dispose();
        this.levelVictoryMusic.dispose();
        this.horn.dispose();
        this.charge.dispose();
        this.batch.dispose();
    }
}
