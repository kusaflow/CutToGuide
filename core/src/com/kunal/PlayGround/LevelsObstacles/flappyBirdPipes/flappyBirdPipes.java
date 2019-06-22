package com.kunal.PlayGround.LevelsObstacles.flappyBirdPipes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

public class flappyBirdPipes {

    Texture tile;

    public flappyBirdPipes(World world){
        tile = new Texture(Gdx.files.internal("playArea/LevelObstacles/FlappyBirdPipes/endAreaPipe.png"));

        for (int i=0; i< VariablesForPlayArea.flappyBirdPipesPiles.size(); i++) {
            //lower Part
            BodyGenerator.BodyAssemble(world, true, "obstacle",
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).x,VariablesForPlayArea.flappyBirdPipesPiles.get(i).y),
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength,VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom),
                    0.5f,1, AllVariables.Bit_land, (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool));
            //lower end
            BodyGenerator.BodyAssemble(world, true, "obstacle",
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).x,VariablesForPlayArea.flappyBirdPipesPiles.get(i).y +VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom-15),
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength+16,32),
                    0.5f,1, AllVariables.Bit_land, (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool));
            //upper end
            BodyGenerator.BodyAssemble(world, true, "obstacle",
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).x,VariablesForPlayArea.flappyBirdPipesPiles.get(i).y +VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom+VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapVerticalLength),
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength+16,32),
                    0.5f,1, AllVariables.Bit_land, (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool));
            //upper part
            BodyGenerator.BodyAssemble(world, true, "obstacle",
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).x,VariablesForPlayArea.flappyBirdPipesPiles.get(i).y +VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom+VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapVerticalLength + 384),
                    new Vector2(VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength,384),
                    0.5f,1, AllVariables.Bit_land, (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool));
        }
    }

    public void render(){
        //the given tiled map is 64x64 but using it as 32x32

        //drwaing pattern
        //first solidPipe
        //second endAreaPipe
        //last contiEndPipe

        AllVariables.batch.begin();

        for (int i=0;i<VariablesForPlayArea.flappyBirdPipesPiles.size();i++) {
            tile = new Texture(Gdx.files.internal("playArea/LevelObstacles/FlappyBirdPipes/solidPipe.png"));
            //lower solid painting
            for (int k=VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom; k>(-1)*VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom;k-=64) {
                for (int j = VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength; j > -1 * VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength; j -= 64) {
                    AllVariables.batch.draw(tile, VariablesForPlayArea.flappyBirdPipesPiles.get(i).x - j,
                            VariablesForPlayArea.flappyBirdPipesPiles.get(i).y-k);
                }
            }
            //upper solid painting
            for (int k=384; k>-384;k-=64) {
                for (int j = VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength; j > -1 * VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength; j -= 64) {
                    AllVariables.batch.draw(tile, VariablesForPlayArea.flappyBirdPipesPiles.get(i).x - j,
                            VariablesForPlayArea.flappyBirdPipesPiles.get(i).y + VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom + VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapVerticalLength +384 -k);
                }
            }

            //end point painting-------------
            tile = new Texture(Gdx.files.internal("playArea/LevelObstacles/FlappyBirdPipes/endAreaPipe.png"));
            //====lower==============
            AllVariables.batch.draw(tile, VariablesForPlayArea.flappyBirdPipesPiles.get(i).x - VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength - 16,
                    VariablesForPlayArea.flappyBirdPipesPiles.get(i).y + VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom-47);
            AllVariables.batch.draw(tile, VariablesForPlayArea.flappyBirdPipesPiles.get(i).x + VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength + 16-64,
                    VariablesForPlayArea.flappyBirdPipesPiles.get(i).y + VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom-47);

            //=====upper==================
            AllVariables.batch.draw(tile, VariablesForPlayArea.flappyBirdPipesPiles.get(i).x - VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength - 16,
                    VariablesForPlayArea.flappyBirdPipesPiles.get(i).y + VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom+VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapVerticalLength-32);
            AllVariables.batch.draw(tile, VariablesForPlayArea.flappyBirdPipesPiles.get(i).x + VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength + 16-64,
                    VariablesForPlayArea.flappyBirdPipesPiles.get(i).y + VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom+VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapVerticalLength-32);

            //contineous tiles
            tile = new Texture(Gdx.files.internal("playArea/LevelObstacles/FlappyBirdPipes/contiEndPipe.png"));
            for (int j= VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength-16;
                 j>-1*(VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapeHorizontalLength-16);
                 j-=32){
                AllVariables.batch.draw(tile, VariablesForPlayArea.flappyBirdPipesPiles.get(i).x - j,
                        VariablesForPlayArea.flappyBirdPipesPiles.get(i).y + VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom-47);
                AllVariables.batch.draw(tile, VariablesForPlayArea.flappyBirdPipesPiles.get(i).x - j,
                        VariablesForPlayArea.flappyBirdPipesPiles.get(i).y + VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapStartFrombottom+VariablesForPlayArea.flappyBirdPipesPiles.get(i).gapVerticalLength-32);

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

        AllVariables.batch.end();

    }

    public void update(){

    }
}
