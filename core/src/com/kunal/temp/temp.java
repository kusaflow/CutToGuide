package com.kunal.temp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.MainGame;
import com.kunal.PlayGround.Area1.AreaOneClass;
import com.kunal.PlayGround.TypeOneArea.TypeOneArea;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.Shop.Shop;
import com.kunal.utils.ReDirectToTheLevel;

public class temp implements Screen {

    MainGame game;
    VariablesForPlayArea variablesForPlayArea;

    public temp(MainGame game) {
        this.game = game;
        variablesForPlayArea = new VariablesForPlayArea();
        variablesForPlayArea.setEndPoint(new Vector2(200,4000));
        variablesForPlayArea.setLevelNumber(2);

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

            //game.setScreen(new AreaOneClass(game));

            game.setScreen(new AreaSelection(game,true));
            //AllVariables.PresentAreaNumber = 1;
            //AllVariables.PresentLevelNumber = 1;
            //ReDirectToTheLevel.Direct(game);

            //game.setScreen(new Shop(game,this));

            //this is compulsory before setting tut area
            VariablesForPlayArea.tutstep = 0;

            //game.setScreen(new ShapeChooser(game));

            //game.setScreen(new levelSelection(game));


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
