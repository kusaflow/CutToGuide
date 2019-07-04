package com.kunal.Shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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

    private Sprite cancel;
    Texture menuKusaCoin, menuBicycle, menuPowerUps, astic;
    Texture freeKusaCoin, bgTobuy;
    //bicycle
    LinkedList<Texture> bicy_wheel, typeOfCoin, bicy_bars;

    AdVideoInterface adVideoInterface = null;

    private byte menuNumber =0, InnerMenuNumber= 0, choice =0;


    public Shop(MainGame game, Byte mN, Byte Imn) {
        this.game = game;
        //menuNumber = mN;
        //InnerMenuNumber = Imn;
        menuNumber = 1;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        cancel= new Sprite(new Texture(Gdx.files.internal("utils/hudX.png")));
        cancel.setSize(128,128);
        cancel.setPosition(0, 720-128);


        //essential Menus
        menuKusaCoin = new Texture(Gdx.files.internal("utils/kusaCoin.png"));
        menuBicycle = new Texture(Gdx.files.internal("Shop/bicycleMenu.png"));
        menuPowerUps  = new Texture(Gdx.files.internal("playArea/LevelObstacles/Jumper/sprung.png"));
        astic = new Texture(Gdx.files.internal("utils/astric.png"));
        bgTobuy = new Texture(Gdx.files.internal("Shop/bgToBuy.png"));
        freeKusaCoin = new Texture(Gdx.files.internal("Shop/VideoIcon.png"));



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
        AllVariables.bitmapFont.setColor(Color.WHITE);
        if (menuNumber == 1) {
            //menu
            AllVariables.batch.draw(menuKusaCoin, 20, 453, 120, 120);
            AllVariables.batch.draw(menuBicycle, 40, 289, 60, 96);
            AllVariables.batch.draw(menuPowerUps, 35, 125, 80, 80);
            AllVariables.batch.draw(astic, 20, 523, 20, 20);


            //=======================================================================
            //1
            AllVariables.batch.draw(bgTobuy, 300, 440, 200, 150);
            AllVariables.batch.draw(freeKusaCoin, 350, 500, 100, 100);
            //4
            AllVariables.batch.draw(bgTobuy, 300, 180, 200, 150);


            //=======================================================================
            //2
            AllVariables.batch.draw(bgTobuy, 600, 440, 200, 150);

            //5
            AllVariables.batch.draw(bgTobuy, 600, 180, 200, 150);



            //=======================================================================
            //3
            AllVariables.batch.draw(bgTobuy, 900, 440, 200, 150);
            //6
            AllVariables.batch.draw(bgTobuy, 900, 180, 200, 150);

            //kusaCoinForDisplay----------------------------------------------------
            AllVariables.batch.draw(menuKusaCoin, 400, 340, 50, 50);


            //kusaCoinamount


            AllVariables.bitmapFont.setColor(Color.BLACK);
            //prize-------------------------------------------------
            //6
            AllVariables.bitmapFont.draw(AllVariables.batch, "$250", 970, 260);
            //3
            AllVariables.bitmapFont.draw(AllVariables.batch, "$100", 970, 520);
            //5
            AllVariables.bitmapFont.draw(AllVariables.batch, "$200", 670, 260);
            //2
            AllVariables.bitmapFont.draw(AllVariables.batch, "$50", 670, 520);
            //4
            AllVariables.bitmapFont.draw(AllVariables.batch, "$250", 370, 260);
            //1
            AllVariables.bitmapFont.draw(AllVariables.batch, "Rewarded \n           Ad", 320, 500);





        }else if (menuNumber ==2){
            AllVariables.batch.draw(menuKusaCoin, 20, 453, 96, 96);
            AllVariables.batch.draw(menuBicycle, 40, 289, 86, 120);
            AllVariables.batch.draw(menuPowerUps, 35, 125, 80, 80);
            AllVariables.batch.draw(astic, 40, 400, 20, 20);
        }else if (menuNumber == 3){
            AllVariables.batch.draw(menuKusaCoin, 20, 453, 96, 96);
            AllVariables.batch.draw(menuBicycle, 40, 289, 60, 96);
            AllVariables.batch.draw(menuPowerUps, 35, 125, 106, 106);
            AllVariables.batch.draw(astic, 40, 210, 20, 20);
        }

        AllVariables.bitmapFont.setColor(Color.ORANGE);
        AllVariables.batch.draw(menuKusaCoin, 400, 668, 50, 50);
        AllVariables.bitmapFont.draw(AllVariables.batch, ">"+AllVariables.kusaCoin, 450, 700);


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
                                && screenY >= 620 * AllVariables.inpM
                                && screenY <= 700 * AllVariables.inpM){
                            dispose();
                            game.setScreen(new AreaSelection(game));
                        }

                        //KusaCoin
                        if(screenX >= (15* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (140* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 455 * AllVariables.inpM
                                && screenY <= 560 * AllVariables.inpM){
                            dispose();
                            menuNumber = 1;
                            InnerMenuNumber = 1;
                        }

                        //bicycle makeup
                        if(screenX >= (15* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (140* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 270 * AllVariables.inpM
                                && screenY <= 420 * AllVariables.inpM){
                            dispose();
                            if (menuNumber != 2) {
                                menuNumber = 2;
                                InnerMenuNumber = 1;
                            }
                        }

                        //powerups
                        if(screenX >= (15* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (160* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 100 * AllVariables.inpM
                                && screenY <= 222 * AllVariables.inpM){
                            dispose();

                            if (menuNumber != 3) {
                                menuNumber = 3;
                                InnerMenuNumber = 1;
                            }
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
