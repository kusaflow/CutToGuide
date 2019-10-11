package com.kunal.PlayGround.LevelsObstacles.Roundcandy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

import java.util.Random;

public class RoundCandyMain {

    int xcord, size;
    Random r;
    Sprite candy1,candy2,candy3,candy4;

    public RoundCandyMain (World world) {
        candy1 = new Sprite(new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre15.png")));
        candy2 = new Sprite(new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre16.png")));
        candy3 = new Sprite(new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre17.png")));
        candy4 = new Sprite(new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre18.png")));

        candy1.setOriginCenter();
        candy2.setOriginCenter();
        candy3.setOriginCenter();
        candy4.setOriginCenter();

        r = new Random();

        for (int i = 0; i< VariablesForPlayArea.roundCandies.size(); i++){
            //init
            VariablesForPlayArea.roundCandies.get(i).sizesList = new short[VariablesForPlayArea.roundCandies.get(i).count];
            VariablesForPlayArea.roundCandies.get(i).typeOfCandyList = new short[VariablesForPlayArea.roundCandies.get(i).count];
            VariablesForPlayArea.roundCandies.get(i).candiesBodyList = new Body[VariablesForPlayArea.roundCandies.get(i).count];

            xcord = VariablesForPlayArea.roundCandies.get(i).x;

            for (int j =0; j<VariablesForPlayArea.roundCandies.get(i).count; j++){
                size = VariablesForPlayArea.roundCandies.get(i).baseSize * r.nextInt(10)/10;

                if (size <5){
                    j--;
                    continue;
                }
                //random
                VariablesForPlayArea.roundCandies.get(i).typeOfCandyList[j] = (short) r.nextInt(4);
                VariablesForPlayArea.roundCandies.get(i).sizesList[j] = (short) size;

                VariablesForPlayArea.roundCandies.get(i).candiesBodyList[j] = BodyGenerator.CircleBody(world, false, "RoundCandy",
                        new Vector2(xcord, VariablesForPlayArea.roundCandies.get(i).y), size, 0.8f,0.6f, AllVariables.Bit_Tool, (short)(AllVariables.Bit_Tool | AllVariables.Bit_enimes | AllVariables.Bit_Bicycle | AllVariables.Bit_land));
                //position remapping
                xcord+=size*2;
            }
        }

    }

    public void render () {
        for (int i = 0; i< VariablesForPlayArea.roundCandies.size(); i++) {
            for (int j =0; j<VariablesForPlayArea.roundCandies.get(i).count; j++) {
                if (VariablesForPlayArea.roundCandies.get(i).typeOfCandyList[j] == 0) {
                    candy1.setPosition((VariablesForPlayArea.roundCandies.get(i).candiesBodyList[j].getPosition().x *AllVariables.PPM) - (VariablesForPlayArea.roundCandies.get(i).sizesList[j]),
                            (VariablesForPlayArea.roundCandies.get(i).candiesBodyList[j].getPosition().y * AllVariables.PPM) - (VariablesForPlayArea.roundCandies.get(i).sizesList[j]));
                    candy1.setSize(VariablesForPlayArea.roundCandies.get(i).sizesList[j]*2,
                            VariablesForPlayArea.roundCandies.get(i).sizesList[j]*2);
                    candy1.draw(AllVariables.batch);
                } else if (VariablesForPlayArea.roundCandies.get(i).typeOfCandyList[j] == 1) {
                    candy2.setPosition((VariablesForPlayArea.roundCandies.get(i).candiesBodyList[j].getPosition().x *AllVariables.PPM) - (VariablesForPlayArea.roundCandies.get(i).sizesList[j]),
                            (VariablesForPlayArea.roundCandies.get(i).candiesBodyList[j].getPosition().y * AllVariables.PPM) - (VariablesForPlayArea.roundCandies.get(i).sizesList[j]));
                    candy2.setSize(VariablesForPlayArea.roundCandies.get(i).sizesList[j]*2,
                            VariablesForPlayArea.roundCandies.get(i).sizesList[j]*2);
                    candy2.draw(AllVariables.batch);
                } else if (VariablesForPlayArea.roundCandies.get(i).typeOfCandyList[j] == 2) {
                    candy3.setPosition((VariablesForPlayArea.roundCandies.get(i).candiesBodyList[j].getPosition().x *AllVariables.PPM) - (VariablesForPlayArea.roundCandies.get(i).sizesList[j]),
                            (VariablesForPlayArea.roundCandies.get(i).candiesBodyList[j].getPosition().y * AllVariables.PPM) - (VariablesForPlayArea.roundCandies.get(i).sizesList[j]));
                    candy3.setSize(VariablesForPlayArea.roundCandies.get(i).sizesList[j]*2,
                            VariablesForPlayArea.roundCandies.get(i).sizesList[j]*2);
                    candy3.draw(AllVariables.batch);
                } else if (VariablesForPlayArea.roundCandies.get(i).typeOfCandyList[j] == 3) {
                    candy4.setPosition((VariablesForPlayArea.roundCandies.get(i).candiesBodyList[j].getPosition().x *AllVariables.PPM) - (VariablesForPlayArea.roundCandies.get(i).sizesList[j]),
                            (VariablesForPlayArea.roundCandies.get(i).candiesBodyList[j].getPosition().y * AllVariables.PPM) - (VariablesForPlayArea.roundCandies.get(i).sizesList[j]));
                    candy4.setSize(VariablesForPlayArea.roundCandies.get(i).sizesList[j]*2,
                            VariablesForPlayArea.roundCandies.get(i).sizesList[j]*2);
                    candy4.draw(AllVariables.batch);
                }
            }
        }

    }

    public void update () {

    }

}
