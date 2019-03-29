package com.kunal.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;

public class BodyGenerator {

    public static Body BodyAssemble(World world, Boolean isStatic, String fixtureId, Vector2 initPos, Vector2 dim,float friction, float density, short cbit, short mbit){
        Body b ;
        BodyDef bdef = new BodyDef();

        if(isStatic) {
            bdef.type = BodyDef.BodyType.StaticBody;
        }else{
            bdef.type = BodyDef.BodyType.DynamicBody;
        }
        bdef.position.set(initPos.x/ AllVariables.PPM, initPos.y/AllVariables.PPM);

        //bdef.fixedRotation = true;


        b = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(dim.x / AllVariables.PPM, dim.y / AllVariables.PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density=density;
        fdef.restitution = 0;
        fdef.friction = friction;
        fdef.filter.categoryBits = cbit;
        fdef.filter.maskBits = mbit;


        b.createFixture(fdef).setUserData(fixtureId);
        shape.dispose();
        return b;
    }

    public static Body CircleBody(World world, Boolean isStatic, String fixtureId, Vector2 initPos, float Radius, float density,float friction,short cbit, short mbit){
        Body b ;
        BodyDef bdef = new BodyDef();

        if(isStatic) {
            bdef.type = BodyDef.BodyType.StaticBody;
        }else{
            bdef.type = BodyDef.BodyType.DynamicBody;
        }
        bdef.position.set(initPos.x/ AllVariables.PPM, initPos.y/AllVariables.PPM);

        //bdef.fixedRotation = true;


        b = world.createBody(bdef);

        CircleShape shape = new CircleShape();
        shape.setRadius(Radius / AllVariables.PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density=density;
        fdef.restitution = 0;
        fdef.friction = friction;
        fdef.filter.categoryBits = cbit;
        fdef.filter.maskBits = mbit;


        b.createFixture(fdef).setUserData(fixtureId);
        shape.dispose();
        return b;
    }

    public static Body ChainLand(World world, Boolean isStatic, String fixtureId, Vector2 initPos, float[] dim, float density, float Bouncy, float friction,short cbit, short mbit) {
        Body b ;
        BodyDef bdef = new BodyDef();

        if(isStatic) {
            bdef.type = BodyDef.BodyType.StaticBody;
        }else{
            bdef.type = BodyDef.BodyType.DynamicBody;
        }
        bdef.position.set(initPos.x/ AllVariables.PPM, initPos.y/AllVariables.PPM);

        bdef.fixedRotation = false;

        b = world.createBody(bdef);

        ChainShape shape = new ChainShape();
        for(int i =0; i < dim.length; i++)
            dim[i] = dim[i]/AllVariables.PPM;
        //shape.createChain(dim);
        shape.createLoop(dim);



        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = density;
        fdef.restitution = Bouncy;
        fdef.friction = friction;

        fdef.filter.categoryBits = cbit;
        fdef.filter.maskBits = mbit;

        b.createFixture(fdef).setUserData(fixtureId);
        shape.dispose();
        return b;
    }

    public static Body PolyShape(World world, Boolean isStatic, String fixtureId, Vector2 initPos, float[] dim, float density, float Bouncy, float friction,short cbit, short mbit) {
        Body b ;
        BodyDef bdef = new BodyDef();

        if(isStatic) {
            bdef.type = BodyDef.BodyType.StaticBody;
        }else{
            bdef.type = BodyDef.BodyType.DynamicBody;
        }
        bdef.position.set(initPos.x/ AllVariables.PPM, initPos.y/AllVariables.PPM);

        //bdef.fixedRotation = true;

        b = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        for(int i =0; i < dim.length; i++) {
            dim[i] = dim[i] / AllVariables.PPM;
        }
        shape.set(dim);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = density;
        fdef.restitution = Bouncy;
        fdef.friction = friction;


        fdef.filter.categoryBits = cbit;
        fdef.filter.maskBits = mbit;

        b.createFixture(fdef).setUserData(fixtureId);
        shape.dispose();
        return b;
    }

    public static Body BodyAssembleKin(World world, Boolean isStatic, String fixtureId, Vector2 initPos, Vector2 dim,short cbit, short mbit){
        Body b ;
        BodyDef bdef = new BodyDef();

        bdef.type = BodyDef.BodyType.KinematicBody;

        bdef.position.set(initPos.x/ AllVariables.PPM, initPos.y/AllVariables.PPM);

        //bdef.fixedRotation = true;


        b = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(dim.x / AllVariables.PPM, dim.y / AllVariables.PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density=1;
        fdef.restitution = 0;
        fdef.friction = 0.3f;
        fdef.filter.categoryBits = cbit;
        fdef.filter.maskBits = mbit;


        b.createFixture(fdef).setUserData(fixtureId);
        shape.dispose();
        return b;
    }


}

