package com.kunal.PlayGround.LevelsObstacles.speedController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

public class SpeedController {
    Texture speedInc, speedDec;

    public SpeedController(){
        speedInc = new Texture(Gdx.files.internal("playArea/speedPowers/GreenPill.png"));
        speedDec = new Texture(Gdx.files.internal("playArea/speedPowers/RedPill.png"));
        for (int i=0; i<VariablesForPlayArea.speedCtrlList.size(); i++)
            VariablesForPlayArea.speedCtrlList.get(i).active = true;
    }

    public void update(){
        // bicycle
        for (int i=0; i<VariablesForPlayArea.speedCtrlList.size(); i++){
            if (VariablesForPlayArea.speedCtrlList.get(i).active) {
                if (((AllVariables.FrontWheel.getPosition().x * AllVariables.PPM) + (25) >= VariablesForPlayArea.speedCtrlList.get(i).x &&
                        (AllVariables.FrontWheel.getPosition().x * AllVariables.PPM) - (25) <= VariablesForPlayArea.speedCtrlList.get(i).x+40)
                ) {
                    if ((AllVariables.FrontWheel.getPosition().y * AllVariables.PPM) + (55) >= VariablesForPlayArea.speedCtrlList.get(i).y) {
                        if ((AllVariables.FrontWheel.getPosition().y * AllVariables.PPM) - (25) <= VariablesForPlayArea.speedCtrlList.get(i).y + 40) {
                            //====================================================
                            if (VariablesForPlayArea.speedCtrlList.get(i).SpeedIncrementor)
                                AllVariables.BackWheel.applyForceToCenter(new Vector2(200, 0), true);
                            else
                                AllVariables.BackWheel.applyForceToCenter(new Vector2(AllVariables.BackWheel.getLinearVelocity().x * (-20), 0), true);

                            VariablesForPlayArea.speedCtrlList.get(i).active = false;
                        }
                    }
                }else if (((AllVariables.BackWheel.getPosition().x * AllVariables.PPM) + (25) >= VariablesForPlayArea.speedCtrlList.get(i).x &&
                                (AllVariables.BackWheel.getPosition().x * AllVariables.PPM) - (25) <= VariablesForPlayArea.speedCtrlList.get(i).x+40 && VariablesForPlayArea.speedCtrlList.get(i).active)
                ) {
                    if ((AllVariables.BackWheel.getPosition().y * AllVariables.PPM) + (55) >= VariablesForPlayArea.speedCtrlList.get(i).y) {
                        if ((AllVariables.BackWheel.getPosition().y * AllVariables.PPM) - (25) <= VariablesForPlayArea.speedCtrlList.get(i).y + 40) {
                            //====================================================
                            if (VariablesForPlayArea.speedCtrlList.get(i).SpeedIncrementor)
                                AllVariables.BackWheel.applyForceToCenter(new Vector2(200, 0), true);
                            else
                                AllVariables.BackWheel.applyForceToCenter(new Vector2(AllVariables.BackWheel.getLinearVelocity().x * (-20), 0), true);

                            VariablesForPlayArea.speedCtrlList.get(i).active = false;
                        }
                    }
                }

            }
        }

    }


    public void render(){
        for (int i=0; i< VariablesForPlayArea.speedCtrlList.size(); i++){
            if (VariablesForPlayArea.speedCtrlList.get(i).active)
                if (VariablesForPlayArea.speedCtrlList.get(i).SpeedIncrementor)
                    AllVariables.batch.draw(speedInc, VariablesForPlayArea.speedCtrlList.get(i).x, VariablesForPlayArea.speedCtrlList.get(i).y,40,40);
                else
                    AllVariables.batch.draw(speedDec, VariablesForPlayArea.speedCtrlList.get(i).x, VariablesForPlayArea.speedCtrlList.get(i).y,40,40);


        }
    }
}
