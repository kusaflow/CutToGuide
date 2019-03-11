package com.kunal.PlayGround.Tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;
import com.kunal.PlayGround.CuttingArea.CuttingAreaManager;
import com.kunal.PlayGround.ObjectCreation;
import com.kunal.PlayGround.PlayAreaUtils;
import com.kunal.PlayGround.ShapeChooser;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

public class TutArea implements Screen {
    MainGame game;

    private World world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera cam;
    private Viewport port;

    private ShapeRenderer sred;

    private ObjectCreation objectCreation;

    private Sprite Brake, start, chooseBody, HardMoveShapes, CamScroller, DropAnyShapeButton, ShapeRotACW, ShapeRotCW;
    private Boolean brakeBool = false, startBool = false, hardMove = true, hardmoveFaultResolver = false, isCamScrollerTouched = false, toDrawDropAnyShapeButton = true, isAnyShapeSelected = false, ACWTouched = false, CWtouched = false;

    //CamScroller
    private short CamScrollerX = 1320, CamScrollerY = 750;
    private byte camScrollSize =60;
    private int dragged_touchX =0;

    //position of camera x
    private float camposX = 700f;

    //temp Rotation Folder for shapes
    short tempRotForShape;

    //tiled map
    private TiledMap map;
    private OrthogonalTiledMapRenderer tmr;

    private float ver[];

    float camscl = 1.4f;

    int originX, originY;
    float shapeX, shapeY;

    Polygon poly;
    PlayAreaUtils playAreaUtils;

    //follow cycle if start is pressed
    private boolean CamfollowCycle = false, startAnimToMoveCycle = false;

    public TutArea(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH*camscl, AllVariables.HEIGHT*camscl, cam);

        port.apply();

        world = new World(new Vector2(0,0f), false);

        b2dr = new Box2DDebugRenderer();

        sred = new ShapeRenderer();

        objectCreation = new ObjectCreation();

        objectCreation.CreateBicycle(world , 20);
        objectCreation.CreateCutouts(world);

        poly = new Polygon();

        playAreaUtils = new PlayAreaUtils();

        //safelt platforn for all objects
        BodyGenerator.BodyAssemble(world, true, "Land", new Vector2(640, -1200),
                new Vector2(200, 50), 1,1, AllVariables.Bit_land,
                (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool|AllVariables.Bit_land));



        //tiled map
        map = new TmxMapLoader().load("playArea/tiledMap/tut/tut.tmx");
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

        CamScroller = new Sprite(new Texture(Gdx.files.internal("playArea/CamScroller.png")));
        CamScroller.setPosition(50,240);
        CamScroller.setSize(camScrollSize*camscl, camScrollSize*camscl);
        CamScroller.setAlpha(1);

        DropAnyShapeButton = new Sprite(new Texture(Gdx.files.internal("playArea/DropAnyShapeButton.png")));
        DropAnyShapeButton.setPosition(50,240);
        DropAnyShapeButton.setSize(100*camscl, 60*camscl);
        DropAnyShapeButton.setAlpha(0.5f);

        ShapeRotACW = new Sprite(new Texture(Gdx.files.internal("playArea/ShapeRotation_ACW.png")));
        ShapeRotACW.setPosition(50,240);
        ShapeRotACW.setSize(70*camscl, 70*camscl);
        ShapeRotACW.setAlpha(0.8f);


        ShapeRotCW = new Sprite(new Texture(Gdx.files.internal("playArea/ShapeRotation_CW.png")));
        ShapeRotCW.setPosition(50,240);
        ShapeRotCW.setSize(70*camscl, 70*camscl);
        ShapeRotCW.setAlpha(0.8f);




        //pos remapping
        //for (int i =0; i<VariablesForPlayArea.CutOutBodies.size(); i++){
        //VariablesForPlayArea.CutOutBodies.get(i).setTransform(VariablesForPlayArea.Sh_pos.get(i), VariablesForPlayArea.CutOutBodies.get(i).getAngle());
        //}

        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;


        VariablesForPlayArea.areaNumber = 0;


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
                ver[k] = VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(0)][0]/(2)-VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(j)][0]/2;
                k++;
                ver[k] = VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(0)][1]/(2) -VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(j)][1]/2;
                k++;
            }

            poly = new Polygon(ver);
            //poly.setPosition(AllVariables.BackWheel.getPosition().x*100
            //    , AllVariables.BackWheel.getPosition().y*100);
            poly.setPosition(VariablesForPlayArea.CutOutBodies.get(i).getPosition().x * 100,
                    VariablesForPlayArea.CutOutBodies.get(i).getPosition().y * 100);
            //it -am ::200);

            poly.setScale(1f,1f);
            poly.setRotation(VariablesForPlayArea.Angle_Of_Shape.get(i));
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
        CamScroller.draw(AllVariables.batch);
        DropAnyShapeButton.draw(AllVariables.batch);
        if(isAnyShapeSelected){
            ShapeRotACW.draw(AllVariables.batch);
            ShapeRotCW.draw(AllVariables.batch);
        }
        AllVariables.batch.end();



    }

    private void update(float dt){
        input(dt);

        //if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        world.step(1/(1/dt), 6,2);


        //Vector3 campos = cam.position;
        //campos.x = (AllVariables.BackWheel.getPosition().x)*AllVariables.PPM;
        //campos.y = (AllVariables.BackWheel.getPosition().y)*AllVariables.PPM;
        //cam.position.set(campos);

        if (!CamfollowCycle)
            cam.position.set(camposX, 90, cam.position.z);
        else
            cam.position.set((AllVariables.BackWheel.getPosition().x) * AllVariables.PPM, 90f, cam.position.z);

        if (startAnimToMoveCycle){
            if(camposX - 146 >= (AllVariables.BackWheel.getPosition().x) * AllVariables.PPM  -40 &&
                    camposX - 146 <= (AllVariables.BackWheel.getPosition().x) * AllVariables.PPM+40 ){
                start.setAlpha(0);
                chooseBody.setAlpha(0);
                HardMoveShapes.setAlpha(0);
                ShapeRotACW.setAlpha(0);
                ShapeRotCW.setAlpha(0);
                toDrawDropAnyShapeButton = false;
                CamScroller.setAlpha(0);
                startBool = true;

                world.setGravity(new Vector2(0,-10));
                Brake.setAlpha(0.4f);
                playAreaUtils.MoveShapesToRealWorld();
                VariablesForPlayArea.shapeNumberSelected = 15;
                CamfollowCycle = true;
                startAnimToMoveCycle = false;
            }else{
                if(camposX - 136 > (AllVariables.BackWheel.getPosition().x) * AllVariables.PPM)
                    camposX -=40;
                if(camposX - 136 < (AllVariables.BackWheel.getPosition().x) * AllVariables.PPM)
                    camposX +=40;

            }
        }
        //cam.position.set(camposX, cam.position.y, cam.position.z);
        cam.update();

        //System.out.println(camposX - 136 + "\t" + (AllVariables.BackWheel.getPosition().x) * AllVariables.PPM);


        tmr.setView(cam);

        //position of sprites
        start.setPosition(-190+(cam.position.x - AllVariables.WIDTH/2), 50+(cam.position.y - AllVariables.HEIGHT/2));
        Brake.setPosition(1200+(cam.position.x - AllVariables.WIDTH/2), 50+(cam.position.y - AllVariables.HEIGHT/2));
        chooseBody.setPosition(1200+(cam.position.x - AllVariables.WIDTH/2), 50+(cam.position.y - AllVariables.HEIGHT/2));
        HardMoveShapes.setPosition(-220+(cam.position.x - AllVariables.WIDTH/2), 540+(cam.position.y -AllVariables.HEIGHT/2));
        CamScroller.setPosition(CamScrollerX+(cam.position.x - AllVariables.WIDTH/2), CamScrollerY+(cam.position.y - AllVariables.HEIGHT/2));
        DropAnyShapeButton.setPosition(-220+(cam.position.x - AllVariables.WIDTH/2), 440+(cam.position.y -AllVariables.HEIGHT/2));

        if (toDrawDropAnyShapeButton){
            ShapeRotACW.setPosition(1420+(cam.position.x - AllVariables.WIDTH/2), 580+(cam.position.y - AllVariables.HEIGHT/2));
            ShapeRotCW.setPosition(1420+(cam.position.x - AllVariables.WIDTH/2), 400+(cam.position.y - AllVariables.HEIGHT/2));

        }

        //cam dragging
        if (isCamScrollerTouched){
            //moved to left
            if(originX - dragged_touchX > Gdx.graphics.getWidth()/24){
                CamScrollerX = 1250;
                CamScrollerY = 755;
                camScrollSize = 50;
                if (hardMove)
                    camposX-=30f;
                else
                    camposX-=10f;
            }
            //moved to right
            else if(dragged_touchX - originX > Gdx.graphics.getWidth()/28){
                CamScrollerX = 1400;
                CamScrollerY = 755;
                camScrollSize = 50;
                if (hardMove)
                    camposX+=30f;
                else
                    camposX+=10f;

            }else{
                CamScrollerX = 1320;
                CamScrollerY = 750;
                camScrollSize = 60;
            }
        }

        //x is init point and y is final point
        if(camposX < VariablesForPlayArea.endPoint.x){
            camposX = VariablesForPlayArea.endPoint.x;
        }
        else if (camposX > VariablesForPlayArea.endPoint.y){
            camposX = VariablesForPlayArea.endPoint.y;
        }

        //reintializing the shape position and rotation
        if (VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.CutOutBodies.size() - 1) {
            VariablesForPlayArea.Sh_pos.set(VariablesForPlayArea.shapeNumberSelected, VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).getPosition());
            VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).setTransform(VariablesForPlayArea.Sh_pos.get(VariablesForPlayArea.shapeNumberSelected), (float) (VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected)* (Math.PI/180)));
        }

        //changing rotation
        if (ACWTouched){
            tempRotForShape = VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected);
            tempRotForShape-=2;
            if(tempRotForShape<=0)
                tempRotForShape = (short) (360 - tempRotForShape);
            VariablesForPlayArea.Angle_Of_Shape.set(VariablesForPlayArea.shapeNumberSelected, tempRotForShape);
        }

        if (CWtouched){
            tempRotForShape = VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected);
            tempRotForShape+=2;
            if(tempRotForShape>=360)
                tempRotForShape = (short) (tempRotForShape - 360);
            VariablesForPlayArea.Angle_Of_Shape.set(VariablesForPlayArea.shapeNumberSelected, tempRotForShape);
        }


        if (toDrawDropAnyShapeButton) {
            //DropAnyShape alpha init
            if (VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.shapes.size() -1) {
                //a shape is selected
                DropAnyShapeButton.setAlpha(1f);
                isAnyShapeSelected = true;
            }
            else {
                //no shape is selected
                DropAnyShapeButton.setAlpha(0.5f);
                isAnyShapeSelected = false;
            }
        }else {
            DropAnyShapeButton.setAlpha(0f);
        }


        //size changer for camScroller
        CamScroller.setSize(camScrollSize*camscl, camScrollSize*camscl);
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

        //System.out.println(isAnyShapeSelected);
        //System.out.println(VariablesForPlayArea.CutOutBodies.get(0).getPosition());


    }

    private void input(float dt){
        Gdx.input.setInputProcessor(
                new InputProcessor() {
                    @Override
                    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                        screenY = Gdx.graphics.getHeight() - screenY;
                        hardmoveFaultResolver = false;
                        //System.out.println(screenX + "\t" + screenY);
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

                        if(!startBool){

                            //harmove
                            if(screenX > (15 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX < (130 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY > 480* AllVariables.inpM && screenY < 595* AllVariables.inpM){
                                hardMove = !hardMove;
                                if (hardMove)
                                    HardMoveShapes.setAlpha(1);
                                else
                                    HardMoveShapes.setAlpha(0.4f);
                                hardmoveFaultResolver = true;
                                return false;
                            }

                            //camScroller
                            if (screenX > (1125 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX < (1190 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY > 650* AllVariables.inpM && screenY < 720* AllVariables.inpM){
                                //System.out.println("omPLan");
                                isCamScrollerTouched = true;
                                CamScroller.setAlpha(0.7f);
                                originX = screenX;
                                originY = screenY;
                                dragged_touchX = screenX;

                            }

                            //Drop any shape resolver
                            if (screenX > (25 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX < (125 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY > 415* AllVariables.inpM && screenY < 470* AllVariables.inpM){
                                VariablesForPlayArea.shapeNumberSelected = 15;
                            }

                            //rotation of shapes
                            if (isAnyShapeSelected) {
                                //Anti Clock Wise
                                if (screenX > (1200 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX < (1270 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY > 515 * AllVariables.inpM && screenY < 590 * AllVariables.inpM) {

                                    ACWTouched = true;
                                }

                                //for Clock Wise
                                if (screenX > (1200 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX < (1270 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY > 390 * AllVariables.inpM && screenY < 460 * AllVariables.inpM) {

                                    CWtouched = true;
                                }
                            }

                        }


                        if (screenX > (45* AllVariables.inpM)+AllVariables.witdth_translation
                                && screenX < (200* AllVariables.inpM)+AllVariables.witdth_translation
                                && screenY > 140* AllVariables.inpM && screenY < 290* AllVariables.inpM) {


                        }else if (screenX > (1040 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (1230 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 140* AllVariables.inpM && screenY < 290* AllVariables.inpM) {

                        }else if (screenX > (1115 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (1195 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 635* AllVariables.inpM && screenY < 705* AllVariables.inpM) {

                        }else if (screenX > (1200 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (1270 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 390 * AllVariables.inpM && screenY < 460 * AllVariables.inpM){

                        } else if (screenX > (1200 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (1270 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 515 * AllVariables.inpM && screenY < 590 * AllVariables.inpM){

                        }else if (isCamScrollerTouched || ACWTouched || CWtouched){

                        } else {
                            if (VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.CutOutBodies.size() - 1) {
                                if (hardMove) {
                                    VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).setTransform(
                                            ((((screenX - AllVariables.witdth_translation) / AllVariables.inpM) * camscl + (cam.position.x - AllVariables.WIDTH / 2)) / AllVariables.PPM)- VariablesForPlayArea.BigSqurePoints[0][0]/(2)/ AllVariables.PPM,
                                            (((screenY / AllVariables.inpM) * camscl - 200 + (cam.position.y - AllVariables.HEIGHT / 2)) / AllVariables.PPM)+ VariablesForPlayArea.BigSqurePoints[12][1]/(2)/ AllVariables.PPM,
                                            (float) (VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected)*(Math.PI/180)));
                                    return false;

                                } else {
                                    originX = screenX;
                                    originY = screenY;
                                    shapeX = VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).getPosition().x;
                                    shapeY = VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).getPosition().y;
                                    return false;
                                }
                            }
                        }


                        return false;
                    }

                    @Override
                    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                        screenY = Gdx.graphics.getHeight() - screenY;


                        if (brakeBool) {
                            Brake.setAlpha(0.4f);
                            brakeBool = false;
                        }

                        if(isCamScrollerTouched) {
                            CamScrollerX = 1320;
                            CamScrollerY = 750;
                            camScrollSize = 60;
                        }

                        if (isCamScrollerTouched) {
                            CamScroller.setAlpha(1f);
                            isCamScrollerTouched = false;
                        }

                        if (ACWTouched)
                            ACWTouched = false;

                        if (CWtouched)
                            CWtouched = false;


                        if (!startBool){
                            //for start
                            if (screenX > (45* AllVariables.inpM)+AllVariables.witdth_translation
                                    && screenX < (200* AllVariables.inpM)+AllVariables.witdth_translation
                                    && screenY > 140* AllVariables.inpM && screenY < 290* AllVariables.inpM) {

                                startAnimToMoveCycle = true;
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
                        }


                        return false;
                    }

                    @Override
                    public boolean touchDragged(int screenX, int screenY, int pointer) {
                        screenY = Gdx.graphics.getHeight() - screenY;

                        //this else is placed to prevent drag to happen for cam when touched on cam buttin and if not then the shape drag will happen

                        if (isCamScrollerTouched){
                            dragged_touchX = screenX;

                        }else {
                            if (!hardmoveFaultResolver) {
                                if (!hardMove) {
                                    if (VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.CutOutBodies.size() - 1) {
                                        if (!hardMove) {
                                            if(!ACWTouched && !CWtouched) {
                                                VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).setTransform(((shapeX * AllVariables.PPM) + (screenX - originX)) / 100,
                                                        ((shapeY * AllVariables.PPM) + (screenY - originY)) / 100,
                                                        (float) (VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected) * (Math.PI / 180)));
                                            }
                                        }
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
