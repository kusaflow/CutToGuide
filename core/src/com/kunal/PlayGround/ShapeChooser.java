package com.kunal.PlayGround;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;
import com.kunal.PlayGround.Area1.AreaOneClass;
import com.kunal.PlayGround.CuttingArea.CuttingAreaManager;

public class ShapeChooser implements Screen {

    private MainGame game;
    private ShapeRenderer sred;
    private int x=-900,y=287;
    private Sprite LetsCut, okTick;

    private OrthographicCamera cam;
    private Viewport port;


    public ShapeChooser(MainGame game) {
        this.game = game;
        sred = new ShapeRenderer();

        cam = new OrthographicCamera();
        //cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        //port = new ScreenViewport(cam);
        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);
        port.apply();
        cam.update();


        LetsCut = new Sprite(new Texture(Gdx.files.internal("ChooseShape/LetsCut.png")));
        LetsCut.setPosition(1140, 507);
        LetsCut.setSize(140, 203);

        okTick = new Sprite(new Texture(Gdx.files.internal("ChooseShape/okTick.png")));
        okTick.setPosition(1140, 304);
        okTick.setSize(140, 203);

        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;

    }

    @Override
    public void show() {
        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;

    }

    @Override
    public void render(float dt) {
        input(dt);
        cam.update();
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        AllVariables.batch.setProjectionMatrix(cam.combined);
        sred.setProjectionMatrix(cam.combined);

        AllVariables.batch.begin();
        LetsCut.draw(AllVariables.batch);
        okTick.draw(AllVariables.batch);
        AllVariables.batch.end();

        sred.begin(ShapeRenderer.ShapeType.Line);
        sred.line(40,720,40,0);
        sred.line(315,720,315,0);
        sred.line(590,720,590,0);
        sred.line(865,720,865,0);
        sred.line(1140,720,1140,0);

        sred.line(0,710,1280,710);
        sred.line(0,507,1280,507);
        sred.line(0,304,1280,304);
        sred.line(0,101,1280,101);

        sred.end();

        sred.begin(ShapeRenderer.ShapeType.Filled);
        sred.rect(x, y,275,203);

        sred.end();
    }

    private void input(float dt){

        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > (40 * AllVariables.inpM)+AllVariables.witdth_translation && Gdx.input.getX() < (315* AllVariables.inpM)+AllVariables.witdth_translation) {
                if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 101* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 304* AllVariables.inpM) {
                    x=40;y=101;VariablesForPlayArea.shapeNumberSelected =8;
                } else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 304* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 507* AllVariables.inpM) {
                    x=40;y=304;VariablesForPlayArea.shapeNumberSelected =4;
                } else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 507* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 710* AllVariables.inpM) {
                    x=40;y=507;VariablesForPlayArea.shapeNumberSelected =0;
                }
            } else if (Gdx.input.getX() > (315* AllVariables.inpM)+AllVariables.witdth_translation && Gdx.input.getX() < (590* AllVariables.inpM)+AllVariables.witdth_translation) {
                if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 101* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 304* AllVariables.inpM){
                    x=315;y=101;VariablesForPlayArea.shapeNumberSelected =9;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 304* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 507* AllVariables.inpM){
                    x=315;y=304;VariablesForPlayArea.shapeNumberSelected =5;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 507* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 710* AllVariables.inpM){
                    x=315;y=507;VariablesForPlayArea.shapeNumberSelected =1;
                }
            } else if (Gdx.input.getX() > (590* AllVariables.inpM)+AllVariables.witdth_translation && Gdx.input.getX() < (865* AllVariables.inpM)+AllVariables.witdth_translation) {
                if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 101* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 304* AllVariables.inpM){
                    x=590;y=101;VariablesForPlayArea.shapeNumberSelected =10;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 304* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 507* AllVariables.inpM){
                    x=590;y=304;VariablesForPlayArea.shapeNumberSelected =6;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 507* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 710* AllVariables.inpM){
                    x=590;y=507;VariablesForPlayArea.shapeNumberSelected =2;
                }
            } else if (Gdx.input.getX() > (865* AllVariables.inpM)+AllVariables.witdth_translation && Gdx.input.getX() < (1140* AllVariables.inpM)+AllVariables.witdth_translation) {
                if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 101* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 304* AllVariables.inpM){
                    x=865;y=101;VariablesForPlayArea.shapeNumberSelected =11;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 304* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 507* AllVariables.inpM){
                    x=865;y=304;VariablesForPlayArea.shapeNumberSelected =7;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 507* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 710* AllVariables.inpM){
                    x=865;y=507;VariablesForPlayArea.shapeNumberSelected =3;
                }
            }

            //oktick
            else if (Gdx.input.getX() > (1140* AllVariables.inpM)+AllVariables.witdth_translation && Gdx.input.getX() <(1300* AllVariables.inpM)+AllVariables.witdth_translation) {

                if ((Gdx.graphics.getHeight() -Gdx.input.getY()) > 304* AllVariables.inpM &&  (Gdx.graphics.getHeight() -Gdx.input.getY()) < 507* AllVariables.inpM){
                    game.setScreen(new AreaOneClass(game));
                    //System.out.println("down wala");
                }

                //letsCut
                if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > (507*AllVariables.inpM) && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 710* AllVariables.inpM){
                    game.setScreen(new CuttingAreaManager(game));
                    //System.out.println("up wala");

                }
            }

        }

    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
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
