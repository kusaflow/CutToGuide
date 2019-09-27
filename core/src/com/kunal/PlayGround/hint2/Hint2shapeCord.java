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
        }

        return allShapes;
    }

    public static LinkedList<Vector2> Location(){
        LinkedList<Vector2> positionsl = new LinkedList<Vector2>();
        if (AllVariables.PresentAreaNumber == 1){
            if (AllVariables.PresentLevelNumber == 1){
                positionsl.add(new Vector2(29,5.1f));
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
        }

        return rot;
    }

}
