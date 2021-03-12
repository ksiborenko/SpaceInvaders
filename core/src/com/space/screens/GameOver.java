package com.space.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.space.App;

public class GameOver implements Screen {

    private final Texture background;
    private final App app;
    private final Music music;

    public GameOver(App app) {
        this.app = app;
        this.background = new Texture("gameOver1.jpg");
        music = Gdx.audio.newMusic(Gdx.files.internal("endMusic.mp3"));
        music.play();
        music.setLooping(true);
    }

    @Override
    public void render(float delta) {
        app.batch.begin();
        app.batch.draw(this.background, 0, 0, App.WIDTH, App.HEIGHT);
        app.batch.end();
    }

    @Override
    public void dispose() {
        this.background.dispose();
        this.music.dispose();
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
