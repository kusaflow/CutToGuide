package com.kunal.PlayGround;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.LinkedList;

public class VariablesForPlayArea {

    public static LinkedList<Byte> vertices;
    public static LinkedList<LinkedList<Byte>> shapes;

    public static LinkedList<Byte> cantuseDots;

    // all the points of the big square
    public static int[][] BigSqurePoints = new int[16][2];

    //end point is when the level will end
    public static Vector2 endPoint;

    //level number
    public static int levelNumber;

    //linked list for bodies
    public static LinkedList<Body> CutOutBodies;

    public VariablesForPlayArea() {
        cantuseDots = new LinkedList<Byte>();
        CutOutBodies = new LinkedList<Body>();

        //all big squre Points
        BigSqurePoints[0][0] = 550;
        BigSqurePoints[0][1] = 700;
        BigSqurePoints[1][0] = 770;
        BigSqurePoints[1][1] = 700;
        BigSqurePoints[2][0] = 990;
        BigSqurePoints[2][1] = 700;
        BigSqurePoints[3][0] = 1210;
        BigSqurePoints[3][1] = 700;
        BigSqurePoints[4][0] = 550;
        BigSqurePoints[4][1] = 480;
        BigSqurePoints[5][0] = 770;
        BigSqurePoints[5][1] = 480;
        BigSqurePoints[6][0] = 990;
        BigSqurePoints[6][1] = 480;
        BigSqurePoints[7][0] = 1210;
        BigSqurePoints[7][1] = 480;
        BigSqurePoints[8][0] = 550;
        BigSqurePoints[8][1] = 260;
        BigSqurePoints[9][0] = 770;
        BigSqurePoints[9][1] = 260;
        BigSqurePoints[10][0] = 990;
        BigSqurePoints[10][1] = 260;
        BigSqurePoints[11][0] = 1210;
        BigSqurePoints[11][1] = 260;
        BigSqurePoints[12][0] = 550;
        BigSqurePoints[12][1] = 40;
        BigSqurePoints[13][0] = 770;
        BigSqurePoints[13][1] = 40;
        BigSqurePoints[14][0] = 990;
        BigSqurePoints[14][1] = 40;
        BigSqurePoints[15][0] = 1210;
        BigSqurePoints[15][1] = 40;

        //shapes and vertices
        vertices = new LinkedList<Byte>();
        shapes = new LinkedList<LinkedList<Byte>>();
        flush();

    }

    public void flush(){
        vertices.add((byte) 0);
        vertices.add((byte) 1);
        vertices.add((byte) 2);
        vertices.add((byte) 3);
        vertices.add((byte) 7);
        vertices.add((byte) 11);
        vertices.add((byte) 15);
        vertices.add((byte) 14);
        vertices.add((byte) 13);
        vertices.add((byte) 12);
        vertices.add((byte) 8);
        vertices.add((byte) 4);


        shapes.add(vertices);
    }

    public void setEndPoint(Vector2 endPoint) {
        this.endPoint = endPoint;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public Vector2 getEndPoint() {
        return endPoint;
    }

    public int getLevelNumber() {
        return levelNumber;
    }
}