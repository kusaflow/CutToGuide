package com.kunal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public class AllVariables {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public static SpriteBatch batch;

    public static final float PPM = 100;


    //Masks
    public static final short Bit_Bicycle= 1;
    public static final short Bit_land = 2;
    public static final short Bit_enimes = 4;
    public static final short Bit_Tool= 8;

    public static Body FrontWheel,BackWheel, rod1, rod2, rod3, rod4, rod5, rod6, Handle;

    public static Body Front_foot1, Front_foot2, Front_leg, Front_Thai, Back_foot1, Back_foot2, Back_leg, Back_Thai,
            Stomach1, Stomach2, Stomach3, neck, head, Front_arm, Back_arm, Front_hand, Back_hand, seat;



}
