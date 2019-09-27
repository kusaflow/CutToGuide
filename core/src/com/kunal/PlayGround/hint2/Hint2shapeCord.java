package com.kunal.PlayGround.hint2;

import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;

import java.util.LinkedList;

public class Hint2shapeCord {
    public static LinkedList<LinkedList<Byte>> Hintshapes(){
        LinkedList<Byte> shapeCoed = new LinkedList<Byte>();
        LinkedList<LinkedList<Byte>> allShapes = new LinkedList<LinkedList<Byte>>();
        if (AllVariables.PresentAreaNumber == 1){
            if (AllVariables.PresentLevelNumber == 1){
                shapeCoed.add((byte) 12);
                shapeCoed.add((byte) 13);
                shapeCoed.add((byte) 14);
                shapeCoed.add((byte) 10);

                allShapes.add(shapeCoed);
            }
            else if (AllVariables.PresentLevelNumber == 2){
                shapeCoed.add((byte) 12);
                shapeCoed.add((byte) 13);
                shapeCoed.add((byte) 14);
                shapeCoed.add((byte) 15);
                shapeCoed.add((byte) 11);
                shapeCoed.add((byte) 7);
                shapeCoed.add((byte) 3);
                shapeCoed.add((byte) 6);
                shapeCoed.add((byte) 9);
                //--------------------------------
                allShapes.add(shapeCoed);
                //===============================

                shapeCoed = new LinkedList<Byte>();
                shapeCoed.add((byte) 1);
                shapeCoed.add((byte) 0);
                shapeCoed.add((byte) 4);
                shapeCoed.add((byte) 8);
                shapeCoed.add((byte) 12);
                shapeCoed.add((byte) 9);
                shapeCoed.add((byte) 6);
                shapeCoed.add((byte) 5);
                //--------------------------------
                allShapes.add(shapeCoed);
                //===============================
            }else if (AllVariables.PresentLevelNumber == 3){
                shapeCoed.add((byte) 14);
                shapeCoed.add((byte) 13);
                shapeCoed.add((byte) 12);
                shapeCoed.add((byte) 9);
                shapeCoed.add((byte) 6);
                shapeCoed.add((byte) 10);
                //--------------------------------
                allShapes.add(shapeCoed);
                //===============================

                shapeCoed = new LinkedList<Byte>();
                shapeCoed.add((byte) 12);
                shapeCoed.add((byte) 8);
                shapeCoed.add((byte) 4);
                shapeCoed.add((byte) 0);
                shapeCoed.add((byte) 1);
                shapeCoed.add((byte) 1);
                shapeCoed.add((byte) 2);
                shapeCoed.add((byte) 5);
                //--------------------------------
                allShapes.add(shapeCoed);
                //===============================

                shapeCoed = new LinkedList<Byte>();
                shapeCoed.add((byte) 3);
                shapeCoed.add((byte) 7);
                shapeCoed.add((byte) 11);
                shapeCoed.add((byte) 15);
                shapeCoed.add((byte) 14);
                //--------------------------------
                allShapes.add(shapeCoed);
                //===============================
            }
            else if (AllVariables.PresentLevelNumber == 4){
                shapeCoed.add((byte) 12);
                shapeCoed.add((byte) 13);
                shapeCoed.add((byte) 14);
                shapeCoed.add((byte) 15);
                shapeCoed.add((byte) 11);
                shapeCoed.add((byte) 7);
                shapeCoed.add((byte) 3);
                shapeCoed.add((byte) 10);
                //--------------------------------
                allShapes.add(shapeCoed);
                //===============================

                shapeCoed = new LinkedList<Byte>();
                shapeCoed.add((byte) 2);
                shapeCoed.add((byte) 3);
                shapeCoed.add((byte) 10);
                shapeCoed.add((byte) 12);
                shapeCoed.add((byte) 9);
                //--------------------------------
                allShapes.add(shapeCoed);
                //===============================
            }
        }

        return allShapes;
    }

    public static LinkedList<Vector2> Location(){
        LinkedList<Vector2> positionsl = new LinkedList<Vector2>();
        if (AllVariables.PresentAreaNumber == 1){
            if (AllVariables.PresentLevelNumber == 1){
                positionsl.add(new Vector2(29,5.3f));
            }
            else if (AllVariables.PresentLevelNumber == 2){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 3){
                positionsl.add(new Vector2(12.7119f,5.2104f));
                positionsl.add(new Vector2(20.8479f,7.2867f));
                positionsl.add(new Vector2(40.1227f,5.1430f));
            }
            else if (AllVariables.PresentLevelNumber == 4){
                positionsl.add(new Vector2(11.52f,5.2f));
                positionsl.add(new Vector2(47.307f,4f));
            }
            else if (AllVariables.PresentLevelNumber == 5){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 6){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 7){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 8){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 9){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 10){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 11){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 12){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 13){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 14){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 15){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 16){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 17){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 18){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 19){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
            else if (AllVariables.PresentLevelNumber == 20){
                positionsl.add(new Vector2(11.62f,5.134f));
                positionsl.add(new Vector2(45.666f,4.427f));
            }
        }

        return positionsl;
    }

    public static LinkedList<Integer> Rotation(){
        LinkedList<Integer> rot= new LinkedList<Integer>();
        if (AllVariables.PresentAreaNumber == 1){
            if (AllVariables.PresentLevelNumber == 1){
                rot.add(180);
            }
            else if (AllVariables.PresentLevelNumber == 2){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 3){
                rot.add(180);
                rot.add(315);
                rot.add(295);
            }else if (AllVariables.PresentLevelNumber == 4){
                rot.add(180);
                rot.add(135);
            }else if (AllVariables.PresentLevelNumber == 5){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 6){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 7){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 8){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 9){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 10){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 11){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 12){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 13){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 14){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 15){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 16){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 17){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 18){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 19){
                rot.add(180);
                rot.add(0);
            }else if (AllVariables.PresentLevelNumber == 20){
                rot.add(180);
                rot.add(0);
            }
        }

        return rot;
    }

}
