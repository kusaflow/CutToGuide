package com.kunal.PlayGround.CuttingArea;

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
import com.kunal.PlayGround.Area1.AreaOneClass;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.temp.temp;

import java.util.LinkedList;

public class CuttingAreaManager implements Screen {

    MainGame game;
    private OrthographicCamera cam;
    private Viewport port;

    protected LinkedList<Byte> inputsToChop;

    private ShapeRenderer sr;

    //drawing shapes
    private float ver[];

    private int presentX, presntY;

    @Override
    public void dispose() {
        game.dispose();
        inputsToChop.clear();
        sr.dispose();
    }


    public CuttingAreaManager(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        sr = new ShapeRenderer();


        //inputs to chop
        inputsToChop = new LinkedList<Byte>();

        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;

    }


    @Override
    public void show() {
        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        sr.begin(ShapeRenderer.ShapeType.Line);

        sr.setColor(0, 0.6f, 1, 1);
        for (int i = 0; i < VariablesForPlayArea.shapes.size(); i++) {
            ver = new float[(VariablesForPlayArea.shapes.get(i).size() * 2)];
            for (int j = 0, k = 0; j < VariablesForPlayArea.shapes.get(i).size(); j++) {
                ver[k] = VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(j)][0];
                k++;
                ver[k] = VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(j)][1];
                k++;
            }
            try {
                sr.polygon(ver);
            }catch (Exception e){}
            ver = null;

        }

        sr.end();

        sr.begin(ShapeRenderer.ShapeType.Filled);

        //sr.setColor(0,1,0.5f,1);
        //sr.rect(BigSqurePoints[12][0], BigSqurePoints[12][1], 660 ,660);
        for (int i = 0; i < 16; i++) {
            sr.setColor(0, 1f, 0.2f, 1);
            for (int j=0; j<VariablesForPlayArea.cantuseDots.size(); j++){
                if (i == VariablesForPlayArea.cantuseDots.get(j))
                    sr.setColor(1,0,0,1);
            }
            if (i ==5 || i == 6 || i ==9 || i==10)
                sr.setColor(1,0,0,1);

            sr.circle(VariablesForPlayArea.BigSqurePoints[i][0], VariablesForPlayArea.BigSqurePoints[i][1], 7);
        }

        sr.setColor(1f, 0f, 0f, 1);
        try {

            for (int i = 0; i < inputsToChop.size() - 1; i++) {
                try {
                    sr.rectLine(VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i)][0], VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i)][1],
                            VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i + 1)][0], VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i + 1)][1], 5);
                } catch (Exception e) {
                }
            }
            sr.rectLine(VariablesForPlayArea.BigSqurePoints[inputsToChop.getLast()][0], VariablesForPlayArea.BigSqurePoints[inputsToChop.getLast()][1],
                    (presentX/AllVariables.inpM) - AllVariables.witdth_translation/AllVariables.inpM, (Gdx.graphics.getHeight() - presntY)/AllVariables.inpM, 5);

        } catch (Exception e) {
        }

        sr.end();

    }

    private void update(float dt) {
        input(dt);
        try {
            cam.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sr.setProjectionMatrix(cam.combined);

//        for (int i = 0; i < shapes.size(); i++) {
//            ver = new float[shapes.get(i).size() * 2];
//            for (int j = 0, k = 0; j < shapes.get(i).size(); j++) {
//                ver[k] = BigSqurePoints[shapes.get(i).get(j)][0];
//                k++;
//                ver[k] = BigSqurePoints[shapes.get(i).get(j)][1];
//                k++;
//            }
//            ver = null;
//
//        }


    }

    private void input(float dt) {
        Gdx.input.setInputProcessor(
                new InputProcessor() {

                    @Override
                    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                        inputsToChop.clear();
                        inputsToChop.add((byte) 20);

                        System.out.println(screenX);

                        return false;
                    }

                    @Override
                    public boolean touchDragged(int screenX, int screenY, int pointer) {

                        presentX = screenX;
                        presntY = screenY;

                        if (screenX > ((VariablesForPlayArea.BigSqurePoints[0][0] - 25)* AllVariables.inpM)+AllVariables.witdth_translation && screenX < ((VariablesForPlayArea.BigSqurePoints[0][0] + 25)* AllVariables.inpM)+AllVariables.witdth_translation) {
                            if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[0][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[0][1] - 25)* AllVariables.inpM) {
                                if (inputsToChop.getLast() != 0)
                                    inputsToChop.add((byte) 0);
                            } else if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[4][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[4][1] - 25)* AllVariables.inpM) {
                                if (inputsToChop.getLast() != 4)
                                    inputsToChop.add((byte) 4);
                            } else if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[8][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[8][1] - 25)* AllVariables.inpM) {
                                if (inputsToChop.getLast() != 8)
                                    inputsToChop.add((byte) 8);
                            } else if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[12][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[12][1] - 25)* AllVariables.inpM) {
                                if (inputsToChop.getLast() != 12)
                                    inputsToChop.add((byte) 12);
                            }
                        } else if (screenX > ((VariablesForPlayArea.BigSqurePoints[1][0] - 25)* AllVariables.inpM)+AllVariables.witdth_translation && screenX < ((VariablesForPlayArea.BigSqurePoints[1][0] + 25)* AllVariables.inpM) + AllVariables.witdth_translation) {
                            if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[1][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[1][1] - 25)* AllVariables.inpM) {
                                if (inputsToChop.getLast() != 1)
                                    inputsToChop.add((byte) 1);
                            } else if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[5][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[5][1] - 25)* AllVariables.inpM) {
                                if (inputsToChop.getLast() != 5)
                                    inputsToChop.add((byte) 5);
                            } else if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[9][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[9][1] - 25)* AllVariables.inpM) {
                                if (inputsToChop.getLast() != 9)
                                    inputsToChop.add((byte) 9);
                            } else if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[13][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[13][1] - 25)* AllVariables.inpM) {
                                if (inputsToChop.getLast() != 13)
                                    inputsToChop.add((byte) 13);
                            }
                        } else if (screenX > ((VariablesForPlayArea.BigSqurePoints[2][0] - 25)* AllVariables.inpM)+AllVariables.witdth_translation && screenX < ((VariablesForPlayArea.BigSqurePoints[2][0] + 25)* AllVariables.inpM)+AllVariables.witdth_translation) {
                            if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[2][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[2][1] - 25)* AllVariables.inpM) {
                                if (inputsToChop.getLast() != 2)
                                    inputsToChop.add((byte) 2);
                            } else if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[6][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[6][1] - 25)* AllVariables.inpM) {
                                if (inputsToChop.getLast() != 6)
                                    inputsToChop.add((byte) 6);
                            } else if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[10][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[10][1] - 25)* AllVariables.inpM) {
                                if (inputsToChop.getLast() != 10)
                                    inputsToChop.add((byte) 10);
                            } else if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[14][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[14][1] - 25)* AllVariables.inpM) {
                                if (inputsToChop.getLast() != 14)
                                    inputsToChop.add((byte) 14);
                            }
                        } else if (screenX > ((VariablesForPlayArea.BigSqurePoints[3][0] - 25)* AllVariables.inpM)+AllVariables.witdth_translation && screenX < ((VariablesForPlayArea.BigSqurePoints[3][0] + 25)* AllVariables.inpM)+AllVariables.witdth_translation) {
                            if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[3][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[3][1] - 25)* AllVariables.inpM) {
                                if (inputsToChop.getLast() != 3)
                                    inputsToChop.add((byte) 3);
                            } else if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[7][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[7][1] - 25)* AllVariables.inpM) {
                                if (inputsToChop.getLast() != 7)
                                    inputsToChop.add((byte) 7);
                            } else if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[11][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[11][1] - 25)* AllVariables.inpM) {
                                if (inputsToChop.getLast() != 11)
                                    inputsToChop.add((byte) 11);
                            } else if (screenY > Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[15][1] + 25)* AllVariables.inpM && screenY < Gdx.graphics.getHeight() - (VariablesForPlayArea.BigSqurePoints[15][1] - 25)* AllVariables.inpM) {
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
                            presentX = VariablesForPlayArea.BigSqurePoints[inputsToChop.getLast()][0];
                            presntY = Gdx.graphics.getHeight() - VariablesForPlayArea.BigSqurePoints[inputsToChop.getLast()][1];

                        } catch (Exception e) {
                        }


                        if (inputsToChop.isEmpty())
                            return false;

                        fillingMissingPoints();

                        if (!cutThePiece()) {
                            //error to say thay you stated from the middle and not from the start
                        }


//                        for (int i =0; i< shapes.size(); i++)
//                            System.out.println(shapes.get(i));
//
//                        System.out.println("\n\n\n");


                        inputsToChop.clear();



                        return false;
                    }


                    @Override
                    public boolean keyDown(int keycode) {
                        if (keycode == Input.Keys.B){
                            game.setScreen(new temp(game));
                        }

                        if (keycode == Input.Keys.P){
                            game.setScreen(new AreaOneClass(game));
                        }


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

    private void fillingMissingPoints() {

        //refilling the unconsistancies
        for (int i = 1; i < inputsToChop.size(); i++) {

            //upside down cutting
            if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) % 4 == 0) {
                //up to down
                if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 4 >= 2) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) - 4));
                    i--;
                    continue;
                } else if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 4 <= -2) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) + 4));
                    i--;
                    continue;
                }
            }

            //cross left to right
            if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) % 5 == 0) {
                //upleft to downright
                if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 5 >= 2 && VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i)][0] > VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i - 1)][0]
                        && VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i)][1] < VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i - 1)][1]) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) - 5));
                    i--;
                    continue;
                }
                //downright to up left
                else if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 5 <= -2 && VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i)][0] < VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i - 1)][0]
                        && VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i)][1] > VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i - 1)][1]) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) + 5));
                    i--;
                    continue;
                }
            }

            //cross right to left
            if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) % 3 == 0) {
                //upright to downleft
                if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 3 >= 2 && VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i)][0] < VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i - 1)][0]
                        && VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i)][1] < VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i - 1)][1]) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) - 3));
                    i--;
                    continue;
                }
                //downleft to upright
                else if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 3 <= -2 && VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i)][0] > VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i - 1)][0]
                        && VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i)][1] > VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i - 1)][1]) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) + 3));
                    i--;
                    continue;
                }
            }

            //sideways
            if (((inputsToChop.get(i) - inputsToChop.get(i - 1)) >= 2 && (inputsToChop.get(i) - inputsToChop.get(i - 1)) <= 4)
                    || ((inputsToChop.get(i) - inputsToChop.get(i - 1)) <= -2 && (inputsToChop.get(i) - inputsToChop.get(i - 1)) >= -4)) {
                if (VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i)][1] == VariablesForPlayArea.BigSqurePoints[inputsToChop.get(i - 1)][1]) {
                    if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) >= 2) {
                        inputsToChop.add(i, (byte) (inputsToChop.get(i) - 1));
                        i--;
                        continue;
                    } else if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) <= -2) {
                        inputsToChop.add(i, (byte) (inputsToChop.get(i) + 1));
                        i--;
                        continue;
                    }
                }
            }
        }
    }


    private Boolean cutThePiece() {

        if (inputsToChop.size() ==2) {
            if(VariablesForPlayArea.BigSqurePoints[inputsToChop.get(0)][1] == VariablesForPlayArea.BigSqurePoints[inputsToChop.get(1)][1]){
                return false;
            }else if(VariablesForPlayArea.BigSqurePoints[inputsToChop.get(0)][0] == VariablesForPlayArea.BigSqurePoints[inputsToChop.get(1)][0]){
                return false;
            }
        }

        short theShapeNumber = -1;

        //check if input point is repeted then there is no cutting
        for (int i =0; i<inputsToChop.size(); i++){
            theShapeNumber = -1;
            for (int j=0; j <inputsToChop.size(); j++){
                if (inputsToChop.get(i) == inputsToChop.get(j)){
                    theShapeNumber++;
                }
            }

            if (theShapeNumber == 0)
                continue;
            else {
                System.out.println("overlap");
                return false;
            }
        }

        theShapeNumber = -1;

        LinkedList<Byte> overlabers = new LinkedList<Byte>();
        Boolean tempBool =false, foundFront = false, stoploop=false;


        //finding the piece to be cut
        for (int i = 0; i < VariablesForPlayArea.shapes.size(); i++) {
            for (int j = 0; j < VariablesForPlayArea.shapes.get(i).size(); j++) {
                if (inputsToChop.get(0) == VariablesForPlayArea.shapes.get(i).get(j))
                    overlabers.add((byte) i);
            }
        }

        if (overlabers.isEmpty()) {
            //error to show that the start point is not on any edge
            System.out.println("the start point is not on any edge");
            return false;
        }

        if (overlabers.size() == 1){
            theShapeNumber = overlabers.getFirst();
        }
        else{
            try {
                int k = 0, x = 0;
                while (!tempBool) {
                    k++;
                    x = overlabers.size();
                    for (int i = 0; i < overlabers.size(); i++) {
                        for (int j = 0; j < VariablesForPlayArea.shapes.get(overlabers.get(i)).size(); j++) {
                            if (inputsToChop.get(k) == VariablesForPlayArea.shapes.get(overlabers.get(i)).get(j))
                                overlabers.add((byte) i);
                        }
                    }
                    for (short xyz = 0; xyz < x - 1; xyz++)
                        overlabers.removeFirst();

                    if (overlabers.size() == 1) {
                        theShapeNumber = overlabers.getFirst();
                        tempBool = true;
                    }

                }
            }catch (Exception e){
                return false;
            }

        }


         short endVal = -1;

        for (short i=0; i<VariablesForPlayArea.shapes.get(theShapeNumber).size(); i++){
            if(inputsToChop.getLast() == VariablesForPlayArea.shapes.get(theShapeNumber).get(i)){
                endVal++;
            }
        }

        if (endVal == -1){
            //error message to say that you cannot end on anywhere is should lie in a edge only
            System.out.println("you cannot end on anywhere is should lie in a edge only");
            return false;
        }

        overlabers.clear();


        short startPoint =0 , endPoint =0;


        //finding the point from where the cutting starts
        // the vertice from where the next point is inside the shape and not on side
        // and after that similaly we find the point where is ends

        tempBool =false;
        foundFront = false;
        stoploop=false;

        if (inputsToChop.size() == 2){
            startPoint = inputsToChop.getFirst();
            endPoint = inputsToChop.getLast();
        }else {
            //false means the start point is being searches and at true we found the end point
            foundFront = false;

            for (int i = 0; i < inputsToChop.size() && stoploop == false; i++){
                //strting point
                if (foundFront == false){
                    tempBool = false;
                    for (int j = 0; j < VariablesForPlayArea.shapes.get(theShapeNumber).size(); j++) {
                        if (inputsToChop.get(i) == VariablesForPlayArea.shapes.get(theShapeNumber).get(j))
                            tempBool = true;
                    }
                    if (tempBool)
                        continue;
                    else {
                        startPoint = inputsToChop.get(i - 1);
                        foundFront = true;
                        continue;
                    }
                }

                //end point
                if (foundFront == true){
                    tempBool = false;
                    for (int j = 0; j < VariablesForPlayArea.shapes.get(theShapeNumber).size(); j++) {
                        if (inputsToChop.get(i) == VariablesForPlayArea.shapes.get(theShapeNumber).get(j))
                            tempBool = true;
                    }
                    if (tempBool){
                        endPoint = inputsToChop.get(i);
                        stoploop = true;
                    }
                    else {
                        continue;
                    }
                }
            }

        }

        if (startPoint == 0 && endPoint == 0){
            //error multiple cutting
            return false;
        }

        tempBool = false;

        for (short i = (short) (inputsToChop.size() -1); i>0; i--){
            if (!tempBool){
                if (inputsToChop.get(i) == endPoint){
                    tempBool = true;
                    continue;
                }
            }
            if (tempBool) {
                for (short j = 0; j < VariablesForPlayArea.shapes.size(); j++) {
                    for (short k = 0; k < VariablesForPlayArea.shapes.get(j).size(); k++) {
                        if (inputsToChop.get(i) == VariablesForPlayArea.shapes.get(j).get(k)){
                            if (VariablesForPlayArea.shapes.get(j) != VariablesForPlayArea.shapes.get(theShapeNumber)){
                                return false;
                            }
                        }
                    }
                }
            }
        }

        VariablesForPlayArea.cantuseDots.add((byte) startPoint);
        VariablesForPlayArea.cantuseDots.add((byte)endPoint);



        //tempBool is not in use so using it to detect if startpoint is found or not
        tempBool = false;
        VariablesForPlayArea.vertices = new LinkedList<Byte>();

        for (short i = 0; true; i++){
            if (tempBool == false) {
                if(VariablesForPlayArea.shapes.get(theShapeNumber).get(i) == startPoint)
                    tempBool = true;
            }

            if(tempBool) {
                if (i > VariablesForPlayArea.shapes.get(theShapeNumber).size()-1)
                    i = 0;

                VariablesForPlayArea.vertices.add(VariablesForPlayArea.shapes.get(theShapeNumber).get(i));

                if (VariablesForPlayArea.shapes.get(theShapeNumber).get(i) == endPoint)
                    break;

            }
        }
        //the pointer is at endpoint
        //using tempbool again as it has no use at this point
        tempBool = false;
        for (int i = inputsToChop.size() - 1; i > 0; i--){
            if (tempBool == false){
                if (inputsToChop.get(i) == endPoint) {
                    tempBool = true;
                    continue;
                }
            }
            if (tempBool) {
                for (int t =0; t < VariablesForPlayArea.shapes.get(theShapeNumber).size(); t++){
                    if (inputsToChop.get(i) == VariablesForPlayArea.shapes.get(theShapeNumber).get(t)){
                        if (inputsToChop.get(i) != startPoint){
                            System.out.println("multiplecutting");
                            //error to show there are multiple cutting happened
                            return false;
                        }
                    }
                }
                VariablesForPlayArea.vertices.add(inputsToChop.get(i));
            }
        }
        VariablesForPlayArea.shapes.add(VariablesForPlayArea.vertices);
        //one part added


        //otherside
        //tempBool is not in use so using it to detect if startpoint is found or not
        tempBool = false;
        VariablesForPlayArea.vertices = new LinkedList<Byte>();

        for (short i = (short) (VariablesForPlayArea.shapes.get(theShapeNumber).size()-1); true; i--){
            if (tempBool == false) {
                if(VariablesForPlayArea.shapes.get(theShapeNumber).get(i) == startPoint)
                    tempBool = true;
            }

            if(tempBool) {
                if (i < 0)
                    i = (short) (VariablesForPlayArea.shapes.get(theShapeNumber).size() -1);

                VariablesForPlayArea.vertices.add(VariablesForPlayArea.shapes.get(theShapeNumber).get(i));

                if (VariablesForPlayArea.shapes.get(theShapeNumber).get(i) == endPoint)
                    break;

            }
        }
        //the pointer is at endpoint
        //using tempbool again as it has no use at this point
        tempBool = false;
        for (int i = inputsToChop.size() - 1; i > 0; i--){
            if (tempBool == false){
                if (inputsToChop.get(i) == endPoint) {
                    tempBool = true;
                    continue;
                }
            }
            if (tempBool)
                VariablesForPlayArea.vertices.add(inputsToChop.get(i));
        }

        VariablesForPlayArea.shapes.remove(theShapeNumber);
        VariablesForPlayArea.shapes.add(VariablesForPlayArea.vertices);

        return true;

    }




    @Override
    public void resize(int width, int height) {
        port.update(width, height);
        cam.update();
        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
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

}
