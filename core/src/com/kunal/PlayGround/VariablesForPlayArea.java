package com.kunal.PlayGround;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.kunal.AllVariables;
import com.kunal.PlayGround.LevelsObstacles.CreateHole.CreateHoleVariables;
import com.kunal.PlayGround.LevelsObstacles.flappyBirdPipes.flappyBirdPipesVariables;

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

    //area number
    // 0 is for tut
    //public static short areaNumber =0;

    //powerUP LinkedList
    public static LinkedList<Byte> powerUps;
    //power UP position
    public static LinkedList<Vector2> powerUpPos;

    //position of camera x
    public static float camposX = 1200f;


    //positions of shapes
    public static LinkedList<Vector2> Sh_pos;

    //linked list for bodies
    public static LinkedList<Body> CutOutBodies;
    public static LinkedList<Float> Angle_Of_Shape;
    public static LinkedList<LinkedList<Byte>> CutoutShapeVertices;

    public static byte shapeNumberSelected = 25 ;

    public static String LevelMapToBeLoaded = "";

    //tut state
    public static short tutstep = 0;

    //==========================================
    //obstacles
    public static LinkedList<flappyBirdPipesVariables> flappyBirdPipesList;
    public static LinkedList<CreateHoleVariables> createHoleList;

    //==========================================

    public VariablesForPlayArea() {
        cantuseDots = new LinkedList<Byte>();
        CutOutBodies = new LinkedList<Body>();
        CutoutShapeVertices = new LinkedList<LinkedList<Byte>>();
        Angle_Of_Shape = new LinkedList<Float>();
        Sh_pos = new LinkedList<Vector2>();
        Sh_pos.add(new Vector2(640/AllVariables.PPM, -1000/AllVariables.PPM));
        Angle_Of_Shape.add(180f);
        powerUps = new LinkedList<Byte>();
        powerUpPos = new LinkedList<Vector2>();

        //obstacles
        flappyBirdPipesList = new LinkedList<flappyBirdPipesVariables>();
        createHoleList = new LinkedList<CreateHoleVariables>();


        //all big squre Points
        BigSqurePoints[0][0] = 430;
        BigSqurePoints[0][1] = 690;
        BigSqurePoints[1][0] = 690;
        BigSqurePoints[1][1] = 690;
        BigSqurePoints[2][0] = 950;
        BigSqurePoints[2][1] = 690;
        BigSqurePoints[3][0] = 1210;
        BigSqurePoints[3][1] = 690;
        BigSqurePoints[4][0] = 430;
        BigSqurePoints[4][1] = 496;
        BigSqurePoints[5][0] = 690;
        BigSqurePoints[5][1] = 496;
        BigSqurePoints[6][0] = 950;
        BigSqurePoints[6][1] = 496;
        BigSqurePoints[7][0] = 1210;
        BigSqurePoints[7][1] = 496;
        BigSqurePoints[8][0] = 430;
        BigSqurePoints[8][1] = 303;
        BigSqurePoints[9][0] = 690;
        BigSqurePoints[9][1] = 303;
        BigSqurePoints[10][0] = 950;
        BigSqurePoints[10][1] = 303;
        BigSqurePoints[11][0] = 1210;
        BigSqurePoints[11][1] = 303;
        BigSqurePoints[12][0] = 430;
        BigSqurePoints[12][1] = 110;
        BigSqurePoints[13][0] = 690;
        BigSqurePoints[13][1] = 110;
        BigSqurePoints[14][0] = 950;
        BigSqurePoints[14][1] = 110;
        BigSqurePoints[15][0] = 1210;
        BigSqurePoints[15][1] = 110;

        //shapes and vertices
        vertices = new LinkedList<Byte>();
        shapes = new LinkedList<LinkedList<Byte>>();
        flush();

    }

    public static void flush(){
        shapes.clear();
        vertices.clear();
        Sh_pos.clear();

        Sh_pos.add(new Vector2(640f/AllVariables.PPM, -1000f/AllVariables.PPM));

        cantuseDots.clear();

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
        VariablesForPlayArea.endPoint = endPoint;
    }

    public void setLevelNumber(int levelNumber) {
        VariablesForPlayArea.levelNumber = levelNumber;
    }

    public Vector2 getEndPoint() {
        return endPoint;
    }

    public int getLevelNumber() {
        return levelNumber;
    }
}
