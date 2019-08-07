package com.kunal.utils.slideShow;

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
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.MainGame;

import java.util.LinkedList;

import sun.applet.Main;

public class slideShow implements Screen {
    /*
    0 means its hint 2
    1 means its basic
     */

    MainGame game;
    String fileLoc = "";
    int senderCode;
    int focused, totalImg, imagesLoaded, yaxis = 400;
    LinkedList<Sprite> helpImg;

    private OrthographicCamera cam;
    private Viewport port;


    public slideShow(MainGame game, int SenderCode){
        this.game = game;
        senderCode = SenderCode;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        port.apply();

        totalImg = slideShowHelper.ImageCount(senderCode);
        focused = 1;

        helpImg = new LinkedList<Sprite>();
        if (totalImg >= 3){
            imagesLoaded = 3;
        }else {
            if (totalImg == 2){
                imagesLoaded = 2;
            }else {
                imagesLoaded = 1;
            }
        }


        if (senderCode == 0) {

        }else if (senderCode == 1){
            fileLoc = "tut/basic/b";
        }

        for (int i =1; i<=imagesLoaded; i++){
            Sprite s = new Sprite(new Texture(Gdx.files.internal(fileLoc + i + ".jpg")));
            helpImg.add(s);
        }

        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;



    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        AllVariables.batch.setProjectionMatrix(cam.combined);

        helpImg.get(2).setPosition(100,200);
        helpImg.get(2).setSize(helpImg.get(2).getTexture().getWidth()/2f,helpImg.get(2).getTexture().getHeight()/2f);
        helpImg.get(2).setRotation(10);

        AllVariables.batch.begin();
        for (int i=0; i<focused; i++){
            if (i == focused-1){
                helpImg.get(2).setAlpha(1f);
            }else {
                helpImg.get(2).setAlpha(.4f);
            }
            helpImg.get(i).draw(AllVariables.batch);
        }
        AllVariables.batch.end();


        if (Gdx.input.isKeyJustPressed(Input.Keys.B))
            game.setScreen(new AreaSelection(game));


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
        for (int i =0; i < helpImg.size(); i++){
            helpImg.get(i).getTexture().dispose();
        }


        helpImg.clear();

    }
}
