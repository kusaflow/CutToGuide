package com.kunal.AreaSelection.levelNumberSelection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.MainGame;

public class LevelNumberSelection implements Screen {
    MainGame game;
    OrthographicCamera cam;
    Viewport port;
    FileHandle file;
    String DataInFile = "";

    //dataFromFile in the variables
    byte UnlockedLevel, TotalLevel, Stars[];

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

        processData();

    }

    private void processData(){
        char[] data = DataInFile.toCharArray();

        for (int i =0; i<DataInFile.length(); i++){

        }


    }

    @Override
    public void show() {
        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.4f, 0.3f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        input(delta);

    }

    private void input(float dt){
        if (Gdx.input.isKeyJustPressed(Input.Keys.B))
            game.setScreen(new AreaSelection(game));
        if (Gdx.input.isKeyJustPressed(Input.Keys.D))
            System.out.println(DataInFile);

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
