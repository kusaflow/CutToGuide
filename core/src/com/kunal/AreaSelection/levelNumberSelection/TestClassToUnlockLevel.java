package com.kunal.AreaSelection.levelNumberSelection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.kunal.MainGame;

public class TestClassToUnlockLevel implements Screen {
    MainGame game;

    short UnlockLevel;
    FileHandle file;
    String DataInFile;

    public TestClassToUnlockLevel(MainGame game, short unlockLevel) {
        this.game = game;
        this.UnlockLevel = unlockLevel;

        file = Gdx.files.internal("TextFiles/LevelAreaInfo");
        DataInFile = file.readString();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(0.5f, 0.1f, 0.6f, 0.2f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


    }

    private void update(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.B)){
            game.setScreen(new LevelNumberSelection(game));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.U)){
            char[] data = DataInFile.toCharArray();



        }


    }

    @Override
    public void resize(int width, int height) {

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
