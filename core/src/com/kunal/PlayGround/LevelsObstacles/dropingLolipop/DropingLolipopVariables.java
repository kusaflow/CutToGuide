package com.kunal.PlayGround.LevelsObstacles.dropingLolipop;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;

public class DropingLolipopVariables {
    public Sprite stickTex, candyTex;
    public int x,y;
    public int stickLen, stickWid, candyRadius;
    public short type;
    public Joint joint;
    Body stick, candy;
}
