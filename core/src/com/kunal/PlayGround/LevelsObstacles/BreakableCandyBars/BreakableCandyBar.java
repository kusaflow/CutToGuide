package com.kunal.PlayGround.LevelsObstacles.BreakableCandyBars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

import java.util.LinkedList;
import java.util.Random;

public class BreakableCandyBar {

    Body body;
    RevoluteJointDef rdef;
    int xcord, ycord;

    World world;

    public BreakableCandyBar (World world) {
        rdef = new RevoluteJointDef();

        this.world = world;

        for (int i =0; i<VariablesForPlayArea.breakingCandyBar.size(); i++) {
            VariablesForPlayArea.breakingCandyBar.get(i).broken = false;
            VariablesForPlayArea.breakingCandyBar.get(i).contactHappend = false;
            //init
            VariablesForPlayArea.breakingCandyBar.get(i).body = new LinkedList<Body>();
            VariablesForPlayArea.breakingCandyBar.get(i).joints = new LinkedList<Joint>();

            VariablesForPlayArea.breakingCandyBar.get(i).midPart = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/BreakableCandyBar/mid" + VariablesForPlayArea.breakingCandyBar.get(i).type + ".png")));
            VariablesForPlayArea.breakingCandyBar.get(i).endPart = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/BreakableCandyBar/end" + VariablesForPlayArea.breakingCandyBar.get(i).type + ".png")));

            VariablesForPlayArea.breakingCandyBar.get(i).midPart.setSize(VariablesForPlayArea.breakingCandyBar.get(i).size*2, VariablesForPlayArea.breakingCandyBar.get(i).size*2);
            VariablesForPlayArea.breakingCandyBar.get(i).endPart.setSize(VariablesForPlayArea.breakingCandyBar.get(i).size*2, VariablesForPlayArea.breakingCandyBar.get(i).size*2);

            VariablesForPlayArea.breakingCandyBar.get(i).midPart.setOriginCenter();
            VariablesForPlayArea.breakingCandyBar.get(i).endPart.setOriginCenter();

            //cord to draw
            xcord = VariablesForPlayArea.breakingCandyBar.get(i).originX;
            ycord = VariablesForPlayArea.breakingCandyBar.get(i).originY;


            for (int j =0; j < VariablesForPlayArea.breakingCandyBar.get(i).length; j++){
                body = BodyGenerator.BodyAssemble(world,false, "candyBar"+i,
                        new Vector2(xcord,
                                ycord),
                        new Vector2(VariablesForPlayArea.breakingCandyBar.get(i).size,VariablesForPlayArea.breakingCandyBar.get(i).size),
                        0.6f,0.8f, AllVariables.Bit_Tool, (short)(AllVariables.Bit_land | AllVariables.Bit_Bicycle | AllVariables.Bit_enimes | AllVariables.Bit_Tool));
                body.setTransform(body.getPosition().x, body.getPosition().y, (VariablesForPlayArea.breakingCandyBar.get(i).angle+90)*MathUtils.degreesToRadians);
                body.setFixedRotation(true);

                //updating postion
                xcord += VariablesForPlayArea.breakingCandyBar.get(i).size * 2 * (MathUtils.cos(VariablesForPlayArea.breakingCandyBar.get(i).angle * MathUtils.degreesToRadians));
                ycord += VariablesForPlayArea.breakingCandyBar.get(i).size * 2 * (MathUtils.sin(VariablesForPlayArea.breakingCandyBar.get(i).angle * MathUtils.degreesToRadians));

                VariablesForPlayArea.breakingCandyBar.get(i).body.add(body);
                if (j >=1){
                    rdef = new RevoluteJointDef();
                    rdef.bodyA = VariablesForPlayArea.breakingCandyBar.get(i).body.get(j-1);
                    rdef.bodyB = VariablesForPlayArea.breakingCandyBar.get(i).body.get(j);
                    rdef.collideConnected = true;
                    rdef.localAnchorA.set(0,-1*VariablesForPlayArea.breakingCandyBar.get(i).size/AllVariables.PPM);
                    rdef.localAnchorB.set(0,VariablesForPlayArea.breakingCandyBar.get(i).size/AllVariables.PPM);
                    rdef.lowerAngle = (float) (-0.5f * Math.PI);
                    rdef.upperAngle = (float) (0.5f * Math.PI);
                    VariablesForPlayArea.breakingCandyBar.get(i).joints.add(world.createJoint(rdef));
                }
            }
        }
    }


    public void render () {
        for (int i =0; i<VariablesForPlayArea.breakingCandyBar.size(); i++){
            for (int j=0; j<VariablesForPlayArea.breakingCandyBar.get(i).length; j++){
                VariablesForPlayArea.breakingCandyBar.get(i).midPart.setPosition((VariablesForPlayArea.breakingCandyBar.get(i).body.get(j).getPosition().x*AllVariables.PPM) - (VariablesForPlayArea.breakingCandyBar.get(i).midPart.getWidth()/2),
                        (VariablesForPlayArea.breakingCandyBar.get(i).body.get(j).getPosition().y*AllVariables.PPM) - (VariablesForPlayArea.breakingCandyBar.get(i).midPart.getWidth()/2));
                VariablesForPlayArea.breakingCandyBar.get(i).endPart.setPosition((VariablesForPlayArea.breakingCandyBar.get(i).body.get(j).getPosition().x*AllVariables.PPM) - (VariablesForPlayArea.breakingCandyBar.get(i).endPart.getWidth()/2),
                        (VariablesForPlayArea.breakingCandyBar.get(i).body.get(j).getPosition().y*AllVariables.PPM) - (VariablesForPlayArea.breakingCandyBar.get(i).endPart.getWidth()/2));
                VariablesForPlayArea.breakingCandyBar.get(i).midPart.setRotation(VariablesForPlayArea.breakingCandyBar.get(i).body.get(j).getAngle()* MathUtils.radiansToDegrees);
                VariablesForPlayArea.breakingCandyBar.get(i).endPart.setRotation(VariablesForPlayArea.breakingCandyBar.get(i).body.get(j).getAngle()* MathUtils.radiansToDegrees);

                if (j==0 || j == VariablesForPlayArea.breakingCandyBar.get(i).length-1) {
                    if (j == 0) {
                        VariablesForPlayArea.breakingCandyBar.get(i).endPart.draw(AllVariables.batch);
                    }else{
                        VariablesForPlayArea.breakingCandyBar.get(i).endPart.flip(true, true);
                        VariablesForPlayArea.breakingCandyBar.get(i).endPart.draw(AllVariables.batch);
                        VariablesForPlayArea.breakingCandyBar.get(i).endPart.flip(true, true);
                    }
                } else {
                    VariablesForPlayArea.breakingCandyBar.get(i).midPart.draw(AllVariables.batch);
                }
            }
        }

    }

    public void update () {
        for (int i =0; i<VariablesForPlayArea.breakingCandyBar.size(); i++){
            //System.out.println(VariablesForPlayArea.breakingCandyBar.get(i).broken);
            if (VariablesForPlayArea.breakingCandyBar.get(i).contactHappend){
                if (!VariablesForPlayArea.breakingCandyBar.get(i).broken){
                    VariablesForPlayArea.breakingCandyBar.get(i).broken = true;
                    breakThem(i);
                }
            }
        }
    }

    private void breakThem(int i){
        Random r = new Random();
        int randomVar;
        for (int j =0; j<VariablesForPlayArea.breakingCandyBar.get(i).joints.size(); j++){
            VariablesForPlayArea.breakingCandyBar.get(i).body.get(j).setFixedRotation(false);
            //System.out.println(j);
            randomVar = r.nextInt(3);
            if (randomVar != 1){
                world.destroyJoint(VariablesForPlayArea.breakingCandyBar.get(i).joints.get(j));
            }
        }
    }

}
