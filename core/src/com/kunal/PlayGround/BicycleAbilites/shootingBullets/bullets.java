package com.kunal.PlayGround.BicycleAbilites.shootingBullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

public class bullets {
    float xdesPos, ydesPos;
    float angle;
    Sprite bulletTex;
    Vector2 velocity;

    ParticleEffect pe;


    float initPosX, initPosY;

    int speed = 1000;


    OrthographicCamera camera;

    World world;
    Body body;

    public bullets(int xdes, int ydes, OrthographicCamera cam, World world){

        camera = cam;

        this.world = world;

        body = BodyGenerator.BodyAssemble(world, true, "BicycleBullet",
                new Vector2(AllVariables.BackWheel.getPosition().x*100, AllVariables.BackWheel.getPosition().x*100),
                new Vector2(60,30), 0.5f,1,AllVariables.Bit_Tool, AllVariables.Bit_enimes);

        pe = new ParticleEffect();
        pe.load(Gdx.files.internal("particles/bullets/fireTail.p"), Gdx.files.internal(""));
        //pe.getEmitters().first().setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        pe.start();
        //pe.getEmitters().first().getAngle().setHigh(90,90);


        bulletTex = new Sprite(new Texture(Gdx.files.internal("playArea/bicycleAbilites/shootingBullets/bulletForBicycle.png")));
        //bulletTex.setPosition(((((((xdes - AllVariables.witdth_translation) / AllVariables.inpM) * 1.4f + (cam.position.x - AllVariables.WIDTH / 2f))) - 280)),
          //      (((ydes / AllVariables.inpM) * 1.4f - 200 + (cam.position.y - AllVariables.HEIGHT / 2f))+20));
        bulletTex.setOriginCenter();

        velocity = new Vector2(0,0);

        //initPosX = 850 + xdes;
        //initPosX = ((((((750 - AllVariables.witdth_translation) / AllVariables.inpM) * 1.4f + (cam.position.x - AllVariables.WIDTH / 2f))) - 280));
        //initPosY = 650 + ydes;
        //initPosY = (((350 / AllVariables.inpM) * 1.4f - 200 + (cam.position.y - AllVariables.HEIGHT / 2f))+20);
        initPosX = AllVariables.FrontWheel.getPosition().x * 100;
        initPosY = AllVariables.FrontWheel.getPosition().y * 100;

        xdesPos =(((((xdes - AllVariables.witdth_translation) / AllVariables.inpM) * 1.4f + (cam.position.x - AllVariables.WIDTH / 2))) - 280);
        ydesPos = (((ydes / AllVariables.inpM) * 1.4f - 200 + (cam.position.y - AllVariables.HEIGHT / 2))+20);

        bulletTex.setPosition(initPosX, initPosY);

        /*
        //let the bullet travel to infinity
        float slope = (ydesPos - initPosY) / (xdesPos - initPosX);

        float c = ydesPos - (slope*xdesPos);

        xdesPos+= 1000;

        ydesPos = slope * xdes + c;
        */

        angle = (float) Math.atan2(ydesPos - bulletTex.getY(), xdesPos - bulletTex.getX());

        //pe.getEmitters().first().getAngle().setHigh(angle * MathUtils.radiansToDegrees,angle * MathUtils.radiansToDegrees);
        pe.getEmitters().first().getAngle().setHigh(angle * MathUtils.radiansToDegrees);

        //body.setTransform(body.getPosition().x, body.getPosition().y);


    }

    public void update(float dt) {
        //angle = (float) Math.atan2(ydesPos - bulletTex.getY(), xdesPos - bulletTex.getX());
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


        //body
        body.setTransform( (bulletTex.getX()+ bulletTex.getWidth()/2)/100 ,
                (bulletTex.getY()+ bulletTex.getHeight()/2)/100,angle);


        //particle
        pe.getEmitters().first().setPosition(body.getPosition().x*100, body.getPosition().y*100);
        //pe.getEmitters().first().setPosition();

        pe.update(Gdx.graphics.getDeltaTime());


        pe.draw(AllVariables.batch);


        if (pe.isComplete())
            pe.reset();



    }

    public float getXdesPos() {
        return xdesPos;
    }

    public float getYdesPos() {
        return ydesPos;
    }

    public float getAngle() {
        return angle;
    }

    public Sprite getBullet() {
        return bulletTex;
    }

    public void dispose(){
        pe.dispose();
    }
}
