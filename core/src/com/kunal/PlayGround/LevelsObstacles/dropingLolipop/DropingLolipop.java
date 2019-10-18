package com.kunal.PlayGround.LevelsObstacles.dropingLolipop;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

public class DropingLolipop {

    Body b;
    RevoluteJointDef rdef;

    public DropingLolipop(World world) {
        rdef = new RevoluteJointDef();

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
            world.createJoint(rdef);

            rdef = new RevoluteJointDef();
            rdef.bodyA = VariablesForPlayArea.dropingLolipop.get(i).candy;
            rdef.bodyB = VariablesForPlayArea.dropingLolipop.get(i).stick;
            rdef.localAnchorA.set(0,0);//VariablesForPlayArea.dropingLolipop.get(i).candyRadius/AllVariables.PPM);
            rdef.localAnchorB.set(0,-1*VariablesForPlayArea.dropingLolipop.get(i).stickLen/AllVariables.PPM);
            world.createJoint(rdef);





        }
    }

    public void update () {

    }

    public void render() {

    }

}
