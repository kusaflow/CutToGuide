package com.kunal.PlayGround.Tutorial.InGameTutorial;

import com.badlogic.gdx.math.Vector2;
import com.kunal.PlayGround.VariablesForPlayArea;

public class TutorailHelper {
    public void TutorailHelper(){

    }

    public static String msgOnScreen(){
        String s ="";
        if (VariablesForPlayArea.tutState == 0){
            s = "Welcome";
        }else if (VariablesForPlayArea.tutState == 1){
            s = "First find the red flag\n                   >>";
        }

        return s;
    }

    public static Vector2 cord(){
        Vector2 v = new Vector2(500,500);
        if (VariablesForPlayArea.tutState == 0){
            v = new Vector2(500,500);
        } else if (VariablesForPlayArea.tutState == 1){
            v = new Vector2(350,500);
        }
        return v;
    }
}
