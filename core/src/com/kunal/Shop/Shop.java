package com.kunal.Shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AdVideoInterface;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.MainGame;
import com.kunal.PlayGround.ObjectCreation;
import com.kunal.VideoEventListener;
import com.kunal.utils.BodyGenerator;
import com.kunal.utils.TextureGiver;

import java.util.LinkedList;

public class Shop implements Screen, VideoEventListener {

    MainGame game;

    OrthographicCamera cam;
    Viewport port;

    World world;

    FileHandle kusaCoinFile, lockUnlockFile;

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
         |-->powerUps N
         |          |
         |          |--> speedInc N
         |          |
         |          |--> speedDec N
         |          |
         |          |--> jumper N
     */

    private Sprite cancel,CycleBars, msgBoxTex, buttonBg;
    Texture menuKusaCoin, menuBicycle, astic;
    Texture freeKusaCoin, bgTobuy;
    Texture arrowR, arrowL, lock, seat, handle, bgblur;
    //bicycle
    LinkedList<Texture> bicy_wheel, typeOfCoin, bicy_bars;

    AdVideoInterface adVideoInterface = null;

    private byte menuNumber =0, InnerMenuNumber= 0, barCh=0,wheelCh=0,coinCh=0;

    private LinkedList<Short> PriceOfWheel, PriceOfBars, PriceOfCoins;

    private Boolean coinLocked, wheelLocked, barsLocked, drawBgblur = false, cantheyBuy = false, wheelSelected = false;

    private String msg = "";

    BitmapFont bigText;

    short moneyHolder=0;
    long kusaCoin;

    public Shop(MainGame game, Byte mN, Byte Imn) {
        this.game = game;
        //menuNumber = mN;
        //InnerMenuNumber = Imn;
        menuNumber = 2;
        InnerMenuNumber = 1;

        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;

    }

    @Override
    public void show() {
        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;


        barCh = AllVariables.bodyOfCycle;
        coinCh = AllVariables.coinType;
        wheelCh = AllVariables.tyreType;

        PriceOfWheel = new LinkedList<Short>();
        PriceOfWheel.add((short) 500);
        PriceOfWheel.add((short) 800);
        PriceOfWheel.add((short) 1200);
        PriceOfWheel.add((short) 1500);
        PriceOfWheel.add((short) 1800);
        PriceOfWheel.add((short) 2200);

        PriceOfBars = new LinkedList<Short>();
        PriceOfBars.add((short) 500);
        PriceOfBars.add((short) 900);
        PriceOfBars.add((short) 1600);
        PriceOfBars.add((short) 1800);
        PriceOfBars.add((short) 1400);
        PriceOfBars.add((short) 2300);


        PriceOfCoins = new LinkedList<Short>();
        PriceOfCoins.add((short)700);

        kusaCoin = AllVariables.kusaCoin;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        world = new World(new Vector2(0,0f), true);

        ObjectCreation obj = new ObjectCreation();
        obj.CreateBicycle(world, 350,700);

        cancel= new Sprite(new Texture(Gdx.files.internal("utils/hudX.png")));
        cancel.setSize(128,128);
        cancel.setPosition(0, 720-128);

        kusaCoinFile = Gdx.files.local("TextFilesToDelete/kusaCoin");
        lockUnlockFile = Gdx.files.local("TextFilesToDelete/LockUnlock");

        bigText = new BitmapFont();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter prams = new FreeTypeFontGenerator.FreeTypeFontParameter();
        prams.size = 42;
        prams.color = Color.FIREBRICK;
        bigText = generator.generateFont(prams);


        //essential Menus
        menuKusaCoin = new Texture(Gdx.files.internal("utils/kusaCoin.png"));
        menuBicycle = new Texture(Gdx.files.internal("Shop/bicycleMenu.png"));
        astic = new Texture(Gdx.files.internal("utils/astric.png"));
        bgTobuy = new Texture(Gdx.files.internal("Shop/bgToBuy.png"));
        freeKusaCoin = new Texture(Gdx.files.internal("Shop/VideoIcon.png"));
        arrowL= new Texture(Gdx.files.internal("utils/arrowLeft.png"));
        lock = new Texture(Gdx.files.internal("AreaSelection/levelSelection/locked.png"));
        arrowR = new Texture(Gdx.files.internal("utils/arrowRight.png"));
        seat = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/seat.png"));
        handle = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/handle.png"));

        bgblur = new Texture(Gdx.files.internal("playArea/fadedBG.png"));

        buttonBg = new Sprite(new Texture(Gdx.files.internal("Shop/ButtonBG.png")));

        msgBoxTex = new Sprite(new Texture(Gdx.files.internal("Shop/bgFotmsg.png")));
        msgBoxTex.setSize(350,350);
        msgBoxTex.setOriginCenter();

        bicy_wheel = new LinkedList<Texture>();
        bicy_bars = new LinkedList<Texture>();
        typeOfCoin = new LinkedList<Texture>();

        //tyres
        for (int i =1; i<=18; i++){
            bicy_wheel.add(TextureGiver.tyre((short) i));
        }

        for (int i =1; i<=25; i++){
            bicy_bars.add(TextureGiver.bars((short) i));
        }

        for (int i =1; i<=6; i++){
            typeOfCoin.add(TextureGiver.coin((short) i));
        }

        CycleBars = new Sprite(new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar1.png")));
        CycleBars.setTexture(bicy_bars.get(barCh));
        //CycleBars.setOriginCenter();

        try {
            AllVariables.adv.setVideoEventListener(this);
        }catch (Exception e){}


    }

    @Override
    public void render(float delta) {
        update();

        world.step(1/60f, 6,2);

        //Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        //Gdx.gl.glClearColor(0.764f,0.925f,0.937f,0.9f);
        if (!drawBgblur) {
            Gdx.gl.glClearColor(0.6235f, 0.854f, 0.2666f, 0.9f);
        }else{
            Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f);
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        AllVariables.batch.setProjectionMatrix(cam.combined);

        AllVariables.batch.begin();


        cancel.draw(AllVariables.batch);
        if (menuNumber == 1) {
            //menu
            AllVariables.batch.draw(menuKusaCoin, 20, 453, 120, 120);
            AllVariables.batch.draw(menuBicycle, 40, 289, 60, 96);
            AllVariables.batch.draw(astic, 20, 523, 20, 20);


            //=======================================================================
            //1
            //AllVariables.batch.draw(bgTobuy, 300, 440, 200, 150);
            //AllVariables.batch.draw(freeKusaCoin, 350, 500, 100, 100);
            //4
            //AllVariables.batch.draw(bgTobuy, 300, 180, 200, 150);


            //=======================================================================
            //2
            //AllVariables.batch.draw(bgTobuy, 600, 440, 200, 150);

            //5
            //AllVariables.batch.draw(bgTobuy, 600, 180, 200, 150);



            //=======================================================================
            //3
            //AllVariables.batch.draw(bgTobuy, 900, 440, 200, 150);
            //6
            //AllVariables.batch.draw(bgTobuy, 900, 180, 200, 150);

            //kusaCoinForDisplay----------------------------------------------------
            //AllVariables.batch.draw(menuKusaCoin, 330, 400, 50, 50);//1
            //AllVariables.batch.draw(menuKusaCoin, 330, 140, 50, 50);//2
            //AllVariables.batch.draw(menuKusaCoin, 630, 400, 50, 50);//3
            //AllVariables.batch.draw(menuKusaCoin, 630, 140, 50, 50);//4
            //AllVariables.batch.draw(menuKusaCoin, 930, 400, 50, 50);//5
            //AllVariables.batch.draw(menuKusaCoin, 930, 140, 50, 50);//6


            AllVariables.bitmapFont.setColor(Color.WHITE);
            //kusaCoinamount----------------------------------------------------------
            //AllVariables.bitmapFont.draw(AllVariables.batch, ":150", 380, 431);//1
            //AllVariables.bitmapFont.draw(AllVariables.batch, ":200", 380, 171);//2
            //AllVariables.bitmapFont.draw(AllVariables.batch, ":500", 680, 431);//3
            //AllVariables.bitmapFont.draw(AllVariables.batch, ":1000", 680, 171);//4
            //AllVariables.bitmapFont.draw(AllVariables.batch, ":10000", 980, 431);//5
            //AllVariables.bitmapFont.draw(AllVariables.batch, ":8000000", 980, 171);//6





            AllVariables.bitmapFont.setColor(Color.BLACK);
            //prize-------------------------------------------------
            //6
            //AllVariables.bitmapFont.draw(AllVariables.batch, "$250", 970, 260);
            //3
            //AllVariables.bitmapFont.draw(AllVariables.batch, "$100", 970, 520);
            //5
            //AllVariables.bitmapFont.draw(AllVariables.batch, "$200", 670, 260);
            //2
            //AllVariables.bitmapFont.draw(AllVariables.batch, "$50", 670, 520);
            //4
            //AllVariables.bitmapFont.draw(AllVariables.batch, "$250", 370, 260);
            //1
            //AllVariables.bitmapFont.draw(AllVariables.batch, "Rewarded \n           Ad", 320, 500);





        }else if (menuNumber ==2){
            AllVariables.batch.draw(menuKusaCoin, 20, 453, 96, 96);
            AllVariables.batch.draw(menuBicycle, 40, 289, 86, 120);
            AllVariables.batch.draw(astic, 40, 400, 20, 20);

            AllVariables.batch.draw(bicy_wheel.get(AllVariables.tyreType),220,420,70,70);
            AllVariables.batch.draw(typeOfCoin.get(AllVariables.coinType),212,260, 100, 100);

            buttonBg.setColor(1,0f,0f,0.8f);


            if (InnerMenuNumber == 1) {
                AllVariables.batch.draw(astic, 220, 483, 15, 15);

                //AllVariables.batch.draw(bicy_wheel.get(AllVariables.tyreType-1),400,300,70,70);
                //AllVariables.batch.draw(bicy_wheel.get(AllVariables.tyreType-1),400,300,70,70);

                if (wheelCh == AllVariables.tyreType && barCh == AllVariables.bodyOfCycle)
                    buttonBg.setColor(0f,1f,0f,1f);

                //lower Arrow
                AllVariables.batch.draw(arrowL, 300, 110, 100, 100);
                AllVariables.batch.draw(arrowR, 1080, 110, 100, 100);

                //upper Arrow
                AllVariables.batch.draw(arrowL, 300, 550, 100, 100);
                AllVariables.batch.draw(arrowR, 1100, 550, 100, 100);

                AllVariables.bitmapFont.setColor(Color.WHITE);

                //left most
                if (wheelCh != 0) {
                    AllVariables.batch.draw(bicy_wheel.get(wheelCh - 1), 440, 120, 80, 80);
                    if (!AllVariables.unlockedWheel.contains((byte)(wheelCh - 1))) {
                        AllVariables.batch.draw(lock, 440, 120, 80, 80);
                    }
                }
                //mainChoice
                AllVariables.batch.draw(bicy_wheel.get(wheelCh), 580, 120, 140, 140);
                wheelLocked = false;
                if (!AllVariables.unlockedWheel.contains((byte)(wheelCh))) {
                    AllVariables.batch.draw(lock, 580, 120, 140, 140);
                    wheelLocked= true;

                    //pricing
                    AllVariables.batch.draw(menuKusaCoin, 580, 260, 50, 50);

                    if(wheelCh == 1)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfWheel.get(0), 630, 293);
                    else if (wheelCh>=2 && wheelCh<=6)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfWheel.get(1), 630, 293);
                    else if (wheelCh>=7 && wheelCh<=9)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfWheel.get(2), 630, 293);
                    else if (wheelCh>=10 && wheelCh<=12)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfWheel.get(3), 630, 293);
                    else if (wheelCh==13)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfWheel.get(4), 630, 293);
                    else if (wheelCh>=14 && wheelCh<=17)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfWheel.get(5), 630, 293);


                }

                //right
                if (wheelCh <= bicy_wheel.size()-2) {
                    AllVariables.batch.draw(bicy_wheel.get(wheelCh + 1), 790, 120, 80, 80);
                    if (!AllVariables.unlockedWheel.contains((byte)(wheelCh + 1)))
                        AllVariables.batch.draw(lock, 790, 120, 80, 80);
                }
                //right most
                if (wheelCh <= bicy_wheel.size()-3) {
                    AllVariables.batch.draw(bicy_wheel.get(wheelCh + 2), 960, 120, 50, 50);
                    if (!AllVariables.unlockedWheel.contains((byte)(wheelCh +2)))
                        AllVariables.batch.draw(lock, 960, 120, 50, 50);
                }


                //upper PArt For Body

                //left most
                if (barCh != 0) {
                    AllVariables.batch.draw(bicy_bars.get(barCh- 1), 475, 550, 13, 80);
                    if (!AllVariables.unlockedBar.contains((byte)(barCh- 1)))
                        AllVariables.batch.draw(lock, 440, 550, 80, 80);
                }
                //mainChoice
                AllVariables.batch.draw(bicy_bars.get(barCh), 645, 550, 13, 140);
                barsLocked = false;
                if (!AllVariables.unlockedBar.contains((byte)(barCh))) {
                    AllVariables.batch.draw(lock, 580, 550, 140, 140);
                    barsLocked = true;

                    //pricing
                    AllVariables.batch.draw(menuKusaCoin, 580, 500, 50, 50);

                    if (barCh>=1 && barCh<=4)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfBars.get(0), 630, 530);
                    else if (barCh>=5 && barCh<=7)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfBars.get(1), 630, 530);
                    else if (barCh>=8 && barCh<=10)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfBars.get(2), 630, 530);
                    else if (barCh>=11 && barCh <=13)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfBars.get(3), 630, 530);
                    else if (barCh>=14 && barCh<=17)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfBars.get(4), 630, 530);
                    else if (barCh>=18 && barCh<=22)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfBars.get(5), 630, 530);
                    else if (barCh>=22)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfBars.get(3), 630, 530);


                }

                //right
                if (barCh <= bicy_bars.size()-2) {
                    AllVariables.batch.draw(bicy_bars.get(barCh+ 1), 825, 550, 10, 80);
                    if (!AllVariables.unlockedBar.contains((byte)(barCh+ 1)))
                        AllVariables.batch.draw(lock, 790, 550, 80, 80);
                }
                //right most
                if (barCh <= bicy_bars.size()-3) {
                    AllVariables.batch.draw(bicy_bars.get(barCh+ 2), 980, 550, 10, 50);
                    if (!AllVariables.unlockedBar.contains((byte)(barCh+2)))
                        AllVariables.batch.draw(lock, 960, 550, 50, 50);
                }

                AllVariables.batch.draw(bicy_wheel.get(wheelCh),(AllVariables.FrontWheel.getPosition().x*100)-25,(AllVariables.FrontWheel.getPosition().y*100)-25,50,50);
                AllVariables.batch.draw(bicy_wheel.get(wheelCh),(AllVariables.BackWheel.getPosition().x*100)-25,(AllVariables.BackWheel.getPosition().y*100)-25,50,50);


                CycleBars.setSize(6,50);
                CycleBars.setPosition(AllVariables.rod1.getPosition().x * AllVariables.PPM-3,
                        AllVariables.rod1.getPosition().y * AllVariables.PPM-30);
                CycleBars.setRotation((int) (AllVariables.rod1.getAngle() * (180 / Math.PI))-90);
                CycleBars.draw(AllVariables.batch);

                CycleBars.setSize(6,65);
                CycleBars.setPosition(AllVariables.rod2.getPosition().x * AllVariables.PPM-3,
                        AllVariables.rod2.getPosition().y * AllVariables.PPM-35);
                CycleBars.setRotation((int) (AllVariables.rod2.getAngle() * (180 / Math.PI))-90);
                CycleBars.draw(AllVariables.batch);


                CycleBars.setSize(6,60);
                CycleBars.setPosition(AllVariables.rod3.getPosition().x * AllVariables.PPM-3,
                        AllVariables.rod3.getPosition().y * AllVariables.PPM-30);
                CycleBars.setRotation((int) (AllVariables.rod3.getAngle() * (180 / Math.PI))-90);
                CycleBars.draw(AllVariables.batch);


                CycleBars.setSize(6,43);
                CycleBars.setPosition((AllVariables.rod4.getPosition().x * AllVariables.PPM)+3,
                        (AllVariables.rod4.getPosition().y * AllVariables.PPM)-33);
                CycleBars.setRotation((int) (AllVariables.rod4.getAngle() * (180 / Math.PI))-90);
                CycleBars.draw(AllVariables.batch);


                CycleBars.setSize(6,63);
                CycleBars.setPosition(AllVariables.rod5.getPosition().x * AllVariables.PPM-3,
                        AllVariables.rod5.getPosition().y * AllVariables.PPM-33);
                CycleBars.setRotation((int) (AllVariables.rod5.getAngle() * (180 / Math.PI)));
                CycleBars.draw(AllVariables.batch);


                CycleBars.setSize(6,70);
                CycleBars.setPosition(AllVariables.rod6.getPosition().x * AllVariables.PPM-3,
                        AllVariables.rod6.getPosition().y * AllVariables.PPM-35);
                CycleBars.setRotation((int) (AllVariables.rod6.getAngle() * (180 / Math.PI)));
                CycleBars.draw(AllVariables.batch);

                AllVariables.batch.draw(seat,785,425,22,10);
                AllVariables.batch.draw(handle,829,432,10,10);

            }else if (InnerMenuNumber == 2) {
                AllVariables.batch.draw(astic, 220, 330, 15, 15);


                if (coinCh == AllVariables.coinType)
                    buttonBg.setColor(0f,1,0f,1f);


                //lower Arrow
                AllVariables.batch.draw(arrowL, 300, 110, 100, 100);
                AllVariables.batch.draw(arrowR, 1080, 110, 100, 100);


                //left most
                if (coinCh != 0) {
                    AllVariables.batch.draw(typeOfCoin.get(coinCh - 1), 440, 120, 80, 80);
                    if (!AllVariables.unlockedCoin.contains((byte)(coinCh - 1)))
                        AllVariables.batch.draw(lock, 440, 120, 80, 80);
                }
                //mainChoice
                AllVariables.batch.draw(typeOfCoin.get(coinCh ), 580, 120, 140, 140);
                coinLocked = false;
                if (!AllVariables.unlockedCoin.contains((byte)(coinCh ))) {
                    AllVariables.batch.draw(lock, 580, 120, 140, 140);
                    coinLocked = true;

                    //pricing
                    AllVariables.batch.draw(menuKusaCoin, 580, 260, 50, 50);

                    if(coinCh == 1)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfCoins.get(0), 630, 293);
                    else if (coinCh >=2 && coinCh <=6)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfCoins.get(0), 630, 293);
                    else if (coinCh >=7 && coinCh <=9)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfCoins.get(0), 630, 293);
                    else if (coinCh >=10 && coinCh <=12)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfCoins.get(0), 630, 293);
                    else if (coinCh ==13)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfCoins.get(0), 630, 293);
                    else if (coinCh >=14 && coinCh <=17)
                        AllVariables.bitmapFont.draw(AllVariables.batch, ""+PriceOfCoins.get(0), 630, 293);

                }

                //right
                if (coinCh <= typeOfCoin.size()-2) {
                    AllVariables.batch.draw(typeOfCoin.get(coinCh + 1), 790, 120, 80, 80);
                    if (!AllVariables.unlockedCoin.contains((byte)(coinCh + 1)))
                        AllVariables.batch.draw(lock, 790, 120, 80, 80);
                }
                //right most
                if (coinCh <= typeOfCoin.size()-3) {
                    AllVariables.batch.draw(typeOfCoin.get(coinCh + 2), 960, 120, 50, 50);
                    if (!AllVariables.unlockedCoin.contains((byte)(coinCh +2)))
                        AllVariables.batch.draw(lock, 960, 120, 50, 50);
                }

                //main display
                AllVariables.batch.draw(typeOfCoin.get(coinCh), 800, 350, 160, 160);




            }

            // button to confirm
            buttonBg.setPosition(400, 330);
            buttonBg.setSize(130,100);
            buttonBg.draw(AllVariables.batch);
            AllVariables.bitmapFont.draw(AllVariables.batch, " equip", 420, 390);

        }

        AllVariables.bitmapFont.setColor(Color.ORANGE);
        AllVariables.batch.draw(menuKusaCoin, 400, 668, 50, 50);
        AllVariables.bitmapFont.draw(AllVariables.batch, ">"+AllVariables.kusaCoin, 450, 700);

//---------------------------------------------------------------------------------------------------
        if (drawBgblur) {
            AllVariables.batch.draw(bgblur, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            msgBoxTex.setRotation(0);
            msgBoxTex.setFlip(false,false);
            msgBoxTex.setPosition(315,350);
            msgBoxTex.draw(AllVariables.batch);
            msgBoxTex.setFlip(true,false);
            msgBoxTex.setPosition(640,350);
            msgBoxTex.draw(AllVariables.batch);
            msgBoxTex.setFlip(false,false);


            msgBoxTex.setRotation(90);
            msgBoxTex.setPosition(130,177);
            msgBoxTex.draw(AllVariables.batch);

            msgBoxTex.setRotation(-90);
            msgBoxTex.setPosition(815,177);
            msgBoxTex.draw(AllVariables.batch);

            msgBoxTex.setRotation(0);
            msgBoxTex.setPosition(315,0);
            msgBoxTex.setFlip(true,true);
            msgBoxTex.draw(AllVariables.batch);
            msgBoxTex.setFlip(true,true);
            msgBoxTex.setPosition(640,0);
            msgBoxTex.draw(AllVariables.batch);

            bigText.setColor(Color.GOLD);
            bigText.draw(AllVariables.batch,  msg, 345, 450);

            if (!cantheyBuy){
                buttonBg.setColor(0.9f,0f,0f,0.1f);
                buttonBg.setSize(270,200);
                buttonBg.setPosition(490,100);
                buttonBg.draw(AllVariables.batch);

                bigText.setColor(new Color(.9f,0,0,0.3f));
                bigText.draw(AllVariables.batch, "Cancel", 530, 250);


            }else {
                buttonBg.setColor(0f,1f,0f,0.1f);
                buttonBg.setPosition(350,100);
                buttonBg.setSize(270,200);
                buttonBg.draw(AllVariables.batch);

                buttonBg.setColor(0.9f,0f,0f,0.1f);
                buttonBg.setPosition(660,100);
                buttonBg.draw(AllVariables.batch);

                bigText.setColor(new Color(0,1,0,.9f));
                bigText.draw(AllVariables.batch, "Confirm", 370, 250);
                bigText.setColor(new Color(.9f,0,0,0.3f));
                bigText.draw(AllVariables.batch, "Cancel", 700, 250);


            }


        }
        AllVariables.batch.end();

    }

    private void update(){
        input();
        if (kusaCoin != AllVariables.kusaCoin){
            String data = String.valueOf(AllVariables.kusaCoin);
            kusaCoinFile.writeString(data,false);
            kusaCoin = AllVariables.kusaCoin;
        }
    }

    private void input(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            AllVariables.kusaCoin += 100;
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

                        if (drawBgblur) {
                            //drawBgblur = false;

                            if (cantheyBuy){
                                if(screenX >= (265* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX <= (1020* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY >= 165 * AllVariables.inpM
                                        && screenY <= 555 * AllVariables.inpM) {
                                        if (screenX >= (660 * AllVariables.inpM) + AllVariables.witdth_translation
                                                && screenX <= (930 * AllVariables.inpM) + AllVariables.witdth_translation
                                                && screenY >= 125 * AllVariables.inpM
                                                && screenY <= 300 * AllVariables.inpM) {
                                            drawBgblur = false;
                                        }
                                        //they bought it
                                        else if (screenX >= (350 * AllVariables.inpM) + AllVariables.witdth_translation
                                                && screenX <= (620 * AllVariables.inpM) + AllVariables.witdth_translation
                                                && screenY >= 125 * AllVariables.inpM
                                                && screenY <= 300 * AllVariables.inpM){
                                            AllVariables.kusaCoin-=moneyHolder;
                                            if (menuNumber == 2){
                                                if (InnerMenuNumber == 1){
                                                    if (wheelSelected){
                                                        AllVariables.unlockedWheel.add(wheelCh);
                                                    }else {
                                                        AllVariables.unlockedBar.add(barCh);
                                                    }
                                                }else {
                                                    AllVariables.unlockedCoin.add(coinCh);
                                                }
                                            }
                                            writeToFile();
                                            drawBgblur = false;
                                            msg="";

                                        }else {
                                            drawBgblur = false;
                                            msg="";
                                        }
                                    }
                            }else {
                                if(screenX >= (265* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX <= (1020* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY >= 165 * AllVariables.inpM
                                        && screenY <= 555 * AllVariables.inpM) {
                                    if (screenX >= (490 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenX <= (760 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenY >= 120 * AllVariables.inpM
                                            && screenY <= 300 * AllVariables.inpM) {
                                        drawBgblur = false;
                                    }
                                }else {
                                    drawBgblur = false;
                                }
                            }

                            return true;
                        }

                        //cancel
                        if(screenX >= (15* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (140* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 620 * AllVariables.inpM
                                && screenY <= 700 * AllVariables.inpM){
                            dispose();
                            game.setScreen(new AreaSelection(game, true));
                        }

                        //KusaCoin
                        if(screenX >= (15* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (140* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 455 * AllVariables.inpM
                                && screenY <= 560 * AllVariables.inpM){
                            menuNumber = 1;
                            InnerMenuNumber = 1;
                        }

                        //bicycle makeup
                        if(screenX >= (15* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX <= (140* AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY >= 270 * AllVariables.inpM
                                && screenY <= 420 * AllVariables.inpM){

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

                            if (menuNumber != 3) {
                                //menuNumber = 3;
                                //InnerMenuNumber = 1;
                            }
                        }


                        //menu number 1
                        if (menuNumber == 1){

                            //free ad -------1
                            if(screenX >= (300* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX <= (500* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY >= 435 * AllVariables.inpM
                                    && screenY <= 590 * AllVariables.inpM){
                                System.out.println("Ad Will Play");
                                if(AllVariables.adv.hasVideoLoaded()) {
                                    AllVariables.adv.showRewardedVideoAd();
                                }else{
                                    AllVariables.openApps.MakeToast("Can't Load any ad. Try Again Later");
                                }
                                return true;
                            }
                            //2
                            else if(screenX >= (600* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX <= (800* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY >= 435 * AllVariables.inpM
                                    && screenY <= 590 * AllVariables.inpM){
                                System.out.println("2");
                                return true;
                            }
                            //3
                            else if(screenX >= (900* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX <= (1100* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY >= 435 * AllVariables.inpM
                                    && screenY <= 590 * AllVariables.inpM){
                                System.out.println("3");
                                return true;
                            }
                            //4
                            else if(screenX >= (300* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX <= (500* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY >= 180 * AllVariables.inpM
                                    && screenY <= 330 * AllVariables.inpM){
                                System.out.println("4");
                                return true;
                            }
                            //5
                            else if(screenX >= (600* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX <= (800* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY >= 180 * AllVariables.inpM
                                    && screenY <= 330 * AllVariables.inpM){
                                System.out.println("5");
                                return true;
                            }
                            //6
                            else if(screenX >= (900* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX <= (1100* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY >= 180 * AllVariables.inpM
                                    && screenY <= 330 * AllVariables.inpM){
                                System.out.println("6");
                                return true;
                            }
                        }
                        //-----------------------------------------------------------------------------------------
                        else if (menuNumber == 2){
                            if(screenX >= (205* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX <= (310* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY >= 390 * AllVariables.inpM
                                    && screenY <= 505 * AllVariables.inpM){
                                InnerMenuNumber = 1;
                            }else if(screenX >= (205* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX <= (310* AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY >= 260 * AllVariables.inpM
                                    && screenY <= 360 * AllVariables.inpM){
                                InnerMenuNumber=2;
                            }

                            if (InnerMenuNumber == 1){//-----------------------------------------------------------


                                //confirm the choice
                                if(screenX >= (400* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX <= (530* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY >= 325 * AllVariables.inpM
                                        && screenY <= 425 * AllVariables.inpM){
                                    if (!wheelLocked && !barsLocked) {
                                        AllVariables.tyreType = wheelCh;
                                        AllVariables.bodyOfCycle = barCh;
                                        writeToFile();
                                    }
                                }


                                //wheel left
                                if(screenX >= (300* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX <= (400* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY >= 120 * AllVariables.inpM
                                        && screenY <= 215 * AllVariables.inpM){
                                    if (wheelCh >= 1) {
                                        wheelCh--;
                                    }
                                    return true;
                                }
                                //wheel right
                                else if(screenX >= (1075* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX <= (1175* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY >= 120 * AllVariables.inpM
                                        && screenY <= 215 * AllVariables.inpM){
                                    if (wheelCh < bicy_wheel.size()-1)
                                        wheelCh++;
                                    return true;
                                }

                                //upside
                                //wheel left
                                if(screenX >= (300* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX <= (400* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY >= 550 * AllVariables.inpM
                                        && screenY <= 650 * AllVariables.inpM){
                                    if (barCh >= 1) {
                                        barCh--;
                                        CycleBars.setTexture(bicy_bars.get(barCh));
                                    }
                                    return true;
                                }
                                //wheel right
                                else if(screenX >= (1090* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX <= (1190* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY >= 550 * AllVariables.inpM
                                        && screenY <= 650 * AllVariables.inpM){
                                    if (barCh < bicy_bars.size()-1) {
                                        barCh++;
                                        CycleBars.setTexture(bicy_bars.get(barCh));
                                    }
                                    return true;
                                }

                                //for wheel to buy
                                if(screenX >= (570* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX <= (735* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY >= 102 * AllVariables.inpM
                                        && screenY <= 275 * AllVariables.inpM && wheelLocked){

                                    drawBgblur = true;
                                    wheelSelected = true;
                                    if(wheelCh == 1) {
                                        if (AllVariables.kusaCoin >= PriceOfWheel.get(0)){
                                            cantheyBuy = true;
                                            moneyHolder = PriceOfWheel.get(0);
                                        }else {
                                            cantheyBuy = false;
                                        }
                                    }
                                    else if (wheelCh>=2 && wheelCh<=6) {
                                        if (AllVariables.kusaCoin >= PriceOfWheel.get(1)){
                                            cantheyBuy = true;
                                            moneyHolder = PriceOfWheel.get(1);
                                        }else {
                                            cantheyBuy = false;
                                        }
                                    }
                                    else if (wheelCh>=7 && wheelCh<=9) {
                                        if (AllVariables.kusaCoin >= PriceOfWheel.get(2)){
                                            cantheyBuy = true;
                                            moneyHolder = PriceOfWheel.get(2);
                                        }else {
                                            cantheyBuy = false;
                                        }
                                    }
                                    else if (wheelCh>=10 && wheelCh<=12) {
                                        if (AllVariables.kusaCoin >= PriceOfWheel.get(3)){
                                            cantheyBuy = true;
                                            moneyHolder = PriceOfWheel.get(3);
                                        }else {
                                            cantheyBuy = false;
                                        }
                                    }
                                    else if (wheelCh==13) {
                                        if (AllVariables.kusaCoin >= PriceOfWheel.get(4)){
                                            cantheyBuy = true;
                                            moneyHolder = PriceOfWheel.get(4);
                                        }else {
                                            cantheyBuy = false;
                                        }
                                    }
                                    else if (wheelCh>=14 && wheelCh<=17) {
                                        if (AllVariables.kusaCoin >= PriceOfWheel.get(5)){
                                            cantheyBuy = true;
                                            moneyHolder = PriceOfWheel.get(5);
                                        }else {
                                            cantheyBuy = false;
                                        }
                                    }

                                    if (cantheyBuy){
                                        msg = "Confirm This Purchase";
                                    }else {
                                        msg = "You Don't Have Enough \n     KusaCoins";
                                    }

                                    return true;
                                }

                                //for bar to buy
                                if(screenX >= (570* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX <= (735* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY >= 543 * AllVariables.inpM
                                        && screenY <= 700 * AllVariables.inpM && barsLocked){

                                    drawBgblur = true;
                                    wheelSelected = false;

                                    if (barCh>=1 && barCh<=4) {
                                        if (AllVariables.kusaCoin >= PriceOfBars.get(0)) {
                                            cantheyBuy = true;
                                            moneyHolder = PriceOfBars.get(0);
                                        }
                                        else
                                            cantheyBuy = false;
                                    }
                                    else if (barCh>=5 && barCh<=7) {
                                        if (AllVariables.kusaCoin >= PriceOfBars.get(1)) {
                                            cantheyBuy = true;
                                            moneyHolder = PriceOfBars.get(1);
                                        }
                                        else
                                            cantheyBuy = false;
                                    }
                                    else if (barCh>=8 && barCh<=10) {
                                        if (AllVariables.kusaCoin >= PriceOfBars.get(2)) {
                                            cantheyBuy = true;
                                            moneyHolder = PriceOfBars.get(2);
                                        }
                                        else
                                            cantheyBuy = false;
                                    }
                                    else if (barCh>=11 && barCh <=13) {
                                        if (AllVariables.kusaCoin >= PriceOfBars.get(3)) {
                                            cantheyBuy = true;
                                            moneyHolder = PriceOfBars.get(3);
                                        }
                                        else
                                            cantheyBuy = false;
                                    }
                                    else if (barCh>=14 && barCh<=17) {
                                        if (AllVariables.kusaCoin >= PriceOfBars.get(4)) {
                                            cantheyBuy = true;
                                            moneyHolder = PriceOfBars.get(4);
                                        }
                                        else
                                            cantheyBuy = false;
                                    }
                                    else if (barCh>=18 && barCh<=22) {
                                        if (AllVariables.kusaCoin >= PriceOfBars.get(5)) {
                                            cantheyBuy = true;
                                            moneyHolder = PriceOfBars.get(5);
                                        }
                                        else
                                            cantheyBuy = false;
                                    }
                                    else if (barCh>=22) {
                                        if (AllVariables.kusaCoin >= PriceOfBars.get(3)) {
                                            cantheyBuy = true;
                                            moneyHolder = PriceOfBars.get(3);
                                        }
                                        else
                                            cantheyBuy = false;
                                    }

                                    if (cantheyBuy){
                                        msg = "Confirm This Purchase";
                                    }else {
                                        msg = "You Don't Have Enough \n     KusaCoins";
                                    }


                                    return true;
                                }


                            }
                            //----------------------------
                            else if (InnerMenuNumber == 2){


                                //confirm the choice
                                if(screenX >= (400* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX <= (530* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY >= 325 * AllVariables.inpM
                                        && screenY <= 425 * AllVariables.inpM){
                                    if (!coinLocked) {
                                        AllVariables.coinType= coinCh;
                                        writeToFile();
                                    }
                                }

                                //coin left
                                if(screenX >= (300* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX <= (400* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY >= 120 * AllVariables.inpM
                                        && screenY <= 215 * AllVariables.inpM){
                                    if (coinCh>= 1) {
                                        coinCh--;
                                    }
                                    return true;
                                }
                                //coin right
                                else if(screenX >= (1075* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX <= (1175* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY >= 120 * AllVariables.inpM
                                        && screenY <= 215 * AllVariables.inpM){
                                    if (coinCh< typeOfCoin.size()-1)
                                        coinCh++;
                                    return true;
                                }

                                //to buy
                                if(screenX >= (570* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX <= (735* AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY >= 102 * AllVariables.inpM
                                        && screenY <= 275 * AllVariables.inpM && coinLocked){

                                    drawBgblur = true;

                                    if (AllVariables.kusaCoin >= PriceOfCoins.get(0))
                                        cantheyBuy = true;
                                    else
                                        cantheyBuy = false;

                                    moneyHolder = PriceOfCoins.get(0);

                                    if (cantheyBuy){
                                        msg = "Confirm This Purchase";
                                    }else {
                                        msg = "You Don't Have Enough \n     KusaCoins";
                                    }


                                    return true;
                                }
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

    //write to the file
    void writeToFile(){
        String theData = AllVariables.bodyOfCycle + "\n" + AllVariables.coinType + "\n" + AllVariables.tyreType + "\n";
        for (int i=0; i<AllVariables.unlockedBar.size(); i++)
            theData += AllVariables.unlockedBar.get(i)+"#";
        theData += "\n";

        for (int i=0; i<AllVariables.unlockedCoin.size(); i++)
            theData += AllVariables.unlockedCoin.get(i)+"#";
        theData += "\n";

        for (int i=0; i<AllVariables.unlockedWheel.size(); i++)
            theData += AllVariables.unlockedWheel.get(i)+"#";
        theData += "\n";

        lockUnlockFile.writeString(theData, false);
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
        cancel.getTexture().dispose();
        CycleBars.getTexture().dispose();
        msgBoxTex.getTexture().dispose();
        buttonBg.getTexture().dispose();
        menuKusaCoin.dispose();
        menuBicycle.dispose();
        astic.dispose();
        freeKusaCoin.dispose();
        bgTobuy.dispose();
        arrowR.dispose();
        arrowL.dispose();
        lock.dispose();
        seat.dispose();
        handle.dispose();
        bgblur.dispose();
        for (int i =0; i<bicy_wheel.size(); i++)
            bicy_wheel.get(i).dispose();
        for (int i =0; i<bicy_bars.size(); i++)
            bicy_bars.get(i).dispose();
        for (int i =0; i<typeOfCoin.size(); i++)
            typeOfCoin.get(i).dispose();

        PriceOfCoins.clear();
        PriceOfBars.clear();
        PriceOfWheel.clear();

        bigText.dispose();

    }


    //video loader--------------------------------------

    @Override
    public void onRewardedEvent(String type, int amount) {
        AllVariables.kusaCoin+=150;
    }

    @Override
    public void onRewardedVideoAdLoadedEvent() {

    }

    @Override
    public void onRewardedVideoAdClosedEvent() {

    }
}
