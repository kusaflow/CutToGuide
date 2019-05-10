package com.kunal.Shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;

public class Shop implements Screen {

    MainGame game;
    Screen prevScreen;

    OrthographicCamera cam;
    Viewport port;

    Sprite freeAd, buy1, buy2, buy3, buy4, buy5, cancel;


    public Shop(MainGame game, Screen PrevScreen) {
        this.game = game;
        prevScreen = PrevScreen;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        freeAd = new Sprite(new Texture(Gdx.files.internal("Shop/FreeForAd.png")));
        freeAd.setSize(350,350);
        freeAd.setPosition(30,350);

        cancel= new Sprite(new Texture(Gdx.files.internal("Shop/cancel.png")));
        cancel.setSize(150,150);
        cancel.setPosition(30,110);

        buy1= new Sprite(new Texture(Gdx.files.internal("Shop/buy1.png")));
        buy1.setSize(200,200);
        buy1.setPosition(550,400);

        buy2= new Sprite(new Texture(Gdx.files.internal("Shop/buy2.png")));
        buy2.setSize(200,200);
        buy2.setPosition(850,400);

        buy3= new Sprite(new Texture(Gdx.files.internal("Shop/buy3.png")));
        buy3.setSize(200,200);
        buy3.setPosition(400,110);

        buy4= new Sprite(new Texture(Gdx.files.internal("Shop/buy4.png")));
        buy4.setSize(200,200);
        buy4.setPosition(700,110);

        buy5= new Sprite(new Texture(Gdx.files.internal("Shop/buy5.png")));
        buy5.setSize(200,200);
        buy5.setPosition(1000,110);


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
        update();
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        AllVariables.batch.setProjectionMatrix(cam.combined);

        AllVariables.batch.begin();
        cancel.draw(AllVariables.batch);
        freeAd.draw(AllVariables.batch);
        buy1.draw(AllVariables.batch);
        buy2.draw(AllVariables.batch);
        buy3.draw(AllVariables.batch);
        buy4.draw(AllVariables.batch);
        buy5.draw(AllVariables.batch);
        AllVariables.batch.end();

    }

    private void update(){
        input();
    }

    private void input(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            game.setScreen(prevScreen);

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
