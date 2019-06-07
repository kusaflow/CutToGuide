package com.kunal.utils;

import com.kunal.AllVariables;

public class TiledMapLoadingHelper {

    public TiledMapLoadingHelper(){}

    public static short NumberOfObj(){
        //testing is area 101 and level 101
        if (AllVariables.PresentLevelNumber == 101 && AllVariables.PresentAreaNumber == 101){
            return 5;
        }

        return 0;
    }
}
