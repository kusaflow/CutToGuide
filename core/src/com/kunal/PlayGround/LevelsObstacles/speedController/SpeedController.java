package com.kunal.PlayGround.LevelsObstacles.speedController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

public class SpeedController {
    Texture speedInc, speedDec;

    public SpeedController(){
        speedInc = new Texture(Gdx.files.internal("playArea/speedPowers/GreenPill.png"));
        speedDec = new Texture(Gdx.files.internal("playArea/speedPowers/RedPill.png"));
    }

    public void update(){

    }

    public void render(){
        for (int i=0; i< VariablesForPlayArea.speedCtrlList.size(); i++){
            if (VariablesForPlayArea.speedCtrlList.get(i).SpeedIncrementor)
                AllVariables.batch.draw(speedInc, VariablesForPlayArea.speedCtrlList.get(i).x, VariablesForPlayArea.speedCtrlList.get(i).y,40,40);
            else
                AllVariables.batch.draw(speedDec, VariablesForPlayArea.speedCtrlList.get(i).x, VariablesForPlayArea.speedCtrlList.get(i).y,40,40);

        }
    }
}
