package com.kunal.utils;

import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;

public class hintImagesAxis {
    public static Vector2 hintOne(){
        Vector2 v = new Vector2(0,0);
        if (AllVariables.PresentAreaNumber == 1){
            if (AllVariables.PresentLevelNumber == 1){
                v.x = 0;
                v.y = 0;
            }
        }

        return v;
    }
}
