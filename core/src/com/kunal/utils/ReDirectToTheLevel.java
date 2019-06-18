package com.kunal.utils;

import com.kunal.AllVariables;
import com.kunal.MainGame;
import com.kunal.PlayGround.Area1.AreaOneClass;
import com.kunal.PlayGround.TypeOneArea.TypeOneArea;
import com.kunal.PlayGround.VariablesForPlayArea;

public class ReDirectToTheLevel {
    public ReDirectToTheLevel() {
    }

    public static void Direct(MainGame game){

        // area 1 ----------------------------------------------------------------
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==1){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level1.tmx";
            VariablesForPlayArea.endPoint.x = 700;
            VariablesForPlayArea.endPoint.y = 5600;
            //game.setScreen(new AreaOneClass(game));
            game.setScreen(new TypeOneArea(game));
        }

        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==2){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level2.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 5600;
            game.setScreen(new TypeOneArea(game));
            //game.setScreen(new AreaOneClass(game));
        }

        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==3){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level3.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 4770;
            game.setScreen(new TypeOneArea(game));
            //game.setScreen(new AreaOneClass(game));
        }

        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==4){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level4.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 6000;
            game.setScreen(new TypeOneArea(game));
            //game.setScreen(new AreaOneClass(game));
        }

        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==5){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level5.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 6000;
            game.setScreen(new TypeOneArea(game));
            //game.setScreen(new AreaOneClass(game));
        }

        //area 1 emd ----------------------------------------------------------------



    }
}
