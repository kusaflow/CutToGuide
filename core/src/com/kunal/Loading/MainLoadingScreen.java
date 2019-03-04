package com.kunal.Loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;

import java.util.Random;

public class MainLoadingScreen implements Screen {

    MainGame game;
    ShapeRenderer sred;

    private OrthographicCamera cam;
    private Viewport port;

    float deltaIncrement = 8f;


    float radius1 = 192;
    boolean rad1expand = true;


    float radius2 = 96;
    boolean rad2expand = true;


    float radius3 = 48;
    boolean rad3expand = true;


    float radius4 = 24;
    boolean rad4expand = true;


    public MainLoadingScreen(MainGame game) {
        sred = new ShapeRenderer();
        this.game = game;

        cam = new OrthographicCamera();
        //cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        //port = new ScreenViewport(cam);
        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);
        port.apply();
        cam.update();


    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        sred.setProjectionMatrix(cam.combined);

        sred.begin(ShapeRenderer.ShapeType.Filled);
        //colors of all circles
        //1- 0.668	0.095	0.02
        //2- 0.536  0.788 0.043
        //3- 0.029  0.381  0.923
        //4- 0.935  0.256  0.832

        //limit of max circle 240
        //limit of min circle 24
        sred.setColor(0.968f,0.095f,0.02f,0.5f);
        sred.circle(400, AllVariables.HEIGHT/2, radius1);
        sred.setColor(0.536f,0.788f,0.043f,0.5f);
        sred.circle(560, AllVariables.HEIGHT/2, radius2);
        sred.setColor(0.029f,0.381f,0.0923f,0.5f);
        sred.circle(720, AllVariables.HEIGHT/2, radius3);
        sred.setColor(0.935f,0.253f,0.832f,0.5f);
        sred.circle(880, AllVariables.HEIGHT/2, radius4);

        sred.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);


    }

    private void update(float dt){
        if(radius1 == 240)
            rad1expand = false;
        else if (radius1 == 24)
            rad1expand = true;

        if(radius2 == 240)
            rad2expand = false;
        else if (radius2 == 24)
            rad2expand = true;

        if(radius3 == 240)
            rad3expand = false;
        else if (radius3 == 24)
            rad3expand = true;

        if(radius4 == 240)
            rad4expand = false;
        else if (radius4 == 24)
            rad4expand = true;


        if (rad1expand)
            radius1+=deltaIncrement;
        else
            radius1-=deltaIncrement;
////////////////////////////////////////////////
        if (rad2expand)
            radius2+=deltaIncrement;
        else
            radius2-=deltaIncrement;
///////////////////////////////////////////////////
        if (rad3expand)
            radius3+=deltaIncrement;
        else
            radius3-=deltaIncrement;
///////////////////////////////////////////////////////
        if (rad4expand)
            radius4+=deltaIncrement;
        else
            radius4-=deltaIncrement;




    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
        cam.update();
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
