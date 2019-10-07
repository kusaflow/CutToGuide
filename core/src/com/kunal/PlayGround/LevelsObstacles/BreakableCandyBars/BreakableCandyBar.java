package com.kunal.PlayGround.LevelsObstacles.BreakableCandyBars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

public class BreakableCandyBar {

    Sprite midPart, endPart;

    Body body;

    public BreakableCandyBar (World world) {
        for (int i =0; i<VariablesForPlayArea.breakingCandyBar.size(); i++) {
            midPart = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/BreakableCandyBar/mid" + VariablesForPlayArea.breakingCandyBar.get(i).type + ".png")));
            endPart = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/BreakableCandyBar/end" + VariablesForPlayArea.breakingCandyBar.get(i).type + ".png")));
            for (int j =0; j < VariablesForPlayArea.breakingCandyBar.get(i).length; j++){
                body = BodyGenerator.BodyAssemble(world,false, "candyBar"+i,
                        new Vector2(VariablesForPlayArea.breakingCandyBar.get(i).originX + j*VariablesForPlayArea.breakingCandyBar.get(i).size,
                                VariablesForPlayArea.breakingCandyBar.get(i).originY + j*VariablesForPlayArea.breakingCandyBar.get(i).size),
                        new Vector2(VariablesForPlayArea.breakingCandyBar.get(i).size,VariablesForPlayArea.breakingCandyBar.get(i).size),
                        0.6f,0.8f, AllVariables.Bit_Tool, (short)(AllVariables.Bit_land | AllVariables.Bit_Bicycle | AllVariables.Bit_enimes | AllVariables.Bit_Tool));
                if (j == 0){
                    VariablesForPlayArea.breakingCandyBar.get(i).controllingBoneEnd1 = body;
                } else if (j == VariablesForPlayArea.breakingCandyBar.get(i).length -1) {
                    VariablesForPlayArea.breakingCandyBar.get(i).controllingBoneEnd2 = body;
                } else {

                }
            }
        }
    }


    public void render () {
        for (int i =0; i<VariablesForPlayArea.breakingCandyBar.size(); i++){

        }

    }

    public void update () {

    }

}
