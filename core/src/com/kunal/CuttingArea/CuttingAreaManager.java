package com.kunal.CuttingArea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;

import java.util.LinkedList;

public class CuttingAreaManager implements Screen {

    MainGame game;
    private OrthographicCamera cam;
    private Viewport port;

    private LinkedList<Byte> vertices;
    private LinkedList<LinkedList<Byte>> shapes;

    private LinkedList<MinMaxClass> minmax;
    private LinkedList<Byte> inputsToChop;

    private ShapeRenderer sr;


    // all the points of the big square
    private int[][] BigSqurePoints = new int[16][2];

    //drawing shapes
    private float ver[];

    private int presentX, presntY;



    public CuttingAreaManager(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        sr = new ShapeRenderer();

        //shapes and vertices
        vertices = new LinkedList<Byte>();
        shapes = new LinkedList<LinkedList<Byte>>();

        //inputs to chop
        inputsToChop = new LinkedList<Byte>();


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


        sr.begin(ShapeRenderer.ShapeType.Line);

        sr.setColor(0,0.6f,1,1);
        for (int i =0; i < shapes.size(); i++){
            ver = new float[shapes.get(i).size()*2];
            for(int j=0, k=0; j< shapes.get(i).size(); j++){
                ver[k] = BigSqurePoints[shapes.get(i).get(j)][0];
                k++;
                ver[k] = BigSqurePoints[shapes.get(i).get(j)][1];
                k++;
            }
            sr.polygon(ver);
            ver = null;
        }

        sr.end();

        sr.begin(ShapeRenderer.ShapeType.Filled);

        //sr.setColor(0,1,0.5f,1);
        //sr.rect(BigSqurePoints[12][0], BigSqurePoints[12][1], 660 ,660);
        sr.setColor(1,0.2f,0.2f,1);
        for (int i=0; i<16; i++){
            sr.circle(BigSqurePoints[i][0], BigSqurePoints[i][1], 7);
        }

        sr.setColor(1f,0f,0f,1);
        try {

            for (int i = 1; i < inputsToChop.size()-1; i++) {
                sr.rectLine(BigSqurePoints[inputsToChop.get(i)][0], BigSqurePoints[inputsToChop.get(i)][1],
                        BigSqurePoints[inputsToChop.get(i+1)][0],BigSqurePoints[inputsToChop.get(i+1)][1],5);
            }
            sr.rectLine(BigSqurePoints[inputsToChop.getLast()][0],BigSqurePoints[inputsToChop.getLast()][1],presentX, AllVariables.HEIGHT-presntY,5);

        }catch (Exception e){}

        sr.end();

    }

    private void update(float dt){
        input(dt);
        try {
            cam.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sr.setProjectionMatrix(cam.combined);

        for (int i =0; i < shapes.size(); i++){
            ver = new float[shapes.get(i).size()*2];
            for(int j=0, k=0; j< shapes.get(i).size(); j++){
                ver[k] = BigSqurePoints[shapes.get(i).get(j)][0];
                k++;
                ver[k] = BigSqurePoints[shapes.get(i).get(j)][1];
                k++;
            }
            ver = null;

        }





    }

    private void input(float dt) {
        Gdx.input.setInputProcessor(
                new InputProcessor() {

                    @Override
                    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                        inputsToChop.clear();
                        inputsToChop.add((byte) 20);

                        return false;
                    }

                    @Override
                    public boolean touchDragged(int screenX, int screenY, int pointer) {

                        presentX = screenX;
                        presntY = screenY;

                        if (screenX > BigSqurePoints[0][0] - 25 && screenX < BigSqurePoints[0][0] + 25) {
                            if (screenY > AllVariables.HEIGHT - (BigSqurePoints[0][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[0][1] - 25)) {
                                if (inputsToChop.getLast() != 0)
                                    inputsToChop.add((byte) 0);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[4][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[4][1] - 25)) {
                                if (inputsToChop.getLast() != 4)
                                    inputsToChop.add((byte) 4);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[8][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[8][1] - 25)) {
                                if (inputsToChop.getLast() != 8)
                                    inputsToChop.add((byte) 8);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[12][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[12][1] - 25)) {
                                if (inputsToChop.getLast() != 12)
                                    inputsToChop.add((byte) 12);
                            }
                        } else if (screenX > BigSqurePoints[1][0] - 25 && screenX < BigSqurePoints[1][0] + 25) {
                            if (screenY > AllVariables.HEIGHT - (BigSqurePoints[1][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[1][1] - 25)) {
                                if (inputsToChop.getLast() != 1)
                                    inputsToChop.add((byte) 1);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[5][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[5][1] - 25)) {
                                if (inputsToChop.getLast() != 5)
                                    inputsToChop.add((byte) 5);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[9][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[9][1] - 25)) {
                                if (inputsToChop.getLast() != 9)
                                    inputsToChop.add((byte) 9);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[13][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[13][1] - 25)) {
                                if (inputsToChop.getLast() != 13)
                                    inputsToChop.add((byte) 13);
                            }
                        } else if (screenX > BigSqurePoints[2][0] - 25 && screenX < BigSqurePoints[2][0] + 25) {
                            if (screenY > AllVariables.HEIGHT - (BigSqurePoints[2][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[2][1] - 25)) {
                                if (inputsToChop.getLast() != 2)
                                    inputsToChop.add((byte) 2);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[6][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[6][1] - 25)) {
                                if (inputsToChop.getLast() != 6)
                                    inputsToChop.add((byte) 6);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[10][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[10][1] - 25)) {
                                if (inputsToChop.getLast() != 10)
                                    inputsToChop.add((byte) 10);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[14][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[14][1] - 25)) {
                                if (inputsToChop.getLast() != 14)
                                    inputsToChop.add((byte) 14);
                            }
                        } else if (screenX > BigSqurePoints[3][0] - 25 && screenX < BigSqurePoints[3][0] + 25) {
                            if (screenY > AllVariables.HEIGHT - (BigSqurePoints[3][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[3][1] - 25)) {
                                if (inputsToChop.getLast() != 3)
                                    inputsToChop.add((byte) 3);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[7][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[7][1] - 25)) {
                                if (inputsToChop.getLast() != 7)
                                    inputsToChop.add((byte) 7);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[11][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[11][1] - 25)) {
                                if (inputsToChop.getLast() != 11)
                                    inputsToChop.add((byte) 11);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[15][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[15][1] - 25)) {
                                if (inputsToChop.getLast() != 15)
                                    inputsToChop.add((byte) 15);
                            }
                        }

                        return false;
                    }


                    @Override
                    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                        //remove first element
                        try {
                            inputsToChop.removeFirst();
                        } catch (Exception e) {
                        }

                        System.out.println(inputsToChop);


                        if (inputsToChop.isEmpty())
                            return false;

                        for (int i = 1; i < inputsToChop.size(); i++) {
                            if (inputsToChop.get(i) == 4)
                                inputsToChop.add(i, (byte)20);

                            //if ((inputsToChop.get(i) - inputsToChop.get(i-1)) %4 == 0){
                                //System.out.println("alright");
                            //}
                        }

                        System.out.println(inputsToChop);



                        return false;
                    }



                    @Override
                    public boolean keyDown(int keycode) {
                        return false;
                    }

                    @Override
                    public boolean keyUp(int keycode) {
                        return false;
                    }

                    @Override
                    public boolean keyTyped(char character) {
                        return false;
                    }

                    @Override
                    public boolean mouseMoved(int screenX, int screenY) {
                        return false;
                    }

                    @Override
                    public boolean scrolled(int amount) {
                        return false;
                    }
                }
        );

    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
        cam.update();
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
