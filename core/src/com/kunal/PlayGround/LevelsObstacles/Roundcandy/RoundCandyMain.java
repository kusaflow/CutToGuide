package com.kunal.PlayGround.LevelsObstacles.Roundcandy;

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


    public RoundCandyMain (World world) {
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
                VariablesForPlayArea.roundCandies.get(i).candiesBodyList[j] = BodyGenerator.CircleBody(world, false, "RoundCandy",
                        new Vector2(xcord, VariablesForPlayArea.roundCandies.get(i).y), size, 0.8f,0.6f, AllVariables.Bit_Tool, (short)(AllVariables.Bit_Tool | AllVariables.Bit_enimes | AllVariables.Bit_Bicycle | AllVariables.Bit_land));

                //position remapping
                xcord+=size*2;

            }



        }

    }

    public void render () {

    }

    public void update () {

    }

}
