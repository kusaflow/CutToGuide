package com.kunal.PlayGround.LevelsObstacles.CreateHole;

import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class CreateHoleVariables {
    public short x=0;
    public short y=0;
    //the type of path is store in the variable typeOfPath
    //-------->1 will be straight means x is variable and y const
    //-------->2 will be slope with both x,y positive(imagine 3rd quadrand)
    //-------->3 will be slope with x positive and y negative(imagine 4th quadrant)
    //-------->4 will be y positive 90deg
    //-------->5 will be y negative 90deg
    public LinkedList<Byte> typeOfPAth = new LinkedList<Byte>();
    public LinkedList<Vector2> pos = new LinkedList<Vector2>();
    //1 for regular red and brown
    public Byte mapType =1;

}
