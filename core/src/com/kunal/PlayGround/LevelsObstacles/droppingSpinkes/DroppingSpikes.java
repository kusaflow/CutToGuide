package com.kunal.PlayGround.LevelsObstacles.droppingSpinkes;

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

public class DroppingSpikes {

    Body b;
    RevoluteJointDef rdef;
    float spikescale = 1.4f;
    World world;
    Sprite spike;

    public DroppingSpikes (World world) {
        spike = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/spikes/spine.png")));
        spike.setScale(spikescale);

        rdef = new RevoluteJointDef();
        this.world = world;

        for (int i = 0; i< VariablesForPlayArea.dropingSpike.size(); i++){
            VariablesForPlayArea.dropingSpike.get(i).jointdestroyed = false;

            b = BodyGenerator.BodyAssemble(world, true, "spineHanger",
                    new Vector2(VariablesForPlayArea.dropingSpike.get(i).x, VariablesForPlayArea.dropingSpike.get(i).y),
                    new Vector2(10,10),0.5f,1, AllVariables.Bit_Tool,AllVariables.Bit_enimes);

            VariablesForPlayArea.dropingSpike.get(i).spine = BodyGenerator.BodyAssemble(world,false,"spine",
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
        for (int i =0; i< VariablesForPlayArea.dropingSpike.size(); i++) {
            //spike.setPosition(VariablesForPlayArea.dropingSpike.get(i).x-(12*spikescale), VariablesForPlayArea.dropingSpike.get(i).y+(9*spikescale));
            spike.setPosition((VariablesForPlayArea.dropingSpike.get(i).spine.getPosition().x * AllVariables.PPM) -(11*spikescale),
                    (VariablesForPlayArea.dropingSpike.get(i).spine.getPosition().y*AllVariables.PPM)-(16*spikescale));
            spike.setRotation(VariablesForPlayArea.dropingSpike.get(i).spine.getAngle() * MathUtils.radiansToDegrees);
            spike.draw(AllVariables.batch);
        }
    }

    public void update () {
        for (int i = 0; i < VariablesForPlayArea.dropingSpike.size(); i++) {
            //droping randomly
            if (!VariablesForPlayArea.dropingSpike.get(i).jointdestroyed) {
                if (VariablesForPlayArea.dropingSpike.get(i).x <= (AllVariables.FrontWheel.getPosition().x * AllVariables.PPM) + 300) {
                    try {
                        VariablesForPlayArea.dropingSpike.get(i).jointdestroyed = true;
                        world.destroyJoint(VariablesForPlayArea.dropingSpike.get(i).joint);
                        VariablesForPlayArea.dropingSpike.get(i).joint = null;
                        VariablesForPlayArea.dropingSpike.get(i).spine.applyForceToCenter(35, -50, true);

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }

}
