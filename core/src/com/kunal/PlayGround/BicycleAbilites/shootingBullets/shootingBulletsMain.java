package com.kunal.PlayGround.BicycleAbilites.shootingBullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

import java.util.LinkedList;

public class shootingBulletsMain {

    OrthographicCamera cam;
    Sprite aim;
    short alpha = 0;
    boolean doIncreaseAplhaOfAim;
    LinkedList<bullets> bulletsList;
    bullets bulletsObj;

    public shootingBulletsMain(OrthographicCamera camera){
        cam = camera;

        alpha = 0;
        doIncreaseAplhaOfAim = false;
        aim = new Sprite(new Texture(Gdx.files.internal("playArea/bicycleAbilites/shootingBullets/aim.png")));
        aim.setOriginCenter();
        aim.setPosition(0,0);

        bulletsList = new LinkedList<bullets>();


    }

    public void render(float dt){
        AllVariables.batch.begin();
        aim.draw(AllVariables.batch);
        for (int i =0; i<bulletsList.size(); i++){
            bulletsList.get(i).update(dt);
            bulletsList.get(i).getBullet().draw(AllVariables.batch);
        }
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
        //System.out.println(x + "\t" + y);

        //place the aim
        //doIncreaseAplhaOfAim = true;
        alpha = 70;
        aim.setScale(3);
        aim.setPosition((((((x - AllVariables.witdth_translation) / AllVariables.inpM) * 1.4f + (cam.position.x - AllVariables.WIDTH / 2))) - 280),
                (((y / AllVariables.inpM) * 1.4f - 200 + (cam.position.y - AllVariables.HEIGHT / 2))+20));

    }

    public void fire(int x, int y){

        //starting point for bicycle
        //850 630

        //System.out.println("shooting bullets and we have "+ VariablesForPlayArea.bulletsHave + " bullets");

        //place the aim
        aim.setPosition((((((x - AllVariables.witdth_translation) / AllVariables.inpM) * 1.4f + (cam.position.x - AllVariables.WIDTH / 2))) - 280),
                (((y / AllVariables.inpM) * 1.4f - 200 + (cam.position.y - AllVariables.HEIGHT / 2))+20));

        doIncreaseAplhaOfAim = true;
        alpha = 0;
        aim.setScale(1);

        //bullet.setPosition(AllVariables.FrontWheel.getPosition().x * 100, AllVariables.FrontWheel.getPosition().y*100);
        bulletsObj = new bullets(x - 715, y-317, cam);

        bulletsList.add(bulletsObj);


    }

}
