package com.kunal.PlayGround.powerUpInInventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

public class PowerUpMngr {

    public PowerUpMngr(Boolean reset) {
        for (int i =0; i < VariablesForPlayArea.powerUpList.size(); i++){
            if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 1){
                VariablesForPlayArea.powerUpList.get(i).texture = new Texture(Gdx.files.internal("playArea/speedPowers/GreenPill.png"));
            }else if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 2){
                VariablesForPlayArea.powerUpList.get(i).texture = new Texture(Gdx.files.internal("playArea/speedPowers/RedPill.png"));
            }else if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 3){
                VariablesForPlayArea.powerUpList.get(i).texture = new Texture(Gdx.files.internal("playArea/LevelObstacles/Jumper/spring.png"));
            }else if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 4){
                VariablesForPlayArea.powerUpList.get(i).texture = new Texture(Gdx.files.internal("playArea/LevelObstacles/dirReverse/dirRev.png"));
            }
            if (reset)
                VariablesForPlayArea.powerUpList.get(i).active = true;

        }
    }

    public void update(){

        //collision mapper for powerUps============================================
        for (int i =0; i<VariablesForPlayArea.powerUpList.size(); i++) {
            if (VariablesForPlayArea.powerUpList.get(i).active) {
                if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 1 || VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 2) {
                    if ((AllVariables.FrontWheel.getPosition().x * AllVariables.PPM) + (25) >= VariablesForPlayArea.powerUpList.get(i).x &&
                            (AllVariables.FrontWheel.getPosition().x * AllVariables.PPM) - (25 ) <= VariablesForPlayArea.powerUpList.get(i).x-40
                    ) {
                        if ((AllVariables.FrontWheel.getPosition().y * AllVariables.PPM) + (55) >= VariablesForPlayArea.powerUpList.get(i).y) {
                            if ((AllVariables.FrontWheel.getPosition().y * AllVariables.PPM) - (25) <= VariablesForPlayArea.powerUpList.get(i).y + 40) {
                                //====================================================
                                if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 1) {
                                    AllVariables.BackWheel.applyForceToCenter(new Vector2(250, 0), true);
                                    AllVariables.FrontWheel.applyForceToCenter(new Vector2(0, 10), true);
                                } else if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 2) {
                                    AllVariables.BackWheel.applyForceToCenter(new Vector2(AllVariables.BackWheel.getLinearVelocity().x * (-20), 0), true);
                                }
                                VariablesForPlayArea.powerUpList.get(i).active = false;
                            }
                        }
                    }else if (
                            ((AllVariables.BackWheel.getPosition().x * AllVariables.PPM) + (25) >= VariablesForPlayArea.powerUpList.get(i).x &&
                                    (AllVariables.BackWheel.getPosition().x * AllVariables.PPM) - (25) <= VariablesForPlayArea.powerUpList.get(i).x+40) && VariablesForPlayArea.powerUpList.get(i).active){
                        if ((AllVariables.BackWheel.getPosition().y * AllVariables.PPM) + (55) >= VariablesForPlayArea.powerUpList.get(i).y) {
                            if ((AllVariables.BackWheel.getPosition().y * AllVariables.PPM) - (25) <= VariablesForPlayArea.powerUpList.get(i).y + 40) {
                                //====================================================
                                if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 1) {
                                    AllVariables.BackWheel.applyForceToCenter(new Vector2(250, 0), true);
                                    AllVariables.FrontWheel.applyForceToCenter(new Vector2(0, 10), true);
                                } else if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 2) {
                                    AllVariables.BackWheel.applyForceToCenter(new Vector2(AllVariables.BackWheel.getLinearVelocity().x * (-20), 0), true);
                                }
                                VariablesForPlayArea.powerUpList.get(i).active = false;
                            }
                        }
                    }
                }else if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 3){
                    if ((AllVariables.FrontWheel.getPosition().x * AllVariables.PPM) <= VariablesForPlayArea.powerUpList.get(i).x + 108 + 64 &&
                            (AllVariables.BackWheel.getPosition().x * AllVariables.PPM) >= VariablesForPlayArea.powerUpList.get(i).x) {
                        if ((AllVariables.FrontWheel.getPosition().y * AllVariables.PPM) + (55) >= VariablesForPlayArea.powerUpList.get(i).y ||
                                (AllVariables.BackWheel.getPosition().y * AllVariables.PPM) + (55) >= VariablesForPlayArea.powerUpList.get(i).y) {
                            if ((AllVariables.FrontWheel.getPosition().y * AllVariables.PPM) - (25 + 5) <= VariablesForPlayArea.powerUpList.get(i).y + 27 ||
                                    (AllVariables.BackWheel.getPosition().y * AllVariables.PPM) - (25 + 5) <= VariablesForPlayArea.powerUpList.get(i).y + 27) {
                                //cccccccffffffffffffffffffffffffffffffffffffffffffffffff
                                VariablesForPlayArea.powerUpList.get(i).active = false;
                                VariablesForPlayArea.powerUpList.get(i).texture = new Texture(Gdx.files.internal("playArea/LevelObstacles/Jumper/sprung.png"));
                                AllVariables.BackWheel.applyForceToCenter(new Vector2(0,AllVariables.FrontWheel.getLinearVelocity().x * 6.5f), true);
                                AllVariables.FrontWheel.applyForceToCenter(new Vector2(0,AllVariables.FrontWheel.getLinearVelocity().x * 7), true);
                            }
                        }
                    }
                }
            }
        }

        //==================================================================================


    }

    public void render(){
        for (int i=0; i< VariablesForPlayArea.powerUpList.size(); i++){
            if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 1 || VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 2 || VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 4) {
                if (VariablesForPlayArea.powerUpList.get(i).active) {
                    AllVariables.batch.draw(VariablesForPlayArea.powerUpList.get(i).texture,
                            VariablesForPlayArea.powerUpList.get(i).x,
                            VariablesForPlayArea.powerUpList.get(i).y,
                            40, 40);
                }
            } else if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 3) {
                AllVariables.batch.draw(VariablesForPlayArea.powerUpList.get(i).texture, VariablesForPlayArea.powerUpList.get(i).x, VariablesForPlayArea.powerUpList.get(i).y, 64, 64);
                AllVariables.batch.draw(VariablesForPlayArea.powerUpList.get(i).texture, VariablesForPlayArea.powerUpList.get(i).x + 54, VariablesForPlayArea.powerUpList.get(i).y, 64, 64);
                AllVariables.batch.draw(VariablesForPlayArea.powerUpList.get(i).texture, VariablesForPlayArea.powerUpList.get(i).x + 108, VariablesForPlayArea.powerUpList.get(i).y, 64, 64);
            }
        }
    }
}
