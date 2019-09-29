package com.kunal.PlayGround.hint2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;
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
    float scale = 0;
    boolean inc = true, incImg = true;
    LinkedList<Sprite> img;
    float imgAlpha = 1;
    BitmapFont font;

    public Hint2shapeDrawer(OrthographicCamera camera) {
        sred = new ShapeRenderer();
        cam = camera;

        //font
        font = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font2.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter prams = new FreeTypeFontGenerator.FreeTypeFontParameter();
        prams.size = 24;
        prams.color = Color.ORANGE;
        font = generator.generateFont(prams);

        pos = new LinkedList<Vector2>();
        shapes = new LinkedList<LinkedList<Byte>>();
        rot = new LinkedList<Integer>();
        poly = new Polygon();

        pos.addAll(Hint2shapeCord.Location());
        shapes.addAll(Hint2shapeCord.Hintshapes());
        rot.addAll(Hint2shapeCord.Rotation());

        img = new LinkedList<Sprite>();

        for (int i =0, j = shapes.size(); i<Hint2shapeCord.typeOfHintImg().size(); i++, j++){
            if (Hint2shapeCord.typeOfHintImg().get(i) == 1){
                img.add(new Sprite(new Texture(Gdx.files.internal("playArea/speedPowers/GreenPill.png"))));
            }else if (Hint2shapeCord.typeOfHintImg().get(i) == 2){
                img.add(new Sprite(new Texture(Gdx.files.internal("playArea/speedPowers/RedPill.png"))));
            }

            img.get(i).setPosition(pos.get(j).x, pos.get(j).y);
            img.get(i).scale(-0.5f);
        }

    }

    public void render () {
        update();
        sred.setProjectionMatrix(cam.combined);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        sred.begin(ShapeRenderer.ShapeType.Line);

        sred.setColor(1, 0f, 0, 0.4f);
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
            poly.setRotation(rot.get(i));
            poly.setPosition(pos.get(i).x*100 , (pos.get(i).y)*100 + scale);
            //poly.scale(scale);
            poly.dirty();
            sred.polygon(poly.getTransformedVertices());
            ver = null;
        }
        sred.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        //-----------------------------------------------
        //img draw
        AllVariables.batch.begin();
        for (int i =0; i<img.size(); i++){
            img.get(i).setAlpha(imgAlpha);
            img.get(i).draw(AllVariables.batch);
        }
        AllVariables.batch.end();
        //=---------------------------------------------=


    }

    public void update(){
        if (scale >= 10f){
            inc = false;
        }else if (scale <= -10f){
            inc = true;
        }

        if (inc){
            scale+=1f;
        }else {
            scale-=1f;
        }

        //alpha value of img
        if (imgAlpha >= 1f){
            incImg = false;
        }else if (imgAlpha <= 0f){
            incImg = true;
        }


        if (incImg){
            imgAlpha += 0.1f;
        }else {
            imgAlpha -= 0.1f;
        }

    }

    public void dispose(){
        sred.dispose();
    }

}
