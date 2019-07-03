package com.kunal.Shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AdVideoInterface;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.MainGame;
import com.kunal.VideoEventListener;

import java.util.LinkedList;

public class Shop implements Screen, VideoEventListener {

    MainGame game;

    OrthographicCamera cam;
    Viewport port;

    /*
    --->shop
         |
         |-->In Game Coin (KusaCoin)
         |
         |
         |
         |
         |-->Bicycle View
         |          |
         |          |--> overLook
         |          |
         |          |--> tyre
         |          |
         |          |--> body
         |          |
         |          |--> coins
         |
         |
         |-->powerUps
         |          |
         |          |--> speedInc
         |          |
         |          |--> speedDec
         |          |
         |          |--> jumper
     */

    private Sprite freeAd, buy1, buy2, buy3, buy4, buy5, cancel;
    Texture menuKusaCoin, menuBicycle, menuPowerUps;
    //bicycle
    LinkedList<Texture> bicy_wheel, typeOfCoin, bicy_bars;

    String tempToShowTheKusaCoins = "";

    AdVideoInterface adVideoInterface = null;

    private byte menuNumber =0, InnerMenuNumber= 0, choice =0;


    public Shop(MainGame game, Byte mN, Byte Imn) {
        this.game = game;
        menuNumber = mN;
        InnerMenuNumber = Imn;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        freeAd = new Sprite(new Texture(Gdx.files.internal("Shop/FreeForAd.png")));
        freeAd.setSize(350,350);
        freeAd.setPosition(30,350);

        cancel= new Sprite(new Texture(Gdx.files.internal("utils/hudX.png")));
        cancel.setSize(128,128);
        cancel.setPosition(0, 720-128);

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

        //essential Menus
        menuKusaCoin = new Texture(Gdx.files.internal("utils/kusaCoin.png"));
        menuBicycle = new Texture(Gdx.files.internal("Shop/bicycleMenu.png"));
        menuPowerUps  = new Texture(Gdx.files.internal("playArea/LevelObstacles/Jumper/sprung.png"));



        //AllVariables.adv.setVideoEventListener(this);

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
        AllVariables.batch.draw(menuKusaCoin,20,453,114,114);
        AllVariables.batch.draw(menuBicycle,40,289,80,114);
        AllVariables.batch.draw(menuPowerUps,35,125,100,100);

        AllVariables.batch.draw(menuKusaCoin,400,670,50,50);
        AllVariables.bitmapFont.draw(AllVariables.batch, ">"+AllVariables.kusaCoin, 450, 700);

        //freeAd.draw(AllVariables.batch);
        //buy1.draw(AllVariables.batch);
        //buy2.draw(AllVariables.batch);
        //buy3.draw(AllVariables.batch);
        //buy4.draw(AllVariables.batch);
        //buy5.draw(AllVariables.batch);
        tempToShowTheKusaCoins = "" +  AllVariables.kusaCoin;
        AllVariables.bitmapFont.draw(AllVariables.batch, tempToShowTheKusaCoins, 60, 700);




        AllVariables.batch.end();

    }

    private void update(){
        input();
    }

    private void input(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            AllVariables.kusaCoin+=100;//game.setScreen(prevScreen);


        if(Gdx.input.justTouched()){
            //System.out.println(Gdx.input.getX() + "\t" +  (720 - Gdx.input.getY()));
            if(Gdx.input.getX() >= (30* AllVariables.inpM) + AllVariables.witdth_translation
                    && Gdx.input.getX() <= (375* AllVariables.inpM) + AllVariables.witdth_translation
                    && Gdx.graphics.getHeight() - Gdx.input.getY() >= 350 * AllVariables.inpM
                    && Gdx.graphics.getHeight() - Gdx.input.getY() <= 700 * AllVariables.inpM
            ) {
                //adVideoInterface.show();
                //if(AllVariables.adv.hasVideoLoaded()) {
                    //AllVariables.adv.showRewardedVideoAd();
                //}
            }
        }

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
                        System.out.println(screenX+"\t" +screenY);

                        //cancel
                        if(screenX >= (15* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (140* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY - Gdx.input.getY() >= 620 * AllVariables.inpM
                                && screenY - Gdx.input.getY() <= 700 * AllVariables.inpM){
                            dispose();
                            game.setScreen(new AreaSelection(game));
                        }

                        //KusaCoin
                        if(screenX >= (15* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (140* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY - Gdx.input.getY() >= 455 * AllVariables.inpM
                                && screenY - Gdx.input.getY() <= 560 * AllVariables.inpM){
                            dispose();
                            menuNumber = 1;
                        }

                        //bicycle makeup
                        if(screenX >= (15* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (140* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY - Gdx.input.getY() >= 270 * AllVariables.inpM
                                && screenY - Gdx.input.getY() <= 420 * AllVariables.inpM){
                            dispose();
                            menuNumber = 2;
                        }

                        //powerups
                        if(screenX >= (15* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (160* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY - Gdx.input.getY() >= 100 * AllVariables.inpM
                                && screenY - Gdx.input.getY() <= 222 * AllVariables.inpM){
                            dispose();
                            menuNumber = 3;
                        }

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


    //video loader--------------------------------------

    @Override
    public void onRewardedEvent(String type, int amount) {
        AllVariables.kusaCoin+=100;
    }

    @Override
    public void onRewardedVideoAdLoadedEvent() {

    }

    @Override
    public void onRewardedVideoAdClosedEvent() {

    }
}
