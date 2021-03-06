package com.kunal.PlayGround.TypeThreeArea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

public class ParticleManagerArea2 {

    ParticleEffect frontTyreFire, backTyreFire,bar1;

    public ParticleManagerArea2(){
        frontTyreFire = new ParticleEffect();
        frontTyreFire.load(Gdx.files.internal("particles/bicycleFire/tyreFire.p"), Gdx.files.internal(""));
        //frontTyreFire.getEmitters().first().setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        frontTyreFire.start();


        backTyreFire = new ParticleEffect();
        backTyreFire.load(Gdx.files.internal("particles/bicycleFire/tyreFire.p"), Gdx.files.internal(""));
        //frontTyreFire.getEmitters().first().setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        backTyreFire.start();

        bar1 = new ParticleEffect();
        bar1.load(Gdx.files.internal("particles/bicycleFire/bar1.p"), Gdx.files.internal(""));
        //frontTyreFire.getEmitters().first().setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        bar1.start();


    }

    public void update(){
        if (VariablesForPlayArea.bicycleOnFire) {
            frontTyreFire.getEmitters().first().setPosition(AllVariables.FrontWheel.getPosition().x * AllVariables.PPM - 20,
                    AllVariables.FrontWheel.getPosition().y * AllVariables.PPM - 20);
            backTyreFire.getEmitters().first().setPosition(AllVariables.BackWheel.getPosition().x * AllVariables.PPM - 20,
                    AllVariables.BackWheel.getPosition().y * AllVariables.PPM - 20);
            bar1.getEmitters().first().setPosition(AllVariables.rod6.getPosition().x * AllVariables.PPM ,
                    AllVariables.rod6.getPosition().y * AllVariables.PPM-20);

            frontTyreFire.update(Gdx.graphics.getDeltaTime());
            backTyreFire.update(Gdx.graphics.getDeltaTime());
            bar1.update(Gdx.graphics.getDeltaTime());

            if (frontTyreFire.isComplete())
                frontTyreFire.reset();

            if (backTyreFire.isComplete())
                backTyreFire.reset();

            if (bar1.isComplete())
                bar1.reset();
        }


    }

    public void render(){
        if (VariablesForPlayArea.bicycleOnFire) {
            AllVariables.batch.begin();
            frontTyreFire.draw(AllVariables.batch);
            backTyreFire.draw(AllVariables.batch);
            bar1.draw(AllVariables.batch);
            AllVariables.batch.end();
        }

    }
}
