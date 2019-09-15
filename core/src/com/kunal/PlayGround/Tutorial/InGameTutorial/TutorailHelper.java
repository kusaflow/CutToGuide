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
        }else if (VariablesForPlayArea.tutState == 2){
            s = "Good Work";
        }else if (VariablesForPlayArea.tutState == 3){
            s = "You Know Where are your\n    shapes and powerups";
        }else if (VariablesForPlayArea.tutState == 4){
            s = "Correct";
        }else if (VariablesForPlayArea.tutState == 5){
            s = "But there is only one shape here";
        }else if (VariablesForPlayArea.tutState == 6){
            s = "But there is only one shape here\n\nYou Know how to cut shapes";
        }

        return s;
    }

    public static Vector2 cord(){
        Vector2 v = new Vector2(500,500);
        if (VariablesForPlayArea.tutState == 0){
            v = new Vector2(500,500);
        } else if (VariablesForPlayArea.tutState == 1){
            v = new Vector2(350,600);
        }else if (VariablesForPlayArea.tutState == 2){
            v = new Vector2(500,600);
        }else if (VariablesForPlayArea.tutState == 3){
            v = new Vector2(-200,500);
        }else if (VariablesForPlayArea.tutState == 4){
            v = new Vector2(490,500);
        }
        return v;
    }
}
