package com.kunal.PlayGround.constScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;
import com.kunal.PlayGround.TypeThreeArea.Type3Area;
import com.kunal.PlayGround.TypeTwoArea.TypeTwoArea;
import com.kunal.PlayGround.constScreen.CuttingArea.CuttingAreaManager;
import com.kunal.PlayGround.VariablesForPlayArea;

public class ShapeChooser implements Screen {

    private MainGame game;
    private ShapeRenderer sred;
    private int x=-900,y=287;
    private Sprite LetsCut, okTick, reCut;

    private OrthographicCamera cam;
    private Viewport port;
    private float ver[];
    private Polygon poly;

    private short[][] ShapePts = new short[20][2];

    Sprite powerUpSprite;

    int i =0;

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


        LetsCut = new Sprite(new Texture(Gdx.files.internal("utils/arrowRight.png")));
        LetsCut.setPosition(1140, 537);
        LetsCut.setSize(140, 140);

        okTick = new Sprite(new Texture(Gdx.files.internal("utils/tick.png")));
        okTick.setPosition(1140, 334);
        okTick.setSize(140, 140);
        okTick.setColor(0.1f,0.7f,0.2f,1);

        reCut = new Sprite(new Texture(Gdx.files.internal("utils/retry.png")));
        reCut.setPosition(1140, 131);
        reCut.setSize(140, 140);

        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;

        ShapePts[0][0] =150 + 40;
        ShapePts[0][1] =30 + 557;
        ShapePts[1][0] =150 + 260;
        ShapePts[1][1] =30 + 557;
        ShapePts[2][0] =150 + 480;
        ShapePts[2][1] =30 + 557;
        ShapePts[3][0] =150 + 700;
        ShapePts[3][1] =30 + 557;
        ShapePts[4][0] =150 + 920;
        ShapePts[4][1] =30 + 557;
        ShapePts[5][0] =150 + 40;
        ShapePts[5][1] =30 + 405;
        ShapePts[6][0] =150 + 260;
        ShapePts[6][1] =30 + 405;
        ShapePts[7][0] =150 + 480;
        ShapePts[7][1] =30 + 405;
        ShapePts[8][0] =150 + 700;
        ShapePts[8][1] =30 + 405;
        ShapePts[9][0] =150 + 920;
        ShapePts[9][1] =30 + 405;
        ShapePts[10][0] =150 + 40;
        ShapePts[10][1] =30 + 252;
        ShapePts[11][0] =150 + 260;
        ShapePts[11][1] =30 + 252;
        ShapePts[12][0] =150 + 480;
        ShapePts[12][1] =30 + 252;
        ShapePts[13][0] =150 + 700;
        ShapePts[13][1] =30 + 252;
        ShapePts[14][0] =150 + 920;
        ShapePts[14][1] =30 + 252;
        ShapePts[15][0] =150 + 40;
        ShapePts[15][1] =30 + 100;
        ShapePts[16][0] =150 + 260;
        ShapePts[16][1] =30 + 100;
        ShapePts[17][0] =150 + 480;
        ShapePts[17][1] =30 + 100;
        ShapePts[18][0] =150 + 700;
        ShapePts[18][1] =30 + 100;
        ShapePts[19][0] =150 + 920;
        ShapePts[19][1] =30 + 100;


        //reinitinting the shapechooser selection
        if (VariablesForPlayArea.shapeNumberSelected <= 19){
            if (VariablesForPlayArea.shapeNumberSelected == 0){
                x = 40;
                y = 557;
            }else if (VariablesForPlayArea.shapeNumberSelected == 1){
                x = 260;
                y = 557;
            }else if (VariablesForPlayArea.shapeNumberSelected == 2){
                x = 480;
                y = 557;
            }else if (VariablesForPlayArea.shapeNumberSelected == 3){
                x = 700;
                y = 557;
            }else if (VariablesForPlayArea.shapeNumberSelected == 4){
                x = 920;
                y = 557;
            }else if (VariablesForPlayArea.shapeNumberSelected == 5){
                x = 40;
                y = 405;
            }else if (VariablesForPlayArea.shapeNumberSelected == 6){
                x = 260;
                y = 405;
            }else if (VariablesForPlayArea.shapeNumberSelected == 7){
                x = 480;
                y = 405;
            }else if (VariablesForPlayArea.shapeNumberSelected == 8){
                x = 700;
                y = 405;
            }else if (VariablesForPlayArea.shapeNumberSelected == 9){
                x = 920;
                y = 405;
            }else if (VariablesForPlayArea.shapeNumberSelected == 10){
                x = 40;
                y = 252;
            }else if (VariablesForPlayArea.shapeNumberSelected == 11){
                x = 260;
                y = 252;
            }else if (VariablesForPlayArea.shapeNumberSelected == 12){
                x = 480;
                y = 252;
            }else if (VariablesForPlayArea.shapeNumberSelected == 13){
                x = 700;
                y = 252;
            }else if (VariablesForPlayArea.shapeNumberSelected == 14){
                x = 920;
                y = 252;
            }else if (VariablesForPlayArea.shapeNumberSelected == 15){
                x = 40;
                y = 100;
            }else if (VariablesForPlayArea.shapeNumberSelected == 16){
                x = 260;
                y = 100;
            }else if (VariablesForPlayArea.shapeNumberSelected == 17){
                x = 480;
                y = 100;
            }else if (VariablesForPlayArea.shapeNumberSelected == 18){
                x = 700;
                y = 100;
            }else if (VariablesForPlayArea.shapeNumberSelected == 19){
                x = 920;
                y = 100;
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

        sred.begin(ShapeRenderer.ShapeType.Line);//----------------------------------------------------

        //verticle
        sred.line(40,710,40,101);
        sred.line(260,710,260,101);
        sred.line(480,710,480,101);
        sred.line(700,710,700,101);
        sred.line(920,710,920,101);
        sred.line(1140,710,1140,101);

        //horizontal
        sred.line(40,710,1140,710);
        sred.line(40,557,1140,557);
        sred.line(40,405,1140,405);
        sred.line(40,252,1140,252);
        sred.line(40,100,1140,100);

        sred.setColor(1, 1f, 1, 1);

        for (i = 0; i < VariablesForPlayArea.shapes.size(); i++) {
            if (VariablesForPlayArea.Sh_pos.get(i).y*AllVariables.PPM == -3000)
                sred.setColor(1, 1f, 1, 1);
            else
                sred.setColor(1f, .4f, .4f, 1);
            ver = new float[(VariablesForPlayArea.shapes.get(i).size() * 2)];
            for (int j = 0, k = 0; j < VariablesForPlayArea.shapes.get(i).size(); j++) {
                ver[k] = 550-VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(j)][0]/2;
                k++;
                ver[k] = 40 -VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(j)][1]/2;
                k++;
            }

            poly = new Polygon(ver);
            poly.setPosition(ShapePts[i][0], ShapePts[i][1]);

            poly.setScale(0.3f,0.3f);
            poly.setRotation(180);
            poly.dirty();
            sred.polygon(poly.getTransformedVertices());

            ver = null;
        }

        sred.end();//-------------------------------------------------------------------------------

        for (int j =0; j < VariablesForPlayArea.powerUpList.size(); j++, i++){
            powerUpSprite = new Sprite(VariablesForPlayArea.powerUpList.get(j).texture);
            powerUpSprite.setSize(30,30);
            powerUpSprite.setPosition(ShapePts[i][0]-50, ShapePts[i][1]+25);

            //renderer
            AllVariables.batch.begin();
            powerUpSprite.draw(AllVariables.batch);
            AllVariables.batch.end();
            //renderer
        }


        //the option chooser
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        //rect
        sred.begin(ShapeRenderer.ShapeType.Filled);
        //sred.setColor(0.423f,0.751f,0.588f,0.2f);
        sred.setColor(0.963f, 0.901f, 0.265f,0.2f);
        //0.786,0.597,0.623;
        //0.783,0.488,0.671;
        //0.963,0.901,0.265;

        sred.rect(x, y,220,153);
        sred.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);

    }

    private void input(float dt){

        if (Gdx.input.isKeyJustPressed(Input.Keys.X))
            game.setScreen(new TypeTwoArea(game, false));


        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > (40 * AllVariables.inpM)+AllVariables.witdth_translation && Gdx.input.getX() < (260* AllVariables.inpM)+AllVariables.witdth_translation) {
                if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 100* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 252* AllVariables.inpM) {
                    x=40;y=100;VariablesForPlayArea.shapeNumberSelected =15;
                } else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 252* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 405* AllVariables.inpM) {
                    x=40;y=252;VariablesForPlayArea.shapeNumberSelected =10;
                } else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 405* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 557* AllVariables.inpM) {
                    x=40;y=405;VariablesForPlayArea.shapeNumberSelected =5;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 557* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 710* AllVariables.inpM) {
                    x=40;y=557;VariablesForPlayArea.shapeNumberSelected =0;
                }
            } else if (Gdx.input.getX() > (260* AllVariables.inpM)+AllVariables.witdth_translation && Gdx.input.getX() < (480* AllVariables.inpM)+AllVariables.witdth_translation) {
                if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 100* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 252* AllVariables.inpM){
                    x=260;y=100;VariablesForPlayArea.shapeNumberSelected =16;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 252* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 405* AllVariables.inpM){
                    x=260;y=252;VariablesForPlayArea.shapeNumberSelected =11;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 405* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 557* AllVariables.inpM){
                    x=260;y=405;VariablesForPlayArea.shapeNumberSelected =6;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 557* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 710* AllVariables.inpM){
                    x=260;y=557;VariablesForPlayArea.shapeNumberSelected =1;
                }
            } else if (Gdx.input.getX() > (480* AllVariables.inpM)+AllVariables.witdth_translation && Gdx.input.getX() < (700* AllVariables.inpM)+AllVariables.witdth_translation) {
                if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 100* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 252* AllVariables.inpM){
                    x=480;y=100;VariablesForPlayArea.shapeNumberSelected =17;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 252* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 405* AllVariables.inpM){
                    x=480;y=252;VariablesForPlayArea.shapeNumberSelected =12;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 405* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 557* AllVariables.inpM){
                    x=480;y=405;VariablesForPlayArea.shapeNumberSelected =7;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 557* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 710* AllVariables.inpM){
                    x=480;y=557;VariablesForPlayArea.shapeNumberSelected =2;
                }
            } else if (Gdx.input.getX() > (700* AllVariables.inpM)+AllVariables.witdth_translation && Gdx.input.getX() < (920* AllVariables.inpM)+AllVariables.witdth_translation) {
                if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 100* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 252* AllVariables.inpM){
                    x=700;y=100;VariablesForPlayArea.shapeNumberSelected =18;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 252* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 405* AllVariables.inpM){
                    x=700;y=252;VariablesForPlayArea.shapeNumberSelected =13;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 405* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 557* AllVariables.inpM){
                    x=700;y=405;VariablesForPlayArea.shapeNumberSelected =8;
                }
                else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 557* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 710* AllVariables.inpM){
                    x=700;y=557;VariablesForPlayArea.shapeNumberSelected =3;
                }
            } else if (Gdx.input.getX() > (920* AllVariables.inpM)+AllVariables.witdth_translation && Gdx.input.getX() < (1140* AllVariables.inpM)+AllVariables.witdth_translation) {
                if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 100* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 252* AllVariables.inpM){
                    x=920;y=100;VariablesForPlayArea.shapeNumberSelected =19;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 252* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 405* AllVariables.inpM){
                    x=920;y=252;VariablesForPlayArea.shapeNumberSelected =14;
                }else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 405* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 557* AllVariables.inpM){
                    x=920;y=405;VariablesForPlayArea.shapeNumberSelected =9;
                }
                else if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > 557* AllVariables.inpM && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 710* AllVariables.inpM){
                    x=920;y=557;VariablesForPlayArea.shapeNumberSelected =4;
                }
            }

            else if (Gdx.input.getX() > (1140* AllVariables.inpM)+AllVariables.witdth_translation && Gdx.input.getX() <(1300* AllVariables.inpM)+AllVariables.witdth_translation) {

                //letsCut
                if ((Gdx.graphics.getHeight() - Gdx.input.getY()) > (507*AllVariables.inpM) && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 710* AllVariables.inpM) {
                    try {
                        game.setScreen(new CuttingAreaManager(game));
                    }catch (Exception e){
                    }
                    //System.out.println("up wala");
                }

                //okTick
                if ((Gdx.graphics.getHeight() -Gdx.input.getY()) > 304* AllVariables.inpM &&  (Gdx.graphics.getHeight() -Gdx.input.getY()) < 507* AllVariables.inpM){
                    if (AllVariables.PresentAreaNumber == 1){
                        try {
                            dispose();
                            game.setScreen(new TypeTwoArea(game, false));
                        }catch (Exception e){}

                    }if (AllVariables.PresentAreaNumber == 2){
                        try {
                            dispose();
                            game.setScreen(new Type3Area(game, false));
                        }catch (Exception e){}

                    }
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
