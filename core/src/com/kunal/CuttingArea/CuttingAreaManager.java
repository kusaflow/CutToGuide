package com.kunal.CuttingArea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;

public class CuttingAreaManager implements Screen {

    MainGame game;
    private OrthographicCamera cam;
    private Viewport port;


    public CuttingAreaManager(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        AllVariables.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        AllVariables.shapeRenderer.setColor(1, 0, 1, 1);
        //AllVariables.shapeRenderer.line(100, 100, 550, 400);
        AllVariables.shapeRenderer.rect(300, 200, 700, 500);
        AllVariables.shapeRenderer.setColor(1, 1, 0, 0);
        AllVariables.shapeRenderer.circle(400, 150, 120);
        AllVariables.shapeRenderer.end();
    }

    private void update(float dt){
        cam.update();
        AllVariables.shapeRenderer.setProjectionMatrix(cam.combined);

    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
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
