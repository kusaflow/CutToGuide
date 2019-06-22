package com.kunal.utils;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;
import com.kunal.MainGame;
import com.kunal.PlayGround.Area1.AreaOneClass;
import com.kunal.PlayGround.LevelsObstacles.flappyBirdPipes.flappyBirdPipes;
import com.kunal.PlayGround.LevelsObstacles.flappyBirdPipes.flappyBirdPipesVariables;
import com.kunal.PlayGround.TypeOneArea.TypeOneArea;
import com.kunal.PlayGround.TypeTwoArea.TypeTwoArea;
import com.kunal.PlayGround.VariablesForPlayArea;

public class ReDirectToTheLevel {
    public ReDirectToTheLevel() {
    }

    public static void Direct(MainGame game){

        flappyBirdPipesVariables fbPipes = new flappyBirdPipesVariables();

        //clear it all
        VariablesForPlayArea.powerUps.clear();
        VariablesForPlayArea.powerUpPos.clear();
        VariablesForPlayArea.flappyBirdPipesPiles.clear();


        // area 1 ----------------------------------------------------------------
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==1){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level1.tmx";
            VariablesForPlayArea.endPoint.x = 700;
            VariablesForPlayArea.endPoint.y = 5600;
            //powerUps
            VariablesForPlayArea.powerUps.clear();

            game.setScreen(new TypeOneArea(game));
        }

        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==2){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level2.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 5600;
            //powerUps
            VariablesForPlayArea.powerUps.clear();

            game.setScreen(new TypeOneArea(game));
        }

        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==3){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level3.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 5000;
            //powerUps
            VariablesForPlayArea.powerUps.clear();

            game.setScreen(new TypeOneArea(game));
        }

        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==4){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level4.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 6000;
            //powerUps
            VariablesForPlayArea.powerUps.clear();

            game.setScreen(new TypeOneArea(game));
        }

        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==5){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level5.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 6000;
            //powerUps
            VariablesForPlayArea.powerUps.clear();

            game.setScreen(new TypeOneArea(game));
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==6){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level6.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 6000;
            //powerUps
            VariablesForPlayArea.powerUps.add((byte) 1);
            VariablesForPlayArea.powerUps.add((byte) 2);
            VariablesForPlayArea.powerUps.add((byte) 1);

            putPowerUpsToDefaultPos();

            game.setScreen(new TypeTwoArea(game));
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==7){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level7.tmx";

            VariablesForPlayArea.endPoint.x = 600;
            VariablesForPlayArea.endPoint.y = 7400;

            fbPipes.x =1500;
            fbPipes.y = 500;
            fbPipes.gapeHorizontalLength = 64;
            fbPipes.gapVerticalLength = 100;
            fbPipes.gapStartFrombottom = 160;
            VariablesForPlayArea.flappyBirdPipesPiles.add(fbPipes);

            fbPipes = new flappyBirdPipesVariables();
            fbPipes.x = 4000;
            fbPipes.y = 500;
            fbPipes.gapeHorizontalLength = 128;
            fbPipes.gapVerticalLength = 200;
            fbPipes.gapStartFrombottom = 32;
            VariablesForPlayArea.flappyBirdPipesPiles.add(fbPipes);

            fbPipes = new flappyBirdPipesVariables();
            fbPipes.x = 6000;
            fbPipes.y = 500;
            fbPipes.gapeHorizontalLength = 192;
            fbPipes.gapVerticalLength = 100;
            fbPipes.gapStartFrombottom = 288;
            VariablesForPlayArea.flappyBirdPipesPiles.add(fbPipes);


            game.setScreen(new TypeTwoArea(game));
        }

        //area 1 end ----------------------------------------------------------------



    }

    private static void putPowerUpsToDefaultPos(){
        for (int i=0; i<VariablesForPlayArea.powerUps.size(); i++){
            VariablesForPlayArea.powerUpPos.add(new Vector2(-500,-500));
        }
    }

}
