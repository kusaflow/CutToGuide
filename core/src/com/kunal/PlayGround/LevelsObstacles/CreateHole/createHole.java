package com.kunal.PlayGround.LevelsObstacles.CreateHole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

public class createHole {
    Texture tile = new Texture(Gdx.files.internal("playArea/LevelObstacles/createHole/solid.png"));


    public createHole(World world) {

    }

    public void render(){

        for (int i =0, x=0,y=0;i<VariablesForPlayArea.createHoleList.size();i++){
            x=VariablesForPlayArea.createHoleList.get(i).x;
            y=VariablesForPlayArea.createHoleList.get(i).y;
            for (int j=x; j<x+VariablesForPlayArea.createHoleList.get(i).lenght; j+=32) {
                if (VariablesForPlayArea.createHoleList.get(i).typeOfPAth == 1) {
                    tile = new Texture(Gdx.files.internal("playArea/LevelObstacles/createHole/solid.png"));
                }
                AllVariables.batch.draw(tile, j,y,32,32);
                tile = new Texture(Gdx.files.internal("playArea/LevelObstacles/createHole/solid.png"));
                for (int k=y; k<y+VariablesForPlayArea.createHoleList.get(i).depth; k+=32){
                    AllVariables.batch.draw(tile, j,k,32,32);
                }

            }
        }
    }
}
