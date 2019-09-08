package com.kunal.utils.slideShow;

import com.kunal.AllVariables;

public class slideShowHelper {

    public static int ImageCount(int code){
        int ret =0;

        if (code ==0) {
            if (AllVariables.PresentAreaNumber == 1){
                if (AllVariables.PresentLevelNumber == 1){
                    ret = 4;
                }else if (AllVariables.PresentLevelNumber == 2){
                    ret = 4;
                }else if (AllVariables.PresentLevelNumber == 3){
                    ret = 3;
                }else if (AllVariables.PresentLevelNumber == 4){
                    ret = 3;
                }else if (AllVariables.PresentLevelNumber == 5){
                    ret = 4;
                }else if (AllVariables.PresentLevelNumber == 6){
                    ret = 4;
                }else if (AllVariables.PresentLevelNumber == 7){
                    ret = 4;
                }else if (AllVariables.PresentLevelNumber == 8){
                    ret = 5;
                }else if (AllVariables.PresentLevelNumber == 9){
                    ret = 4;
                }else if (AllVariables.PresentLevelNumber == 10){
                    ret = 6;
                }else if (AllVariables.PresentLevelNumber == 11){
                    ret = 4;
                }else if (AllVariables.PresentLevelNumber == 12){
                    ret = 6;
                }else if (AllVariables.PresentLevelNumber == 13){
                    ret = 4;
                }else if (AllVariables.PresentLevelNumber == 14){
                    ret = 4;
                }else if (AllVariables.PresentLevelNumber == 15){
                    ret = 5;
                }else if (AllVariables.PresentLevelNumber == 16){
                    ret = 5;
                }else if (AllVariables.PresentLevelNumber == 17){
                    ret = 5;
                }else if (AllVariables.PresentLevelNumber == 18){
                    ret = 3;
                }else if (AllVariables.PresentLevelNumber == 19){
                    ret = 5;
                }else if (AllVariables.PresentLevelNumber == 20) {
                    ret = 5;
                }
            }
        }else if (code == 1){
            ret = 39;
        }

        return ret;
    }

    public static String TutMessage(int code, int focusImg){
        String msg = "";

        if (code == 0){
            //hint 2 -------------------------------------------------------------------------------

            //=======================================================================================

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
                msg="                                  This is the ShapeChoosing Screen. \n                                                 All the shapes and powerups are present here.";
            }else if (focusImg == 7) {
                msg = "                           Touching this will bring you to the cutting sheet.";
            }else if (focusImg == 8) {
                msg = "                           Use your imagination to create pieces out of it.";
            }else if (focusImg == 9) {
                msg = "                           You can start Cutting with the green points Only.";
            }else if (focusImg == 10) {
                msg = "                                                                  Like this.";
            }else if (focusImg == 11) {
                msg = "                             You can start with any green point and land \n                                          anywhere you want";
            }else if (focusImg == 12) {
                msg = "                                                                 Like this.";
            }else if (focusImg == 13) {
                msg = "                                You cannot start and end with same point.";
            }else if (focusImg == 14) {
                msg = "                                  But can move like this, green to red.";
            }else if (focusImg == 15) {
                msg = "                                   You cannot start with any red point.";
            }else if (focusImg == 16) {
                msg = "                                   You can start with only green points.";
            }else if (focusImg == 17) {
                msg = "                                    Touch this to reset the sheet.";
            }else if (focusImg == 18) {
                msg = "                                                                 Like this.";
            }else if (focusImg == 19) {
                msg = "                   When you are done with cutting touch this to continue.";
            }else if (focusImg == 20) {
                msg = "                              Your cutouts are present like this in the grid.";
            }else if (focusImg == 21) {
                msg = "                                        You can reset from here too.";
            }else if (focusImg == 22) {
                msg = "                                                                 Like this.";
            }else if (focusImg == 23) {
                msg = "                  Touch any grid and this faded yellow rectangle \n                                                      will appear to your choice.";
            }else if (focusImg == 24) {
                msg = "              With the yellow rectangle at your shape touch this \n                                                        to move the shape to mainScreen";
            }else if (focusImg == 25) {
                msg = "Notice this will get enable. This means you can interact with shapes.\nTouch this to disable this and then you cant interact with shapes.";
            }else if (focusImg == 26) {
                msg = "                                        Touch this to toggle this on/off.";
            }else if (focusImg == 27) {
                msg = "           With this on touch anywhere the shape will be placed there.";
            }else if (focusImg == 28) {
                msg = "                                        Touch this to toggle this on/off.";
            }else if (focusImg == 29) {
                msg = "           Now drag your finger anywhere and shape will move accordingly.";
            }else if (focusImg == 30) {
                msg = "                                        This is used to rotate the shapes.";
            }else if (focusImg == 31) {
                msg = "                                    This is used rotate the shape clockwise.";
            }else if (focusImg == 32) {
                msg = "                             This is used rotate the shape anti-clockwise.";
            }else if (focusImg == 33) {
                msg = "                              This is used rotate the shape 45 deg anti-clockwise.";
            }else if (focusImg == 34) {
                msg = "        Press this is when you are ready to let bicycle come in action.";
            }else if (focusImg == 35) {
                msg = "                                          Camera will move to hard left.";
            }else if (focusImg == 36) {
                msg = "                                          Touch this to reset the level.";
            }else if (focusImg == 37) {
                msg = "                                      Touch this to have a  zoomed out view.";
            }else if (focusImg == 38) {
                msg = "                                                                 Like this.";
            }else if (focusImg == 39) {
                msg = "                                           Touch this to slow down bicycle.";
            }
        }

        return msg;
    }

}
