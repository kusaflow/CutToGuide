package com.kunal.utils;

import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;

public class TiledMapLoadingHelper {

    public TiledMapLoadingHelper(){}

    public static short NumberOfObj(){
        //testing is area 101 and level 101
        if (AllVariables.PresentLevelNumber == 101 && AllVariables.PresentAreaNumber == 101){
            return 10;
        }

        if (AllVariables.PresentAreaNumber == 0 && AllVariables.PresentLevelNumber == 0){
            return 2;
        }


        //area one ------------------------------------------------------
        if (AllVariables.PresentAreaNumber == 1) {
            if (AllVariables.PresentLevelNumber == 1) {
                return 1;
            }

            if (AllVariables.PresentLevelNumber == 2) {
                return 6;
            }

            if (AllVariables.PresentLevelNumber == 3) {
                return 8;
            }

            if (AllVariables.PresentLevelNumber == 4) {
                return 8;
            }
            if (AllVariables.PresentLevelNumber == 5) {
                return 11;
            }
            if (AllVariables.PresentLevelNumber == 6) {
                return 6;
            }
            if (AllVariables.PresentLevelNumber == 7) {
                return 3;
            }
            if (AllVariables.PresentLevelNumber == 8) {
                return 10;
            }
            if (AllVariables.PresentLevelNumber == 9) {
                return 15;
            }
            if (AllVariables.PresentLevelNumber == 10) {
                return 10;
            }
            if (AllVariables.PresentLevelNumber == 11) {
                return 12;
            }
            if (AllVariables.PresentLevelNumber == 12) {
                return 15;
            }
            if (AllVariables.PresentLevelNumber == 13) {
                return 20;
            }
            if (AllVariables.PresentLevelNumber == 14) {
                return 11;
            }
            if (AllVariables.PresentLevelNumber == 15) {
                return 17;
            }
            if (AllVariables.PresentLevelNumber == 16) {
                return 19;
            }
            if (AllVariables.PresentLevelNumber == 17) {
                return 11;
            }
            if (AllVariables.PresentLevelNumber == 18) {
                return 21;
            }
            if (AllVariables.PresentLevelNumber == 19) {
                return 6;
            }
            if (AllVariables.PresentLevelNumber == 20) {
                return 10;
            }
        }else if (AllVariables.PresentAreaNumber == 2){
            if (AllVariables.PresentLevelNumber == 1) {
                return 1;
            }
        }

        //area one end--------------------------------------------------


        return 0;
    }

    public static Vector2 flagpos(){
        Vector2 pos = new Vector2(0,0);

        //-------------for tutorial---------------------------------------[
        if (AllVariables.PresentAreaNumber == 0 && AllVariables.PresentLevelNumber == 0){
            pos.x = 6367;
            pos.y = 700;
        }
        //=================================================================

        //area one ------------------------------------------------------
        if (AllVariables.PresentAreaNumber == 1) {
            if (AllVariables.PresentLevelNumber == 1) {
                pos.x = 6145;
                pos.y = 510;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 2) {
                pos.x = 6445;
                pos.y = 510;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 3) {
                pos.x = 5710;
                pos.y = 608;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 4) {
                pos.x = 6440;
                pos.y = 511;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 5) {
                pos.x = 6540;
                pos.y = 511;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 6) {
                pos.x = 7000;
                pos.y = 511;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 7) {
                pos.x = 9000;
                pos.y = 765;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 8) {
                pos.x = 9000;
                pos.y = 512;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 9) {
                pos.x = 9000;
                pos.y = 512;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 10) {
                pos.x = 9000;
                pos.y = 540;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 11) {
                pos.x = 9372;
                pos.y = 380;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 12) {
                pos.x = 9372;
                pos.y = 380;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 13) {
                pos.x = 9297;
                pos.y = 322;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 14) {
                pos.x = 9127;
                pos.y = 317;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 15) {
                pos.x = 8550;
                pos.y = 472;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 16) {
                pos.x = 9297;
                pos.y = 322;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 17) {
                pos.x = 9297;
                pos.y = 322;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 18) {
                pos.x = 9297;
                pos.y = 322;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 19) {
                pos.x = 8762;
                pos.y = 857;
                return pos;
            } else if (AllVariables.PresentLevelNumber == 20) {
                pos.x = 9297;
                pos.y = 322;
                return pos;
            }
        }
        //area 2---------------------------------------
        if (AllVariables.PresentAreaNumber == 2){
            if (AllVariables.PresentLevelNumber == 1) {
                pos.x = 6145;
                pos.y = 1100;
                return pos;
            }
        }


        return pos;
    }

    public static Vector2 coin1Pos(){
        Vector2 cpos = new Vector2(0,0);

        //area one ------------------------------------------------------
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 1){
            cpos.x = 1945;
            cpos.y = 500;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 2){
            cpos.x = 1500;
            cpos.y = 825;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 3){
            cpos.x = 1590;
            cpos.y = 800;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 4){
            cpos.x = 1617;
            cpos.y = 987;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 5){
            cpos.x = 2600;
            cpos.y = 830;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 6){
            cpos.x = 3120;
            cpos.y = 475;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 7){
            cpos.x = 2300;
            cpos.y = 537;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 8){
            cpos.x = 1730;
            cpos.y = 532;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 9){
            cpos.x = 3287;
            cpos.y = 552;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 10){
            cpos.x = 2439;
            cpos.y = 747;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 11){
            cpos.x = 2890;
            cpos.y = 522;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 12){
            cpos.x = 2030;
            cpos.y = 542;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 13){
            cpos.x = 7017;
            cpos.y = 322;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 14){
            cpos.x = 1977;
            cpos.y = 387;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 15){
            cpos.x = 7982;
            cpos.y = 767;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 16){
            cpos.x = 2037;
            cpos.y = 527;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 17){
            cpos.x = 3592;
            cpos.y = 627;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 18){
            cpos.x = 2782;
            cpos.y = 667;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 19){
            cpos.x = 2837;
            cpos.y = 872;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 20){
            cpos.x = 1897;
            cpos.y = 642;
            return cpos;
        }
        return cpos;
    }


    public static Vector2 coin2Pos(){
        Vector2 cpos = new Vector2(0,0);

        //area one ------------------------------------------------------
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 1){
            cpos.x = 3290;
            cpos.y = 700;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 2){
            cpos.x = 2272;
            cpos.y = 790;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 3){
            cpos.x = 2070;
            cpos.y = 750;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 4){
            cpos.x = 3172;
            cpos.y = 527;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 5){
            cpos.x = 4087;
            cpos.y = 627;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 6){
            cpos.x = 4307;
            cpos.y = 937;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 7){
            cpos.x = 5907;
            cpos.y = 517;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 8){
            cpos.x = 5407;
            cpos.y = 532;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 9){
            cpos.x = 5262;
            cpos.y = 542;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 10){
            cpos.x = 3927;
            cpos.y = 897;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 11){
            cpos.x = 6030;
            cpos.y = 532;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 12){
            cpos.x = 4052;
            cpos.y = 502;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 13){
            cpos.x = 4532;
            cpos.y = 587;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 14){
            cpos.x = 4182;
            cpos.y = 357;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 15){
            cpos.x = 6647;
            cpos.y = 902;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 16){
            cpos.x = 5287;
            cpos.y = 707;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 17){
            cpos.x = 5452;
            cpos.y = 402;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 18){
            cpos.x = 4552;
            cpos.y = 487;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 19){
            cpos.x = 4577;
            cpos.y = 992;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 20){
            cpos.x = 4362;
            cpos.y = 712;
            return cpos;
        }
        return cpos;
    }


    public static Vector2 coin3Pos(){
        Vector2 cpos = new Vector2(0,0);

        //area one ------------------------------------------------------
        if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 1){
            cpos.x = 4925;
            cpos.y = 500;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 2){
            cpos.x = 5657;
            cpos.y = 855;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 3){
            cpos.x = 5310;
            cpos.y = 700;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 4){
            cpos.x = 6040;
            cpos.y = 515;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 5){
            cpos.x = 5740;
            cpos.y = 450;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 6){
            cpos.x = 6160;
            cpos.y = 437;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 7){
            cpos.x = 8382;
            cpos.y = 787;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 8){
            cpos.x = 7867;
            cpos.y = 572;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber==9){
            cpos.x = 7917;
            cpos.y = 467;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 10){
            cpos.x = 7428;
            cpos.y = 557;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 11){
            cpos.x = 7787;
            cpos.y = 390;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 12){
            cpos.x = 5547;
            cpos.y = 727;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 13){
            cpos.x = 5692;
            cpos.y = 537;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 14){
            cpos.x = 6200;
            cpos.y = 332;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 15){
            cpos.x = 4732;
            cpos.y = 402;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 16){
            cpos.x = 7867;
            cpos.y = 547;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 17){
            cpos.x = 8452;
            cpos.y = 357;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 18){
            cpos.x = 7167;
            cpos.y = 447;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 19){
            cpos.x = 7552;
            cpos.y = 872;
            return cpos;
        }
        else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber== 20){
            cpos.x = 8687;
            cpos.y = 787;
            return cpos;
        }
        return cpos;
    }


}
