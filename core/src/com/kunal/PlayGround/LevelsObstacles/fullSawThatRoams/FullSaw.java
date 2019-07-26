package com.kunal.PlayGround.LevelsObstacles.fullSawThatRoams;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

public class FullSaw {
    Sprite fullSaw, dead;
    FixtureDef fdef;

    public FullSaw(World world) {
        fullSaw = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/Enimies/FullSaw/fullSaw.png")));
        dead = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/Enimies/FullSaw/saw_dead.png")));
        for (int i=0; i< VariablesForPlayArea.fullSawList.size();i++){
            VariablesForPlayArea.fullSawList.get(i).body = BodyGenerator.CircleBody(world,false, "fullSaw",
                    new Vector2(VariablesForPlayArea.fullSawList.get(i).xpos, VariablesForPlayArea.fullSawList.get(i).ypos),
                    VariablesForPlayArea.fullSawList.get(i).size/2.3f,0.5f, 0.6f,
                    AllVariables.Bit_enimes,(short)(AllVariables.Bit_enimes|AllVariables.Bit_Bicycle|AllVariables.Bit_land|AllVariables.Bit_Tool));
            if (VariablesForPlayArea.fullSawList.get(i).initialdead)
                VariablesForPlayArea.fullSawList.get(i).dead = false;
            else
                VariablesForPlayArea.fullSawList.get(i).dead = true;
            if (VariablesForPlayArea.fullSawList.get(i).intialDirectionForward)
                VariablesForPlayArea.fullSawList.get(i).forwardDirection = true;
            else
                VariablesForPlayArea.fullSawList.get(i).forwardDirection = false;
        }


    }

    public void update(){
        for (int i=0; i<VariablesForPlayArea.fullSawList.size(); i++) {
            if (!VariablesForPlayArea.fullSawList.get(i).dead) {
                if (VariablesForPlayArea.fullSawList.get(i).forwardDirection)
                    VariablesForPlayArea.fullSawList.get(i).body.setAngularVelocity(-5);
                else
                    VariablesForPlayArea.fullSawList.get(i).body.setAngularVelocity(5);
            }

            //for bicycle ---------------------------------------------------------
            if (!VariablesForPlayArea.fullSawList.get(i).dead) {
                if (VariablesForPlayArea.rageMode) {
                    if (((AllVariables.FrontWheel.getPosition().x * AllVariables.PPM) + 25 + 5 >= VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM - 56 - 5 &&
                            (AllVariables.FrontWheel.getPosition().x * AllVariables.PPM) - 25 <= VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM + 56) ||
                            ((AllVariables.BackWheel.getPosition().x * AllVariables.PPM) - 25 >= VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM + -56 - 5 &&
                                    (AllVariables.BackWheel.getPosition().x * AllVariables.PPM) <= VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM + 56)) {
                        //------------------------------------------------------------------------------------------------------------------------------------------
                        if ((AllVariables.FrontWheel.getPosition().y * AllVariables.PPM) + (25) >= VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM - 56 ||
                                (AllVariables.BackWheel.getPosition().y * AllVariables.PPM) + (25) >= VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM - 56) {
                            //---------------------------------------------------------------------------------------------------------------------------------------
                            if ((AllVariables.FrontWheel.getPosition().y * AllVariables.PPM) - (25) <= VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM + 56 ||
                                    (AllVariables.BackWheel.getPosition().y * AllVariables.PPM) - (25) <= VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM + 56) {
                                //-----------------------------------------------------------------------------------------------------------------------------------
                                VariablesForPlayArea.fullSawList.get(i).dead = true;
                                VariablesForPlayArea.fullSawList.get(i).body.destroyFixture(VariablesForPlayArea.fullSawList.get(i).body.getFixtureList().get(0));

                            }
                        }
                        //===============================================///
                    }
                }

                //=====================================================================

                // for speed controller-----------------------------------------------
                for (int j =0; j<VariablesForPlayArea.speedCtrlList.size(); j++){
                    if (VariablesForPlayArea.speedCtrlList.get(j).active) {
                        if (VariablesForPlayArea.fullSawList.get(i).forwardDirection){
                            if (((VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM) + (56) >= VariablesForPlayArea.speedCtrlList.get(j).x &&
                                    (VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM) - (56) <= VariablesForPlayArea.speedCtrlList.get(j).x+40)) {
                                //---------------------------------------------------------------------------------------------------------
                                if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) + (56) >= VariablesForPlayArea.speedCtrlList.get(j).y) {
                                    //------------------------------------------------------------------------------------------------------
                                    if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) - (55) <= VariablesForPlayArea.speedCtrlList.get(j).y + 40) {
                                        //====================================================
                                        if (VariablesForPlayArea.speedCtrlList.get(j).SpeedIncrementor)
                                            VariablesForPlayArea.fullSawList.get(i).body.applyForceToCenter(new Vector2(600, 0), true);
                                        else
                                            VariablesForPlayArea.fullSawList.get(i).body.applyForceToCenter(new Vector2(VariablesForPlayArea.fullSawList.get(i).body.getLinearVelocity().x * (-30), 0), true);
                                        VariablesForPlayArea.speedCtrlList.get(j).active = false;
                                    }
                                }
                            }
                        }else {
                            if (((VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM) -56 <= VariablesForPlayArea.speedCtrlList.get(j).x+40 &&
                                    (VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM) + (56) >= VariablesForPlayArea.speedCtrlList.get(j).x)) {
                                //---------------------------------------------------------------------------------------------------------
                                if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) + (56) >= VariablesForPlayArea.speedCtrlList.get(j).y) {
                                    //------------------------------------------------------------------------------------------------------
                                    if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) - (55) <= VariablesForPlayArea.speedCtrlList.get(j).y + 40) {
                                        //====================================================
                                        if (VariablesForPlayArea.speedCtrlList.get(j).SpeedIncrementor)
                                            VariablesForPlayArea.fullSawList.get(i).body.applyForceToCenter(new Vector2(-600, 0), true);
                                        else
                                            VariablesForPlayArea.fullSawList.get(i).body.applyForceToCenter(new Vector2(VariablesForPlayArea.fullSawList.get(i).body.getLinearVelocity().x * (-30), 0), true);
                                        VariablesForPlayArea.speedCtrlList.get(j).active = false;
                                    }
                                }
                            }
                        }

                        //-------------------------------------------------------------------------
                    }
                }
                //Speed cnrt====================================================================

                //direction Reverse===================================================================
                for (int j =0; j<VariablesForPlayArea.dirRevList.size(); j++){
                    if (VariablesForPlayArea.dirRevList.get(j).active) {
                        if (VariablesForPlayArea.fullSawList.get(i).forwardDirection){
                            if (((VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM) + (56) >= VariablesForPlayArea.dirRevList.get(j).x &&
                                    (VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM) - (56) <= VariablesForPlayArea.dirRevList.get(j).x+40)) {
                                //---------------------------------------------------------------------------------------------------------
                                if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) + (56) >= VariablesForPlayArea.dirRevList.get(j).y) {
                                    //------------------------------------------------------------------------------------------------------
                                    if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) - (55) <= VariablesForPlayArea.dirRevList.get(j).y + 40) {
                                        //====================================================
                                        VariablesForPlayArea.fullSawList.get(i).forwardDirection = !VariablesForPlayArea.fullSawList.get(i).forwardDirection;
                                        VariablesForPlayArea.dirRevList.get(j).active = false;
                                    }
                                }
                            }
                        }else {
                            if (((VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM) -56 <= VariablesForPlayArea.dirRevList.get(j).x+40 &&
                                    (VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM) + (56) >= VariablesForPlayArea.dirRevList.get(j).x)) {
                                //---------------------------------------------------------------------------------------------------------
                                if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) + (56) >= VariablesForPlayArea.dirRevList.get(j).y) {
                                    //------------------------------------------------------------------------------------------------------
                                    if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) - (55) <= VariablesForPlayArea.dirRevList.get(j).y + 40) {
                                        //====================================================
                                        VariablesForPlayArea.fullSawList.get(i).forwardDirection = !VariablesForPlayArea.fullSawList.get(i).forwardDirection;
                                        VariablesForPlayArea.dirRevList.get(j).active = false;
                                    }
                                }
                            }
                        }

                        //-------------------------------------------------------------------------
                    }
                }
                //dirREverse-----------------------------------------------------------------------

                //jumper=======================================================================
                for (int j =0; j<VariablesForPlayArea.jumperList.size(); j++){
                    if (!VariablesForPlayArea.jumperList.get(j).textureChanged){
                        if (((VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM)-56 <= VariablesForPlayArea.jumperList.get(j).x + 108 + 64 &&
                                (VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM)+56 >= VariablesForPlayArea.jumperList.get(j).x)) {
                            //-----------------------------
                            if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) + (56) >= VariablesForPlayArea.jumperList.get(j).y) {
                                //----------------------------------
                                if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) - (56) <= VariablesForPlayArea.jumperList.get(j).y + 23) {
                                    VariablesForPlayArea.jumperList.get(j).textureChanged = true;
                                    VariablesForPlayArea.fullSawList.get(i).body.applyForceToCenter(new Vector2(0,400), true);
                                }
                            }
                        }

                    }
                }
                //jumper-----------------------------------------------------------------------

                //powerUps=====================================================================
                //speed--- and dirChange
                for (int j =0; j<VariablesForPlayArea.powerUpList.size(); j++){
                    if (VariablesForPlayArea.powerUpList.get(j).active){
                        if (VariablesForPlayArea.powerUpList.get(j).TypeOfPower == 1 || VariablesForPlayArea.powerUpList.get(j).TypeOfPower == 2 || VariablesForPlayArea.powerUpList.get(j).TypeOfPower ==4){
                            if (VariablesForPlayArea.fullSawList.get(i).forwardDirection){
                                if (((VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM) + (56) >= VariablesForPlayArea.powerUpList.get(j).x &&
                                        (VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM) - (56) <= VariablesForPlayArea.powerUpList.get(j).x+40)) {
                                    //---------------------------------------------------------------------------------------------------------
                                    if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) + (56) >= VariablesForPlayArea.powerUpList.get(j).y) {
                                        //------------------------------------------------------------------------------------------------------
                                        if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) - (55) <= VariablesForPlayArea.powerUpList.get(j).y + 40) {
                                            //====================================================
                                            if (VariablesForPlayArea.powerUpList.get(j).TypeOfPower == 1)
                                                VariablesForPlayArea.fullSawList.get(i).body.applyForceToCenter(new Vector2(600, 0), true);
                                            else if (VariablesForPlayArea.powerUpList.get(j).TypeOfPower == 2)
                                                VariablesForPlayArea.fullSawList.get(i).body.applyForceToCenter(new Vector2(VariablesForPlayArea.fullSawList.get(i).body.getLinearVelocity().x * (-20), 0), true);
                                            else if (VariablesForPlayArea.powerUpList.get(j).TypeOfPower == 4)
                                                VariablesForPlayArea.fullSawList.get(i).forwardDirection = !VariablesForPlayArea.fullSawList.get(i).forwardDirection;

                                            VariablesForPlayArea.powerUpList.get(j).active = false;
                                        }
                                    }
                                }
                            }else {
                                if (((VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM) -56 <= VariablesForPlayArea.powerUpList.get(j).x+40 &&
                                        (VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM) + (56) >= VariablesForPlayArea.powerUpList.get(j).x)) {
                                    //---------------------------------------------------------------------------------------------------------
                                    if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) + (56) >= VariablesForPlayArea.powerUpList.get(j).y) {
                                        //------------------------------------------------------------------------------------------------------
                                        if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) - (55) <= VariablesForPlayArea.powerUpList.get(j).y + 40) {
                                            //====================================================
                                            if (VariablesForPlayArea.powerUpList.get(j).TypeOfPower == 1)
                                                VariablesForPlayArea.fullSawList.get(i).body.applyForceToCenter(new Vector2(-600, 0), true);
                                            else if (VariablesForPlayArea.powerUpList.get(j).TypeOfPower == 2)
                                                VariablesForPlayArea.fullSawList.get(i).body.applyForceToCenter(new Vector2(VariablesForPlayArea.fullSawList.get(i).body.getLinearVelocity().x * (-30), 0), true);
                                            else if (VariablesForPlayArea.powerUpList.get(j).TypeOfPower == 4)
                                                VariablesForPlayArea.fullSawList.get(i).forwardDirection = !VariablesForPlayArea.fullSawList.get(i).forwardDirection;
                                            VariablesForPlayArea.powerUpList.get(j).active = false;
                                        }
                                    }
                                }
                            }
                        }
                        // for jumper
                        else if (VariablesForPlayArea.powerUpList.get(j).TypeOfPower == 3){
                            if (VariablesForPlayArea.powerUpList.get(j).active){
                                if (((VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM)-56 <= VariablesForPlayArea.powerUpList.get(j).x + 108 + 64 &&
                                        (VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM)+56 >= VariablesForPlayArea.powerUpList.get(j).x)) {
                                    //-----------------------------
                                    if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) + (56) >= VariablesForPlayArea.powerUpList.get(j).y) {
                                        //----------------------------------
                                        if ((VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM) - (56) <= VariablesForPlayArea.powerUpList.get(j).y + 23) {
                                            //------------------
                                            VariablesForPlayArea.powerUpList.get(j).active= false;
                                            VariablesForPlayArea.powerUpList.get(j).texture = new Texture(Gdx.files.internal("playArea/LevelObstacles/Jumper/sprung.png"));
                                            VariablesForPlayArea.fullSawList.get(i).body.applyForceToCenter(new Vector2(0,400), true);
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
                //powerups---------------------------------------------------------------------


            }


        }


    }

    public void render(){
        for (int i=0; i<VariablesForPlayArea.fullSawList.size(); i++){
            if (!VariablesForPlayArea.fullSawList.get(i).dead) {
                fullSaw.setOriginCenter();
                fullSaw.setPosition(VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM - (VariablesForPlayArea.fullSawList.get(i).size / 2),
                        VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM - (VariablesForPlayArea.fullSawList.get(i).size / 2));
                fullSaw.setSize(VariablesForPlayArea.fullSawList.get(i).size, VariablesForPlayArea.fullSawList.get(i).size);
                fullSaw.setRotation((int) (VariablesForPlayArea.fullSawList.get(i).body.getAngle() * (180 / Math.PI)));
                fullSaw.draw(AllVariables.batch);
            }else {
                dead.setOriginCenter();
                dead.setPosition(VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM - (VariablesForPlayArea.fullSawList.get(i).size / 2),
                        VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM - (VariablesForPlayArea.fullSawList.get(i).size / 2));
                dead.setSize(VariablesForPlayArea.fullSawList.get(i).size, VariablesForPlayArea.fullSawList.get(i).size);
                dead.setRotation((int) (VariablesForPlayArea.fullSawList.get(i).body.getAngle() * (180 / Math.PI)));
                dead.draw(AllVariables.batch);
            }
        }
    }
}
