package com.kunal.PlayGround.LevelsObstacles.barnacle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

public class Barnacle {

    TextureAtlas atlas;
    Sprite img;
    TextureRegion region;
    boolean img1 = true;
    long timer;
    long upDownCounter;
    boolean goingUp = true;
    int deltaUp = 2,upchecker = 0;

    public Barnacle (World world) {

        atlas = new TextureAtlas(Gdx.files.internal("playArea/LevelObstacles/barnance/pack.atlas"));
        region = atlas.findRegion("barnacle");
        img = new Sprite(region);

        for (int i = 0; i< VariablesForPlayArea.barnacle.size(); i++){
            VariablesForPlayArea.barnacle.get(i).isDead = false;
            VariablesForPlayArea.barnacle.get(i).hitBox = BodyGenerator.BodyAssemble(world, true, "Evil",
                    new Vector2(VariablesForPlayArea.barnacle.get(i).x, VariablesForPlayArea.barnacle.get(i).y),
                    new Vector2(30,30),0,1,AllVariables.Bit_enimes,
                    (short)(AllVariables.Bit_Bicycle | AllVariables.Bit_Tool | AllVariables.Bit_enimes));

        }

        timer = System.currentTimeMillis();
        upDownCounter = System.currentTimeMillis();
    }

    public void render () {
        for (int i =0; i<VariablesForPlayArea.barnacle.size(); i++){
            img.setPosition(VariablesForPlayArea.barnacle.get(i).x, VariablesForPlayArea.barnacle.get(i).y);
            img.setScale(1.5f);

            VariablesForPlayArea.barnacle.get(i).hitBox.setTransform((VariablesForPlayArea.barnacle.get(i).x+27)/AllVariables.PPM,
                    (VariablesForPlayArea.barnacle.get(i).y+27)/AllVariables.PPM, 0);

            if (VariablesForPlayArea.barnacle.get(i).is180rot){
                img.setRotation(180);
            }else {
                img.setRotation(0);
            }
            if (VariablesForPlayArea.barnacle.get(i).isDead) {
                img.setRegion(atlas.findRegion("barnacle_dead"));
            }else {
                if (img1){
                    img.setRegion(atlas.findRegion("barnacle"));
                }else {
                    img.setRegion(atlas.findRegion("barnacle_bite"));
                }
            }
            img.draw(AllVariables.batch);

        }
    }

    public void update () {
        //change texture------------------------------------------
        if (System.currentTimeMillis() - timer >= 100){
            img1 = !img1;
            timer = System.currentTimeMillis();
        }
        //--------------------------------------------------------

        //moving up down
        if (System.currentTimeMillis() - upDownCounter >= 2){
            upchecker += deltaUp;
            upDownCounter = System.currentTimeMillis();
            for (int i=0; i<VariablesForPlayArea.barnacle.size(); i++) {

                //up going
                if (goingUp) {
                    VariablesForPlayArea.barnacle.get(i).y += deltaUp;
                } else {
                    VariablesForPlayArea.barnacle.get(i).y -= deltaUp;
                }
            }
        }


        if (upchecker>=90) {
            upchecker = 0;
            goingUp = !goingUp;
        }

    }
}
