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
import com.kunal.temp.temp;

import java.util.LinkedList;

public class CuttingAreaManager implements Screen {

    MainGame game;
    private OrthographicCamera cam;
    private Viewport port;

    protected LinkedList<Byte> vertices;
    protected LinkedList<LinkedList<Byte>> shapes;

    private LinkedList<Byte> cantuseDots;

    protected LinkedList<Byte> inputsToChop;

    int step = 0, t = 0;

    private ShapeRenderer sr;


    // all the points of the big square
    protected int[][] BigSqurePoints = new int[16][2];

    //drawing shapes
    private float ver[];

    private int presentX, presntY;


    public CuttingAreaManager(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        sr = new ShapeRenderer();

        cantuseDots = new LinkedList<Byte>();

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
        vertices.add((byte) 1);
        vertices.add((byte) 2);
        vertices.add((byte) 3);
        vertices.add((byte) 7);
        vertices.add((byte) 11);
        vertices.add((byte) 15);
        vertices.add((byte) 14);
        vertices.add((byte) 13);
        vertices.add((byte) 12);
        vertices.add((byte) 8);
        vertices.add((byte) 4);


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

        sr.setColor(0, 0.6f, 1, 1);
        for (int i = 0; i < shapes.size(); i++) {
            ver = new float[(shapes.get(i).size() * 2)];
            for (int j = 0, k = 0; j < shapes.get(i).size(); j++) {
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
        for (int i = 0; i < 16; i++) {
            sr.setColor(0, 1f, 0.2f, 1);
            for (int j=0; j<cantuseDots.size(); j++){
                if (i == cantuseDots.get(j))
                    sr.setColor(1,0,0,1);
            }
            if (i ==5 || i == 6 || i ==9 || i==10)
                sr.setColor(1,0,0,1);

            sr.circle(BigSqurePoints[i][0], BigSqurePoints[i][1], 7);
        }

        sr.setColor(1f, 0f, 0f, 1);
        try {

            for (int i = 0; i < inputsToChop.size() - 1; i++) {
                try {
                    sr.rectLine(BigSqurePoints[inputsToChop.get(i)][0], BigSqurePoints[inputsToChop.get(i)][1],
                            BigSqurePoints[inputsToChop.get(i + 1)][0], BigSqurePoints[inputsToChop.get(i + 1)][1], 5);
                } catch (Exception e) {
                }
            }
            sr.rectLine(BigSqurePoints[inputsToChop.getLast()][0], BigSqurePoints[inputsToChop.getLast()][1], presentX, AllVariables.HEIGHT - presntY, 5);

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
                            presentX = BigSqurePoints[inputsToChop.getLast()][0];
                            presntY = AllVariables.HEIGHT - BigSqurePoints[inputsToChop.getLast()][1];

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
                if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 5 >= 2 && BigSqurePoints[inputsToChop.get(i)][0] > BigSqurePoints[inputsToChop.get(i - 1)][0]
                        && BigSqurePoints[inputsToChop.get(i)][1] < BigSqurePoints[inputsToChop.get(i - 1)][1]) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) - 5));
                    i--;
                    continue;
                }
                //downright to up left
                else if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 5 <= -2 && BigSqurePoints[inputsToChop.get(i)][0] < BigSqurePoints[inputsToChop.get(i - 1)][0]
                        && BigSqurePoints[inputsToChop.get(i)][1] > BigSqurePoints[inputsToChop.get(i - 1)][1]) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) + 5));
                    i--;
                    continue;
                }
            }

            //cross right to left
            if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) % 3 == 0) {
                //upright to downleft
                if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 3 >= 2 && BigSqurePoints[inputsToChop.get(i)][0] < BigSqurePoints[inputsToChop.get(i - 1)][0]
                        && BigSqurePoints[inputsToChop.get(i)][1] < BigSqurePoints[inputsToChop.get(i - 1)][1]) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) - 3));
                    i--;
                    continue;
                }
                //downleft to upright
                else if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 3 <= -2 && BigSqurePoints[inputsToChop.get(i)][0] > BigSqurePoints[inputsToChop.get(i - 1)][0]
                        && BigSqurePoints[inputsToChop.get(i)][1] > BigSqurePoints[inputsToChop.get(i - 1)][1]) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) + 3));
                    i--;
                    continue;
                }
            }

            //sideways
            if (((inputsToChop.get(i) - inputsToChop.get(i - 1)) >= 2 && (inputsToChop.get(i) - inputsToChop.get(i - 1)) <= 4)
                    || ((inputsToChop.get(i) - inputsToChop.get(i - 1)) <= -2 && (inputsToChop.get(i) - inputsToChop.get(i - 1)) >= -4)) {
                if (BigSqurePoints[inputsToChop.get(i)][1] == BigSqurePoints[inputsToChop.get(i - 1)][1]) {
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
        for (int i = 0; i < shapes.size(); i++) {
            for (int j = 0; j < shapes.get(i).size(); j++) {
                if (inputsToChop.get(0) == shapes.get(i).get(j))
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
                        for (int j = 0; j < shapes.get(overlabers.get(i)).size(); j++) {
                            if (inputsToChop.get(k) == shapes.get(overlabers.get(i)).get(j))
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

        for (short i=0; i<shapes.get(theShapeNumber).size(); i++){
            if(inputsToChop.getLast() == shapes.get(theShapeNumber).get(i)){
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
                    for (int j = 0; j < shapes.get(theShapeNumber).size(); j++) {
                        if (inputsToChop.get(i) == shapes.get(theShapeNumber).get(j))
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
                    for (int j = 0; j < shapes.get(theShapeNumber).size(); j++) {
                        if (inputsToChop.get(i) == shapes.get(theShapeNumber).get(j))
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
                for (short j = 0; j < shapes.size(); j++) {
                    for (short k = 0; k < shapes.get(j).size(); k++) {
                        if (inputsToChop.get(i) == shapes.get(j).get(k)){
                            if (shapes.get(j) != shapes.get(theShapeNumber)){
                                return false;
                            }
                        }
                    }
                }
            }
        }

        cantuseDots.add((byte) startPoint);
        cantuseDots.add((byte)endPoint);



        //tempBool is not in use so using it to detect if startpoint is found or not
        tempBool = false;
        vertices = new LinkedList<Byte>();

        for (short i = 0; true; i++){
            if (tempBool == false) {
                if(shapes.get(theShapeNumber).get(i) == startPoint)
                    tempBool = true;
            }

            if(tempBool) {
                if (i > shapes.get(theShapeNumber).size()-1)
                    i = 0;

                vertices.add(shapes.get(theShapeNumber).get(i));

                if (shapes.get(theShapeNumber).get(i) == endPoint)
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
                for (int t =0; t < shapes.get(theShapeNumber).size(); t++){
                    if (inputsToChop.get(i) == shapes.get(theShapeNumber).get(t)){
                        if (inputsToChop.get(i) != startPoint){
                            System.out.println("multiplecutting");
                            //error to show there are multiple cutting happened
                            return false;
                        }
                    }
                }
                vertices.add(inputsToChop.get(i));
            }
        }
        shapes.add(vertices);
        //one part added


        //otherside
        //tempBool is not in use so using it to detect if startpoint is found or not
        tempBool = false;
        vertices = new LinkedList<Byte>();

        for (short i = (short) (shapes.get(theShapeNumber).size()-1); true; i--){
            if (tempBool == false) {
                if(shapes.get(theShapeNumber).get(i) == startPoint)
                    tempBool = true;
            }

            if(tempBool) {
                if (i < 0)
                    i = (short) (shapes.get(theShapeNumber).size() -1);

                vertices.add(shapes.get(theShapeNumber).get(i));

                if (shapes.get(theShapeNumber).get(i) == endPoint)
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
                vertices.add(inputsToChop.get(i));
        }

        shapes.remove(theShapeNumber);
        shapes.add(vertices);

        return true;

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
