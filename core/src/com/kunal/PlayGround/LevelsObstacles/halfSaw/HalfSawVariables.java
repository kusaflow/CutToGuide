package com.kunal.PlayGround.LevelsObstacles.halfSaw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class HalfSawVariables {
    public Boolean oneisSelected = true;
    public Boolean movingForward = true;
    public short orientation =1;
    /*
    1 for +ve x
    2 for 1st quadrant
    3 for 4th quadrant
     */
    public short xorigin;
    public short yorigin;
    public short xdestination;
    public short xpos;
    public short ypos;

    public short texchange =0;
}
