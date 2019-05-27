package com.kunal.AreaSelection.levelNumberSelection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.MainGame;
import com.kunal.PlayGround.VariablesForPlayArea;

import java.util.LinkedList;

public class LevelNumberSelection implements Screen {
    MainGame game;
    OrthographicCamera cam;
    Viewport port;
    FileHandle file;
    String DataInFile = "";

    //dataFromFile in the variables
    private short UnlockedLevel, TotalLevel;
    private LinkedList<Short> stars;

    private Texture stone;

    private Texture cross;

    /*
    for the file structure :
        for every area the format is : "_LevelNumber_#_UnLockedLevels_#_TotalLevels_#_UnLockedLevel_MultiplyBy_Star"
        "$" is the end of the file
     */


    public LevelNumberSelection(MainGame game) {
        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        file = Gdx.files.internal("TextFiles/LevelAreaInfo");
        DataInFile = file.readString();

        stars = new LinkedList<Short>();

        processData();

        //texture and sprites
        stone = new Texture(Gdx.files.internal("AreaSelection/levelSelection/stone.png"));
        cross = new Texture(Gdx.files.internal("utils/hudX.png"));

    }

    private void processData(){
        String tempstring= "";
        boolean doSkip = false;
        short hashCount =0, shorttmp;

        char[] data = DataInFile.toCharArray();
        hashCount = 0;
        tempstring = "";

        for (int i =0; i<DataInFile.length(); i++){
            if (doSkip){
                if (data[i] == '\n'){
                    doSkip = false;
                    hashCount = 0;
                    continue;
                }else {
                    continue;
                }
            }

            if (hashCount == 0){
                if (data[i] != '#') {
                    tempstring += data[i];
                }else {
                    hashCount++;
                    shorttmp = new Short(tempstring);
                    tempstring = "";

                    if (shorttmp == AllVariables.PresentAreaNumber) {
                        continue;
                    } else {
                        doSkip = true;
                        continue;
                    }
                }
            }

            if (hashCount == 1){
                if (data[i] != '#'){
                    tempstring += data[i];
                }else{
                    hashCount++;
                    shorttmp = new Short(tempstring);
                    tempstring = "";

                    UnlockedLevel = shorttmp;
                    continue;
                }
            }

            if (hashCount == 2){
                if (data[i] != '#'){
                    tempstring += data[i];
                }else{
                    hashCount++;
                    shorttmp = new Short(tempstring);
                    tempstring = "";

                    TotalLevel = shorttmp;

                    stars.clear();
                    continue;
                }
            }

            if (hashCount == 3){
                if (data[i] != '#'){
                    tempstring += data[i];
                    shorttmp = new Short(tempstring);
                    tempstring = "";

                    stars.add(shorttmp);
                }else{
                    hashCount++;
                    continue;
                }
            }

            if (hashCount == 4){
                break;
            }

            ///////////////////////////////
        }


    }

    @Override
    public void show() {
        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.3f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        AllVariables.batch.setProjectionMatrix(cam.combined);
        input(delta);

        cam.update();


        AllVariables.batch.begin();

        //cross to go back
        AllVariables.batch.draw(cross,0,720-128);

        //all stages
        AllVariables.batch.draw(stone, 250,460, 120,200);
        AllVariables.batch.draw(stone, 450,460, 120,200);
        AllVariables.batch.draw(stone, 650,460, 120,200);
        AllVariables.batch.draw(stone, 850,460, 120,200);
        AllVariables.batch.draw(stone, 1050,460, 120,200);

        AllVariables.batch.draw(stone, 250,200, 120,200);
        AllVariables.batch.draw(stone, 450,200, 120,200);
        AllVariables.batch.draw(stone, 650,200, 120,200);
        AllVariables.batch.draw(stone, 850,200, 120,200);
        AllVariables.batch.draw(stone, 1050,200, 120,200);









        AllVariables.batch.end();

    }

    private void input(float dt){
        if (Gdx.input.isKeyJustPressed(Input.Keys.B))
            game.setScreen(new AreaSelection(game));
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            System.out.println("Unlock Levels : \t" + UnlockedLevel);
            System.out.println("Total Levels : \t" + TotalLevel);
            System.out.println("Stars : \t" + stars);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            cam.position.set(cam.position.x-=10, cam.position.y, cam.position.z);

        if (Gdx.input.isKeyPressed(Input.Keys.S))
            cam.position.set(cam.position.x+=10, cam.position.y, cam.position.z);




    }

    @Override
    public void resize(int width, int height) {
        //port.update(width, height);
        //cam.update();
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

    @Override
    public void dispose() {

    }
}
