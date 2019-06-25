package com.kunal.PlayGround.LevelsObstacles.halfSaw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

public class HalfSaw {
    Texture t1, t2;

    public HalfSaw() {
        t1 = new Texture(Gdx.files.internal("playArea/LevelObstacles/Enimies/halfSaw/sawHalf1.png"));
        t2 = new Texture(Gdx.files.internal("playArea/LevelObstacles/Enimies/halfSaw/sawHalf2.png"));
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
        }
    }

    public void render(){
        for (int i=0; i<VariablesForPlayArea.halfSawList.size(); i++){
            if (VariablesForPlayArea.halfSawList.get(i).texchange%5 == 0){
                VariablesForPlayArea.halfSawList.get(i).oneisSelected = !VariablesForPlayArea.halfSawList.get(i).oneisSelected;
            }

            if (VariablesForPlayArea.halfSawList.get(i).oneisSelected)
                AllVariables.batch.draw(t1, VariablesForPlayArea.halfSawList.get(i).xpos, VariablesForPlayArea.halfSawList.get(i).ypos);
            else
                AllVariables.batch.draw(t2, VariablesForPlayArea.halfSawList.get(i).xpos, VariablesForPlayArea.halfSawList.get(i).ypos);

            VariablesForPlayArea.halfSawList.get(i).texchange++;
            if (VariablesForPlayArea.halfSawList.get(i).texchange == 2000){
                VariablesForPlayArea.halfSawList.get(i).texchange = 0;
            }


        }

    }
}
