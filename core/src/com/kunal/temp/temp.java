package com.kunal.temp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.MainGame;
import com.kunal.PlayGround.Area1.AreaOneClass;
import com.kunal.PlayGround.CuttingArea.CuttingAreaManager;
import com.kunal.PlayGround.VariablesForPlayArea;

public class temp implements Screen {

    MainGame game;
    VariablesForPlayArea variablesForPlayArea;

    public temp(MainGame game) {
        this.game = game;
        variablesForPlayArea = new VariablesForPlayArea();
        variablesForPlayArea.setEndPoint(new Vector2(50,100));
        variablesForPlayArea.setLevelNumber(1);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.5f, 0.2f, 0.2f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if(Gdx.input.justTouched()){
            //game.setScreen(new PlayArea(game));

            //game.setScreen(new CuttingAreaManager(game));
            //game.setScreen(new MainLoadingScreen(game));

            game.setScreen(new AreaOneClass(game));

            //game.setScreen(new AreaSelection(game));

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
