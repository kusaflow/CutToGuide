package com.kunal.PlayGround;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.kunal.AllVariables;
import com.kunal.PlayGround.LevelsObstacles.BreakableCandyBars.BreakableCandyBarVariables;
import com.kunal.PlayGround.LevelsObstacles.CreateHole.CreateHoleVariables;
import com.kunal.PlayGround.LevelsObstacles.DirectionReverse.DirectionReverseVariables;
import com.kunal.PlayGround.LevelsObstacles.Jumper.JumperVariables;
import com.kunal.PlayGround.LevelsObstacles.Roundcandy.RoundCandyVariables;
import com.kunal.PlayGround.LevelsObstacles.barnacle.Barnacle;
import com.kunal.PlayGround.LevelsObstacles.barnacle.BarnacleVariables;
import com.kunal.PlayGround.LevelsObstacles.dropingLolipop.DropingLolipopVariables;
import com.kunal.PlayGround.LevelsObstacles.droppingSpinkes.DroppingSpikeVariables;
import com.kunal.PlayGround.LevelsObstacles.fireOnFloor.FireOnFloorVariables;
import com.kunal.PlayGround.LevelsObstacles.flappyBirdPipes.flappyBirdPipesVariables;
import com.kunal.PlayGround.LevelsObstacles.fullSawThatRoams.FullSawVariables;
import com.kunal.PlayGround.LevelsObstacles.halfSaw.HalfSawVariables;
import com.kunal.PlayGround.LevelsObstacles.snake.SnakeVariables;
import com.kunal.PlayGround.LevelsObstacles.speedController.SpeedControllerVariables;
import com.kunal.PlayGround.LevelsObstacles.spikes.SpikesVariables;
import com.kunal.PlayGround.powerUpInInventory.PowerUpInInventoryVariables;

import java.util.LinkedList;

public class VariablesForPlayArea {

    public static LinkedList<Byte> vertices;
    public static LinkedList<LinkedList<Byte>> shapes;

    public static LinkedList<Byte> cantuseDots;

    // all the points of the big square
    public static int[][] BigSqurePoints = new int[16][2];

    //end point is when the level will end
    public static Vector2 endPoint;

    public static Boolean gameOver = false;
    public static Boolean rageMode = false;
    public static Boolean HintOneEnabled = false;
    public static Boolean HintTwoEnabled = false;


    public static short bulletsHave = 0;



    public static byte starsGained =0;

    //level number
    public static int levelNumber;

    //area number
    // 0 is for tut
    //public static short areaNumber =0;

    //position of camera x
    public static float camposX = 1200f;


    //positions of shapes
    public static LinkedList<Vector2> Sh_pos;

    //linked list for bokagdies
    public static LinkedList<Body> CutOutBodies;
    public static LinkedList<Float> Angle_Of_Shape;
    public static LinkedList<LinkedList<Byte>> CutoutShapeVertices;

    public static byte shapeNumberSelected = 25 ;

    public static String LevelMapToBeLoaded = "";

    //do slowmo
    public static boolean doSlowMo = false;

    //tut state
    public static short tutstep = 0;

    //===================================
    //POwerUps
    public static LinkedList<PowerUpInInventoryVariables> powerUpList;
    //-----------------------------------

    //==========================================
    //obstacles
    public static LinkedList<flappyBirdPipesVariables> flappyBirdPipesList;
    public static LinkedList<CreateHoleVariables> createHoleList;
    public static LinkedList<JumperVariables> jumperList;
    public static LinkedList<HalfSawVariables> halfSawList;
    public static LinkedList<FullSawVariables> fullSawList;
    public static LinkedList<SpeedControllerVariables> speedCtrlList;
    public static LinkedList<DirectionReverseVariables> dirRevList;

    //candy world
    public static LinkedList<BreakableCandyBarVariables> breakingCandyBar;
    public static LinkedList<RoundCandyVariables> roundCandies;
    public static LinkedList<DropingLolipopVariables> dropingLolipop;
    public static LinkedList<FireOnFloorVariables> fire;
    public static LinkedList<SpikesVariables> spike;
    public static LinkedList<DroppingSpikeVariables> dropingSpike;
    public static LinkedList<SnakeVariables> snakes;
    public static LinkedList<BarnacleVariables> barnacle;


    public static Boolean bicycleOnFire = false;
    //------------------------------------------

    //--------Tutorial-------------------------------------------------------
    public static int tutState= 0;
    //------------------------------------------------------------------------

    public VariablesForPlayArea() {
        cantuseDots = new LinkedList<Byte>();
        CutOutBodies = new LinkedList<Body>();
        CutoutShapeVertices = new LinkedList<LinkedList<Byte>>();
        Angle_Of_Shape = new LinkedList<Float>();
        Sh_pos = new LinkedList<Vector2>();
        Sh_pos.add(new Vector2(640/AllVariables.PPM, -3000/AllVariables.PPM));
        Angle_Of_Shape.add(180f);
        powerUpList = new LinkedList<PowerUpInInventoryVariables>();


        cantuseDots.add((byte) 5);
        cantuseDots.add((byte) 6);
        cantuseDots.add((byte) 9);
        cantuseDots.add((byte) 10);

        //obstacles
        flappyBirdPipesList = new LinkedList<flappyBirdPipesVariables>();
        createHoleList = new LinkedList<CreateHoleVariables>();
        jumperList = new LinkedList<JumperVariables>();
        halfSawList = new LinkedList<HalfSawVariables>();
        fullSawList = new LinkedList<FullSawVariables>();
        speedCtrlList = new LinkedList<SpeedControllerVariables>();
        dirRevList = new LinkedList<DirectionReverseVariables>();

        //candyWorld
        breakingCandyBar = new LinkedList<BreakableCandyBarVariables>();
        roundCandies = new LinkedList<RoundCandyVariables>();
        dropingLolipop = new LinkedList<DropingLolipopVariables>();
        fire = new LinkedList<FireOnFloorVariables>();
        spike = new LinkedList<SpikesVariables>();
        dropingSpike = new LinkedList<DroppingSpikeVariables>();
        snakes = new LinkedList<SnakeVariables>();
        barnacle = new LinkedList<BarnacleVariables>();


        //bullets
        bulletsHave = 0;

        //all big squre Points
        BigSqurePoints[0][0] = 430;
        BigSqurePoints[0][1] = 690 - 50;
        BigSqurePoints[1][0] = 690;
        BigSqurePoints[1][1] = 690 - 50;
        BigSqurePoints[2][0] = 950;
        BigSqurePoints[2][1] = 690 - 50;
        BigSqurePoints[3][0] = 1210;
        BigSqurePoints[3][1] = 690 - 50;
        BigSqurePoints[4][0] = 430;
        BigSqurePoints[4][1] = 496 - 50;
        BigSqurePoints[5][0] = 690;
        BigSqurePoints[5][1] = 496 - 50;
        BigSqurePoints[6][0] = 950;
        BigSqurePoints[6][1] = 496 - 50;
        BigSqurePoints[7][0] = 1210;
        BigSqurePoints[7][1] = 496 - 50;
        BigSqurePoints[8][0] = 430;
        BigSqurePoints[8][1] = 303 - 50;
        BigSqurePoints[9][0] = 690;
        BigSqurePoints[9][1] = 303 - 50;
        BigSqurePoints[10][0] = 950;
        BigSqurePoints[10][1] = 303 - 50;
        BigSqurePoints[11][0] = 1210;
        BigSqurePoints[11][1] = 303 - 50;
        BigSqurePoints[12][0] = 430;
        BigSqurePoints[12][1] = 110 - 50;
        BigSqurePoints[13][0] = 690;
        BigSqurePoints[13][1] = 110 - 50;
        BigSqurePoints[14][0] = 950;
        BigSqurePoints[14][1] = 110 - 50;
        BigSqurePoints[15][0] = 1210;
        BigSqurePoints[15][1] = 110 - 50;

        //shapes and vertices
        vertices = new LinkedList<Byte>();
        shapes = new LinkedList<LinkedList<Byte>>();
        flush();

    }

    public static void flush(){
        shapes.clear();
        vertices.clear();
        Sh_pos.clear();

        Sh_pos.add(new Vector2(640f/AllVariables.PPM, -3000f/AllVariables.PPM));

        bulletsHave = 0;
        doSlowMo = false;

        cantuseDots.clear();
        cantuseDots.add((byte) 5);
        cantuseDots.add((byte) 6);
        cantuseDots.add((byte) 9);
        cantuseDots.add((byte) 10);

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

        camposX = 1200f;


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
