package com.kunal.PlayGround.LevelsObstacles.BreakableCandyBars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.PlayGround.VariablesForPlayArea;

public class BreakableCandyBar {

    Sprite midPart, endPart;


    public BreakableCandyBar (World world) {
        for (int i =0; i<VariablesForPlayArea.breakingCandyBar.size(); i++) {
            midPart = new Sprite(new Texture(Gdx.files.internal("mid" + VariablesForPlayArea.breakingCandyBar.get(i).type + ".png")));
            endPart = new Sprite(new Texture(Gdx.files.internal("end" + VariablesForPlayArea.breakingCandyBar.get(i).type + ".png")));



        }
    }


    public void render () {

    }

    public void update () {

    }

}
