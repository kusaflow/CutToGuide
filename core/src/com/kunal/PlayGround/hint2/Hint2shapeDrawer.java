package com.kunal.PlayGround.hint2;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.kunal.PlayGround.VariablesForPlayArea;

import java.util.LinkedList;

public class Hint2shapeDrawer {

    ShapeRenderer sred;
    OrthographicCamera cam;
    LinkedList<Vector2> pos;
    LinkedList<LinkedList<Byte>> shapes;
    LinkedList<Integer> rot;
    private float ver[];
    Polygon poly;
    float scale = 1;

    public Hint2shapeDrawer(OrthographicCamera camera) {
        sred = new ShapeRenderer();
        cam = camera;

        pos = new LinkedList<Vector2>();
        shapes = new LinkedList<LinkedList<Byte>>();
        rot = new LinkedList<Integer>();
        poly = new Polygon();

        pos.addAll(Hint2shapeCord.Location());
        shapes.addAll(Hint2shapeCord.Hintshapes());
        rot.addAll(Hint2shapeCord.Rotation());
    }

    public void render () {
        update();
        sred.setProjectionMatrix(cam.combined);

        sred.begin(ShapeRenderer.ShapeType.Line);

        sred.setColor(1, 0f, 0, 1);
        //sred.setColor(.1f,.1f,.1f,1);

        for (int i = 0; i < shapes.size(); i++) {
            ver = new float[(shapes.get(i).size() * 2)];
            for (int j = 0, k = 0; j < shapes.get(i).size(); j++) {
                ver[k] = VariablesForPlayArea.BigSqurePoints[shapes.get(i).get(0)][0]/(2)-VariablesForPlayArea.BigSqurePoints[shapes.get(i).get(j)][0]/2;
                k++;
                ver[k] = VariablesForPlayArea.BigSqurePoints[shapes.get(i).get(0)][1]/(2) -VariablesForPlayArea.BigSqurePoints[shapes.get(i).get(j)][1]/2;
                k++;
            }

            poly = new Polygon(ver);
            //poly.setPosition(AllVariables.BackWheel.getPosition().x*100
            //    , AllVariables.BackWheel.getPosition().y*100);
            poly.setPosition(pos.get(i).x*100, pos.get(i).y*100);
            poly.setRotation(rot.get(i));
            poly.scale(scale);
            poly.dirty();
            sred.polygon(poly.getTransformedVertices());
            ver = null;
        }
        sred.end();

    }

    public void update(){

    }

    public void dispose(){
        sred.dispose();
    }

}
