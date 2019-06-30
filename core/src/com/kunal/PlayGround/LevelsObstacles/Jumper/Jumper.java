package com.kunal.PlayGround.LevelsObstacles.Jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.TiledMapLoadingHelper;

public class Jumper {

    Texture spring, spung;

    public Jumper() {
        spring = new Texture(Gdx.files.internal("playArea/LevelObstacles/Jumper/spring.png"));
        spung  = new Texture(Gdx.files.internal("playArea/LevelObstacles/Jumper/sprung.png"));
        for (int i=0; i<VariablesForPlayArea.jumperList.size(); i++){
            VariablesForPlayArea.jumperList.get(i).textureChanged = false;
        }
    }

    //108
    public void render(){
        for (int i=0; i< VariablesForPlayArea.jumperList.size();i++){
            if (!VariablesForPlayArea.jumperList.get(i).textureChanged) {
                AllVariables.batch.draw(spring, VariablesForPlayArea.jumperList.get(i).x, VariablesForPlayArea.jumperList.get(i).y, 64, 64);
                AllVariables.batch.draw(spring, VariablesForPlayArea.jumperList.get(i).x + 54, VariablesForPlayArea.jumperList.get(i).y, 64, 64);
                AllVariables.batch.draw(spring, VariablesForPlayArea.jumperList.get(i).x + 108, VariablesForPlayArea.jumperList.get(i).y, 64, 64);
            }else {
                AllVariables.batch.draw(spung, VariablesForPlayArea.jumperList.get(i).x, VariablesForPlayArea.jumperList.get(i).y, 64, 64);
                AllVariables.batch.draw(spung, VariablesForPlayArea.jumperList.get(i).x + 54, VariablesForPlayArea.jumperList.get(i).y, 64, 64);
                AllVariables.batch.draw(spung, VariablesForPlayArea.jumperList.get(i).x + 108, VariablesForPlayArea.jumperList.get(i).y, 64, 64);
            }
        }
    }

    public void update(){
        for (int i=0; i<VariablesForPlayArea.jumperList.size(); i++) {
            if (!VariablesForPlayArea.jumperList.get(i).textureChanged) {
                if (((AllVariables.FrontWheel.getPosition().x * AllVariables.PPM) <= VariablesForPlayArea.jumperList.get(i).x + 108 + 64 &&
                        (AllVariables.BackWheel.getPosition().x * AllVariables.PPM) >= VariablesForPlayArea.jumperList.get(i).x) ||
                        ((AllVariables.FrontWheel.getPosition().x * AllVariables.PPM)-25 >= VariablesForPlayArea.jumperList.get(i).x + 172 &&
                                (AllVariables.BackWheel.getPosition().x * AllVariables.PPM) <= VariablesForPlayArea.jumperList.get(i).x+172)) {
                    if ((AllVariables.FrontWheel.getPosition().y * AllVariables.PPM) + (55) >= VariablesForPlayArea.jumperList.get(i).y ||
                            (AllVariables.BackWheel.getPosition().y * AllVariables.PPM) + (55) >= VariablesForPlayArea.jumperList.get(i).y) {
                        if ((AllVariables.FrontWheel.getPosition().y * AllVariables.PPM) - (25 + 5) <= VariablesForPlayArea.jumperList.get(i).y + 23 ||
                                (AllVariables.BackWheel.getPosition().y * AllVariables.PPM) - (25 + 5) <= VariablesForPlayArea.jumperList.get(i).y + 23) {
                            VariablesForPlayArea.jumperList.get(i).textureChanged = true;
                            AllVariables.BackWheel.applyForceToCenter(new Vector2(0,AllVariables.FrontWheel.getLinearVelocity().x * 6.5f), true);
                            AllVariables.FrontWheel.applyForceToCenter(new Vector2(0,AllVariables.FrontWheel.getLinearVelocity().x * 7), true);
                        }
                    }
                }
            }
        }
    }

}
