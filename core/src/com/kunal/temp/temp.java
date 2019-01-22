package com.kunal.temp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.kunal.CuttingArea.CuttingAreaManager;
import com.kunal.Loading.MainLoadingScreen;
import com.kunal.MainGame;
import com.kunal.PlayGround.PlayArea;

public class temp implements Screen {

    MainGame game;

    public temp(MainGame game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.5f, 0.2f, 0.2f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if(Gdx.input.justTouched()){
            game.setScreen(new PlayArea(game));
            //game.setScreen(new CuttingAreaManager(game));
            //game.setScreen(new MainLoadingScreen(game));

        }
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
