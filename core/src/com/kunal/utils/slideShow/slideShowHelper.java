package com.kunal.utils.slideShow;

public class slideShowHelper {

    public static int ImageCount(int code){
        int ret =0;

        if (code ==0) {
            // level wise classification
        }else if (code == 1){
            ret = 40;
        }

        return ret;
    }

    public static String TutMessage(int code, int focusImg){
        String msg = "";

        if (code == 0){

        }else if (code == 1){
            if (focusImg == 1){
                msg="                                                                This is the Mainscreen";
            }else if (focusImg == 2){
                msg= "                This is CamScroller. It is used to travel inside the level.";
            }else if (focusImg == 3){
                msg= "                                 Drag this left to move left in the level.";
            }else if (focusImg == 4){
                msg= "                                 Drag this right to move right in the level.";
            }else if (focusImg == 5){
                msg= "       This is ShapeChooser. It is used to pick shapes and powerups \n                                                                  to place in the level.";
            }else if (focusImg == 6){
                msg="                                                            This is the ShapeChoosing Screen";
            }
        }

        return msg;
    }

}
