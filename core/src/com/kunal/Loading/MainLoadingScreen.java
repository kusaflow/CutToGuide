package com.kunal.Loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kunal.AllVariables;
import com.kunal.MainGame;

public class MainLoadingScreen implements Screen {

    MainGame game;
    ShapeRenderer sred;

    //circle initial position
    int c3x = AllVariables.WIDTH/2 -60, c3y = AllVariables.HEIGHT/2 - 60;
    int c2x = AllVariables.WIDTH/2 +60, c2y = AllVariables.HEIGHT/2 - 60;
    int c1x = AllVariables.WIDTH/2 -60, c1y = AllVariables.HEIGHT/2 + 60;
    int c4x = AllVariables.WIDTH/2 +60, c4y = AllVariables.HEIGHT/2 + 60;


    public MainLoadingScreen(MainGame game) {
        sred = new ShapeRenderer();
        this.game = game;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sred.begin(ShapeRenderer.ShapeType.Filled);
        sred.circle(c1x, c1y, 15);
        sred.circle(c2x, c2y, 15);
        sred.circle(c3x, c3y, 15);
        sred.circle(c4x, c4y, 15);
        sred.end();


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
