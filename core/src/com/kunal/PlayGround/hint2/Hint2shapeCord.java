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
            }
        }

        return rot;
    }

}
