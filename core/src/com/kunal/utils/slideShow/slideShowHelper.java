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
            }
        }

        return msg;
    }

}
