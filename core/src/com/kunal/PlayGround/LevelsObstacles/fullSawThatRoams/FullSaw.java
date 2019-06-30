package com.kunal.PlayGround.LevelsObstacles.fullSawThatRoams;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

public class FullSaw {
    Sprite fullSaw, dead;

    public FullSaw(World world) {
        fullSaw = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/Enimies/FullSaw/fullSaw.png")));
        dead = new Sprite(new Texture(Gdx.files.internal("playArea/LevelObstacles/Enimies/FullSaw/saw_dead.png")));
        for (int i=0; i< VariablesForPlayArea.fullSawList.size();i++){
            VariablesForPlayArea.fullSawList.get(i).body = BodyGenerator.CircleBody(world,false, "fullSaw",
                    new Vector2(VariablesForPlayArea.fullSawList.get(i).xpos, VariablesForPlayArea.fullSawList.get(i).ypos),
                    VariablesForPlayArea.fullSawList.get(i).size/2.3f,0.5f, 0.6f,
                    AllVariables.Bit_enimes,(short)(AllVariables.Bit_enimes|AllVariables.Bit_Bicycle|AllVariables.Bit_land));
            VariablesForPlayArea.fullSawList.get(i).dead = false;
        }


    }

    public void update(){
        for (int i=0; i<VariablesForPlayArea.fullSawList.size(); i++) {
            if (!VariablesForPlayArea.fullSawList.get(i).dead) {
                if (VariablesForPlayArea.fullSawList.get(i).forwardDirection)
                    VariablesForPlayArea.fullSawList.get(i).body.setLinearVelocity(2, VariablesForPlayArea.fullSawList.get(i).body.getLinearVelocity().y);
                else
                    VariablesForPlayArea.fullSawList.get(i).body.setLinearVelocity(-2, VariablesForPlayArea.fullSawList.get(i).body.getLinearVelocity().y);
            }


        }


    }

    public void render(){
        for (int i=0; i<VariablesForPlayArea.fullSawList.size(); i++){
            if (!VariablesForPlayArea.fullSawList.get(i).dead) {
                fullSaw.setOriginCenter();
                fullSaw.setPosition(VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM - (VariablesForPlayArea.fullSawList.get(i).size / 2),
                        VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM - (VariablesForPlayArea.fullSawList.get(i).size / 2));
                fullSaw.setSize(VariablesForPlayArea.fullSawList.get(i).size, VariablesForPlayArea.fullSawList.get(i).size);
                fullSaw.setRotation((int) (VariablesForPlayArea.fullSawList.get(i).body.getAngle() * (180 / Math.PI)));
                fullSaw.draw(AllVariables.batch);
            }else {
                dead.setOriginCenter();
                dead.setPosition(VariablesForPlayArea.fullSawList.get(i).body.getPosition().x * AllVariables.PPM - (VariablesForPlayArea.fullSawList.get(i).size / 2),
                        VariablesForPlayArea.fullSawList.get(i).body.getPosition().y * AllVariables.PPM - (VariablesForPlayArea.fullSawList.get(i).size / 2));
                dead.setSize(VariablesForPlayArea.fullSawList.get(i).size, VariablesForPlayArea.fullSawList.get(i).size);
                dead.setRotation((int) (VariablesForPlayArea.fullSawList.get(i).body.getAngle() * (180 / Math.PI)));
                dead.draw(AllVariables.batch);
            }
        }
    }
}
