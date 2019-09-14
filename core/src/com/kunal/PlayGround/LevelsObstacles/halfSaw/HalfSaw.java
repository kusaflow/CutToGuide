package com.kunal.PlayGround.LevelsObstacles.halfSaw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

public class HalfSaw {
    Texture t1,t2, tdead;

    public HalfSaw() {
        t1 = new Texture(Gdx.files.internal("playArea/LevelObstacles/Enimies/halfSaw/sawHalf1_2.png"));
        t2 = new Texture(Gdx.files.internal("playArea/LevelObstacles/Enimies/halfSaw/sawHalf2_2.png"));
        tdead = new Texture(Gdx.files.internal("playArea/LevelObstacles/Enimies/halfSaw/deadsaw.png"));
        for (int i=0; i<VariablesForPlayArea.halfSawList.size(); i++) {
            VariablesForPlayArea.halfSawList.get(i).xpos = VariablesForPlayArea.halfSawList.get(i).xorigin;
            VariablesForPlayArea.halfSawList.get(i).ypos = VariablesForPlayArea.halfSawList.get(i).yorigin;
            VariablesForPlayArea.halfSawList.get(i).dead = false;
        }
    }

    public void update(){
        for (int i=0; i<VariablesForPlayArea.halfSawList.size(); i++) {

            if (!VariablesForPlayArea.halfSawList.get(i).dead) {
                if (VariablesForPlayArea.halfSawList.get(i).xpos <= VariablesForPlayArea.halfSawList.get(i).xorigin) {
                    VariablesForPlayArea.halfSawList.get(i).movingForward = true;
                } else if (VariablesForPlayArea.halfSawList.get(i).xpos >= VariablesForPlayArea.halfSawList.get(i).xdestination) {
                    VariablesForPlayArea.halfSawList.get(i).movingForward = false;
                }

                if (VariablesForPlayArea.halfSawList.get(i).movingForward)
                    VariablesForPlayArea.halfSawList.get(i).xpos += 5;
                else
                    VariablesForPlayArea.halfSawList.get(i).xpos -= 5;

                //collision
                if (((AllVariables.FrontWheel.getPosition().x * AllVariables.PPM) <= VariablesForPlayArea.halfSawList.get(i).xpos + 128 &&
                        (AllVariables.BackWheel.getPosition().x * AllVariables.PPM) >= VariablesForPlayArea.halfSawList.get(i).xpos) ||
                        ((AllVariables.FrontWheel.getPosition().x * AllVariables.PPM)-25 >= VariablesForPlayArea.halfSawList.get(i).xpos + 128 &&
                                (AllVariables.BackWheel.getPosition().x * AllVariables.PPM) <= VariablesForPlayArea.halfSawList.get(i).xpos+128)) {
                    if ((AllVariables.FrontWheel.getPosition().y * AllVariables.PPM) + (25) >= VariablesForPlayArea.halfSawList.get(i).ypos ||
                            (AllVariables.BackWheel.getPosition().y * AllVariables.PPM) + (25) >= VariablesForPlayArea.halfSawList.get(i).ypos) {
                        if ((AllVariables.FrontWheel.getPosition().y * AllVariables.PPM) - (25) <= VariablesForPlayArea.halfSawList.get(i).ypos + 50 ||
                                (AllVariables.BackWheel.getPosition().y * AllVariables.PPM) - (25) <= VariablesForPlayArea.halfSawList.get(i).ypos + 50) {
                            if (VariablesForPlayArea.rageMode)
                                VariablesForPlayArea.halfSawList.get(i).dead = true;
                            else
                                VariablesForPlayArea.gameOver = true;

                        }
                    }
                }
                //collision
            }
        }
    }

    public void render(){
        for (int i=0; i<VariablesForPlayArea.halfSawList.size(); i++){
            if (!VariablesForPlayArea.halfSawList.get(i).dead) {
                if (VariablesForPlayArea.halfSawList.get(i).texchange % 5 == 0) {
                    VariablesForPlayArea.halfSawList.get(i).oneisSelected = !VariablesForPlayArea.halfSawList.get(i).oneisSelected;
                }
                if (VariablesForPlayArea.halfSawList.get(i).oneisSelected) {
                    AllVariables.batch.draw(t1, VariablesForPlayArea.halfSawList.get(i).xpos, VariablesForPlayArea.halfSawList.get(i).ypos);
                } else {
                    AllVariables.batch.draw(t2, VariablesForPlayArea.halfSawList.get(i).xpos, VariablesForPlayArea.halfSawList.get(i).ypos);
                }

                VariablesForPlayArea.halfSawList.get(i).texchange++;
                if (VariablesForPlayArea.halfSawList.get(i).texchange == 2000) {
                    VariablesForPlayArea.halfSawList.get(i).texchange = 0;
                }
            }else{
                AllVariables.batch.draw(tdead, VariablesForPlayArea.halfSawList.get(i).xpos, VariablesForPlayArea.halfSawList.get(i).ypos);
            }
        }
    }

    public void dispose () {
        t1.dispose();
        t2.dispose();
        tdead.dispose();
    }
}
