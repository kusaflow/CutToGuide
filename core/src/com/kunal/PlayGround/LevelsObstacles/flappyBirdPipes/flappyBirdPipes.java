package com.kunal.PlayGround.LevelsObstacles.flappyBirdPipes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

public class flappyBirdPipes {

    public flappyBirdPipes(World world){
        for (int i=0; i< VariablesForPlayArea.flappyBirdPipesPiles.size(); i++) {
            //lower Part
            BodyGenerator.BodyAssemble(world, true, "obstacle",
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).x,VariablesForPlayArea.flappyBirdPipesPiles.get(i).y),
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength,VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom),
                    0.5f,1, AllVariables.Bit_land, (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool));
            //lower end
            BodyGenerator.BodyAssemble(world, true, "obstacle",
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).x,VariablesForPlayArea.flappyBirdPipesPiles.get(i).y +VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom),
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength+12,30),
                    0.5f,1, AllVariables.Bit_land, (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool));
            //upper end
            BodyGenerator.BodyAssemble(world, true, "obstacle",
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).x,VariablesForPlayArea.flappyBirdPipesPiles.get(i).y +VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom+VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapVerticalLength),
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength+12,30),
                    0.5f,1, AllVariables.Bit_land, (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool));
            //upper part
            BodyGenerator.BodyAssemble(world, true, "obstacle",
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).x,VariablesForPlayArea.flappyBirdPipesPiles.get(i).y +VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom+VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapVerticalLength + 700),
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength,700),
                    0.5f,1, AllVariables.Bit_land, (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool));
        }
    }

    public void render(){

    }

    public void update(){

    }
}
