package com.kunal.PlayGround.Tutorial.InGameTutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.AreaSelection.levelNumberSelection.LevelNumberSelection;
import com.kunal.MainGame;
import com.kunal.PlayGround.LevelsObstacles.DirectionReverse.DirectionReverse;
import com.kunal.PlayGround.LevelsObstacles.Jumper.Jumper;
import com.kunal.PlayGround.LevelsObstacles.flappyBirdPipes.flappyBirdPipes;
import com.kunal.PlayGround.LevelsObstacles.fullSawThatRoams.FullSaw;
import com.kunal.PlayGround.LevelsObstacles.halfSaw.HalfSaw;
import com.kunal.PlayGround.LevelsObstacles.speedController.SpeedController;
import com.kunal.PlayGround.ObjectCreation;
import com.kunal.PlayGround.PlayAreaUtils;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.PlayGround.constScreen.ShapeChooser;
import com.kunal.PlayGround.constScreen.levelUpScreen.LevelCompleted;
import com.kunal.PlayGround.powerUpInInventory.PowerUpMngr;
import com.kunal.utils.BodyGenerator;
import com.kunal.utils.ReDirectToTheLevel;
import com.kunal.utils.TextureGiver;
import com.kunal.utils.TiledMapLoadingHelper;
import com.kunal.utils.slideShow.simpleSlideShow;

import java.util.Random;

public class BasicTutorial implements Screen {
    MainGame game;

    private World world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera cam;
    private Viewport port;

    BitmapFont Font, TutorialFont;

    private Sprite Brake, start, chooseBody, MoveToDustBin, CamScroller, DropAnyShapeButton, ShapeRotACW, ShapeRotCW,
            per45degRot, pause, fadedBG, resume, exit, flag,coin1,coin2,coin3, retryWhenStarted, ZoomOutCam;
    private Boolean brakeBool = false, startBool = false,/* todrawMoveToDustBin = true, MoveToDustBinBoolFaultResolver = false,*/
            isCamScrollerTouched = false, toDrawDropAnyShapeButton = true, isAnyShapeSelected = false,
            ACWTouched = false, CWtouched = false, paused = false, coin1anim = false,
            coin2anim = false, coin3anim= false, powerUpSelected = false, ZoomOutBool = false, levelCompleteCAmMove;

    private float coin1Alpha = 0, coin2Alpha = 0,coin3Alpha = 0;

    //CamScroller
    private short CamScrollerX = 1020, CamScrollerY = 710;
    private byte camScrollSize = 100;
    private ShapeRenderer sred;

    private ObjectCreation objectCreation;

    private int dragged_touchX =0;

    //temp Rotation Folder for shapes
    float tempRotForShape;

    //tiled map
    private TiledMap map;
    private OrthogonalTiledMapRenderer tmr;

    private float ver[];

    float camscl = 1.4f;

    int originX, originY;
    float shapeX, shapeY;

    Polygon poly;

    //follow cycle if start is pressed
    private boolean CamfollowCycle = false, startAnimToMoveCycle = false, finalvalofcamcontroller = false;

    //posision mapper sprite
    Sprite posMap;

    //obstables

    //bicycle maleup
    private Sprite frontTyre, backtyre, rod1, rod2, rod3, rod4, rod5, rod6, handle, seat, bg1, bg2;

    private Texture camScrollerBG;

    private Boolean[] bgRandNumber = new Boolean[15];

    //================================================================
    //tutorial related stuff
    long timestamp;
    float tut_SizeVal= 0;
    boolean tut_isInc = true;
    //----------------------------------------------------------------


    public BasicTutorial(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH*camscl, AllVariables.HEIGHT*camscl, cam);

        port.apply();

        world = new World(new Vector2(0,0f), false);

        b2dr = new Box2DDebugRenderer();

        sred = new ShapeRenderer();

        objectCreation = new ObjectCreation();

        objectCreation.CreateBicycle(world, 600,0);
        objectCreation.CreateCutouts(world);

        levelCompleteCAmMove = false;

        poly = new Polygon();

        Font = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter prams = new FreeTypeFontGenerator.FreeTypeFontParameter();
        prams.size = 50;
        prams.color = Color.BLUE;
        Font = generator.generateFont(prams);

        TutorialFont = new BitmapFont();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font2.ttf"));
        prams = new FreeTypeFontGenerator.FreeTypeFontParameter();
        prams.size = 60;
        prams.color = Color.RED;
        TutorialFont = generator.generateFont(prams);



        //for testing
        //AllVariables.PresentAreaNumber = 101;
        //AllVariables.PresentLevelNumber = 101;
        //for testing

        //safelt platforn for all objects
        BodyGenerator.BodyAssemble(world, true, "Land", new Vector2(640, -9000),
                new Vector2(2000, 50), 1,1, AllVariables.Bit_land,
                (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool|AllVariables.Bit_land));


        //tiled map
        map = new TmxMapLoader().load(VariablesForPlayArea.LevelMapToBeLoaded);
        tmr = new OrthogonalTiledMapRenderer(map);
        for (int parseObjI = 1; parseObjI <= TiledMapLoadingHelper.NumberOfObj(); parseObjI++)
            PlayAreaUtils.parseTiledObj(world,map.getLayers().get("Object Layer "+parseObjI).getObjects());

        //cam.position.set(port.getWorldWidth()/2, port.getWorldHeight()/2,0);

        //background
        bg1 = new Sprite(new Texture(Gdx.files.internal("backGround/colored_grass.png")));
        bg2 = new Sprite(new Texture(Gdx.files.internal("backGround/colored_land.png")));
        //bg1.setAlpha(0.5f);

        //bg2.setAlpha(0.5f);

        Brake = new Sprite(new Texture(Gdx.files.internal("playArea/Brake.png")));
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

        MoveToDustBin = new Sprite(new Texture(Gdx.files.internal("playArea/HardMove.png")));
        MoveToDustBin.setPosition(50, 240);
        MoveToDustBin.setSize(100*camscl, 100*camscl);
        MoveToDustBin.setAlpha(1f);

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
        ShapeRotACW.setSize(50*camscl, 50*camscl);
        ShapeRotACW.setAlpha(0.8f);

        per45degRot = new Sprite(new Texture(Gdx.files.internal("playArea/ShapeRotation_per45deg.png")));
        per45degRot.setPosition(50,240);
        per45degRot.setSize(50*camscl, 50*camscl);
        per45degRot.setAlpha(0.8f);


        ShapeRotCW = new Sprite(new Texture(Gdx.files.internal("playArea/ShapeRotation_CW.png")));
        ShapeRotCW.setPosition(50,240);
        ShapeRotCW.setSize(50*camscl, 50*camscl);
        ShapeRotCW.setAlpha(0.8f);

        pause = new Sprite(new Texture(Gdx.files.internal("playArea/pauseTex.png")));
        pause.setPosition(50,240);
        pause.setSize(100*camscl, 100*camscl);
        pause.setAlpha(0.8f);

        camScrollerBG = new Texture(Gdx.files.internal("playArea/CamScrollerBG.png"));

        fadedBG = new Sprite(new Texture(Gdx.files.internal("playArea/fadedBG.png")));
        fadedBG.setPosition(0,0);
        fadedBG.setSize(Gdx.graphics.getWidth()*2f, Gdx.graphics.getHeight()*2);

        resume = new Sprite(new Texture(Gdx.files.internal("playArea/resume.png")));
        resume.setPosition(0,0);
        resume.setSize(250*camscl, 100*camscl);

        exit = new Sprite(new Texture(Gdx.files.internal("playArea/exit.png")));
        exit.setPosition(0,0);
        exit.setSize(250*camscl, 100*camscl);

        flag = new Sprite(new Texture(Gdx.files.internal("playArea/flagRed_up.png")));
        flag.setPosition(TiledMapLoadingHelper.flagpos().x,TiledMapLoadingHelper.flagpos().y);

        posMap = new Sprite(new Texture(Gdx.files.internal("badlogic.png")));
        posMap.setPosition(32,32);
        posMap.setSize(70,70);

        coin1 = new Sprite(TextureGiver.coin((short) (AllVariables.coinType+1)));
        coin1.setPosition(TiledMapLoadingHelper.coin1Pos().x,TiledMapLoadingHelper.coin1Pos().y);
        coin1.setSize(100,100);
        coin1.setAlpha(coin1Alpha);

        coin2 = new Sprite(TextureGiver.coin((short) (AllVariables.coinType+1)));
        coin2.setPosition(TiledMapLoadingHelper.coin2Pos().x,TiledMapLoadingHelper.coin2Pos().y);
        coin2.setSize(100,100);
        coin2.setAlpha(coin2Alpha);

        coin3 = new Sprite(TextureGiver.coin((short) (AllVariables.coinType+1)));
        coin3.setPosition(TiledMapLoadingHelper.coin3Pos().x,TiledMapLoadingHelper.coin3Pos().y);
        coin3.setSize(100,100);
        coin3.setAlpha(coin3Alpha);

        retryWhenStarted = new Sprite(new Texture(Gdx.files.internal("utils/retry.png")));
        retryWhenStarted.setColor(0,0,0,1);
        ZoomOutCam = new Sprite(new Texture(Gdx.files.internal("playArea/Zoomout.png")));
        ZoomOutCam.setSize(100,100);

        //bicycle makeUp

        frontTyre = new Sprite(TextureGiver.tyre((short) (AllVariables.tyreType+1)));
        backtyre = new Sprite(TextureGiver.tyre((short) (AllVariables.tyreType+1)));

        frontTyre.setSize(50,50);
        frontTyre.setOriginCenter();

        backtyre.setSize(50,50);
        backtyre.setOriginCenter();

        rod1= new Sprite(TextureGiver.bars((short) (AllVariables.bodyOfCycle+1)));
        rod2 = new Sprite(TextureGiver.bars((short) (AllVariables.bodyOfCycle+1)));
        rod3 = new Sprite(TextureGiver.bars((short) (AllVariables.bodyOfCycle+1)));
        rod4 = new Sprite(TextureGiver.bars((short) (AllVariables.bodyOfCycle+1)));
        rod5 = new Sprite(TextureGiver.bars((short) (AllVariables.bodyOfCycle+1)));
        rod6 = new Sprite(TextureGiver.bars((short) (AllVariables.bodyOfCycle+1)));

        rod1.setSize(6,50);
        rod1.setOriginCenter();


        rod2.setSize(6,65);
        rod2.setOriginCenter();

        rod3.setSize(6,60);
        rod3.setOriginCenter();

        rod4.setSize(6,43);
        rod4.setOriginCenter();

        rod5.setSize(6,66);
        rod5.setOriginCenter();

        rod6.setSize(6,70);
        rod6.setOriginCenter();

        handle = new Sprite(new Texture(Gdx.files.internal("playArea/BicycleMakeUp/handle.png")));
        handle.setSize(10,10);
        handle.setOriginCenter();

        seat = new Sprite(new Texture(Gdx.files.internal("playArea/BicycleMakeUp/seat.png")));
        seat.setSize(22,10);
        seat.setOriginCenter();

        //--------------------------------------------------------------------------------





        //to check if any power is selected or not
        if (VariablesForPlayArea.shapeNumberSelected > VariablesForPlayArea.CutOutBodies.size() - 1 &&
                VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.powerUpList.size() + (VariablesForPlayArea.CutOutBodies.size()-1)){
            powerUpSelected = true;
        }
        //pos remapping
        //for (int i =0; i<VariablesForPlayArea.CutOutBodies.size(); i++){
        //VariablesForPlayArea.CutOutBodies.get(i).setTransform(VariablesForPlayArea.Sh_pos.get(i), VariablesForPlayArea.CutOutBodies.get(i).getAngle());
        //}


        //random bg
        Random r = new Random();
        for (int i =0; i< 15; i++)
            bgRandNumber[i] = r.nextBoolean();

        VariablesForPlayArea.starsGained = 0;

        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
    }

    @Override
    public void show() {
        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;

        timestamp = System.currentTimeMillis();
    }

    @Override
    public void render(float dt) {
        //Gdx.gl.glClearColor(.7f, 0.7f, .9f, 1);
        //Gdx.gl.glClearColor(0.764f,0.925f,0.937f,0.9f);
        Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1f);
        //Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (!VariablesForPlayArea.gameOver)
            update(dt);
        //tutorial
        tutorialUpdate();

        input(dt);



        b2dr.render(world, cam.combined.scl(AllVariables.PPM));
        //cam.combined.scl(AllVariables.PPM);
        //need to fix this

        sred.setProjectionMatrix(cam.combined.scl(1/100f));

        //bg
        sred.begin(ShapeRenderer.ShapeType.Filled);
        sred.setColor(.8117f, .9529f, .9647f, 1f);
        sred.rect(-1300*4,1236,20000,1900);

        sred.setColor(.6235294118f, .8549019608f, .26666667f, 1f);
        sred.rect(-1300*4,-2000,20000,2550);
        sred.end();

        AllVariables.batch.begin();
        for (int i =0, xbg =-1300*4 ; i< 15; i++, xbg+=1024) {
            if(bgRandNumber[i]) {
                bg1.setPosition(xbg, 512);
                bg1.draw(AllVariables.batch);
            }
            else {
                bg2.setPosition(xbg, 512);
                bg2.draw(AllVariables.batch);
            }
            if (xbg==-80){
                i = 0;
            }
        }


        //things at bg of the of the tiled map goes here
        AllVariables.batch.end();

        tmr.render();

        sred.begin(ShapeRenderer.ShapeType.Line);

        sred.setColor(0, 0f, 0, 1);
        //sred.setColor(.1f,.1f,.1f,1);

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

            poly.setRotation(VariablesForPlayArea.Angle_Of_Shape.get(i));
            poly.dirty();
            sred.polygon(poly.getTransformedVertices());


            ver = null;

        }


        sred.end();

        //exit Tutorial
        sred.begin(ShapeRenderer.ShapeType.Filled);
        sred.rect(-260+(cam.position.x - AllVariables.WIDTH/2), -145+(cam.position.y - AllVariables.HEIGHT/2), 500,100);
        sred.end();


        AllVariables.batch.setProjectionMatrix(cam.combined);

        AllVariables.batch.begin();
        //bicycle
        frontTyre.draw(AllVariables.batch);
        backtyre.draw(AllVariables.batch);
        rod1.draw(AllVariables.batch);
        rod2.draw(AllVariables.batch);
        rod3.draw(AllVariables.batch);
        rod4.draw(AllVariables.batch);
        rod5.draw(AllVariables.batch);
        rod6.draw(AllVariables.batch);
        handle.draw(AllVariables.batch);
        seat.draw(AllVariables.batch);


        //---------------------

        if (startBool && !VariablesForPlayArea.gameOver) {
            retryWhenStarted.draw(AllVariables.batch);
            ZoomOutCam.draw(AllVariables.batch);
            Brake.draw(AllVariables.batch);
        }
        else if(VariablesForPlayArea.gameOver){}
        else {
            start.draw(AllVariables.batch);
            chooseBody.draw(AllVariables.batch);
            MoveToDustBin.draw(AllVariables.batch);
            AllVariables.batch.draw(camScrollerBG, 1020 - 68 + (cam.position.x - AllVariables.WIDTH / 2), 710 + 55 + (cam.position.y - AllVariables.HEIGHT / 2));
            CamScroller.draw(AllVariables.batch);
            DropAnyShapeButton.draw(AllVariables.batch);
            pause.draw(AllVariables.batch);
        }
        flag.draw(AllVariables.batch);
        if(isAnyShapeSelected){
            ShapeRotACW.draw(AllVariables.batch);
            ShapeRotCW.draw(AllVariables.batch);
            per45degRot.draw(AllVariables.batch);
        }

        posMap.draw(AllVariables.batch);
        //coin
        coin1.draw(AllVariables.batch);
        coin2.draw(AllVariables.batch);
        coin3.draw(AllVariables.batch);

        //this should be at last
        if (paused) {
            fadedBG.draw(AllVariables.batch);
            resume.draw(AllVariables.batch);
            exit.draw(AllVariables.batch);
        }

        Font.draw(AllVariables.batch,AllVariables.PresentAreaNumber+"-"+AllVariables.PresentLevelNumber,
                1400+(cam.position.x - AllVariables.WIDTH/2), 850+(cam.position.y -AllVariables.HEIGHT/2));

        Font.draw(AllVariables.batch, "Exit Tutorial", -230+(cam.position.x - AllVariables.WIDTH/2), -80+(cam.position.y - AllVariables.HEIGHT/2));

        TutorialFont.draw(AllVariables.batch, TutorailHelper.msgOnScreen(),
                TutorailHelper.cord().x+(cam.position.x - AllVariables.WIDTH/2),
                TutorailHelper.cord().y+(cam.position.y - AllVariables.HEIGHT/2));
        AllVariables.batch.end();

    }


    private void update(float dt){
        // System.out.println(AllVariables.BackWheel.getLinearVelocity().x);
        //if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        //world.step((1)/(1/dt), 6,2);


        //all collectable code here=========================================================================
        // //meaning dynamic obj

        //this statement here because it will always be false and the program will not went in and this save some sweet time
        //flag
        if ((AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.flagpos().x-5 &&
                (AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)-(25+100+5)<=TiledMapLoadingHelper.flagpos().x-5){
            if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.flagpos().y-5 ||
                    (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.flagpos().y-5) {
                if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.flagpos().y+60 ||
                        (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.flagpos().y+60) {
                    flag.setTexture(new Texture(Gdx.files.internal("playArea/flagRed_down.png")));
                    flag.setSize(flag.getTexture().getWidth(), flag.getTexture().getHeight());
                    levelCompleteCAmMove = true;
                }
            }
        }

        //coin1
        if ((AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin1Pos().x-5 &&
                (AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)-(25+100+5)<=TiledMapLoadingHelper.coin1Pos().x){
            if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin1Pos().y-5 ||
                    (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin1Pos().y-5) {
                if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.coin1Pos().y+100 ||
                        (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.coin1Pos().y+100) {
                    if (!coin1anim)
                        VariablesForPlayArea.starsGained++;
                    coin1anim = true;
                }
            }
        }


        //coin2
        if ((AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin2Pos().x-5 &&
                (AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)-(25+100+5)<=TiledMapLoadingHelper.coin2Pos().x){
            if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin2Pos().y-5 ||
                    (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin2Pos().y-5) {
                if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.coin2Pos().y+100 ||
                        (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.coin2Pos().y+100) {
                    if (!coin2anim)
                        VariablesForPlayArea.starsGained++;
                    coin2anim = true;
                }
            }
        }


        //coin3
        if ((AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin3Pos().x-5 &&
                (AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)-(25+100+5)<=TiledMapLoadingHelper.coin3Pos().x){
            if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin3Pos().y-5 ||
                    (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin3Pos().y-5) {
                if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.coin3Pos().y+100 ||
                        (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.coin3Pos().y+100) {
                    if (!coin3anim)
                        VariablesForPlayArea.starsGained++;
                    coin3anim = true;
                }
            }
        }


        //System.out.println(((AllVariables.BackWheel.getPosition().y * AllVariables.PPM)));


        //if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        world.step(1/(1/dt), 6,2);

        //ends here

        //Vector3 campos = cam.position;
        //campos.x = (AllVariables.BackWheel.getPosition().x)*AllVariables.PPM;
        //campos.y = (AllVariables.BackWheel.getPosition().y)*AllVariables.PPM;
        //cam.position.set(campos);

        //all animations---------------------------------------------------------------------------
        if (coin1anim){
            if (coin1.getScaleX()<=2.5f)
                coin1.scale(0.5f);
            if (coin1Alpha <=1) {
                coin1Alpha += 0.1f;
                coin1.translate(0,60);
            }
        }
        if (coin2anim){
            if (coin2.getScaleX()<=2.5f)
                coin2.scale(0.5f);
            if (coin2Alpha <=1) {
                coin2Alpha += 0.1f;
                coin2.translate(0,60);
            }
        }
        if (coin3anim){
            if (coin3.getScaleX()<=2.5f)
                coin3.scale(0.5f);
            if (coin3Alpha <=1) {
                coin3Alpha += 0.1f;
                coin3.translate(0,60);
            }
        }



        //==-------------------------------------------------------------------------------------


        //System.out.println(cam.position.x);

        coin1.setAlpha(1-coin1Alpha);
        coin2.setAlpha(1-coin2Alpha);
        coin3.setAlpha(1-coin3Alpha);

        if (!CamfollowCycle)
            cam.position.set(VariablesForPlayArea.camposX, 600, cam.position.z);
        else {
            VariablesForPlayArea.camposX = (AllVariables.BackWheel.getPosition().x) * AllVariables.PPM;
            cam.position.set(VariablesForPlayArea.camposX, 600f, cam.position.z);
            if (cam.position.x<-4000)
                VariablesForPlayArea.camposX = -4000;
            cam.position.set(VariablesForPlayArea.camposX, 600f, cam.position.z);
        }

        if (VariablesForPlayArea.camposX >= VariablesForPlayArea.endPoint.y)
            CamfollowCycle = false;

        //start
        if (startAnimToMoveCycle){
            if(VariablesForPlayArea.camposX - 156 >= (AllVariables.BackWheel.getPosition().x) * AllVariables.PPM  -25 &&
                    VariablesForPlayArea.camposX - 156 <= (AllVariables.BackWheel.getPosition().x) * AllVariables.PPM+25 ){
                start.setAlpha(0);
                chooseBody.setAlpha(0);
                MoveToDustBin.setAlpha(0);
                ShapeRotACW.setAlpha(0);
                ShapeRotCW.setAlpha(0);
                per45degRot.setAlpha(0);
                toDrawDropAnyShapeButton = false;
                CamScroller.setAlpha(0);
                startBool = true;
                pause.setAlpha(0);

                world.setGravity(new Vector2(0,-10));
                Brake.setAlpha(0.4f);
                PlayAreaUtils.MoveShapesToRealWorld();
                VariablesForPlayArea.shapeNumberSelected = 21;
                CamfollowCycle = true;
                startAnimToMoveCycle = false;
                finalvalofcamcontroller = true;


            }else{
                if(VariablesForPlayArea.camposX - 136 > (AllVariables.BackWheel.getPosition().x) * AllVariables.PPM)
                    VariablesForPlayArea.camposX -=40;
                if(VariablesForPlayArea.camposX - 136 < (AllVariables.BackWheel.getPosition().x) * AllVariables.PPM)
                    VariablesForPlayArea.camposX +=40;
            }
        }

        cam.update();

        //bicycle texture update
        makeupOfCycle();

        tmr.setView(cam);

        //position of sprites
        start.setPosition(-190+(cam.position.x - AllVariables.WIDTH/2), 50+(cam.position.y - AllVariables.HEIGHT/2));
        Brake.setPosition(1200+(cam.position.x - AllVariables.WIDTH/2), 50+(cam.position.y - AllVariables.HEIGHT/2));
        chooseBody.setPosition(1200+(cam.position.x - AllVariables.WIDTH/2), 50+(cam.position.y - AllVariables.HEIGHT/2));
        MoveToDustBin.setPosition(-220+(cam.position.x - AllVariables.WIDTH/2), 540+(cam.position.y -AllVariables.HEIGHT/2));
        CamScroller.setPosition(CamScrollerX+(cam.position.x - AllVariables.WIDTH/2), CamScrollerY+(cam.position.y - AllVariables.HEIGHT/2));
        DropAnyShapeButton.setPosition(-220+(cam.position.x - AllVariables.WIDTH/2), 440+(cam.position.y -AllVariables.HEIGHT/2));
        pause.setPosition(-220+(cam.position.x - AllVariables.WIDTH/2), 700+(cam.position.y -AllVariables.HEIGHT/2));
        fadedBG.setPosition(-280+(cam.position.x - AllVariables.WIDTH/2), -200+(cam.position.y -AllVariables.HEIGHT/2));
        resume.setPosition(220+(cam.position.x - AllVariables.WIDTH/2), 600+(cam.position.y -AllVariables.HEIGHT/2));
        exit.setPosition(730+(cam.position.x - AllVariables.WIDTH/2), 600+(cam.position.y -AllVariables.HEIGHT/2));
        //when started retry
        retryWhenStarted.setPosition(-200+(cam.position.x - AllVariables.WIDTH/2), 700+(cam.position.y -AllVariables.HEIGHT/2));
        ZoomOutCam.setPosition(-170+(cam.position.x - (AllVariables.WIDTH)/2), 100+(cam.position.y - AllVariables.HEIGHT/2));

        //System.out.println(AllVariables.BackWheel.getLinearVelocity().x);

        //-------------------------------
        if (AllVariables.PresentLevelNumber >= 6)
            if (AllVariables.BackWheel.getLinearVelocity().x >= 17f) {
                if (AllVariables.BackWheel.getLinearVelocity().x>20.5f)
                    AllVariables.BackWheel.setLinearVelocity((AllVariables.BackWheel.getLinearVelocity().x - AllVariables.BackWheel.getLinearVelocity().x*.04f) ,
                            AllVariables.BackWheel.getLinearVelocity().y);
                else
                    AllVariables.BackWheel.setLinearVelocity((AllVariables.BackWheel.getLinearVelocity().x - AllVariables.BackWheel.getLinearVelocity().x*.02f) ,
                            AllVariables.BackWheel.getLinearVelocity().y);

                if (AllVariables.BackWheel.getLinearVelocity().x >= 18f)
                    VariablesForPlayArea.rageMode = true;
            } else
                VariablesForPlayArea.rageMode = false;


        //to zoom out the cam
        if (!levelCompleteCAmMove) {
            if (ZoomOutBool) {
                if (cam.zoom < 5) {
                    cam.zoom += 0.4f;

                    retryWhenStarted.setAlpha(cam.zoom / 5);
                    ZoomOutCam.setAlpha(cam.zoom / 5);
                    Brake.setAlpha(cam.zoom / 5 * 0.4f);
                } else {
                    cam.zoom = 5;

                    retryWhenStarted.setAlpha(0);
                    ZoomOutCam.setAlpha(0);
                    Brake.setAlpha(0);
                }
            } else {
                if (cam.zoom <= 1) {
                    cam.zoom = 1;

                    retryWhenStarted.setAlpha(1);
                    ZoomOutCam.setAlpha(1);
                    if (startBool) {
                        if (brakeBool)
                            Brake.setAlpha(0.9f);
                        else
                            Brake.setAlpha(0.4f);
                    } else {
                        Brake.setAlpha(0);
                    }
                } else {
                    cam.zoom -= 0.4f;
                    retryWhenStarted.setAlpha(cam.zoom / 5);
                    ZoomOutCam.setAlpha(cam.zoom / 5);
                    Brake.setAlpha(cam.zoom / 5 * 0.4f);
                }
            }
        }


        if (toDrawDropAnyShapeButton){
            ShapeRotACW.setPosition(1420+(cam.position.x - AllVariables.WIDTH/2), 370+(cam.position.y - AllVariables.HEIGHT/2));
            ShapeRotCW.setPosition(1420+(cam.position.x - AllVariables.WIDTH/2), 610+(cam.position.y - AllVariables.HEIGHT/2));
            per45degRot.setPosition(1420+(cam.position.x - AllVariables.WIDTH/2), 490+(cam.position.y - AllVariables.HEIGHT/2));

        }

        //cam dragging
        if (isCamScrollerTouched){
            //moved to left
            if(originX - dragged_touchX > Gdx.graphics.getWidth()/24){
                CamScrollerX = 950;
                CamScrollerY = 735;
                camScrollSize = 70;
                VariablesForPlayArea.camposX-=20f;
            }
            //moved to right
            else if(dragged_touchX - originX > Gdx.graphics.getWidth()/28){
                CamScrollerX = 1136;
                CamScrollerY = 735;
                camScrollSize = 70;
                VariablesForPlayArea.camposX+=20f;
            }else{
                CamScrollerX = 1020;
                CamScrollerY = 710;
                camScrollSize = 100;
            }
        }

        if (!finalvalofcamcontroller) {
            //x is init point and y is final point
            if (VariablesForPlayArea.camposX < VariablesForPlayArea.endPoint.x) {
                VariablesForPlayArea.camposX = VariablesForPlayArea.endPoint.x;
            } else if (VariablesForPlayArea.camposX > VariablesForPlayArea.endPoint.y) {
                VariablesForPlayArea.camposX = VariablesForPlayArea.endPoint.y;
            }

        }



        //changing rotation
        if (ACWTouched){
            tempRotForShape = VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected);
            tempRotForShape-=3;
            if(tempRotForShape<=0)
                tempRotForShape = (360 - tempRotForShape);
            VariablesForPlayArea.Angle_Of_Shape.set(VariablesForPlayArea.shapeNumberSelected, tempRotForShape);
        }

        if (CWtouched){
            tempRotForShape = VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected);
            tempRotForShape+=3;
            if(tempRotForShape>=360)
                tempRotForShape = (short) (tempRotForShape - 360);
            VariablesForPlayArea.Angle_Of_Shape.set(VariablesForPlayArea.shapeNumberSelected, tempRotForShape);
        }

        if (toDrawDropAnyShapeButton) {
            //DropAnyShape alpha init
            if (VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.shapes.size() + VariablesForPlayArea.powerUpList.size() -1) {
                //a shape is selected
                DropAnyShapeButton.setAlpha(1f);
                if (!powerUpSelected)
                    isAnyShapeSelected = true;
            }
            else {
                //no shape is selected
                DropAnyShapeButton.setAlpha(0f);
                isAnyShapeSelected = false;
            }
        }else {
            DropAnyShapeButton.setAlpha(0f);
        }

        //reintializing the shape position and rotation
        if (VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.CutOutBodies.size() - 1) {
            VariablesForPlayArea.Sh_pos.set(VariablesForPlayArea.shapeNumberSelected, VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).getPosition());
            VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).setTransform(VariablesForPlayArea.Sh_pos.get(VariablesForPlayArea.shapeNumberSelected), (float) (VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected)* (Math.PI/180)));
        }

        for (int i=0; i<VariablesForPlayArea.CutOutBodies.size(); i++){
            VariablesForPlayArea.Angle_Of_Shape.set(i, (float) (VariablesForPlayArea.CutOutBodies.get(i).getAngle()*(180/Math.PI)));
        }
        //-------------------------------------------

        //size changer for camScroller
        CamScroller.setSize(camScrollSize*camscl, camScrollSize*camscl);
        if (startBool){
            if (brakeBool) {
                //AllVariables.BackWheel.setAngularVelocity(0);
                AllVariables.FrontWheel.setAngularVelocity(AllVariables.BackWheel.getLinearVelocity().x * 50);
                AllVariables.BackWheel.setAngularVelocity(AllVariables.BackWheel.getLinearVelocity().x * 50);
            }else{
                if (AllVariables.BackWheel.getAngularVelocity() > -8)
                    AllVariables.BackWheel.setAngularVelocity(-10);
                else
                    AllVariables.BackWheel.setAngularVelocity(AllVariables.BackWheel.getAngularVelocity()-3);
            }
        }

        //levelUPCam Mover
        if (levelCompleteCAmMove){
            if (cam.zoom>0)
                cam.zoom-=0.1;
            else {
                //this.dispose();
                game.setScreen(new LevelCompleted(game));
            }

        }

        //to make dustbon wotk
        if (VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.CutOutBodies.size() - 1) {
            if (VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).getPosition().y * AllVariables.PPM == -3000) {
                MoveToDustBin.setAlpha(0f);
            } else {
                MoveToDustBin.setAlpha(1);
            }
        }else{
            MoveToDustBin.setAlpha(0);
        }

        //to check if any power is selected or not
        if (VariablesForPlayArea.shapeNumberSelected > VariablesForPlayArea.CutOutBodies.size() - 1 &&
                VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.powerUpList.size() + (VariablesForPlayArea.CutOutBodies.size()-1)){
            powerUpSelected = true;
        }else{
            powerUpSelected = false;
        }

        //obstacles===================

        //System.out.println(isAnyShapeSelected);
        //System.out.println(VariablesForPlayArea.CutOutBodies.get(0).getPosition());


    }

    private void input(float dt){
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            posMap.translate(-5,0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            posMap.translate(0,-5f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            posMap.translate(5,0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            posMap.translate(0,5);
        }
        //System.out.println(posMap.getX()+"\t" + posMap.getY());

        Gdx.input.setInputProcessor(
                new InputProcessor() {
                    @Override
                    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                        screenY = Gdx.graphics.getHeight() - screenY;
                        System.out.println(screenX + "\t" + screenY);

                        if (startBool && !VariablesForPlayArea.gameOver) {
                            //for brake
                            if (screenX > (1040* AllVariables.inpM)+AllVariables.witdth_translation
                                    && screenX < (1230* AllVariables.inpM)+AllVariables.witdth_translation
                                    && screenY > 140*AllVariables.inpM && screenY < 290*AllVariables.inpM
                                    && VariablesForPlayArea.tutState ==202) {
                                Brake.setAlpha(0.9f);
                                brakeBool = true;
                                return true;
                            }

                            //retry
                            if (screenX > (30 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX < (130 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY > 600 * AllVariables.inpM && screenY < 700 * AllVariables.inpM
                                    && VariablesForPlayArea.tutState ==202){
                                dispose();
                                ReDirectToTheLevel.Direct(game, true);
                                return true;
                            }
                            //zoomout
                            if (screenX > (55 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX < (140 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY > 170 * AllVariables.inpM && screenY < 250 * AllVariables.inpM
                                    && VariablesForPlayArea.tutState ==202){
                                ZoomOutBool = true;
                                return false;
                            }
                        }

                        if(!startBool && !startAnimToMoveCycle) {
                            if (!paused) {
                                //cdelete the shape
                                if (screenX > (15 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX < (130 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY > 480 * AllVariables.inpM && screenY < 595 * AllVariables.inpM
                                        && VariablesForPlayArea.tutState ==25) {
                                    if (VariablesForPlayArea.tutState == 25) {
                                        timestamp = System.currentTimeMillis();
                                        VariablesForPlayArea.tutState = 26;
                                    }
                                    /*hardMove = !hardMove;
                                    if (hardMove)
                                        HardMoveShapes.setAlpha(1);
                                    else
                                        HardMoveShapes.setAlpha(0f);*/
                                    if (VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.CutOutBodies.size() - 1){
                                        VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).setTransform(640/AllVariables.PPM, -3000/AllVariables.PPM, (float) (180 * (Math.PI / 180)));
                                        VariablesForPlayArea.shapeNumberSelected = 21;
                                    }
                                    return false;
                                }

                                //camScroller
                                if (screenX > (910 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX < (1010 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY > 600 * AllVariables.inpM && screenY < 710 * AllVariables.inpM
                                        && (VariablesForPlayArea.tutState ==1 || VariablesForPlayArea.tutState >=34)) {
                                    //System.out.println("omPLan");
                                    isCamScrollerTouched = true;
                                    CamScroller.setAlpha(0.9f);
                                    originX = screenX;
                                    originY = screenY;
                                    dragged_touchX = screenX;

                                }

                                //place the shape
                                if (screenX > (25 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX < (125 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY > 415 * AllVariables.inpM && screenY < 470 * AllVariables.inpM
                                        && VariablesForPlayArea.tutState ==39) {
                                    VariablesForPlayArea.tutState++;
                                    timestamp = System.currentTimeMillis();
                                    VariablesForPlayArea.shapeNumberSelected = 21;
                                }

                                //rotation of shapes
                                if (isAnyShapeSelected) {
                                    //for clock Wise
                                    if (screenX > (1190 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenX < (1250 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenY > 535 * AllVariables.inpM && screenY < 585 * AllVariables.inpM
                                            && VariablesForPlayArea.tutState >=31) {
                                        if (VariablesForPlayArea.tutState==31)
                                            VariablesForPlayArea.tutState++;

                                        ACWTouched = true;
                                    }

                                    //for anti Clock Wise
                                    else if (screenX > (1190 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenX < (1250 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenY > 370 * AllVariables.inpM && screenY < 420 * AllVariables.inpM
                                            && VariablesForPlayArea.tutState >=32) {
                                        if (VariablesForPlayArea.tutState == 32)
                                            VariablesForPlayArea.tutState++;

                                        CWtouched = true;
                                    }

                                    //for 45 deg rotation
                                    else if (screenX > (1190 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenX < (1250 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenY > 455 * AllVariables.inpM && screenY < 505 * AllVariables.inpM
                                            && VariablesForPlayArea.tutState >=33) {
                                        if (VariablesForPlayArea.tutState == 33) {
                                            VariablesForPlayArea.tutState++;
                                            timestamp = System.currentTimeMillis();
                                        }

                                        //get the present angle of the shape selected
                                        tempRotForShape = VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected);
                                        if (tempRotForShape % 45 == 0) {
                                            tempRotForShape += 45;
                                            if (tempRotForShape >= 360)
                                                tempRotForShape = (short) (tempRotForShape - 360);
                                            VariablesForPlayArea.Angle_Of_Shape.set(VariablesForPlayArea.shapeNumberSelected, tempRotForShape);
                                        } else {
                                            for (int i =1;true;i++){
                                                if ((int)(tempRotForShape / (45*i)) <= 0){
                                                    tempRotForShape -= tempRotForShape - (45*(i));
                                                    break;
                                                }
                                            }
                                            if (tempRotForShape >= 360)
                                                tempRotForShape = (short) (tempRotForShape - 360);
                                            VariablesForPlayArea.Angle_Of_Shape.set(VariablesForPlayArea.shapeNumberSelected, tempRotForShape);
                                        }

                                    }
                                }
                            }

                            //pause
                            if (screenX > (30 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX < (130 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY > 600 * AllVariables.inpM && screenY < 700 * AllVariables.inpM
                                    && VariablesForPlayArea.tutState ==202) {
                                paused = true;
                                pause.setAlpha(0);
                            }
                        }


                        if (screenX > (45* AllVariables.inpM)+AllVariables.witdth_translation
                                && screenX < (200* AllVariables.inpM)+AllVariables.witdth_translation
                                && screenY > 140* AllVariables.inpM && screenY < 290* AllVariables.inpM) {

                        }else if (screenX > (1040 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (1230 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 140* AllVariables.inpM && screenY < 290* AllVariables.inpM) {
                            //brake
                        } else if (screenX > (1180 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (1270 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 360 * AllVariables.inpM && screenY < 605 * AllVariables.inpM && !powerUpSelected){

                        } else if (screenX > (20 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (150 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 580 * AllVariables.inpM && screenY < 920 * AllVariables.inpM){
                            //hardmove
                        }else if (isCamScrollerTouched || ACWTouched || CWtouched){

                        } else {
                            if (!paused) {
                                if ((VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.CutOutBodies.size() + VariablesForPlayArea.powerUpList.size() - 1)) {
                                    if (powerUpSelected){
                                        VariablesForPlayArea.powerUpList.get(VariablesForPlayArea.shapeNumberSelected - VariablesForPlayArea.CutOutBodies.size()).x = (short) (((((screenX - AllVariables.witdth_translation) / AllVariables.inpM) * camscl + (cam.position.x - AllVariables.WIDTH / 2))) - 250);
                                        VariablesForPlayArea.powerUpList.get(VariablesForPlayArea.shapeNumberSelected - VariablesForPlayArea.CutOutBodies.size()).y = (short) (((screenY / AllVariables.inpM) * camscl - 200 + (cam.position.y - AllVariables.HEIGHT / 2))+20);
                                    }else {
                                        VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).setTransform(
                                                ((((screenX - AllVariables.witdth_translation) / AllVariables.inpM) * camscl + (cam.position.x - AllVariables.WIDTH / 2)) / AllVariables.PPM) - VariablesForPlayArea.BigSqurePoints[0][0] / (2) / AllVariables.PPM,
                                                (((screenY / AllVariables.inpM) * camscl - 200 + (cam.position.y - AllVariables.HEIGHT / 2)) / AllVariables.PPM) + VariablesForPlayArea.BigSqurePoints[12][1] / (2) / AllVariables.PPM,
                                                (float) (VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected) * (Math.PI / 180)));

                                        originX = screenX;
                                        originY = screenY;
                                        shapeX = VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).getPosition().x;
                                        shapeY = VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).getPosition().y;

                                    }
                                    return false;


                                }
                            }
                        }


                        return false;
                    }

                    @Override
                    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                        screenY = Gdx.graphics.getHeight() - screenY;

                        ZoomOutBool = false;

                        //exit tutoial
                        if (screenX > (0* AllVariables.inpM)+AllVariables.witdth_translation
                                && screenX < (355* AllVariables.inpM)+AllVariables.witdth_translation
                                && screenY > 0* AllVariables.inpM && screenY < 70* AllVariables.inpM){
                            dispose();
                            VariablesForPlayArea.flush();
                            game.setScreen(new AreaSelection(game, true));
                        }



                        if (brakeBool) {
                            Brake.setAlpha(0.4f);
                            brakeBool = false;
                        }

                        if(isCamScrollerTouched) {
                            CamScrollerX = 1020;
                            CamScrollerY = 710;
                            camScrollSize = 100;
                        }

                        if (isCamScrollerTouched) {
                            CamScroller.setAlpha(1f);
                            isCamScrollerTouched = false;
                        }

                        if (ACWTouched)
                            ACWTouched = false;

                        if (CWtouched)
                            CWtouched = false;


                        if (!startBool && !paused){
                            //for start
                            if (screenX > (45* AllVariables.inpM)+AllVariables.witdth_translation
                                    && screenX < (200* AllVariables.inpM)+AllVariables.witdth_translation
                                    && screenY > 140* AllVariables.inpM && screenY < 290* AllVariables.inpM
                                    && VariablesForPlayArea.tutState ==41) {
                                VariablesForPlayArea.tutState++;
                                timestamp = System.currentTimeMillis();
                                startAnimToMoveCycle = true;
                                VariablesForPlayArea.endPoint.x = 200;
                                return true;
                            }
                            if (!startAnimToMoveCycle) {
                                //shape chooser
                                if (screenX > (1040 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX < (1230 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY > 140 * AllVariables.inpM && screenY < 290 * AllVariables.inpM
                                        && (VariablesForPlayArea.tutState == 3 || VariablesForPlayArea.tutState == 27)) {
                                    //code to choosing body

                                    if (VariablesForPlayArea.tutState ==3){
                                        VariablesForPlayArea.tutState++;
                                    }

                                    dispose();
                                    game.setScreen(new ShapeChooserTut(game));
                                    return false;
                                }
                            }
                        }

                        if (paused){
                            //resume
                            if (screenX > (335 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX < (595 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY > 530 * AllVariables.inpM && screenY < 635 * AllVariables.inpM
                                    && VariablesForPlayArea.tutState ==202) {
                                paused = false;
                                if (!startBool)
                                    pause.setAlpha(1);

                            }

                            //exit
                            if (screenX > (700 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX < (960 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY > 530 * AllVariables.inpM && screenY < 635 * AllVariables.inpM
                                    && VariablesForPlayArea.tutState ==202) {
                                VariablesForPlayArea.flush();
                                dispose();
                                game.setScreen(new AreaSelection(game,true));
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
                            if (!paused) {
                                if (VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.CutOutBodies.size() - 1) {
                                    if (screenX > (1160 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenX < (1280 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenY > 360 * AllVariables.inpM && screenY < 626 * AllVariables.inpM) {
                                        //rotator
                                    } else {
                                        if (!ACWTouched && !CWtouched) {
                                            //this is to prevent the ghost movement of the shapes
                                            //start
                                            if (screenX > (45 * AllVariables.inpM) + AllVariables.witdth_translation
                                                    && screenX < (200 * AllVariables.inpM) + AllVariables.witdth_translation
                                                    && screenY > 140 * AllVariables.inpM && screenY < 290 * AllVariables.inpM) {
                                            } else {
                                                //shape chooser
                                                if (screenX > (1040 * AllVariables.inpM) + AllVariables.witdth_translation
                                                        && screenX < (1230 * AllVariables.inpM) + AllVariables.witdth_translation
                                                        && screenY > 140 * AllVariables.inpM && screenY < 290 * AllVariables.inpM) {
                                                    // rot per 45 deg
                                                } else {
                                                    if (screenX > (1190 * AllVariables.inpM) + AllVariables.witdth_translation
                                                            && screenX < (1250 * AllVariables.inpM) + AllVariables.witdth_translation
                                                            && screenY > 455 * AllVariables.inpM && screenY < 505 * AllVariables.inpM) {
                                                    } else {
                                                        //dragging statement-------------------------------------------------
                                                        VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).setTransform(((shapeX * AllVariables.PPM) + (screenX - originX)) / 100,
                                                                ((shapeY * AllVariables.PPM) + (screenY - originY)) / 100,
                                                                (float) (VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected) * (Math.PI / 180)));
                                                    }
                                                }
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
                            game.setScreen(new ShapeChooser(game));
                        }
                        if (keycode == Input.Keys.SPACE){
                            ZoomOutBool = true;
                        }
                        if (keycode == Input.Keys.B){
                            game.setScreen(new LevelNumberSelection(game));
                        }
                        if (keycode == Input.Keys.L){
                            levelCompleteCAmMove = true;
                        }


                        if (keycode == Input.Keys.E){
                            System.out.println(posMap.getX() + "\t" + posMap.getY());
                        }

                        if (keycode == Input.Keys.P){
                            VariablesForPlayArea.tutState++;
                        }




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


    private void tutorialUpdate(){

        //System.out.println(cam.position.x);

        notifierToHints();
        if (VariablesForPlayArea.tutState == 0){
            if (timestamp + 2000 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                start.setAlpha(0.3f);
                pause.setAlpha(0.3f);
                chooseBody.setAlpha(0.3f);
                CamScroller.setAlpha(1);
                tut_SizeVal = -0.2f;
                return;
            }
        }else if (VariablesForPlayArea.tutState == 1){
            if (cam.position.x >=5560){
                CamScroller.setAlpha(1);
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                start.setAlpha(0.8f);
                pause.setAlpha(0.8f);
                chooseBody.setAlpha(0.8f);
                CamScroller.setSize(camScrollSize*camscl, camScrollSize*camscl);
                return;
            }
        }else if (VariablesForPlayArea.tutState == 2) {
            if (timestamp + 2000 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                start.setAlpha(0.3f);
                pause.setAlpha(0.3f);
                chooseBody.setAlpha(1f);
                CamScroller.setAlpha(0.3f);
                tut_SizeVal = -0.2f;
                return;
            }
        }
        else if (VariablesForPlayArea.tutState == 21) {
            if (timestamp + 3500 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                return;
            }
        }
        else if (VariablesForPlayArea.tutState == 22) {
            if (timestamp + 3500 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                return;
            }
        }
        else if (VariablesForPlayArea.tutState == 23) {
            if (VariablesForPlayArea.Sh_pos.get(VariablesForPlayArea.shapeNumberSelected).y*AllVariables.PPM != -3000){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
            }
        }
        else if (VariablesForPlayArea.tutState == 24) {
            if (timestamp + 3500 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                start.setAlpha(0.3f);
                pause.setAlpha(0.3f);
                chooseBody.setAlpha(0.3f);
                CamScroller.setAlpha(0.3f);
                MoveToDustBin.setAlpha(1f);
                DropAnyShapeButton.setAlpha(0.3f);
                ShapeRotACW.setAlpha(0.3f);
                ShapeRotCW.setAlpha(0.3f);
                per45degRot.setAlpha(0.3f);
                tut_SizeVal = -0.2f;

                return;
            }
        } else if (VariablesForPlayArea.tutState == 26) {
            if (timestamp + 3000 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                chooseBody.setAlpha(1f);
                tut_SizeVal = -0.2f;
                return;
            }
        }
        else if (VariablesForPlayArea.tutState == 27) {
            if (VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.shapes.size()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
            }
        }
        else if (VariablesForPlayArea.tutState == 28) {
            if (timestamp + 3200 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                return;
            }
        } else if (VariablesForPlayArea.tutState == 29) {
            if (timestamp + 3200 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                return;
            }
        }else if (VariablesForPlayArea.tutState == 30) {
            if (VariablesForPlayArea.Sh_pos.get(VariablesForPlayArea.shapeNumberSelected).y*AllVariables.PPM != -3000){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
            }
        }else if (VariablesForPlayArea.tutState == 34) {
            if (timestamp + 3500 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                return;
            }
        } else if (VariablesForPlayArea.tutState == 35) {
            if (timestamp + 3000 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                tut_SizeVal = -0.2f;
                return;
            }
        } else if (VariablesForPlayArea.tutState == 36) {
            if (timestamp + 3000 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                tut_SizeVal = -0.2f;
                return;
            }
        } else if (VariablesForPlayArea.tutState == 37) {
            if (timestamp + 4000 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                tut_SizeVal = -0.2f;
                return;
            }
        }else if (VariablesForPlayArea.tutState == 38) {
            if (timestamp + 3000 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                start.setAlpha(0.3f);
                pause.setAlpha(0.3f);
                chooseBody.setAlpha(0.3f);
                CamScroller.setAlpha(0.3f);
                MoveToDustBin.setAlpha(0.3f);
                DropAnyShapeButton.setAlpha(1f);
                tut_SizeVal = -0.2f;
                return;
            }
        }else if (VariablesForPlayArea.tutState == 40) {
            if (timestamp + 5000 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                tut_SizeVal = -0.2f;
                start.setAlpha(1);
                return;
            }
        }else if (VariablesForPlayArea.tutState == 42) {
            if (timestamp + 4200 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                tut_SizeVal = -0.2f;
                return;
            }
        }else if (VariablesForPlayArea.tutState == 43) {
            if (timestamp + 4500 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                tut_SizeVal = -0.2f;
                return;
            }
        }else if (VariablesForPlayArea.tutState == 44) {
            if (timestamp + 4500 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                tut_SizeVal = -0.2f;
                return;
            }
        }else if (VariablesForPlayArea.tutState == 45) {
            if (timestamp + 4500 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                tut_SizeVal = -0.2f;
                return;
            }
        }else if (VariablesForPlayArea.tutState == 46) {
            if (timestamp + 4500 < System.currentTimeMillis()){
                VariablesForPlayArea.tutState++;
                timestamp = System.currentTimeMillis();
                tut_SizeVal = -0.2f;
                return;
            }
        }else if (VariablesForPlayArea.tutState == 47) {
            if (Gdx.input.justTouched()){
                VariablesForPlayArea.flush();
                game.setScreen(new AreaSelection(game,true));
            }
        }


    }

    private void notifierToHints() {
        if (VariablesForPlayArea.tutState == 1 || VariablesForPlayArea.tutState == 3 || VariablesForPlayArea.tutState == 25
                ||VariablesForPlayArea.tutState  == 31 || VariablesForPlayArea.tutState == 32 || VariablesForPlayArea.tutState == 33
                || VariablesForPlayArea.tutState == 39 || VariablesForPlayArea.tutState == 41) {
            if (tut_SizeVal <= -0.05f) {
                tut_isInc = true;
                tut_SizeVal = -0.05f;
            } else if (tut_SizeVal >= 0.05f) {
                tut_isInc = false;
                tut_SizeVal = 0.05f;
            }
            if (tut_isInc){
                tut_SizeVal+=0.01f;
            } else {
                tut_SizeVal-=0.01f;
            }

            if (VariablesForPlayArea.tutState == 1) {
                CamScroller.scale(tut_SizeVal);
            }else if (VariablesForPlayArea.tutState == 3){
                chooseBody.scale(tut_SizeVal);
            }else if (VariablesForPlayArea.tutState == 25){
                MoveToDustBin.scale(tut_SizeVal);
            }else if (VariablesForPlayArea.tutState == 31){
                ShapeRotCW.scale(tut_SizeVal);
            }else if (VariablesForPlayArea.tutState == 32){
                ShapeRotACW.scale(tut_SizeVal);
            }else if (VariablesForPlayArea.tutState == 33){
                per45degRot.scale(tut_SizeVal);
            }else if (VariablesForPlayArea.tutState == 39){
                DropAnyShapeButton.scale(tut_SizeVal);
            }else if (VariablesForPlayArea.tutState == 41){
                start.scale(tut_SizeVal);
            }
        }



    }




    private void makeupOfCycle(){
        frontTyre.setPosition(AllVariables.FrontWheel.getPosition().x * AllVariables.PPM - 25,
                AllVariables.FrontWheel.getPosition().y * AllVariables.PPM - 25);
        frontTyre.setRotation((int) (AllVariables.FrontWheel.getAngle() * (180 / Math.PI)));
        if (VariablesForPlayArea.rageMode)
            frontTyre.setSize(70,70);
        else
            frontTyre.setSize(50,50);
        frontTyre.setOriginCenter();



        backtyre.setPosition(AllVariables.BackWheel.getPosition().x * AllVariables.PPM - 25,
                AllVariables.BackWheel.getPosition().y * AllVariables.PPM - 25);
        backtyre.setRotation((int) (AllVariables.BackWheel.getAngle() * (180 / Math.PI)));
        if (VariablesForPlayArea.rageMode)
            backtyre.setSize(70,70);
        else
            backtyre.setSize(50,50);
        backtyre.setOriginCenter();



        //backwheel to the seat rod
        rod3.setPosition(AllVariables.rod3.getPosition().x * AllVariables.PPM-3,
                AllVariables.rod3.getPosition().y * AllVariables.PPM-30);
        rod3.setRotation((int) (AllVariables.rod3.getAngle() * (180 / Math.PI))-90);

        //backwheel to the peedle
        rod4.setPosition(AllVariables.rod4.getPosition().x * AllVariables.PPM-3,
                AllVariables.rod4.getPosition().y * AllVariables.PPM-23);
        rod4.setRotation((int) (AllVariables.rod4.getAngle() * (180 / Math.PI))-90);

        //connect with both rod3 and rod4
        rod5.setPosition(AllVariables.rod5.getPosition().x * AllVariables.PPM-3,
                AllVariables.rod5.getPosition().y * AllVariables.PPM-33);
        rod5.setRotation((int) (AllVariables.rod5.getAngle() * (180 / Math.PI)));

        //connect to rod5 and rod3
        rod1.setPosition(AllVariables.rod1.getPosition().x * AllVariables.PPM-3,
                AllVariables.rod1.getPosition().y * AllVariables.PPM-25);
        rod1.setRotation((int) (AllVariables.rod1.getAngle() * (180 / Math.PI))-90);


        //connect with rod5, rod1 and rod4
        rod2.setPosition(AllVariables.rod2.getPosition().x * AllVariables.PPM-3,
                AllVariables.rod2.getPosition().y * AllVariables.PPM-35);
        rod2.setRotation((int) (AllVariables.rod2.getAngle() * (180 / Math.PI))-90);

        //connect with rod1 and rod2
        rod6.setPosition(AllVariables.rod6.getPosition().x * AllVariables.PPM-3,
                AllVariables.rod6.getPosition().y * AllVariables.PPM-35);
        rod6.setRotation((int) (AllVariables.rod6.getAngle() * (180 / Math.PI)));

        handle.setPosition(AllVariables.handle.getPosition().x * AllVariables.PPM-5,
                AllVariables.handle.getPosition().y * AllVariables.PPM-5);
        handle.setRotation((int) (AllVariables.handle.getAngle() * (180 / Math.PI)));

        seat.setPosition(AllVariables.seat.getPosition().x * AllVariables.PPM-11,
                AllVariables.seat.getPosition().y * AllVariables.PPM-5);
        seat.setRotation((int) (AllVariables.seat.getAngle() * (180 / Math.PI)));


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
    public void dispose() {world.dispose();
        b2dr.dispose();
        Brake.getTexture().dispose();
        start.getTexture().dispose();
        chooseBody.getTexture().dispose();
        MoveToDustBin.getTexture().dispose();
        CamScroller.getTexture().dispose();
        DropAnyShapeButton.getTexture().dispose();
        ShapeRotACW.getTexture().dispose();
        ShapeRotCW.getTexture().dispose();
        per45degRot.getTexture().dispose();
        pause.getTexture().dispose();
        fadedBG.getTexture().dispose();
        resume.getTexture().dispose();
        exit.getTexture().dispose();
        flag.getTexture().dispose();
        coin1.getTexture().dispose();
        coin2.getTexture().dispose();
        retryWhenStarted.getTexture().dispose();
        ZoomOutCam.getTexture().dispose();
        sred.dispose();
        map.dispose();
        tmr.dispose();
        Gdx.input.setInputProcessor(null);
        posMap.getTexture().dispose();
        frontTyre.getTexture().dispose();
        backtyre.getTexture().dispose();
        rod1.getTexture().dispose();
        rod2.getTexture().dispose();
        rod3.getTexture().dispose();
        rod4.getTexture().dispose();
        rod5.getTexture().dispose();
        rod6.getTexture().dispose();
        camScrollerBG.dispose();

    }
}
