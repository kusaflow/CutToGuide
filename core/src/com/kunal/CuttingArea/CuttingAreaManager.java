package com.kunal.CuttingArea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.utils.ShortArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;

import java.util.LinkedList;

public class CuttingAreaManager implements Screen {

    /*
    for drawing polygons i use this tutorial
    --->https://www.youtube.com/watch?v=UL_XQLu6sPQ

     */

    MainGame game;
    private OrthographicCamera cam;
    private Viewport port;

    private LinkedList<Byte> vertices;
    private LinkedList<LinkedList<Byte>> shapes;

    private ShapeRenderer sr;


    // all the points of the big square
    private int[][] BigSqurePoints = new int[16][2];

    

    public CuttingAreaManager(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        sr = new ShapeRenderer();

        //shapes and vertices
        vertices = new LinkedList<Byte>();
        shapes = new LinkedList<LinkedList<Byte>>();


        //all big squre Points
        BigSqurePoints[0][0] = 550;
        BigSqurePoints[0][1] = 700;
        BigSqurePoints[1][0] = 770;
        BigSqurePoints[1][1] = 700;
        BigSqurePoints[2][0] = 990;
        BigSqurePoints[2][1] = 700;
        BigSqurePoints[3][0] = 1210;
        BigSqurePoints[3][1] = 700;
        BigSqurePoints[4][0] = 550;
        BigSqurePoints[4][1] = 480;
        BigSqurePoints[5][0] = 770;
        BigSqurePoints[5][1] = 480;
        BigSqurePoints[6][0] = 990;
        BigSqurePoints[6][1] = 480;
        BigSqurePoints[7][0] = 1210;
        BigSqurePoints[7][1] = 480;
        BigSqurePoints[8][0] = 550;
        BigSqurePoints[8][1] = 260;
        BigSqurePoints[9][0] = 770;
        BigSqurePoints[9][1] = 260;
        BigSqurePoints[10][0] = 990;
        BigSqurePoints[10][1] = 260;
        BigSqurePoints[11][0] = 1210;
        BigSqurePoints[11][1] = 260;
        BigSqurePoints[12][0] = 550;
        BigSqurePoints[12][1] = 40;
        BigSqurePoints[13][0] = 770;
        BigSqurePoints[13][1] = 40;
        BigSqurePoints[14][0] = 990;
        BigSqurePoints[14][1] = 40;
        BigSqurePoints[15][0] = 1210;
        BigSqurePoints[15][1] = 40;

        /*// for big square
        vertices.add(BigSqurePoints[0][0]); //0
        vertices.add(BigSqurePoints[0][1]);
        vertices.add(BigSqurePoints[3][0]); //3
        vertices.add(BigSqurePoints[3][1]);
        vertices.add(BigSqurePoints[15][0]); //15
        vertices.add(BigSqurePoints[15][1]);
        vertices.add(BigSqurePoints[12][0]); //12
        vertices.add(BigSqurePoints[12][1]);
        */
        vertices.add((byte) 0);
        vertices.add((byte) 3);
        vertices.add((byte) 15);
        vertices.add((byte) 12);



        shapes.add(vertices);

        //Integer[] data = shapes.get(0).toArray(new Integer[shapes.get(0).size()]);


        //System.out.println(shapes.size());
        //System.out.println(shapes.get(0).size());
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sr.begin(ShapeRenderer.ShapeType.Filled);

        sr.setColor(0,1,0.5f,1);
        sr.rect(BigSqurePoints[12][0], BigSqurePoints[12][1], 660 ,660);

        sr.setColor(1,1,1,1);
        for (int i=0; i<16; i++){
            sr.circle(BigSqurePoints[i][0], BigSqurePoints[i][1], 5);
        }

        sr.end();

    }

    private void update(float dt){
        input(dt);
        cam.update();


    }

    private void input(float dt) {

    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
