package com.kunal.PlayGround;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.kunal.AllVariables;

public class PlayAreaUtils {

    public PlayAreaUtils() {

    }

    public void MoveShapesToRealWorld(){
        for (Body b : VariablesForPlayArea.CutOutBodies){
            FixtureDef fdef = new FixtureDef();
            fdef.density = b.getFixtureList().get(0).getDensity();
            fdef.friction = b.getFixtureList().get(0).getFriction();
            fdef.restitution = b.getFixtureList().get(0).getRestitution();
            fdef.shape = b.getFixtureList().get(0).getShape();
            fdef.filter.categoryBits = AllVariables.Bit_Tool;
            fdef.filter.maskBits = (short)(AllVariables.Bit_Bicycle | AllVariables.Bit_Tool | AllVariables.Bit_land | AllVariables.Bit_enimes);

            b.createFixture(fdef);

        }
    }


}
