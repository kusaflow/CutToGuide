package com.kunal.PlayGround.LevelsObstacles.halfSaw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

public class HalfSaw {
    Sprite t1, t2;

    public HalfSaw() {
        t1 = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/Enimies/halfSaw/sawHalf1.png")));
        t2 = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/Enimies/halfSaw/sawHalf2.png")));
        for (int i=0; i<VariablesForPlayArea.halfSawList.size(); i++) {
            VariablesForPlayArea.halfSawList.get(i).xpos = VariablesForPlayArea.halfSawList.get(i).xorigin;
            VariablesForPlayArea.halfSawList.get(i).ypos = VariablesForPlayArea.halfSawList.get(i).yorigin;
        }
    }

    public void update(){
        for (int i=0; i<VariablesForPlayArea.halfSawList.size(); i++) {

            if (VariablesForPlayArea.halfSawList.get(i).xpos <= VariablesForPlayArea.halfSawList.get(i).xorigin){
                VariablesForPlayArea.halfSawList.get(i).movingForward = true;
            }else if (VariablesForPlayArea.halfSawList.get(i).xpos >= VariablesForPlayArea.halfSawList.get(i).xdestination){
                VariablesForPlayArea.halfSawList.get(i).movingForward = false;
            }

            if (VariablesForPlayArea.halfSawList.get(i).movingForward){
                if (VariablesForPlayArea.halfSawList.get(i).orientation == 1)
                    VariablesForPlayArea.halfSawList.get(i).xpos+=5;
                else if (VariablesForPlayArea.halfSawList.get(i).orientation == 2) {
                    VariablesForPlayArea.halfSawList.get(i).xpos+=5;
                    VariablesForPlayArea.halfSawList.get(i).ypos+=5;
                }else if (VariablesForPlayArea.halfSawList.get(i).orientation == 3) {
                    VariablesForPlayArea.halfSawList.get(i).xpos+=5;
                    VariablesForPlayArea.halfSawList.get(i).ypos-=5;
                }
            }else{
                if (VariablesForPlayArea.halfSawList.get(i).orientation == 1)
                    VariablesForPlayArea.halfSawList.get(i).xpos-=5;
                else if (VariablesForPlayArea.halfSawList.get(i).orientation == 2) {
                    VariablesForPlayArea.halfSawList.get(i).xpos-=5;
                    VariablesForPlayArea.halfSawList.get(i).ypos-=5;
                }else if (VariablesForPlayArea.halfSawList.get(i).orientation == 3) {
                    VariablesForPlayArea.halfSawList.get(i).xpos-=5;
                    VariablesForPlayArea.halfSawList.get(i).ypos+=5;
                }
            }

            //collision
            if ((AllVariables.FrontWheel.getPosition().x * AllVariables.PPM) <= VariablesForPlayArea.halfSawList.get(i).xpos + 128 &&
                    (AllVariables.BackWheel.getPosition().x * AllVariables.PPM) >= VariablesForPlayArea.halfSawList.get(i).xpos) {
                if ((AllVariables.FrontWheel.getPosition().y * AllVariables.PPM) + (25) >= VariablesForPlayArea.halfSawList.get(i).ypos||
                        (AllVariables.BackWheel.getPosition().y * AllVariables.PPM) + (25) >= VariablesForPlayArea.halfSawList.get(i).ypos) {
                    if ((AllVariables.FrontWheel.getPosition().y * AllVariables.PPM) - (25) <= VariablesForPlayArea.halfSawList.get(i).ypos+ 58 ||
                            (AllVariables.BackWheel.getPosition().y * AllVariables.PPM) - (25) <= VariablesForPlayArea.halfSawList.get(i).ypos+ 58) {
                        VariablesForPlayArea.gameOver = true;
                    }
                }
            }

        }
    }

    public void render(){
        for (int i=0; i<VariablesForPlayArea.halfSawList.size(); i++){
            if (VariablesForPlayArea.halfSawList.get(i).texchange%5 == 0){
                VariablesForPlayArea.halfSawList.get(i).oneisSelected = !VariablesForPlayArea.halfSawList.get(i).oneisSelected;
            }

            if (VariablesForPlayArea.halfSawList.get(i).oneisSelected) {
                t1.setPosition(VariablesForPlayArea.halfSawList.get(i).xpos, VariablesForPlayArea.halfSawList.get(i).ypos);
                t1.draw(AllVariables.batch);
            } else {
                t2.setPosition(VariablesForPlayArea.halfSawList.get(i).xpos, VariablesForPlayArea.halfSawList.get(i).ypos);
                t2.draw(AllVariables.batch);
            }

            VariablesForPlayArea.halfSawList.get(i).texchange++;
            if (VariablesForPlayArea.halfSawList.get(i).texchange == 2000){
                VariablesForPlayArea.halfSawList.get(i).texchange = 0;
            }


        }

    }
}
