package com.kunal.PlayGround.LevelsObstacles.DirectionReverse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

public class DirectionReverse {

    Texture tex;

    public DirectionReverse() {
        tex = new Texture(Gdx.files.internal("playArea/LevelObstacles/dirReverse/dirRev.png"));
        for (int i=0; i<VariablesForPlayArea.dirRevList.size(); i++)
            VariablesForPlayArea.dirRevList.get(i).active = true;
    }

    public void render(){
        for (int i=0; i < VariablesForPlayArea.dirRevList.size(); i++){
            if (VariablesForPlayArea.dirRevList.get(i).active)
                AllVariables.batch.draw(tex, VariablesForPlayArea.dirRevList.get(i).x, VariablesForPlayArea.dirRevList.get(i).y,40,40);
        }
    }

    public void update(){

    }
}
