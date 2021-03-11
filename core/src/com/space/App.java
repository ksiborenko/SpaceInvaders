package com.space;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.screens.*;
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
    public Music levelOneVictoryMusic;
    public Music accessMusicLevelTwo;


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
        this.levelOneVictoryMusic = Gdx.audio.newMusic(Gdx.files.internal("music/victoryLevelMusic.mp3"));
        this.accessMusicLevelTwo = Gdx.audio.newMusic(Gdx.files.internal("music/accessMusicLevelTwo.mp3"));

        this.setScreen(new WelcomeScreen(this));


    }

    @Override
    public void render() {
        super.render();


    }

    @Override
    public void dispose() {
        super.dispose();
        accessGranted.dispose();
        resetDataVoice.dispose();
        accessDenied.dispose();
        accessMusic.dispose();
        welcomeMusic.dispose();
        buttonSound.dispose();
        levelMusic.dispose();
        levelOneVictoryMusic.dispose();
        accessMusicLevelTwo.dispose();

    }
}
