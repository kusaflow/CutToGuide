package com.kunal.PlayGround.constScreen.CuttingArea;

import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

import java.util.LinkedList;

public class HintOneData {

    public static LinkedList<Vector2> axis(){
        LinkedList<Vector2> retList = new LinkedList<Vector2>();

        //VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(j)][0]

        if (AllVariables.PresentAreaNumber == 1){
            if (AllVariables.PresentLevelNumber == 1){
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[12][0],VariablesForPlayArea.BigSqurePoints[12][1]));

                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[10][0] ,VariablesForPlayArea.BigSqurePoints[10][1]));

                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[10][0], VariablesForPlayArea.BigSqurePoints[10][1]));

                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[14][0], VariablesForPlayArea.BigSqurePoints[14][1]));

                return retList;
            } else if (AllVariables.PresentLevelNumber == 2){
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[1][0], VariablesForPlayArea.BigSqurePoints[1][1]));

                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[5][0], VariablesForPlayArea.BigSqurePoints[5][1]));

                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[5][0], VariablesForPlayArea.BigSqurePoints[5][1]));

                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[6][0], VariablesForPlayArea.BigSqurePoints[6][1]));

                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[12][0], VariablesForPlayArea.BigSqurePoints[12][1]));

                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[3][0], VariablesForPlayArea.BigSqurePoints[3][1]));

                return retList;
            } else if (AllVariables.PresentLevelNumber == 3){
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[12][0], VariablesForPlayArea.BigSqurePoints[12][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[5][0], VariablesForPlayArea.BigSqurePoints[5][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[5][0], VariablesForPlayArea.BigSqurePoints[5][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[2][0], VariablesForPlayArea.BigSqurePoints[2][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[12][0], VariablesForPlayArea.BigSqurePoints[12][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[6][0], VariablesForPlayArea.BigSqurePoints[6][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[6][0], VariablesForPlayArea.BigSqurePoints[6][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[14][0], VariablesForPlayArea.BigSqurePoints[14][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[14][0], VariablesForPlayArea.BigSqurePoints[14][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[3][0], VariablesForPlayArea.BigSqurePoints[3][1]));
                return retList;
            } else if (AllVariables.PresentLevelNumber == 4){
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[12][0], VariablesForPlayArea.BigSqurePoints[12][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[9][0], VariablesForPlayArea.BigSqurePoints[9][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[9][0], VariablesForPlayArea.BigSqurePoints[9][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[2][0], VariablesForPlayArea.BigSqurePoints[2][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[12][0], VariablesForPlayArea.BigSqurePoints[12][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[10][0], VariablesForPlayArea.BigSqurePoints[10][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[10][0], VariablesForPlayArea.BigSqurePoints[10][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[3][0], VariablesForPlayArea.BigSqurePoints[3][1]));
                return retList;
            } else if (AllVariables.PresentLevelNumber == 5){
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[12][0], VariablesForPlayArea.BigSqurePoints[12][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[9][0], VariablesForPlayArea.BigSqurePoints[9][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[9][0], VariablesForPlayArea.BigSqurePoints[9][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[2][0], VariablesForPlayArea.BigSqurePoints[2][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[2][0], VariablesForPlayArea.BigSqurePoints[2][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[15][0], VariablesForPlayArea.BigSqurePoints[15][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[2][0], VariablesForPlayArea.BigSqurePoints[2][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[14][0], VariablesForPlayArea.BigSqurePoints[14][1]));
                return retList;
            } else if (AllVariables.PresentLevelNumber == 6){
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[0][0], VariablesForPlayArea.BigSqurePoints[0][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[5][0], VariablesForPlayArea.BigSqurePoints[5][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[5][0], VariablesForPlayArea.BigSqurePoints[5][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[11][0], VariablesForPlayArea.BigSqurePoints[11][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[11][0], VariablesForPlayArea.BigSqurePoints[11][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[12][0], VariablesForPlayArea.BigSqurePoints[12][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[11][0], VariablesForPlayArea.BigSqurePoints[11][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[8][0], VariablesForPlayArea.BigSqurePoints[8][1]));
                return retList;
            } else if (AllVariables.PresentLevelNumber == 7){
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[12][0], VariablesForPlayArea.BigSqurePoints[12][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[5][0], VariablesForPlayArea.BigSqurePoints[5][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[5][0], VariablesForPlayArea.BigSqurePoints[5][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[2][0], VariablesForPlayArea.BigSqurePoints[2][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[12][0], VariablesForPlayArea.BigSqurePoints[12][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[6][0], VariablesForPlayArea.BigSqurePoints[6][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[6][0], VariablesForPlayArea.BigSqurePoints[6][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[14][0], VariablesForPlayArea.BigSqurePoints[14][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[14][0], VariablesForPlayArea.BigSqurePoints[14][1]));
                retList.add(new Vector2(VariablesForPlayArea.BigSqurePoints[3][0], VariablesForPlayArea.BigSqurePoints[3][1]));
                return retList;
            }
        }




        return retList;
    }
}
