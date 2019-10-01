package com.kunal.PlayGround.hintToGuide;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.kunal.AllVariables;

public class HintsToGuide {

    public static void renterText(BitmapFont font){
        AllVariables.batch.begin();
        if (AllVariables.PresentAreaNumber == 1){
            if (AllVariables.PresentLevelNumber == 3){
                font.draw(AllVariables.batch, "Touch the flag to clear the level", 2767, 400);
            }else if (AllVariables.PresentLevelNumber == 6){
                font.draw(AllVariables.batch, "Consume this to increase \nspeed instantly", 717, 450);
                font.draw(AllVariables.batch, "Consuming green pill activates rage mode ", 2957, 320);
            } else if (AllVariables.PresentLevelNumber == 7){
                font.draw(AllVariables.batch, "He is a friend(saw)", 932, 450);
                font.draw(AllVariables.batch, "Your friend can \nconsume this too", 2922, 450);
            } else if (AllVariables.PresentLevelNumber == 8){
                font.draw(AllVariables.batch, "cross on the eyes show they can do nothing", 4800, 450);
            } else if (AllVariables.PresentLevelNumber == 9){
                font.draw(AllVariables.batch, "He is Evil", 1707, 422);
                font.draw(AllVariables.batch, "You can kill him with your rage mode\n(consume green pill)", 1657, 917);
            } else if (AllVariables.PresentLevelNumber == 10){
                font.draw(AllVariables.batch, "This can only be consumed by saw\n and it will change its direction", 322, 750);
            }
        }

        AllVariables.batch.end();
    }

}
