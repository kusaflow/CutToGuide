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

    Random rand;

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

        rand = new Random();

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        sred.setProjectionMatrix(cam.combined);

        if (Gdx.input.isKeyJustPressed(Input.Keys.V))
            sred.setColor(rand.nextInt(1000)/1000f,rand.nextInt(1000)/1000f,rand.nextInt(1000)/1000f,1);
        if (Gdx.input.isKeyJustPressed(Input.Keys.B))
            System.out.println(sred.getColor().r + "\t" + sred.getColor().g + "\t" + sred.getColor().b);


        sred.begin(ShapeRenderer.ShapeType.Filled);
        //colors of all circles
        //1- 0.668	0.095	0.02
        //2- 0.536  0.788 0.043
        //3- 0.029  0.381  0.923
        //4- 0.935  0.256  0.832
        sred.circle(AllVariables.WIDTH/2, AllVariables.HEIGHT/2, 100);
        sred.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);


    }

    private void update(float dt){

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
