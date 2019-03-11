package com.kunal.PlayGround;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
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
import com.kunal.PlayGround.Tutorial.TutArea;

import java.util.Random;

public class ShapeChooser implements Screen {

    private MainGame game;
    private ShapeRenderer sred;
    private int x=-900,y=287;
    private Sprite LetsCut, okTick, reCut;

    private OrthographicCamera cam;
    private Viewport port;
    private float ver[];
    private Polygon poly;

    private short[][] ShapePts = new short[12][2];


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

        poly = new Polygon();


        LetsCut = new Sprite(new Texture(Gdx.files.internal("ChooseShape/LetsCut.png")));
        LetsCut.setPosition(1140, 507);
        LetsCut.setSize(140, 203);

        okTick = new Sprite(new Texture(Gdx.files.internal("ChooseShape/okTick.png")));
        okTick.setPosition(1140, 304);
        okTick.setSize(140, 203);

        reCut = new Sprite(new Texture(Gdx.files.internal("ChooseShape/reCut.png")));
        reCut.setPosition(1140, 101);
        reCut.setSize(140, 203);


        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;

        ShapePts[0][0] =200 + 40;
        ShapePts[0][1] =30 + 507;
        ShapePts[1][0] =200 + 315;
        ShapePts[1][1] =30 + 507;
        ShapePts[2][0] =200 + 590;
        ShapePts[2][1] =30 + 507;
        ShapePts[3][0] =200 + 865;
        ShapePts[3][1] =30 + 507;
        ShapePts[4][0] =200 + 40;
        ShapePts[4][1] =30 + 304;
        ShapePts[5][0] =200 + 315;
        ShapePts[5][1] =30 + 304;
        ShapePts[6][0] =200 + 590;
        ShapePts[6][1] =30 + 304;
        ShapePts[7][0] =200 + 865;
        ShapePts[7][1] =30 + 304;
        ShapePts[8][0] =200 + 40;
        ShapePts[8][1] =30 + 101;
        ShapePts[9][0] =200 + 315;
        ShapePts[9][1] =30 + 101;
        ShapePts[10][0] =200 + 590;
        ShapePts[10][1] =30 + 101;
        ShapePts[11][0] =200 + 865;
        ShapePts[11][1] =30 + 101;

        //reinitinting the shapechooser selection
        if (VariablesForPlayArea.shapeNumberSelected <= 11){
            if (VariablesForPlayArea.shapeNumberSelected == 0){
                x = 40;
                y = 507;
            }else if (VariablesForPlayArea.shapeNumberSelected == 1){
                x = 315;
                y = 507;
            }else if (VariablesForPlayArea.shapeNumberSelected == 2){
                x = 590;
                y = 507;
            }else if (VariablesForPlayArea.shapeNumberSelected == 3){
                x = 865;
                y = 507;
            }else if (VariablesForPlayArea.shapeNumberSelected == 4){
                x = 40;
                y = 304;
            }else if (VariablesForPlayArea.shapeNumberSelected == 5){
                x = 315;
                y = 304;
            }else if (VariablesForPlayArea.shapeNumberSelected == 6){
                x = 590;
                y = 304;
            }else if (VariablesForPlayArea.shapeNumberSelected == 7){
                x = 865;
                y = 304;
            }else if (VariablesForPlayArea.shapeNumberSelected == 8){
                x = 40;
                y = 101;
            }else if (VariablesForPlayArea.shapeNumberSelected == 9){
                x = 315;
                y = 101;
            }else if (VariablesForPlayArea.shapeNumberSelected == 10){
                x = 590;
                y = 101;
            }else if (VariablesForPlayArea.shapeNumberSelected == 11){
                x = 865;
                y = 101;
            }
        }




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


        Gdx.gl.glClearColor(0.105f, 0.118f, 0.198f, 1f);
        //Gdx.gl.glClearColor(0,0,0, 1f);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        AllVariables.batch.setProjectionMatrix(cam.combined);
        sred.setProjectionMatrix(cam.combined);

        AllVariables.batch.begin();
        LetsCut.draw(AllVariables.batch);
        okTick.draw(AllVariables.batch);
        reCut.draw(AllVariables.batch);
        AllVariables.batch.end();

        sred.begin(ShapeRenderer.ShapeType.Line);


        //verticle
        sred.line(40,710,40,101);
        sred.line(315,710,315,101);
        sred.line(590,710,590,101);
        sred.line(865,710,865,101);
        sred.line(1140,710,1140,101);

        //horizontal
        sred.line(40,710,1280,710);
        sred.line(40,507,1280,507);
        sred.line(40,304,1280,304);
        sred.line(40,101,1280,101);

        sred.setColor(1, 1f, 1, 1);
        for (int i = 0; i < VariablesForPlayArea.shapes.size(); i++) {
            ver = new float[(VariablesForPlayArea.shapes.get(i).size() * 2)];
            for (int j = 0, k = 0; j < VariablesForPlayArea.shapes.get(i).size(); j++) {
                ver[k] = 550-VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(j)][0]/2;
                k++;
                ver[k] = 40 -VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(j)][1]/2;
                k++;
            }

            poly = new Polygon(ver);
            poly.setPosition(ShapePts[i][0], ShapePts[i][1]);

            poly.setScale(0.5f,0.5f);
            poly.setRotation(180);
            poly.dirty();
            sred.polygon(poly.getTransformedVertices());

            ver = null;

        }

        sred.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        //rect
        sred.begin(ShapeRenderer.ShapeType.Filled);
        //sred.setColor(0.423f,0.751f,0.588f,0.2f);
        sred.setColor(0.963f, 0.901f, 0.265f,0.2f);
        //0.786,0.597,0.623;
        //0.783,0.488,0.671;
        //0.963,0.901,0.265;

        sred.rect(x, y,275,203);
        sred.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);

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

            else if (Gdx.input.getX() > (1140* AllVariables.inpM)+AllVariables.witdth_translation && Gdx.input.getX() <(1300* AllVariables.inpM)+AllVariables.witdth_translation) {

                //letsCut
                if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > (507*AllVariables.inpM) && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 710* AllVariables.inpM) {
                    game.setScreen(new CuttingAreaManager(game));
                    //System.out.println("up wala");
                }

                //okTick
                if ((Gdx.graphics.getHeight() -Gdx.input.getY()) > 304* AllVariables.inpM &&  (Gdx.graphics.getHeight() -Gdx.input.getY()) < 507* AllVariables.inpM){
                    if (VariablesForPlayArea.areaNumber == 0)
                        game.setScreen(new TutArea(game));
                    else if (VariablesForPlayArea.areaNumber == 1)
                        game.setScreen(new AreaOneClass(game));
                    //System.out.println("down wala");
                }

                //reCut
                if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > (101*AllVariables.inpM) && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 304* AllVariables.inpM){
                    //VariablesForPlayArea.CutOutBodies.clear();
                    VariablesForPlayArea.flush();
                    //System.out.println("Lets RecUT");
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
