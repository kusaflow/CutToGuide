package com.kunal.PlayGround.LevelsObstacles.flappyBirdPipes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

public class flappyBirdPipes {

    Texture tile_contiEndPipe, tile_solid, tile_endArea;

    public flappyBirdPipes(World world){
        tile_endArea = new Texture(Gdx.files.internal("playArea/LevelObstacles/FlappyBirdPipes/endAreaPipe.png"));
        tile_solid = new Texture(Gdx.files.internal("playArea/LevelObstacles/FlappyBirdPipes/solidPipe.png"));
        tile_contiEndPipe = new Texture(Gdx.files.internal("playArea/LevelObstacles/FlappyBirdPipes/contiEndPipe.png"));

        for (int i=0; i< VariablesForPlayArea.flappyBirdPipesList.size(); i++) {
            //lower Part
            BodyGenerator.BodyAssemble(world, true, "obstacle",
                    new Vector2(VariablesForPlayArea.flappyBirdPipesList.get(i).x,VariablesForPlayArea.flappyBirdPipesList.get(i).y),
                    new Vector2(VariablesForPlayArea.flappyBirdPipesList.get(i).gapeHorizontalLength,VariablesForPlayArea.flappyBirdPipesList.get(i).gapStartFrombottom),
                    0.5f,1, AllVariables.Bit_land, (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool));
            //lower end
            BodyGenerator.BodyAssemble(world, true, "obstacle",
                    new Vector2(VariablesForPlayArea.flappyBirdPipesList.get(i).x,VariablesForPlayArea.flappyBirdPipesList.get(i).y +VariablesForPlayArea.flappyBirdPipesList.get(i).gapStartFrombottom-15),
                    new Vector2(VariablesForPlayArea.flappyBirdPipesList.get(i).gapeHorizontalLength+16,32),
                    0.5f,1, AllVariables.Bit_land, (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool));
            //upper end
            BodyGenerator.BodyAssemble(world, true, "obstacle",
                    new Vector2(VariablesForPlayArea.flappyBirdPipesList.get(i).x,VariablesForPlayArea.flappyBirdPipesList.get(i).y +VariablesForPlayArea.flappyBirdPipesList.get(i).gapStartFrombottom+VariablesForPlayArea.flappyBirdPipesList.get(i).gapVerticalLength),
                    new Vector2(VariablesForPlayArea.flappyBirdPipesList.get(i).gapeHorizontalLength+16,32),
                    0.5f,1, AllVariables.Bit_land, (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool));
            //upper part
            BodyGenerator.BodyAssemble(world, true, "obstacle",
                    new Vector2(VariablesForPlayArea.flappyBirdPipesList.get(i).x,VariablesForPlayArea.flappyBirdPipesList.get(i).y +VariablesForPlayArea.flappyBirdPipesList.get(i).gapStartFrombottom+VariablesForPlayArea.flappyBirdPipesList.get(i).gapVerticalLength + 384),
                    new Vector2(VariablesForPlayArea.flappyBirdPipesList.get(i).gapeHorizontalLength,384),
                    0.5f,1, AllVariables.Bit_land, (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool));
        }
    }

    public void render(){
        //the given tiled map is 64x64 but using it as 32x32

        //drwaing pattern
        //first solidPipe
        //second endAreaPipe
        //last contiEndPipe

        for (int i=0;i<VariablesForPlayArea.flappyBirdPipesList.size();i++) {
            //lower solid painting
            for (int k=VariablesForPlayArea.flappyBirdPipesList.get(i).gapStartFrombottom; k>(-1)*VariablesForPlayArea.flappyBirdPipesList.get(i).gapStartFrombottom;k-=64) {
                for (int j = VariablesForPlayArea.flappyBirdPipesList.get(i).gapeHorizontalLength; j > -1 * VariablesForPlayArea.flappyBirdPipesList.get(i).gapeHorizontalLength; j -= 64) {
                    AllVariables.batch.draw(tile_solid, VariablesForPlayArea.flappyBirdPipesList.get(i).x - j,
                            VariablesForPlayArea.flappyBirdPipesList.get(i).y-k);
                }
            }
            //upper solid painting
            for (int k=384; k>-384;k-=64) {
                for (int j = VariablesForPlayArea.flappyBirdPipesList.get(i).gapeHorizontalLength; j > -1 * VariablesForPlayArea.flappyBirdPipesList.get(i).gapeHorizontalLength; j -= 64) {
                    AllVariables.batch.draw(tile_solid, VariablesForPlayArea.flappyBirdPipesList.get(i).x - j,
                            VariablesForPlayArea.flappyBirdPipesList.get(i).y + VariablesForPlayArea.flappyBirdPipesList.get(i).gapStartFrombottom + VariablesForPlayArea.flappyBirdPipesList.get(i).gapVerticalLength +384 -k);
                }
            }

            //end point painting-------------
            //====lower==============
            AllVariables.batch.draw(tile_endArea, VariablesForPlayArea.flappyBirdPipesList.get(i).x - VariablesForPlayArea.flappyBirdPipesList.get(i).gapeHorizontalLength - 16,
                    VariablesForPlayArea.flappyBirdPipesList.get(i).y + VariablesForPlayArea.flappyBirdPipesList.get(i).gapStartFrombottom-47);
            AllVariables.batch.draw(tile_endArea, VariablesForPlayArea.flappyBirdPipesList.get(i).x + VariablesForPlayArea.flappyBirdPipesList.get(i).gapeHorizontalLength + 16-64,
                    VariablesForPlayArea.flappyBirdPipesList.get(i).y + VariablesForPlayArea.flappyBirdPipesList.get(i).gapStartFrombottom-47);

            //=====upper==================
            AllVariables.batch.draw(tile_endArea, VariablesForPlayArea.flappyBirdPipesList.get(i).x - VariablesForPlayArea.flappyBirdPipesList.get(i).gapeHorizontalLength - 16,
                    VariablesForPlayArea.flappyBirdPipesList.get(i).y + VariablesForPlayArea.flappyBirdPipesList.get(i).gapStartFrombottom+VariablesForPlayArea.flappyBirdPipesList.get(i).gapVerticalLength-32);
            AllVariables.batch.draw(tile_endArea, VariablesForPlayArea.flappyBirdPipesList.get(i).x + VariablesForPlayArea.flappyBirdPipesList.get(i).gapeHorizontalLength + 16-64,
                    VariablesForPlayArea.flappyBirdPipesList.get(i).y + VariablesForPlayArea.flappyBirdPipesList.get(i).gapStartFrombottom+VariablesForPlayArea.flappyBirdPipesList.get(i).gapVerticalLength-32);

            //contineous tiles
            for (int j= VariablesForPlayArea.flappyBirdPipesList.get(i).gapeHorizontalLength-16;
                 j>-1*(VariablesForPlayArea.flappyBirdPipesList.get(i).gapeHorizontalLength-16);
                 j-=32){
                AllVariables.batch.draw(tile_contiEndPipe, VariablesForPlayArea.flappyBirdPipesList.get(i).x - j,
                        VariablesForPlayArea.flappyBirdPipesList.get(i).y + VariablesForPlayArea.flappyBirdPipesList.get(i).gapStartFrombottom-47);
                AllVariables.batch.draw(tile_contiEndPipe, VariablesForPlayArea.flappyBirdPipesList.get(i).x - j,
                        VariablesForPlayArea.flappyBirdPipesList.get(i).y + VariablesForPlayArea.flappyBirdPipesList.get(i).gapStartFrombottom+VariablesForPlayArea.flappyBirdPipesList.get(i).gapVerticalLength-32);

            }
        }

        /*
        tile = new Texture(Gdx.files.internal("playArea/LevelObstacles/FlappyBirdPipes/endAreaPipe.png"));
        AllVariables.batch.draw(tile,1500-64-16,500+150-47);
        AllVariables.batch.draw(tile,1500+64+16-64,500+150-47);

        tile = new Texture(Gdx.files.internal("playArea/LevelObstacles/FlappyBirdPipes/contiEndPipe.png"));
        AllVariables.batch.draw(tile,1500,500+150-47);
        AllVariables.batch.draw(tile,1500-32,500+150-47);
        */

    }

}
