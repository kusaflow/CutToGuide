package com.kunal.AreaSelection.levelNumberSelection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.kunal.AllVariables;
import com.kunal.MainGame;

public class LevelNumberSelection implements Screen {
    MainGame game;

    public LevelNumberSelection(MainGame game) {
        this.game = game;
        System.out.println(AllVariables.PresentAreaNumber);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.4f, 0.3f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
