package com.kunal.PlayGround.BicycleAbilites.shootingBullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

public class bullets {
    int xdesPos, ydesPos;
    float angle;
    Sprite bulletTex;
    Vector2 velocity;
    float initPosX, initPosY;

    int speed = 1000;

    OrthographicCamera camera;

    public bullets(int xdes, int ydes, OrthographicCamera cam){

        camera = cam;

        bulletTex = new Sprite(new Texture(Gdx.files.internal("playArea/bicycleAbilites/shootingBullets/bulletForBicycle.png")));
        //bulletTex.setPosition(((((((xdes - AllVariables.witdth_translation) / AllVariables.inpM) * 1.4f + (cam.position.x - AllVariables.WIDTH / 2f))) - 280)),
          //      (((ydes / AllVariables.inpM) * 1.4f - 200 + (cam.position.y - AllVariables.HEIGHT / 2f))+20));
        bulletTex.setOriginCenter();
        bulletTex.setPosition(0,0);


        velocity = new Vector2(0,0);

        //initPosX = 850 + xdes;
        //initPosX = ((((((750 - AllVariables.witdth_translation) / AllVariables.inpM) * 1.4f + (cam.position.x - AllVariables.WIDTH / 2f))) - 280));
        //initPosY = 650 + ydes;
        //initPosY = (((350 / AllVariables.inpM) * 1.4f - 200 + (cam.position.y - AllVariables.HEIGHT / 2f))+20);
        initPosX = AllVariables.FrontWheel.getPosition().x * 100;
        initPosY = AllVariables.FrontWheel.getPosition().y * 100;

        xdesPos = (int) (xdes);
        ydesPos = (int) (ydes);

        bulletTex.setPosition(initPosX, initPosY);

    }

    public void update(float dt) {
        angle = (float) Math.atan2(ydesPos, xdesPos);
        if (!VariablesForPlayArea.doSlowMo) {
            velocity.set((float) Math.cos(angle) * speed, (float) Math.sin(angle) * speed);
        }else {
            velocity.set((float) Math.cos(angle) * speed/10, (float) Math.sin(angle) * speed/10);
        }

        //bulletTex.setPosition(bulletTex.getX() + velocity.x * dt,  bulletTex.getY() + velocity.y * dt);
        initPosX += velocity.x * dt;
        initPosY += velocity.y * dt;
        bulletTex.setPosition(initPosX, initPosY);
        bulletTex.setRotation(angle * MathUtils.radiansToDegrees);
    }

    public int getXdesPos() {
        return xdesPos;
    }

    public int getYdesPos() {
        return ydesPos;
    }

    public float getAngle() {
        return angle;
    }

    public Sprite getBullet() {
        return bulletTex;
    }
}
