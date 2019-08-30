package com.kunal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.physics.box2d.Body;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.AreaSelection.levelNumberSelection.LevelNumberSelection;
import com.kunal.PlayGround.TypeOneArea.TypeOneArea;
import com.kunal.PlayGround.TypeTwoArea.TypeTwoArea;
import com.kunal.PlayGround.constScreen.CuttingArea.CuttingAreaManager;
import com.kunal.PlayGround.constScreen.ShapeChooser;
import com.kunal.Shop.Shop;

import java.util.LinkedList;

public class AllVariables {

    public static int WIDTH = 1280;
    //public static final int WIDTH = 1380;
    public static int HEIGHT = 720;


    public static SpriteBatch batch;

    public static final float PPM = 100;

    public static float inpM;
    public static int witdth_translation;

    public static BitmapFont bitmapFont;


    //the coins in the game I will call them KusaCoin
    public static int kusaCoin = 100;

    public static AdVideoInterface adv = null;

    public static openOtherApps openApps = null;

    public static short PresentAreaNumber = 0;
    public static short PresentLevelNumber = 0;
    /*
    0 is for no area meaning it is at AreaSelection Menu
     */

    //Mask
    public static final short Bit_Bicycle= 1;
    public static final short Bit_land = 2;
    public static final short Bit_enimes = 4;
    public static final short Bit_Tool= 8;

    public static Body FrontWheel,BackWheel, rod1, rod2, rod3, rod4, rod5, rod6, rod7, seat, handle;

    public static byte tyreType =0, bodyOfCycle =0, coinType=0;
    public static short speedIncCount =0, speedDecCount =0, JumperCount=0;
    public static LinkedList<Byte> unlockedWheel, unlockedBar, unlockedCoin;


    public static String coinAdd = "typesOfCoin/hudCoin.png";
}

//format of basic file is
//--------------------------
//barchoice
//coinChoice
//wheelSelected
//unlockbar
//unlockcoin
//unlockWheel
