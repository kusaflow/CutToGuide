package com.kunal.PlayGround.LevelsObstacles.dropingLolipop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

import java.util.Random;

public class DropingLolipop {

    Body b;
    RevoluteJointDef rdef;
    int angle;
    Random r;

    public DropingLolipop(World world) {
        rdef = new RevoluteJointDef();
        r = new Random();

        for (int i =0; i< VariablesForPlayArea.dropingLolipop.size(); i++){
            b = BodyGenerator.BodyAssemble(world, true, "dropLolipop",
                    new Vector2(VariablesForPlayArea.dropingLolipop.get(i).x, VariablesForPlayArea.dropingLolipop.get(i).y),
                    new Vector2(10,10),0.5f,1, AllVariables.Bit_Tool,AllVariables.Bit_enimes);

            VariablesForPlayArea.dropingLolipop.get(i).stick = BodyGenerator.BodyAssemble(world, false, "dropLolipop",
                    new Vector2(VariablesForPlayArea.dropingLolipop.get(i).x, VariablesForPlayArea.dropingLolipop.get(i).y - VariablesForPlayArea.dropingLolipop.get(i).stickLen - 10),
                    new Vector2(VariablesForPlayArea.dropingLolipop.get(i).stickWid,VariablesForPlayArea.dropingLolipop.get(i).stickLen),
                    0.5f,1, AllVariables.Bit_Tool, (short)(AllVariables.Bit_land | AllVariables.Bit_Tool | AllVariables.Bit_Bicycle | AllVariables.Bit_enimes));

            VariablesForPlayArea.dropingLolipop.get(i).candy = BodyGenerator.CircleBody(world, false, "dropLolipop",
                    new Vector2(VariablesForPlayArea.dropingLolipop.get(i).x, VariablesForPlayArea.dropingLolipop.get(i).y - VariablesForPlayArea.dropingLolipop.get(i).stickLen*2 - VariablesForPlayArea.dropingLolipop.get(i).candyRadius - 10),
                    VariablesForPlayArea.dropingLolipop.get(i).candyRadius,0.8f,0.5f,
                    AllVariables.Bit_Tool, (short)(AllVariables.Bit_land | AllVariables.Bit_Tool | AllVariables.Bit_Bicycle | AllVariables.Bit_enimes));

            rdef = new RevoluteJointDef();
            rdef.bodyA = b;
            rdef.bodyB = VariablesForPlayArea.dropingLolipop.get(i).stick;
            rdef.localAnchorA.set(0,-10/AllVariables.PPM);
            rdef.localAnchorB.set(0,VariablesForPlayArea.dropingLolipop.get(i).stickLen/AllVariables.PPM);
            VariablesForPlayArea.dropingLolipop.get(i).joint = world.createJoint(rdef);

            rdef = new RevoluteJointDef();
            rdef.bodyA = VariablesForPlayArea.dropingLolipop.get(i).candy;
            rdef.bodyB = VariablesForPlayArea.dropingLolipop.get(i).stick;
            rdef.localAnchorA.set(0,0);//VariablesForPlayArea.dropingLolipop.get(i).candyRadius/AllVariables.PPM);
            rdef.localAnchorB.set(0,-1*VariablesForPlayArea.dropingLolipop.get(i).stickLen/AllVariables.PPM);
            world.createJoint(rdef);

            angle = r.nextInt(2);

            if (angle == 0){
                angle = r.nextInt(10);
            }else {
                angle = r.nextInt(10);
                angle*=-1;
            }

            VariablesForPlayArea.dropingLolipop.get(i).stick.setTransform(VariablesForPlayArea.dropingLolipop.get(i).stick.getPosition().x,
                    VariablesForPlayArea.dropingLolipop.get(i).stick.getPosition().y, angle);// * MathUtils.radiansToDegrees);

            //texturing
            VariablesForPlayArea.dropingLolipop.get(i).stickTex = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/dropingLolipop/lollipopBase.png")));
            if (VariablesForPlayArea.dropingLolipop.get(i).type == 1){
                VariablesForPlayArea.dropingLolipop.get(i).candyTex = new Sprite(new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre11.png")));
            } else if (VariablesForPlayArea.dropingLolipop.get(i).type == 2){
                VariablesForPlayArea.dropingLolipop.get(i).candyTex = new Sprite(new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre12.png")));
            } else if (VariablesForPlayArea.dropingLolipop.get(i).type == 3){
                VariablesForPlayArea.dropingLolipop.get(i).candyTex = new Sprite(new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre13.png")));
            }
        }
    }

    public void update () {

    }

    public void render() {
        for (int i = 0; i < VariablesForPlayArea.dropingLolipop.size(); i++) {
            
        }
    }

}
