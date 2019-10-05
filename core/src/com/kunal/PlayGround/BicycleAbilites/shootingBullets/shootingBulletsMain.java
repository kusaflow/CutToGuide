package com.kunal.PlayGround.BicycleAbilites.shootingBullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

public class shootingBulletsMain {

    OrthographicCamera cam;
    Sprite aim;
    short alpha = 0;
    boolean doIncreaseAplhaOfAim;

    public shootingBulletsMain(OrthographicCamera camera){
        cam = camera;

        alpha = 0;
        doIncreaseAplhaOfAim = false;
        aim = new Sprite(new Texture(Gdx.files.internal("playArea/bicycleAbilites/shootingBullets/aim.png")));
        aim.setPosition(0,0);

    }

    public void render(){
        AllVariables.batch.begin();
        aim.draw(AllVariables.batch);
        AllVariables.batch.end();
    }

    public void update(){
        if (doIncreaseAplhaOfAim){
            if (alpha<100){
                alpha+=10;
            }else {
                alpha = 100;
                doIncreaseAplhaOfAim = false;
            }
        }
        aim.setAlpha(alpha/100f);
    }

    public void shoot(int x, int y){
        //System.out.println("shooting bullets and we have "+ VariablesForPlayArea.bulletsHave + " bullets");

        //place the aim
        doIncreaseAplhaOfAim = true;
        alpha = 0;
        aim.setPosition((((((x - AllVariables.witdth_translation) / AllVariables.inpM) * 1.4f + (cam.position.x - AllVariables.WIDTH / 2))) - 280),
                (((y / AllVariables.inpM) * 1.4f - 200 + (cam.position.y - AllVariables.HEIGHT / 2))+20));

    }

}
