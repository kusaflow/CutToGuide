package com.kunal.PlayGround.LevelsObstacles.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.PlayGround.VariablesForPlayArea;

public class Snake {

    TextureAtlas atlas;
    Sprite img;
    TextureRegion region;
    boolean img1 = true;
    long timer;

    public Snake (World world) {

        atlas = new TextureAtlas(Gdx.files.internal("playArea/LevelObstacles/snake/pack.atlas"));
        region = atlas.findRegion("snakeSlime");
        img = new Sprite(region);

        for (int i=0; i< VariablesForPlayArea.snakes.size(); i++){
            VariablesForPlayArea.snakes.get(i).isDead = false;

        }

        timer = System.currentTimeMillis();
    }

    public void render () {

    }

    public void update () {
        //change texture------------------------------------------
        if (System.currentTimeMillis() - timer >=2000){
            img1 = !img1;
        }
        if (img1){
            img.setRegion(atlas.findRegion("snakeSlime"));
        }else {
            img.setRegion(atlas.findRegion("snakeSlime_ani"));
        }
        //--------------------------------------------------------


    }

}
