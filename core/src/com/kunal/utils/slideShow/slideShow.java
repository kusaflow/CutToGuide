package com.kunal.utils.slideShow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
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
    int focused, totalImg, imagesLoaded, yaxis = 200, xaxis = 200;
    LinkedList<Sprite> helpImg;
    Texture exit;

    private OrthographicCamera cam;
    private Viewport port;


    public slideShow(MainGame game, int SenderCode){
        this.game = game;
        senderCode = SenderCode;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        port.apply();

        exit = new Texture(Gdx.files.internal("utils/hudX.png"));

        totalImg = slideShowHelper.ImageCount(senderCode);
        focused =0;

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

        helpImg.get(0).setPosition(xaxis, yaxis);
        helpImg.get(0).setSize(helpImg.get(0).getTexture().getWidth()/2f,helpImg.get(0).getTexture().getHeight()/2f);


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

        input();

        AllVariables.batch.setProjectionMatrix(cam.combined);

        //helpImg.get(2).setPosition(100,200);
        //helpImg.get(2).setSize(helpImg.get(2).getTexture().getWidth()/2f,helpImg.get(2).getTexture().getHeight()/2f);
        //helpImg.get(2).setRotation(10);

        AllVariables.batch.begin();
        for (int i=0; i<focused; i++){
            if (i == focused-1){
                helpImg.get(i).setAlpha(1f);
            }else {
                helpImg.get(i).setPosition(xaxis+120, helpImg.get(i).getY()+200);
                helpImg.get(i).setAlpha(.4f);
                helpImg.get(i).setSize(helpImg.get(i).getTexture().getWidth()/2f,helpImg.get(i).getTexture().getHeight()/2f);
            }
            helpImg.get(i).draw(AllVariables.batch);
        }
        AllVariables.batch.draw(exit,1120+(cam.position.x - AllVariables.WIDTH/2), 600+(cam.position.y - AllVariables.HEIGHT/2));
        AllVariables.bitmapFont.draw(AllVariables.batch,"Touch anywhere to continue", 400+(cam.position.x - AllVariables.WIDTH/2), 710+(cam.position.y - AllVariables.HEIGHT/2));
        AllVariables.bitmapFont.draw(AllVariables.batch,slideShowHelper.TutMessage(senderCode,focused), 100+(cam.position.x - AllVariables.WIDTH/2), 180+(cam.position.y - AllVariables.HEIGHT/2));
        AllVariables.batch.end();


        if (Gdx.input.isKeyJustPressed(Input.Keys.B))
            game.setScreen(new AreaSelection(game));


    }

    private void input(){
        Gdx.input.setInputProcessor(
                new InputProcessor() {
                    @Override
                    public boolean keyDown(int keycode) {
                        return false;
                    }

                    @Override
                    public boolean keyUp(int keycode) {
                        return false;
                    }

                    @Override
                    public boolean keyTyped(char character) {
                        return false;
                    }

                    @Override
                    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                        screenY = Gdx.graphics.getHeight() - screenY;
                        System.out.println(screenX + "\t" + screenY);
                        if (screenX > (1110 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (1270 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 605 * AllVariables.inpM && screenY < 720 * AllVariables.inpM){
                            game.setScreen(new AreaSelection(game));
                            return true;
                        }
                        addNewSlide();

                        return false;
                    }

                    @Override
                    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                        return false;
                    }

                    @Override
                    public boolean touchDragged(int screenX, int screenY, int pointer) {
                        return false;
                    }

                    @Override
                    public boolean mouseMoved(int screenX, int screenY) {
                        return false;
                    }

                    @Override
                    public boolean scrolled(int amount) {
                        return false;
                    }
                }
        );
    }

    private void addNewSlide(){
        if (focused == totalImg) {
            //return to the back class
            game.setScreen(new AreaSelection(game));
            return;
        }
        focused++;


        try {
            Sprite s = new Sprite(new Texture(Gdx.files.internal(fileLoc + (focused+3) + ".jpg")));
            helpImg.add(s);

        }catch (Exception e){
        }

        yaxis+=300;

        cam.position.y = cam.position.y+300;
        cam.update();

        helpImg.get(focused-1).setPosition(xaxis, yaxis);
        helpImg.get(focused-1).setSize(helpImg.get(focused-1).getTexture().getWidth()/1.4f,helpImg.get(focused-1).getTexture().getHeight()/1.4f);


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
        Gdx.input.setInputProcessor(null);

    }
}
