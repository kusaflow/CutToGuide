package com.kunal.utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;

public class ContactListenerForTheBox2d implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

        if (contact.getFixtureA() == null && contact.getFixtureB() == null) return;
        if (contact.getFixtureA().getUserData() == null && contact.getFixtureB().getUserData() == null) return;

        String adata = (String)contact.getFixtureA().getUserData();
        String bdata = (String)contact.getFixtureB().getUserData();

        //bicycle and full saw
        if (adata == "fullSaw" && bdata == "Bicycle"){
            FixtureDef fdef = new FixtureDef();
            fdef.density = contact.getFixtureB().getBody().getFixtureList().get(0).getDensity();
            fdef.friction = contact.getFixtureB().getBody().getFixtureList().get(0).getFriction();
            fdef.restitution = contact.getFixtureB().getBody().getFixtureList().get(0).getRestitution();
            fdef.shape = contact.getFixtureB().getBody().getFixtureList().get(0).getShape();
            fdef.filter.categoryBits = AllVariables.Bit_Tool;
            fdef.filter.maskBits = (short)(AllVariables.Bit_land);
            contact.getFixtureB().getBody().createFixture(fdef);
        }if (bdata == "fullSaw" && adata == "Bicycle"){
            FixtureDef fdef = new FixtureDef();
            fdef.density = contact.getFixtureA().getBody().getFixtureList().get(0).getDensity();
            fdef.friction = contact.getFixtureA().getBody().getFixtureList().get(0).getFriction();
            fdef.restitution = contact.getFixtureA().getBody().getFixtureList().get(0).getRestitution();
            fdef.shape = contact.getFixtureA().getBody().getFixtureList().get(0).getShape();
            fdef.filter.categoryBits = AllVariables.Bit_Tool;
            fdef.filter.maskBits = (short)(AllVariables.Bit_land);
            contact.getFixtureA().getBody().createFixture(fdef);
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
