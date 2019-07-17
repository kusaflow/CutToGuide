package com.kunal.utils;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;
import com.kunal.MainGame;
import com.kunal.PlayGround.Area1.AreaOneClass;
import com.kunal.PlayGround.LevelsObstacles.CreateHole.CreateHoleVariables;
import com.kunal.PlayGround.LevelsObstacles.DirectionReverse.DirectionReverseVariables;
import com.kunal.PlayGround.LevelsObstacles.Jumper.JumperVariables;
import com.kunal.PlayGround.LevelsObstacles.flappyBirdPipes.flappyBirdPipes;
import com.kunal.PlayGround.LevelsObstacles.flappyBirdPipes.flappyBirdPipesVariables;
import com.kunal.PlayGround.LevelsObstacles.fullSawThatRoams.FullSawVariables;
import com.kunal.PlayGround.LevelsObstacles.halfSaw.HalfSaw;
import com.kunal.PlayGround.LevelsObstacles.halfSaw.HalfSawVariables;
import com.kunal.PlayGround.LevelsObstacles.speedController.SpeedController;
import com.kunal.PlayGround.LevelsObstacles.speedController.SpeedControllerVariables;
import com.kunal.PlayGround.TypeOneArea.TypeOneArea;
import com.kunal.PlayGround.TypeTwoArea.TypeTwoArea;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.PlayGround.powerUpInInventory.PowerUpInInventoryVariables;

public class ReDirectToTheLevel {
    public ReDirectToTheLevel() {
    }

    public static void Direct(MainGame game, Boolean restarted){

        flappyBirdPipesVariables fbPipes;
        //CreateHoleVariables createHole;
        JumperVariables jumper;
        HalfSawVariables halfSawVariables;
        FullSawVariables fullSawVariables;
        PowerUpInInventoryVariables powerupVar;
        SpeedControllerVariables speedctlrvar;
        DirectionReverseVariables dirRev;

        if (!restarted) {
            //clear it all
            VariablesForPlayArea.powerUpList.clear();
            VariablesForPlayArea.flappyBirdPipesList.clear();
            VariablesForPlayArea.createHoleList.clear();
            VariablesForPlayArea.jumperList.clear();
            VariablesForPlayArea.halfSawList.clear();
            VariablesForPlayArea.fullSawList.clear();
            VariablesForPlayArea.speedCtrlList.clear();
            VariablesForPlayArea.dirRevList.clear();
        }

        VariablesForPlayArea.gameOver =false;

        // area 1 ----------------------------------------------------------------
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==1){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level1.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 5600;

            game.setScreen(new TypeTwoArea(game, restarted));
        }

        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==2){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level2.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 5600;

            game.setScreen(new TypeTwoArea(game, restarted));
        }

        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==3){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level3.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 5000;

            game.setScreen(new TypeTwoArea(game, restarted));
        }

        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==4){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level4.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 6000;

            game.setScreen(new TypeTwoArea(game, restarted));
        }

        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==5){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level5.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 6000;

            game.setScreen(new TypeTwoArea(game, restarted));
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==6){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level6.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 6000;
            if (!restarted) {

                speedctlrvar = new SpeedControllerVariables();
                speedctlrvar.SpeedIncrementor = true;
                speedctlrvar.x = 1820;
                speedctlrvar.y = 520;
                VariablesForPlayArea.speedCtrlList.add(speedctlrvar);

            }

            game.setScreen(new TypeTwoArea(game, restarted));
        }else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==7){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level6.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 6000;
            game.setScreen(new TypeTwoArea(game, restarted));
        }


        //area 1 end ----------------------------------------------------------------



    }

    //else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==7){
    //            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level7.tmx";
    //
    //            VariablesForPlayArea.endPoint.x = 600;
    //            VariablesForPlayArea.endPoint.y = 7400;
    //            if (!restarted) {
    //
    //                /*
    //                // flappy bird type pipes
    //                fbPipes.x =1500;
    //                fbPipes.y = 500;
    //                fbPipes.gapeHorizontalLength = 64;
    //                fbPipes.gapVerticalLength = 400;
    //                fbPipes.gapStartFrombottom = 160;
    //                VariablesForPlayArea.flappyBirdPipesList.add(fbPipes);
    //
    //                fbPipes = new flappyBirdPipesVariables();
    //                fbPipes.x = 4000;
    //                fbPipes.y = 500;
    //                fbPipes.gapeHorizontalLength = 128;
    //                fbPipes.gapVerticalLength = 200;
    //                fbPipes.gapStartFrombottom = 32;
    //                VariablesForPlayArea.flappyBirdPipesList.add(fbPipes);
    //
    //                fbPipes = new flappyBirdPipesVariables();
    //                fbPipes.x = 6000;
    //                fbPipes.y = 500;
    //                fbPipes.gapeHorizontalLength = 192;
    //                fbPipes.gapVerticalLength = 300;
    //                fbPipes.gapStartFrombottom = 288;
    //                VariablesForPlayArea.flappyBirdPipesList.add(fbPipes);
    //                */
    //                /*
    //                createHole = new CreateHoleVariables();
    //                createHole.map = new TmxMapLoader().load("playArea/LevelObstacles/createHole/level7/Area1Level7.tmx");
    //                createHole.tmr = new OrthogonalTiledMapRenderer(createHole.map);
    //                VariablesForPlayArea.createHoleList.add(createHole);
    //                */
    //                /*
    //                //jumper
    //
    //                jumper = new JumperVariables();
    //                jumper.x = 1500;
    //                jumper.y = 510;
    //                VariablesForPlayArea.jumperList.add(jumper);
    //
    //
    //                jumper = new JumperVariables();
    //                jumper.x = 2500;
    //                jumper.y = 510;
    //                VariablesForPlayArea.jumperList.add(jumper);
    //                */
    //
    //
    //                /*
    //                //halhSaw
    //                halfSawVariables = new HalfSawVariables();
    //                halfSawVariables.xdestination = 1700; //-100 for 2
    //                halfSawVariables.oneisSelected = true;
    //                halfSawVariables.xorigin = 1700;
    //                halfSawVariables.yorigin = 512;
    //                VariablesForPlayArea.halfSawList.add(halfSawVariables);
    //                */
    //
    //
    //
    //                //fullSaw
    //                fullSawVariables = new FullSawVariables();
    //                fullSawVariables.xpos = 3000;
    //                fullSawVariables.ypos = 572;
    //                fullSawVariables.size = 128;
    //                fullSawVariables.forwardDirection = false;
    //                fullSawVariables.intialDirectionForward = false;
    //
    //                VariablesForPlayArea.fullSawList.add(fullSawVariables);
    //
    //
    //                /*
    //                //powerUps
    //                powerupVar = new PowerUpInInventoryVariables();
    //                powerupVar.TypeOfPower = 1;
    //                VariablesForPlayArea.powerUpList.add(powerupVar);
    //
    //                powerupVar = new PowerUpInInventoryVariables();
    //                powerupVar.TypeOfPower = 2;
    //                VariablesForPlayArea.powerUpList.add(powerupVar);
    //
    //                powerupVar = new PowerUpInInventoryVariables();
    //                powerupVar.TypeOfPower = 3;
    //                VariablesForPlayArea.powerUpList.add(powerupVar);
    //
    //                powerupVar = new PowerUpInInventoryVariables();
    //                powerupVar.TypeOfPower = 2;
    //                VariablesForPlayArea.powerUpList.add(powerupVar);
    //
    //                powerupVar = new PowerUpInInventoryVariables();
    //                powerupVar.TypeOfPower = 1;
    //                VariablesForPlayArea.powerUpList.add(powerupVar);
    //
    //                powerupVar = new PowerUpInInventoryVariables();
    //                powerupVar.TypeOfPower = 3;
    //                VariablesForPlayArea.powerUpList.add(powerupVar);
    //                */
    //
    //                powerupVar = new PowerUpInInventoryVariables();
    //                powerupVar.TypeOfPower = 4;
    //                VariablesForPlayArea.powerUpList.add(powerupVar);
    //
    //
    //
    //                /*
    //                //speed controller
    //                speedctlrvar = new SpeedControllerVariables();
    //                speedctlrvar.SpeedIncrementor = false;
    //                speedctlrvar.x = 800;
    //                speedctlrvar.y = 512;
    //                VariablesForPlayArea.speedCtrlList.add(speedctlrvar);
    //
    //                speedctlrvar = new SpeedControllerVariables();
    //                speedctlrvar.SpeedIncrementor = false;
    //                speedctlrvar.x = 2200;
    //                speedctlrvar.y = 512;
    //                VariablesForPlayArea.speedCtrlList.add(speedctlrvar);
    //
    //                speedctlrvar = new SpeedControllerVariables();
    //                speedctlrvar.SpeedIncrementor = true;
    //                speedctlrvar.x = 3000;
    //                speedctlrvar.y = 512;
    //                VariablesForPlayArea.speedCtrlList.add(speedctlrvar);
    //                */
    //
    //                /*
    //                dirRev = new DirectionReverseVariables();
    //                dirRev.x = 2000;
    //                dirRev.y = 525;
    //                dirRev.active = true;
    //
    //                VariablesForPlayArea.dirRevList.add(dirRev);
    //                */
    //
    //
    //
    //            }
    //
    //            game.setScreen(new TypeTwoArea(game, restarted));
    //        }
    // sds

}
