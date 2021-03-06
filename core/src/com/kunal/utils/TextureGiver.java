package com.kunal.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureGiver {
    public TextureGiver() {
    }

    public static Texture coin(short i){
        Texture t = new Texture("typesOfCoin/hudCoin.png");
        if (i == 1){
            t = new Texture(Gdx.files.internal("typesOfCoin/hudCoin.png"));
        }else if(i==2){
            t = new Texture(Gdx.files.internal("typesOfCoin/hudPlayer_beige.png"));
        }else if(i==3){
            t = new Texture(Gdx.files.internal("typesOfCoin/hudPlayer_blue.png"));
        }else if(i==4){
            t = new Texture(Gdx.files.internal("typesOfCoin/hudPlayer_green.png"));
        }else if(i==5){
            t = new Texture(Gdx.files.internal("typesOfCoin/hudPlayer_pink.png"));
        }else if(i==6){
            t = new Texture(Gdx.files.internal("typesOfCoin/hudPlayer_yellow.png"));
        }
        return t;
    }

    public static Texture tyre(short i){
        Texture t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre1.png"));

        if (i == 1){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre1.png"));
        }else if (i == 2){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre2.png"));
        }else if (i == 3){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre3.png"));
        }else if (i == 4){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre4.png"));
        }else if (i == 5){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre5.png"));
        }else if (i == 6){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre6.png"));
        }else if (i == 7){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre7.png"));
        }else if (i == 8){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre8.png"));
        }else if (i == 9){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre9.png"));
        }else if (i == 10){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre10.png"));
        }else if (i == 11){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre11.png"));
        }else if (i == 12){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre12.png"));
        }else if (i == 13){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre13.png"));
        }else if (i == 14){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre14.png"));
        }else if (i == 15){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre15.png"));
        }else if (i == 16){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre16.png"));
        }else if (i == 17){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre17.png"));
        }else if (i == 18){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/tyre/tyre18.png"));
        }

        return t;
    }

    public static Texture bars(short i){
        Texture t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar1.png"));

        if (i == 1){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar1.png"));
        }else if (i==2){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar2.png"));
        }else if (i==3){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar3.png"));
        }else if (i==4){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar4.png"));
        }else if (i==5){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar5.png"));
        }else if (i==6){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar6.png"));
        }else if (i==7){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar7.png"));
        }else if (i==8){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar8.png"));
        }else if (i==9){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar9.png"));
        }else if (i==10){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar10.png"));
        }else if (i==11){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar11.png"));
        }else if (i==12){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar12.png"));
        }else if (i==13){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar13.png"));
        }else if (i==14){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar14.png"));
        }else if (i==15){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar15.png"));
        }else if (i==16){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar16.png"));
        }else if (i==17){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar17.png"));
        }else if (i==18){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar18.png"));
        }else if (i==19){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar19.png"));
        }else if (i==20){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar20.png"));
        }else if (i==21){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar21.png"));
        }else if (i==22){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar22.png"));
        }else if (i==23){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar23.png"));
        }else if (i==24){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar24.png"));
        }else if (i==25){
            t = new Texture(Gdx.files.internal("playArea/BicycleMakeUp/bars/bar25.png"));
        }

        return t;
    }
}
