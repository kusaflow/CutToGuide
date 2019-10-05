package com.kunal.PlayGround;

import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.utils.TiledMapLoadingHelper;

public class ExtraUpdateMethods {

    boolean slowmo, activeSlowMoCheck;
    long time;

    public ExtraUpdateMethods () {
        activeSlowMoCheck = true;
        slowmo = false;
    }

    public void update (World world, float dt) {
        if (AllVariables.BackWheel.getPosition().y * AllVariables.PPM <=-600){
            VariablesForPlayArea.gameOver = true;
        }

        if (AllVariables.BackWheel.getPosition().x * AllVariables.PPM >= TiledMapLoadingHelper.flagpos().x +600){
            VariablesForPlayArea.gameOver = true;
        }

        /*if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            world.step(1 / 2000f, 6, 2);
        }else{
            world.step(1 / (1 / dt), 6, 2);
        }*/

        if (activeSlowMoCheck) {
            //slow motion
            if (AllVariables.BackWheel.getPosition().x * AllVariables.PPM + 250 >= TiledMapLoadingHelper.flagpos().x
                    && AllVariables.BackWheel.getPosition().y * AllVariables.PPM >= 0
                    && AllVariables.BackWheel.getPosition().y * AllVariables.PPM >= TiledMapLoadingHelper.flagpos().y - 200
                    && AllVariables.BackWheel.getPosition().y * AllVariables.PPM <= TiledMapLoadingHelper.flagpos().y + 700) {
                slowmo = true;
                time = System.currentTimeMillis();
                activeSlowMoCheck = false;
            } else {
                slowmo = false;
            }
        }

        if (AllVariables.PresentAreaNumber >= 2){
            if (VariablesForPlayArea.doSlowMo){
                world.step(1 / 500f, 6, 2);
            }else{
                if (slowmo) {
                    if (time + 1000 >= System.currentTimeMillis()) {
                        world.step(1 / 1000f, 6, 2);
                    } else {
                        world.step(1 / (1 / dt), 6, 2);

                    }
                } else {
                    world.step(1 / (1 / dt), 6, 2);
                }
            }
        }else {
            // for area one
            if (slowmo) {
                if (time + 1000 >= System.currentTimeMillis()) {
                    world.step(1 / 1000f, 6, 2);
                } else {
                    world.step(1 / (1 / dt), 6, 2);

                }
            } else {
                world.step(1 / (1 / dt), 6, 2);
            }
        }



    }
}
