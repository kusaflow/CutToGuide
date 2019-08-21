package com.kunal.PlayGround.Tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.MainGame;

import java.util.LinkedList;

public class tutScreen implements Screen {

    MainGame game;
    OrthographicCamera cam;
    Viewport port;

    //need  to add left right after wards reffer lever selection for ease
    short levelState;

    private Texture cross;//, movRight, movLeft;
    // tut icons
    private Texture Basic;

    public tutScreen (MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        cross = new Texture(Gdx.files.internal("utils/hudX.png"));
        //movRight = new Texture(Gdx.files.internal("utils/arrowRight.png"));
        //movLeft = new Texture(Gdx.files.internal("utils/arrowLeft.png"));



    }

    @Override
    public void show() {
        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.3f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        AllVariables.batch.setProjectionMatrix(cam.combined);
        input(delta);

        cam.update();

    }

    private void input(float dt){
        if (Gdx.input.justTouched()) {
            //cross
            if (Gdx.input.getX() >= (0 * AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getX() < (128 * AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getY() >= 0 * AllVariables.inpM && Gdx.input.getY() < 128 * AllVariables.inpM) {
                game.setScreen(new AreaSelection(game));
            }

        }
    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
        cam.update();
        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
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
