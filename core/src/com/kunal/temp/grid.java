package com.kunal.temp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class grid implements Screen {

    ShapeRenderer sred;

    public grid() {
        sred = new ShapeRenderer();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sred.begin(ShapeRenderer.ShapeType.Line);
        sred.line(90,720,90,0);
        sred.line(365,720,365,0);
        sred.line(640,720,640,0);
        sred.line(915,720,915,0);
        sred.line(1190,720,1190,0);

        sred.line(0,710,1280,710);
        sred.line(0,507,1280,507);
        sred.line(0,287,1280,287);
        sred.line(0,102,1280,102);

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
