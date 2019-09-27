package com.kunal.PlayGround;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.utils.TiledMapLoadingHelper;

public class ExtraUpdateMethods {

    public ExtraUpdateMethods () {

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

        //slow motion
        if (AllVariables.BackWheel.getPosition().x * AllVariables.PPM + 250 >= TiledMapLoadingHelper.flagpos().x
                && AllVariables.BackWheel.getPosition().y * AllVariables.PPM >= 0
                && AllVariables.BackWheel.getPosition().y * AllVariables.PPM >= TiledMapLoadingHelper.flagpos().y -200
                && AllVariables.BackWheel.getPosition().y * AllVariables.PPM <= TiledMapLoadingHelper.flagpos().y +700
                && AllVariables.BackWheel.getPosition().x * AllVariables.PPM  <= TiledMapLoadingHelper.flagpos().x - 180) {
            world.step(1 / 2000f, 6, 2);

        }else {
            world.step(1 / (1 / dt), 6, 2);
        }

    }
}
