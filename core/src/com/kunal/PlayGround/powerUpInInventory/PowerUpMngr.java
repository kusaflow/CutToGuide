package com.kunal.PlayGround.powerUpInInventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

public class PowerUpMngr {

    public PowerUpMngr() {
        for (int i =0; i < VariablesForPlayArea.powerUpList.size(); i++){
            if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 1){
                VariablesForPlayArea.powerUpList.get(i).texture = new Texture(Gdx.files.internal("playArea/speedPowers/GreenPill.png"));
            }else if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 2){
                VariablesForPlayArea.powerUpList.get(i).texture = new Texture(Gdx.files.internal("playArea/speedPowers/RedPill.png"));
            }else if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 3){
                VariablesForPlayArea.powerUpList.get(i).texture = new Texture(Gdx.files.internal("playArea/LevelObstacles/Jumper/spring.png"));
            }

        }
    }

    public void update(){

        /*

        //collision mapper for powerUps============================================
        for (int i =0; i<VariablesForPlayArea.powerUpPos.size(); i++){
            if ((AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)+(25+5) >= VariablesForPlayArea.powerUpPos.get(i).x-5 &&
                    (AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)-(25+100+5) <= VariablesForPlayArea.powerUpPos.get(i).x){
                if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)+(55+5) >= VariablesForPlayArea.powerUpPos.get(i).y-5 ||
                        (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)+(55+5) >= VariablesForPlayArea.powerUpPos.get(i).y - 5) {
                    if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)-(25+5) <= VariablesForPlayArea.powerUpPos.get(i).y + 40 +5 ||
                            (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)-(25+5) <= VariablesForPlayArea.powerUpPos.get(i).y+50) {
                        if (VariablesForPlayArea.powerUps.get(i) == 1){
                            AllVariables.BackWheel.applyForceToCenter(new Vector2(200,0), true);
                        }
                        if (VariablesForPlayArea.powerUps.get(i) == 2){
                            AllVariables.BackWheel.applyForceToCenter(new Vector2(AllVariables.BackWheel.getLinearVelocity().x *(-0),0), true);
                        }
                        VariablesForPlayArea.powerUpPos.set(i, new Vector2(0, -1000));
                    }
                }
            }
        }

        //==================================================================================

         */

    }

    public void render(){
        for (int i=0; i< VariablesForPlayArea.powerUpList.size(); i++){
            if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 1 || VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 2){
                AllVariables.batch.draw(VariablesForPlayArea.powerUpList.get(i).texture,
                        VariablesForPlayArea.powerUpList.get(i).x,
                        VariablesForPlayArea.powerUpList.get(i).y,
                        40, 40);
            }else if (VariablesForPlayArea.powerUpList.get(i).TypeOfPower == 3){

            }
        }
    }
}
