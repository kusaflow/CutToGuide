package com.kunal.AreaSelection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.levelNumberSelection.LevelNumberSelection;
import com.kunal.MainGame;
import com.kunal.PlayGround.Area1.AreaOneClass;
import com.kunal.PlayGround.constScreen.ShapeChooser;
import com.kunal.Shop.Shop;
import com.kunal.playScreen.playScreen;

import java.util.LinkedList;

public class AreaSelection implements Screen {

    MainGame game;

    LinkedList<Sprite> AreaList;
    OrthographicCamera cam;
    Viewport port;

    Sprite settings , shop, credit, backToPrevScreen, showMoreLevelsOnRight, showMoreLevelsOnLeft, Tutorial;

    short LevelState=0;

    float positionY, transparency;

    Texture bg;


    public AreaSelection(MainGame game) {
        this.game = game;

        positionY = 150;
        transparency = 1;

        AllVariables.PresentAreaNumber = 0;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        AreaList = new LinkedList<Sprite>();

        settings = new Sprite(new Texture(Gdx.files.internal("AreaSelection/settings.png")));
        settings.setSize(90,90);
        settings.setPosition(1150,620);

        //space for coins==================================================================================================

        credit = new Sprite(new Texture(Gdx.files.internal("AreaSelection/credit.png")));
        credit.setSize(200,70);
        credit.setPosition(250, 620);


        shop = new Sprite(new Texture(Gdx.files.internal("AreaSelection/shop.png")));
        shop.setSize(130,130);
        shop.setPosition(20+35, 380+35);

        Tutorial = new Sprite(new Texture(Gdx.files.internal("AreaSelection/Tutorial.png")));
        Tutorial.setSize(200,200);
        Tutorial.setPosition(20, 150);

        showMoreLevelsOnLeft = new Sprite(new Texture(Gdx.files.internal("AreaSelection/PrevLevel.png")));
        showMoreLevelsOnLeft.setSize(100,200);
        showMoreLevelsOnLeft.setPosition(250, 240);

        showMoreLevelsOnRight = new Sprite(new Texture(Gdx.files.internal("AreaSelection/NextLevel.png")));
        showMoreLevelsOnRight.setSize(100,200);
        showMoreLevelsOnRight.setPosition(1200, 240);

        backToPrevScreen = new Sprite(new Texture(Gdx.files.internal("utils/hudX.png")));
        backToPrevScreen.setSize(128,128);
        backToPrevScreen.setPosition(0, 720-128);

        bg = new Texture(Gdx.files.internal("AreaSelection/bg.png"));



        //first 3
        Sprite s;
        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/Area1.png")));
        s.setSize(200, 400);
        s.setPosition(390,150);
        AreaList.add(s);

        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/soon.png")));
        s.setSize(200, 400);
        s.setPosition(660,150);
        AreaList.add(s);

        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/soon.png")));
        s.setSize(200, 400);
        s.setPosition(930,150);
        AreaList.add(s);


        //next 3
        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/soon.png")));
        s.setSize(200, 400);
        s.setPosition(390,150);
        s.setAlpha(0);
        AreaList.add(s);

        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/soon.png")));
        s.setSize(200, 400);
        s.setPosition(660,150);
        s.setAlpha(0);
        AreaList.add(s);


        ///last one for coming soon levels
        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/soon.png")));
        s.setSize(200, 400);
        s.setPosition(930,150);
        s.setAlpha(0);
        AreaList.add(s);


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
        input(delta);

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        AllVariables.batch.setProjectionMatrix(cam.combined);
        cam.update();



        AllVariables.batch.begin();
        AllVariables.batch.draw(bg,0,0, AllVariables.WIDTH, AllVariables.HEIGHT);


        for (Sprite s : AreaList)
            s.draw(AllVariables.batch);
        settings.draw(AllVariables.batch);
        shop.draw(AllVariables.batch);
        credit.draw(AllVariables.batch);
        Tutorial.draw(AllVariables.batch);
        backToPrevScreen.draw(AllVariables.batch);
        showMoreLevelsOnLeft.draw(AllVariables.batch);
        showMoreLevelsOnRight.draw(AllVariables.batch);

        AllVariables.batch.end();

    }

    private void update(float dt){
        //System.out.println(LevelState);
        if (transparency >= 1){

        }else{
            transparency += 0.1f;
        }

        if (positionY >= 150){

        }else{
            positionY += 10;
        }

        if (LevelState == 0){
            AreaList.get(0).setAlpha(transparency);
            AreaList.get(1).setAlpha(transparency);
            AreaList.get(2).setAlpha(transparency);
            AreaList.get(3).setAlpha(1-transparency);
            AreaList.get(4).setAlpha(1-transparency);
            AreaList.get(5).setAlpha(1-transparency);

            AreaList.get(0).setPosition(390, positionY);
            AreaList.get(1).setPosition(660, positionY);
            AreaList.get(2).setPosition(930, positionY);
            AreaList.get(3).setPosition(390, 150 - positionY);
            AreaList.get(4).setPosition(660, 150 - positionY);
            AreaList.get(5).setPosition(930, 150 - positionY);

        }if (LevelState == 1){
            AreaList.get(0).setAlpha(1-transparency);
            AreaList.get(1).setAlpha(1-transparency);
            AreaList.get(2).setAlpha(1-transparency);
            AreaList.get(3).setAlpha(transparency);
            AreaList.get(4).setAlpha(transparency);
            AreaList.get(5).setAlpha(transparency);

            AreaList.get(0).setPosition(390, 150 - positionY);
            AreaList.get(1).setPosition(660, 150 - positionY);
            AreaList.get(2).setPosition(930, 150 - positionY);
            AreaList.get(3).setPosition(390, positionY);
            AreaList.get(4).setPosition(660, positionY);
            AreaList.get(5).setPosition(930, positionY);

        }

    }

    private void input(float dt){

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


                        //shop
                        if(screenX >= (15* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (220* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 380* AllVariables.inpM && screenY <= 580* AllVariables.inpM) {
                            dispose();
                            game.setScreen(new Shop(game,(byte)1,(byte)1));
                            //code for shop
                            return false;
                        }


                        //tutorials
                        if(screenX >= (15* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (220* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 150* AllVariables.inpM && screenY <= 350* AllVariables.inpM) {
                            //code for Tutorial
                            dispose();
                            //game.setScreen(new AreaOneClass(game));
                            return false;
                        }

                        //back
                        if(screenX >= (15* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (140* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 620* AllVariables.inpM && screenY <= 700* AllVariables.inpM) {
                            game.setScreen(new playScreen(game));
                            return false;
                        }

                        //credits
                        if(screenX >= (250* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (450* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 620* AllVariables.inpM && screenY <= 700* AllVariables.inpM) {
                            //code for credits
                            System.out.println("credits");
                            return false;
                        }

                        //coins---------------------------------------------------------------leaving for now

                        //setting
                        if(screenX >= (1150* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (1240* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 620* AllVariables.inpM && screenY <= 710* AllVariables.inpM) {
                            //code for settings
                            System.out.println("setting");
                            return false;
                        }

                        //System.out.println(screenX + "\t" +screenY);

                        //prev levels
                        if(screenX >= (250* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (320* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 200* AllVariables.inpM && screenY <= 450* AllVariables.inpM) {
                            if(LevelState == 0){

                            }else {
                                LevelState--;
                                transparency = 0;
                                positionY = 70;
                            }
                            return false;
                        }

                        //next levels-------------------------------------------------------------------------change level state accordingly
                        if(screenX >= (1200* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (1270* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 200* AllVariables.inpM && screenY <= 450* AllVariables.inpM) {
                            if(LevelState == 1){

                            }else {
                                LevelState++;
                                transparency =0;
                                positionY = 70;
                            }
                            return false;
                        }


                        return false;
                    }

                    @Override
                    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                        screenY = Gdx.graphics.getHeight() - screenY;

                        //level Area Selection------------------------------------------------------------------
                        if(screenX >= (390* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (590* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 150* AllVariables.inpM && screenY <= 550* AllVariables.inpM) {

                            if(LevelState == 0){
                                //System.out.println("Area 1");
                                dispose();
                                AllVariables.PresentAreaNumber = 1;
                            }
                            else if (LevelState == 1){
                                //System.out.println("Area 4");
                                dispose();
                                AllVariables.PresentAreaNumber = 4;
                            }

                            Gdx.input.setInputProcessor(null);
                            game.setScreen(new LevelNumberSelection(game));

                            return false;
                        }

                        if(screenX >= (660* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (860* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 150* AllVariables.inpM && screenY <= 550* AllVariables.inpM) {

                            if(LevelState == 0){
                                //System.out.println("Area 2");
                                dispose();
                                AllVariables.PresentAreaNumber = 2;
                            }
                            else if (LevelState == 1){
                                //System.out.println("Area 5");
                                dispose();
                                AllVariables.PresentAreaNumber = 5;
                            }
                            Gdx.input.setInputProcessor(null);
                            game.setScreen(new LevelNumberSelection(game));

                            return false;
                        }

                        if(screenX >= (930* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (1130* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 150* AllVariables.inpM && screenY <= 550* AllVariables.inpM) {

                            if(LevelState == 0){
                                //System.out.println("Area 3");
                                dispose();
                                AllVariables.PresentAreaNumber = 3;
                            }
                            else if (LevelState == 1){
                                //System.out.println("Area 6");
                                dispose();
                                AllVariables.PresentAreaNumber = 6;
                            }
                            Gdx.input.setInputProcessor(null);
                            game.setScreen(new LevelNumberSelection(game));

                            return false;
                        }
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
        AreaList.clear();
        settings.getTexture().dispose();
        shop.getTexture().dispose();
        credit.getTexture().dispose();
        backToPrevScreen.getTexture().dispose();
        showMoreLevelsOnRight.getTexture().dispose();
        showMoreLevelsOnLeft.getTexture().dispose();
        Tutorial.getTexture().dispose();
        Gdx.input.setInputProcessor(null);
    }
}
