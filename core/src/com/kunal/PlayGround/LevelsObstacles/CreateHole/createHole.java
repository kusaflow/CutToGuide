package com.kunal.PlayGround.LevelsObstacles.CreateHole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

public class createHole {

    public createHole(World world) {

    }

    public void render(){
        for (int i=0; i<VariablesForPlayArea.createHoleList.size(); i++){
            VariablesForPlayArea.createHoleList.get(i).tmr.render();
        }
    }
}
