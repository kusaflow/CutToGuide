package com.kunal.PlayGround.LevelsObstacles.fireOnFloor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

public class FireOnFloor {


    public FireOnFloor (World world) {

        for (int i =0; i< VariablesForPlayArea.fire.size(); i++){

            VariablesForPlayArea.fire.get(i).mainFire = new ParticleEffect();
            VariablesForPlayArea.fire.get(i).mainFire.load(Gdx.files.internal("particles/fire/fire.p"), Gdx.files.internal(""));
            VariablesForPlayArea.fire.get(i).mainFire.getEmitters().first().setPosition(VariablesForPlayArea.fire.get(i).x,
                    VariablesForPlayArea.fire.get(i).y);
            VariablesForPlayArea.fire.get(i).mainFire.start();

            BodyGenerator.BodyAssemble(world, true, "fireBox",
                    new Vector2(VariablesForPlayArea.fire.get(i).x, VariablesForPlayArea.fire.get(i).y),
                    new Vector2(20, 25), 0.5f, 1, AllVariables.Bit_Tool, AllVariables.Bit_land);

            if (VariablesForPlayArea.fire.get(i).type == 1) {
                VariablesForPlayArea.fire.get(i).tex = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/candOnFire/candyBlue.png")));
            } else if (VariablesForPlayArea.fire.get(i).type == 2) {
                VariablesForPlayArea.fire.get(i).tex = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/candOnFire/candyGreen.png")));
            } else if (VariablesForPlayArea.fire.get(i).type == 3) {
                VariablesForPlayArea.fire.get(i).tex = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/candOnFire/candyRed.png")));
            } else if (VariablesForPlayArea.fire.get(i).type == 4) {
                VariablesForPlayArea.fire.get(i).tex = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/candOnFire/candyYellow.png")));
            }

            VariablesForPlayArea.fire.get(i).tex.setSize(40,40);
            VariablesForPlayArea.fire.get(i).tex.setPosition(VariablesForPlayArea.fire.get(i).x - 20, VariablesForPlayArea.fire.get(i).y-25);

        }

    }

    public void render () {
        for (int i = 0; i < VariablesForPlayArea.fire.size(); i++) {
            VariablesForPlayArea.fire.get(i).mainFire.draw(AllVariables.batch);

            VariablesForPlayArea.fire.get(i).tex.draw(AllVariables.batch);
        }
    }

    public void update () {
        for (int i = 0; i < VariablesForPlayArea.fire.size(); i++) {
            VariablesForPlayArea.fire.get(i).mainFire.update(Gdx.graphics.getDeltaTime());

            if (VariablesForPlayArea.fire.get(i).mainFire.isComplete())
                VariablesForPlayArea.fire.get(i).mainFire.reset();
        }
    }

}
