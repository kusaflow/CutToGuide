package com.kunal.PlayGround.LevelsObstacles.CreateHole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

public class createHole {
    Texture tile;

    public createHole(World world) {

    }

    public void render(){
        tile = new Texture(Gdx.files.internal("playArea/LevelObstacles/createHole/solid.png"));
        //AllVariables.batch.draw(tile,18241,480,32,32);

    }
}
