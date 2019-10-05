package com.kunal.PlayGround.BicycleAbilites.shootingBullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;

public class bullets {
    int xdesPos, ydesPos;
    float angle;
    Sprite bulletTex;
    Vector2 velocity;
    float initPosX, initPosY;

    OrthographicCamera camera;

    public bullets(int xdes, int ydes, OrthographicCamera cam){

        camera = cam;

        bulletTex = new Sprite(new Texture(Gdx.files.internal("playArea/bicycleAbilites/shootingBullets/bulletForBicycle.png")));
        //bulletTex.setPosition(((((((xdes - AllVariables.witdth_translation) / AllVariables.inpM) * 1.4f + (cam.position.x - AllVariables.WIDTH / 2f))) - 280)),
          //      (((ydes / AllVariables.inpM) * 1.4f - 200 + (cam.position.y - AllVariables.HEIGHT / 2f))+20));
        bulletTex.setPosition(0,0);

        velocity = new Vector2(0,0);

        xdesPos = xdes;
        ydesPos = ydes;

        //initPosX = 850 + xdes;
        initPosX = ((((((715 - AllVariables.witdth_translation) / AllVariables.inpM) * 1.4f + (cam.position.x - AllVariables.WIDTH / 2f))) - 280));
        //initPosY = 650 + ydes;
        initPosY = (((317 / AllVariables.inpM) * 1.4f - 200 + (cam.position.y - AllVariables.HEIGHT / 2f))+20);
        bulletTex.setPosition(initPosX, initPosY);

    }

    public void update(float dt) {
        angle = (float) Math.atan2(ydesPos-bulletTex.getY(), xdesPos - bulletTex.getX());
        velocity.set((float)Math.cos(angle) * 100, (float)Math.sin(angle)*100);

        //bulletTex.setPosition(bulletTex.getX() + velocity.x * dt,  bulletTex.getY() + velocity.y * dt);
        initPosX += velocity.x * dt;
        initPosY += velocity.y * dt;
        //bulletTex.setPosition(initPosX, initPosY);
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
