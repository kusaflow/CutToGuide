package com.kunal.PlayGround.TypeThreeArea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.kunal.AllVariables;

public class ParticleManagerArea2 {

    ParticleEffect dirtTailParticle, dirtTailParticle2;
    public Boolean dirtTailReady = false;

    public ParticleManagerArea2(){
        //load according to level

        dirtTailParticle = new ParticleEffect();
        dirtTailParticle.load(Gdx.files.internal("particles/area2Dirt/dirtTailC.p"), Gdx.files.internal(""));
        dirtTailParticle.getEmitters().first().scaleSize(0.6f);

        dirtTailParticle.start();

        dirtTailParticle2 = new ParticleEffect();
        dirtTailParticle2.load(Gdx.files.internal("particles/area2Dirt/dirtTailC.p"), Gdx.files.internal(""));
        dirtTailParticle2.getEmitters().first().scaleSize(0.6f);
        dirtTailParticle2.start();

    }

    public void update(){
        dirtTailParticle.update(Gdx.graphics.getDeltaTime());
        dirtTailParticle2.update(Gdx.graphics.getDeltaTime());
    }

    public void dirtTail(){
        if (dirtTailReady) {
            AllVariables.batch.begin();
            //backWheel
            dirtTailParticle.getEmitters().first().setPosition(AllVariables.BackWheel.getPosition().x * 100,
                    (AllVariables.BackWheel.getPosition().y * 100)- 25);
            dirtTailParticle.draw(AllVariables.batch);

            //front wheel
            dirtTailParticle2.getEmitters().first().setPosition((AllVariables.FrontWheel.getPosition().x * 100),
                    (AllVariables.FrontWheel.getPosition().y * 100) - 25);
            dirtTailParticle2.draw(AllVariables.batch);

            AllVariables.batch.end();
        }
        if (dirtTailParticle.isComplete())
            dirtTailParticle.reset();
        if (dirtTailParticle2.isComplete())
            dirtTailParticle2.reset();

    }
}
