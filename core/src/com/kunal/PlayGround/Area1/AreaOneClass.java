package com.kunal.PlayGround.Area1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;
import com.kunal.PlayGround.CuttingArea.CuttingAreaManager;
import com.kunal.PlayGround.ObjectCreation;
import com.kunal.PlayGround.PlayAreaUtils;
import com.kunal.PlayGround.ShapeChooser;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

public class AreaOneClass implements Screen {
    MainGame game;

    private World world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera cam;
    private Viewport port;

    private ShapeRenderer sred;

    private ObjectCreation objectCreation;

    private Sprite Brake, start, chooseBody, HardMoveShapes;
    private Boolean brakeBool = false, startBool = false, hardMove = true, hardmoveFaultResolver = false;

    //tiled map
    private TiledMap map;
    private OrthogonalTiledMapRenderer tmr;

    private float ver[];

    float camscl = 1.4f;

    int originX, originY;
    float shapeX, shapeY;

    Polygon poly;
    PlayAreaUtils playAreaUtils;

    public AreaOneClass(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH*camscl, AllVariables.HEIGHT*camscl, cam);

        port.apply();

        world = new World(new Vector2(0,0f), false);

        b2dr = new Box2DDebugRenderer();

        sred = new ShapeRenderer();

        objectCreation = new ObjectCreation();

        objectCreation.CreateBicycle(world);
        objectCreation.CreateCutouts(world);

        poly = new Polygon();

        playAreaUtils = new PlayAreaUtils();

        //safelt platforn for all objects
        BodyGenerator.BodyAssemble(world, true, "Land", new Vector2(640, -1200),
                new Vector2(200, 50), 1,1, AllVariables.Bit_land,
                (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool|AllVariables.Bit_land));



        //tiled map
        map = new TmxMapLoader().load("playArea/tiledMap/area1/level1.tmx");
        tmr = new OrthogonalTiledMapRenderer(map);

        //cam.position.set(port.getWorldWidth()/2, port.getWorldHeight()/2,0);

        Brake = new Sprite(new Texture(Gdx.files.internal("playArea/BothBrake.png")));
        Brake.setPosition(1050,140);
        Brake.setSize(180*camscl,150*camscl);
        Brake.setAlpha(0f);

        start = new Sprite(new Texture(Gdx.files.internal("playArea/Start.png")));
        start.setPosition(-190, 50);
        start.setSize(150*camscl, 150*camscl);
        start.setAlpha(0.8f);

        chooseBody = new Sprite(new Texture(Gdx.files.internal("playArea/ChooseBody.png")));
        chooseBody.setPosition(50, 140);
        chooseBody.setSize(150*camscl, 150*camscl);
        chooseBody.setAlpha(0.8f);

        HardMoveShapes = new Sprite(new Texture(Gdx.files.internal("playArea/HardMove.png")));
        HardMoveShapes.setPosition(50, 240);
        HardMoveShapes.setSize(100*camscl, 100*camscl);
        HardMoveShapes.setAlpha(1f);



        //pos remapping
        for (int i =0; i<VariablesForPlayArea.CutOutBodies.size(); i++){
            VariablesForPlayArea.CutOutBodies.get(i).setTransform(VariablesForPlayArea.Sh_pos.get(i), 0);
        }

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
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(dt);

        b2dr.render(world, cam.combined.scl(AllVariables.PPM));
        //need to fix this

        sred.setProjectionMatrix(cam.combined.scl(1/100f));

        b2dr.setDrawJoints(false);

        //tmr.render();

        sred.begin(ShapeRenderer.ShapeType.Line);

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
            //poly.setPosition(AllVariables.BackWheel.getPosition().x*100
              //    , AllVariables.BackWheel.getPosition().y*100);
            poly.setPosition(VariablesForPlayArea.CutOutBodies.get(i).getPosition().x * 100,
                    VariablesForPlayArea.CutOutBodies.get(i).getPosition().y * 100);
                    //it -am ::200);

            poly.setScale(1f,1f);
            poly.setRotation(180);
            poly.dirty();
            sred.polygon(poly.getTransformedVertices());

            ver = null;

        }


        sred.end();

        AllVariables.batch.setProjectionMatrix(cam.combined);


        AllVariables.batch.begin();
        Brake.draw(AllVariables.batch);
        start.draw(AllVariables.batch);
        chooseBody.draw(AllVariables.batch);
        HardMoveShapes.draw(AllVariables.batch);
        AllVariables.batch.end();



    }

    private void update(float dt){
        input(dt);

        //if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        world.step(1/(1/dt), 6,2);


        Vector3 campos = cam.position;
        campos.x = (AllVariables.BackWheel.getPosition().x)*AllVariables.PPM;
        campos.y = (AllVariables.BackWheel.getPosition().y)*AllVariables.PPM;
        cam.position.set(campos);
        cam.update();

        tmr.setView(cam);

        //position of sprites
        start.setPosition(-190+(cam.position.x - AllVariables.WIDTH/2), 50+(cam.position.y - AllVariables.HEIGHT/2));
        Brake.setPosition(1200+(cam.position.x - AllVariables.WIDTH/2), 50+(cam.position.y - AllVariables.HEIGHT/2));
        chooseBody.setPosition(1200+(cam.position.x - AllVariables.WIDTH/2), 50+(cam.position.y - AllVariables.HEIGHT/2));
        HardMoveShapes.setPosition(-220+(cam.position.x - AllVariables.WIDTH/2), 500+(cam.position.y -AllVariables.HEIGHT)/2);



        if (startBool){
            if (brakeBool) {
                //AllVariables.BackWheel.setAngularVelocity(0);
                AllVariables.FrontWheel.setAngularVelocity(0);
            }else{
                if (AllVariables.BackWheel.getAngularVelocity() > -8)
                    AllVariables.BackWheel.setAngularVelocity(-10);
                else
                    AllVariables.BackWheel.setAngularVelocity(AllVariables.BackWheel.getAngularVelocity()-3);
            }
        }

        //System.out.println(VariablesForPlayArea.CutOutBodies.size());


    }

    private void input(float dt){
        Gdx.input.setInputProcessor(
                new InputProcessor() {
                    @Override
                    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                        screenY = Gdx.graphics.getHeight() - screenY;
                        hardmoveFaultResolver = false;


                        if (startBool) {
                            //for brake
                            if (screenX > (1040* AllVariables.inpM)+AllVariables.witdth_translation
                                    && screenX < (1230* AllVariables.inpM)+AllVariables.witdth_translation
                                    && screenY > 140*AllVariables.inpM && screenY < 290*AllVariables.inpM) {
                                Brake.setAlpha(0.9f);
                                brakeBool = true;
                                return false;
                            }
                        }
                        if (!startBool){
                            //for start
                            if (screenX > (45* AllVariables.inpM)+AllVariables.witdth_translation
                                    && screenX < (200* AllVariables.inpM)+AllVariables.witdth_translation
                                    && screenY > 140* AllVariables.inpM && screenY < 290* AllVariables.inpM) {
                                start.setAlpha(0);
                                chooseBody.setAlpha(0);
                                HardMoveShapes.setAlpha(0);
                                world.setGravity(new Vector2(0,-10));
                                startBool = true;
                                Brake.setAlpha(0.4f);
                                playAreaUtils.MoveShapesToRealWorld();
                                VariablesForPlayArea.shapeNumberSelected = 15;

                                return false;
                            }
                            //shape chooser
                            if (screenX > (1040 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX < (1230 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY > 140* AllVariables.inpM && screenY < 290* AllVariables.inpM) {
                                //code to choosing body
                                game.setScreen(new ShapeChooser(game));
                                return false;
                            }
                            //harmove
                            if(screenX > (22 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX < (125 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY > 457* AllVariables.inpM && screenY < 560* AllVariables.inpM){
                                hardMove = !hardMove;
                                if (hardMove)
                                    HardMoveShapes.setAlpha(1);
                                else
                                    HardMoveShapes.setAlpha(0.4f);
                                hardmoveFaultResolver = true;
                                return false;

                            }
                        }

                        if(VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.CutOutBodies.size()-1) {
                            if (hardMove) {
                                VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).setTransform(
                                        (((screenX - AllVariables.witdth_translation)/AllVariables.inpM)  * camscl + (cam.position.x - AllVariables.WIDTH / 2)) / AllVariables.PPM,
                                        ((screenY/AllVariables.inpM)  * camscl - 200 + (cam.position.y - AllVariables.HEIGHT/ 2)) / AllVariables.PPM,
                                        (float) (180 * (Math.PI / 180)));
                                return false;

                            } else {
                                originX = screenX;
                                originY = screenY;
                                shapeX = VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).getPosition().x;
                                shapeY = VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).getPosition().y;
                                return false;
                            }
                        }

                        return false;
                    }

                    @Override
                    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                        if (brakeBool) {
                            Brake.setAlpha(0.4f);
                            brakeBool = false;
                        }
                        return false;
                    }

                    @Override
                    public boolean touchDragged(int screenX, int screenY, int pointer) {
                        screenY = Gdx.graphics.getHeight() - screenY;


                        if(!hardmoveFaultResolver) {
                            if (!hardMove) {
                                if (VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.CutOutBodies.size() - 1) {
                                    if (!hardMove) {
                                        VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).setTransform(((shapeX * AllVariables.PPM) + (screenX - originX)) / 100,
                                                ((shapeY * AllVariables.PPM) + (screenY - originY)) / 100, (float) (180 * (Math.PI / 180)));
                                    }
                                }
                            }
                        }


                        return false;
                    }

                    @Override
                    public boolean keyDown(int keycode) {
                        if (keycode == Input.Keys.Z){
                            game.setScreen(new CuttingAreaManager(game));
                        }
                        if (keycode == Input.Keys.SPACE){
                            world.setGravity(new Vector2(0,-10));
                        }


                        return false;
                    }

                    @Override
                    public boolean keyUp(int keycode) {
                        if (keycode == Input.Keys.SPACE){
                            world.setGravity(new Vector2(0,0));
                        }
                        return false;
                    }

                    @Override
                    public boolean keyTyped(char character) {
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
        world.dispose();
        b2dr.dispose();
        sred.dispose();
    }
}
