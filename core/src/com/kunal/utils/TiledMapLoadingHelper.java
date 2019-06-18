package com.kunal.utils;

import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;

public class TiledMapLoadingHelper {

    public TiledMapLoadingHelper(){}

    public static short NumberOfObj(){
        //testing is area 101 and level 101
        if (AllVariables.PresentLevelNumber == 101 && AllVariables.PresentAreaNumber == 101){
            return 10;
        }


        //area one ------------------------------------------------------
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 1){
            return 1;
        }

        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber == 2){
            return 6;
        }

        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber == 3){
            return 8;
        }

        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber == 4){
            return 8;
        }
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber == 5){
            return 11;
        }

        //area one end--------------------------------------------------


        return 0;
    }

    public static Vector2 flagpos(){
        Vector2 pos = new Vector2(0,0);

        //area one ------------------------------------------------------
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 1){
            pos.x = 6145;
            pos.y = 510;
            return pos;
        }
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 2){
            pos.x = 6145;
            pos.y = 510;
            return pos;
        }
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 3){
            pos.x = 5510;
            pos.y = 610;
            return pos;
        }
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 4){
            pos.x = 6440;
            pos.y = 515;
            return pos;
        }
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 5){
            pos.x = 6540;
            pos.y = 515;
            return pos;
        }

        return pos;
    }

    public static Vector2 coin1Pos(){
        Vector2 cpos = new Vector2(0,0);

        //area one ------------------------------------------------------
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 1){
            cpos.x = 1945;
            cpos.y = 500;
            return cpos;
        }
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 2){
            cpos.x = 3290;
            cpos.y = 515;
            return cpos;
        }
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 3){
            cpos.x = 4925;
            cpos.y = 615;
            return cpos;
        }
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 4){
            cpos.x = 6440;
            cpos.y = 515;
            return cpos;
        }
        return cpos;
    }


    public static Vector2 coin2Pos(){
        Vector2 cpos = new Vector2(0,0);

        //area one ------------------------------------------------------
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 1){
            cpos.x = 3290;
            cpos.y = 500;
            return cpos;
        }
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 2){
            cpos.x = 6145;
            cpos.y = 510;
            return cpos;
        }
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 3){
            cpos.x = 5510;
            cpos.y = 610;
            return cpos;
        }
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 4){
            cpos.x = 6440;
            cpos.y = 515;
            return cpos;
        }
        return cpos;
    }


    public static Vector2 coin3Pos(){
        Vector2 cpos = new Vector2(0,0);

        //area one ------------------------------------------------------
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 1){
            cpos.x = 4925;
            cpos.y = 500;
            return cpos;
        }
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 2){
            cpos.x = 6145;
            cpos.y = 510;
            return cpos;
        }
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 3){
            cpos.x = 5510;
            cpos.y = 610;
            return cpos;
        }
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 4){
            cpos.x = 6440;
            cpos.y = 515;
            return cpos;
        }
        return cpos;
    }


}
