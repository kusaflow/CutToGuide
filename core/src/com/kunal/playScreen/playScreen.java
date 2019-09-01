package com.kunal.playScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.AreaSelection.levelNumberSelection.LevelNumberSelection;
import com.kunal.MainGame;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.temp.temp;

public class playScreen implements Screen {

    MainGame game;

    private OrthographicCamera cam;
    private Viewport port;

    float alpha = 0;
    Sprite playScreen;
    VariablesForPlayArea variablesForPlayArea;


    public playScreen(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        port.apply();
        cam.update();

        variablesForPlayArea = new VariablesForPlayArea();
        variablesForPlayArea.setEndPoint(new Vector2(200,4000));
        variablesForPlayArea.setLevelNumber(2);

        //playScreen = new Sprite(new Texture(Gdx.files.internal("playScreen/playscreen.png")));
        playScreen = new Sprite(new Texture(Gdx.files.internal("playScreen/playscreen.jpg")));
        playScreen.setPosition(0,0);
        playScreen.setSize(AllVariables.WIDTH, AllVariables.HEIGHT);

        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
    }

    @Override
    public void show() {
        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0.2627450980f, 0.2705882f, 0.18431372f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        AllVariables.batch.setProjectionMatrix(cam.combined);

        AllVariables.batch.begin();
        playScreen.setAlpha(alpha);
        playScreen.draw(AllVariables.batch);
        AllVariables.batch.end();


    }

    private void update(float dt){

        if(Gdx.input.justTouched()){
            dispose();
            game.setScreen(new AreaSelection(game));
        }
        alpha+=0.08f;
        if(alpha>1)
            alpha = 1;
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
        playScreen.getTexture().dispose();
    }
}
