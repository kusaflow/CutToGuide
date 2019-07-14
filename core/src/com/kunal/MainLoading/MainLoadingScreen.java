package com.kunal.MainLoading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;
import com.kunal.playScreen.playScreen;
import com.kunal.temp.temp;

public class MainLoadingScreen implements Screen {

    MainGame game;
    ShapeRenderer sred;

    private OrthographicCamera cam;
    private Viewport port;

    Sprite kusaGames;
    int timmer= 0;
    float alpha =0;


    public MainLoadingScreen(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        kusaGames = new Sprite(new Texture(Gdx.files.internal("mainLoading/kusaGames.jpg")));
        kusaGames.setPosition(0,0);
        kusaGames.setSize(AllVariables.WIDTH,AllVariables.HEIGHT);
        kusaGames.setAlpha(0);

        port.apply();
        cam.update();

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
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        AllVariables.batch.setProjectionMatrix(cam.combined);

        AllVariables.batch.begin();
        kusaGames.setAlpha(alpha);
        kusaGames.draw(AllVariables.batch);

        AllVariables.batch.end();


    }

    private void update(float dt){
        timmer++;
        if (timmer<50){
            kusaGames.setAlpha(0.01f);
            alpha+=0.02f;
        }
        else if (timmer>=50 && timmer<200){

        }else if (timmer>=200 && timmer<300) {
            alpha-=0.02f;
        }else if (timmer>=300){
            dispose();
            //game.setScreen(new temp(game));
            game.setScreen(new playScreen(game));
        }

        if (alpha<0)
            alpha = 0;
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
        kusaGames.getTexture().dispose();
    }
}
