package com.space.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.space.App;

public class Laugh implements Screen {

    private final Texture background;
    private final Music laugh;
    private final App app;


    public Laugh(App app) {
        this.app = app;
        this.background = new Texture("looser.png");
        this.laugh = Gdx.audio.newMusic(Gdx.files.internal("laugh.mp3"));
        this.laugh.play();
    }

    @Override
    public void render(float delta) {
        app.batch.begin();
        app.batch.draw(this.background, 0, 0, App.WIDTH, App.HEIGHT);
        app.batch.end();
        if (!laugh.isPlaying()) {
           app.setScreen(new GameOver(app));
           this.laugh.dispose();
        }
    }

    @Override
    public void dispose() {
        this.background.dispose();
        this.laugh.dispose();
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
