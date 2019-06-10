package com.kunal.utils;

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



        //area one end--------------------------------------------------


        return 0;
    }
}
