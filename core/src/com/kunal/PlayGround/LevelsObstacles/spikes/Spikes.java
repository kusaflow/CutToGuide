package com.kunal.PlayGround.LevelsObstacles.spikes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

public class Spikes {

    Sprite spike;
    float spikescale =1.4f;

    public Spikes (World world) {
        spike = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/spikes/spine.png")));
        spike.setRotation(180);
        spike.setScale(spikescale);
        for (int i =0; i < VariablesForPlayArea.spike.size(); i++){
            BodyGenerator.BodyAssemble(world,true,"spine",
                    new Vector2(VariablesForPlayArea.spike.get(i).x, VariablesForPlayArea.spike.get(i).y),
                    new Vector2(13*spikescale,13*spikescale),0.5f,0.8f, AllVariables.Bit_enimes,
                    (short)(AllVariables.Bit_Bicycle | AllVariables.Bit_enimes | AllVariables.Bit_Tool)).
                    setTransform(new Vector2(VariablesForPlayArea.spike.get(i).x/AllVariables.PPM, VariablesForPlayArea.spike.get(i).y/AllVariables.PPM),
                            VariablesForPlayArea.spike.get(i).angle* MathUtils.degreesToRadians);

        }
    }


    public void render () {
        for (int i =0; i< VariablesForPlayArea.spike.size(); i++) {
            spike.setRotation(180 + VariablesForPlayArea.spike.get(i).angle);
            spike.setPosition(VariablesForPlayArea.spike.get(i).x-(12*spikescale), VariablesForPlayArea.spike.get(i).y-(9*spikescale));
            spike.draw(AllVariables.batch);
        }
    }

    public void update () {

    }

}
