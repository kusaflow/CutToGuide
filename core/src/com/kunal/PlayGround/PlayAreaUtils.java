package com.kunal.PlayGround;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.utils.BodyGenerator;

import java.util.LinkedList;

public class PlayAreaUtils {


    public PlayAreaUtils() {
    }

    public void MoveShapesToRealWorld(){
        for (Body b : VariablesForPlayArea.CutOutBodies){
            FixtureDef fdef = new FixtureDef();
            fdef.density = b.getFixtureList().get(0).getDensity();
            fdef.friction = b.getFixtureList().get(0).getFriction();
            fdef.restitution = b.getFixtureList().get(0).getRestitution();
            fdef.shape = b.getFixtureList().get(0).getShape();
            fdef.filter.categoryBits = AllVariables.Bit_Tool;
            fdef.filter.maskBits = (short)(AllVariables.Bit_Bicycle | AllVariables.Bit_Tool | AllVariables.Bit_land | AllVariables.Bit_enimes);

            b.createFixture(fdef);

        }
    }


    public static void parseTiledObj(World world, MapObjects objects){
        for (MapObject object : objects){
            Shape shape;
            if(object instanceof PolylineMapObject){
                shape = createPolyLine((PolylineMapObject)object);
            }else{
                continue;
            }

            Body body;
            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bdef);
            body.createFixture(shape, 1f);
            shape.dispose();
        }
    }

    public static ChainShape createPolyLine(PolylineMapObject polyline){
        float[] vertices = polyline.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length/2];

        for (int i=0; i < worldVertices.length; i++){
            worldVertices[i] = new Vector2(vertices[i*2]/AllVariables.PPM, vertices[i*2+1]/AllVariables.PPM);
        }

        ChainShape cs = new ChainShape();
        cs.createChain(worldVertices);

        return cs;
    }



}
