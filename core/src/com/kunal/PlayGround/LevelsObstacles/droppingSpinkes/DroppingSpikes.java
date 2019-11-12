package com.kunal.PlayGround.LevelsObstacles.droppingSpinkes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

public class DroppingSpikes {

    Body b;
    RevoluteJointDef rdef;
    float spikescale = 1.4f;

    public DroppingSpikes (World world) {
        rdef = new RevoluteJointDef();

        for (int i = 0; i< VariablesForPlayArea.dropingSpike.size(); i++){
            VariablesForPlayArea.dropingSpike.get(i).jointdestroyed = false;

            b = BodyGenerator.BodyAssemble(world, true, "spineHanger",
                    new Vector2(VariablesForPlayArea.dropingLolipop.get(i).x, VariablesForPlayArea.dropingLolipop.get(i).y),
                    new Vector2(10,10),0.5f,1, AllVariables.Bit_Tool,AllVariables.Bit_enimes);

            VariablesForPlayArea.dropingSpike.get(i).spine = BodyGenerator.BodyAssemble(world,true,"spine",
                    new Vector2(VariablesForPlayArea.dropingSpike.get(i).x, VariablesForPlayArea.dropingSpike.get(i).y),
                    new Vector2(13*spikescale,13*spikescale),0.5f,0.8f, AllVariables.Bit_enimes,
                    (short)(AllVariables.Bit_Bicycle | AllVariables.Bit_enimes | AllVariables.Bit_Tool));

            rdef = new RevoluteJointDef();
            rdef.bodyA = b;
            rdef.bodyB = VariablesForPlayArea.dropingSpike.get(i).spine;
            rdef.localAnchorA.set(0,-10/AllVariables.PPM);
            rdef.localAnchorB.set(0,0);
            VariablesForPlayArea.dropingSpike.get(i).joint = world.createJoint(rdef);



        }

    }

    public void render () {

    }

    public void update () {

    }

}
