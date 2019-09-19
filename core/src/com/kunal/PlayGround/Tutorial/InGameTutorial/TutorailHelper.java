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
            s = "You Know Where are your\n    shapes and powerups?";
        }else if (VariablesForPlayArea.tutState == 4){
            s = "Correct";
        }else if (VariablesForPlayArea.tutState == 5){
            s = "But there is only one shape here";
        }else if (VariablesForPlayArea.tutState == 6){
            s = "But there is only one shape here\n\nYou Know how to cut shapes?";
        }else if (VariablesForPlayArea.tutState == 7){
            s = "Great";
        }else if (VariablesForPlayArea.tutState == 8){
            s = "Lets cut a triangle";
        }else if (VariablesForPlayArea.tutState == 9){
            s = "Lets cut a triangle\n\nJoin these two \npoints";
        }
        else if (VariablesForPlayArea.tutState == 10){
            s = "                             Cool now we have two shapes";
        }
        else if (VariablesForPlayArea.tutState == 11){
            s = "                             Cool now we have two shapes\n\nYou see the green\npoints turned red\n";
        }
        else if (VariablesForPlayArea.tutState == 12){
            s = "                             Cool now we have two shapes\n\nYou see the green\npoints turned red\n\n                                                                You can Only start with \n                                                                      green points\n(tap anywhere to \ncontinue)";
        }
        else if (VariablesForPlayArea.tutState == 13){
            s = "                             Experiment here\n\n\ntap here to\ncontinue";
        }else if (VariablesForPlayArea.tutState == 14) {
            s = "You know how to\nclear this?";
        }else if (VariablesForPlayArea.tutState == 15){
            s = "Great";
        }else if (VariablesForPlayArea.tutState == 16){
            s = "                             Draw anything(atleast 2 shapes)\n\n\ntap here to\ncontinue";
        }else if (VariablesForPlayArea.tutState == 17){
            s = "Lets take these\nshapes to our\n level";
        }else if (VariablesForPlayArea.tutState == 18){
            s = "You see now we have " +VariablesForPlayArea.shapes.size() + " shapes";
        }else if (VariablesForPlayArea.tutState == 19){
            s = "Tap on any shape to select ";
        }else if (VariablesForPlayArea.tutState == 20){
            s = "Take this to the level";
        }else if (VariablesForPlayArea.tutState == 21){
            s = "So many new buttons on the screen";
        }else if (VariablesForPlayArea.tutState == 22){
            s = "Lets use them one by one";
        }else if (VariablesForPlayArea.tutState == 23){
            s = "Tap and drag your finger";
        }else if (VariablesForPlayArea.tutState == 24){
            s = "This is how you can manipulate shapes.\ntap and drag anywhere";
        }else if (VariablesForPlayArea.tutState == 25){
            s = "You know how to delete a shape";
        }else if (VariablesForPlayArea.tutState == 26){
            s = "Perfect";
        }else if (VariablesForPlayArea.tutState == 27){
            s = "Go and bring same or another shape";
        }else if (VariablesForPlayArea.tutState == 28){
            s = "Great";
        }else if (VariablesForPlayArea.tutState == 29){
            s = "You know you can also rotate the shapes";
        }else if (VariablesForPlayArea.tutState == 30){
            s = "Rotate anticlockwise";
        }else if (VariablesForPlayArea.tutState == 31){
            s = "Rotate clockwise";
        }else if (VariablesForPlayArea.tutState == 32){
            s = "Rotate in multiple of 45 degree";
        }else if (VariablesForPlayArea.tutState == 33){
            s = "Tap and drag your finger";
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
            v = new Vector2(500,500);
        }else if (VariablesForPlayArea.tutState == 5){
            v = new Vector2(50,500);
        }else if (VariablesForPlayArea.tutState == 6){
            v = new Vector2(50,500);
        }else if (VariablesForPlayArea.tutState == 7){
            v = new Vector2(750,500);
        }else if (VariablesForPlayArea.tutState == 8){
            v = new Vector2(20,600);
        }else if (VariablesForPlayArea.tutState == 9){
            v = new Vector2(20,600);
        }else if (VariablesForPlayArea.tutState == 10){
            v = new Vector2(20,700);
        }else if (VariablesForPlayArea.tutState == 11){
            v = new Vector2(20,700);
        }else if (VariablesForPlayArea.tutState == 12){
            v = new Vector2(20,700);
        }else if (VariablesForPlayArea.tutState == 13){
            v = new Vector2(20,700);
        }else if (VariablesForPlayArea.tutState == 14){
            v = new Vector2(20,500);
        }else if (VariablesForPlayArea.tutState == 15){
            v = new Vector2(750,500);
        }else if (VariablesForPlayArea.tutState == 16){
            v = new Vector2(20,700);
        }else if (VariablesForPlayArea.tutState == 17){
            v = new Vector2(20,600);
        }else if (VariablesForPlayArea.tutState == 18 || VariablesForPlayArea.tutState == 19 || VariablesForPlayArea.tutState == 20){
            if (VariablesForPlayArea.shapes.size() <=5)
                v = new Vector2(60,500);
            else if (VariablesForPlayArea.shapes.size() >=5  && VariablesForPlayArea.shapes.size() <=10)
                v = new Vector2(60,350);
            else if (VariablesForPlayArea.shapes.size() >=11  && VariablesForPlayArea.shapes.size() <=15)
                v = new Vector2(60,200);
        } else if (VariablesForPlayArea.tutState == 21){
            v = new Vector2(250,600);
        } else if (VariablesForPlayArea.tutState == 22){
            v = new Vector2(250,600);
        } else if (VariablesForPlayArea.tutState == 23){
            v = new Vector2(250,600);
        } else if (VariablesForPlayArea.tutState == 24){
            v = new Vector2(250,600);
        } else if (VariablesForPlayArea.tutState == 25){
            v = new Vector2(250,600);
        } else if (VariablesForPlayArea.tutState == 26){
            v = new Vector2(250,600);
        } else if (VariablesForPlayArea.tutState == 27){
            v = new Vector2(200,350);
        } else if (VariablesForPlayArea.tutState == 28){
            v = new Vector2(250,600);
        } else if (VariablesForPlayArea.tutState == 29){
            v = new Vector2(250,600);
        } else if (VariablesForPlayArea.tutState == 30){
            v = new Vector2(250,600);
        } else if (VariablesForPlayArea.tutState == 31){
            v = new Vector2(250,600);
        } else if (VariablesForPlayArea.tutState == 32){
            v = new Vector2(250,600);
        }
        return v;
    }
}
