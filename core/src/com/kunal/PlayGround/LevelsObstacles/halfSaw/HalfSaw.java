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
    }

    public void update(){

    }

    public void render(){
        for (int i=0; i<VariablesForPlayArea.halfSawList.size(); i++){
            if (VariablesForPlayArea.halfSawList.get(i).texchange%5 == 0){
                VariablesForPlayArea.halfSawList.get(i).oneisSelected = !VariablesForPlayArea.halfSawList.get(i).oneisSelected;
            }

            if (VariablesForPlayArea.halfSawList.get(i).oneisSelected)
                AllVariables.batch.draw(t1, VariablesForPlayArea.halfSawList.get(i).xorigin, VariablesForPlayArea.halfSawList.get(i).yorigin);
            else
                AllVariables.batch.draw(t2, VariablesForPlayArea.halfSawList.get(i).xorigin, VariablesForPlayArea.halfSawList.get(i).yorigin);

            VariablesForPlayArea.halfSawList.get(i).texchange++;
            if (VariablesForPlayArea.halfSawList.get(i).texchange == 2000){
                VariablesForPlayArea.halfSawList.get(i).texchange = 0;
            }


        }

    }
}
