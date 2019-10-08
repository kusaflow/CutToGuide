package com.kunal.utils.contextListerner;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.kunal.PlayGround.VariablesForPlayArea;

public class myContactListner implements ContactListener {

    public myContactListner(){

    }

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if (a == null || b == null) return;
        if (a.getUserData() == null || b.getUserData() == null) return;

        //System.out.println(a.getUserData() + "\t" + b.getUserData());


        //for breaking candy
        for (int i = 0; i< VariablesForPlayArea.breakingCandyBar.size(); i++){
            if ((a.getUserData().equals("candyBar"+i) && b.getUserData().equals("Bicycle"))
                    || ((b.getUserData().equals("candyBar"+i) && a.getUserData().equals("Bicycle")))){
                VariablesForPlayArea.breakingCandyBar.get(i).contactHappend = true;
            }
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
