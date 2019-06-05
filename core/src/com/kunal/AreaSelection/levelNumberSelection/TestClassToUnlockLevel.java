package com.kunal.AreaSelection.levelNumberSelection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.kunal.AllVariables;
import com.kunal.MainGame;

public class TestClassToUnlockLevel implements Screen {
    MainGame game;

    short UnlockLevel;
    FileHandle file;
    String DataInFile;

    String tempstring= "";
    boolean doSkip = false;
    short hashCount =0, shorttmp;

    short starValue;

    boolean breakTowriteinfile = false;
    short placeToStartfile;




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
            if (AllVariables.PresentLevelNumber+1 == 31){

            }else {
                char[] data = DataInFile.toCharArray();
                hashCount = 0;
                tempstring = "";
                doSkip = false;
                breakTowriteinfile = false;

                for (int i = 0; i < DataInFile.length() && breakTowriteinfile == false; i++) {
                    if (doSkip) {
                        if (data[i] == '\n') {
                            doSkip = false;
                            hashCount = 0;
                            continue;
                        } else {
                            continue;
                        }
                    }

                    if (hashCount == 0) {
                        if (data[i] != '#') {
                            tempstring += data[i];
                        } else {
                            hashCount++;
                            shorttmp = new Short(tempstring);
                            tempstring = "";

                            if (shorttmp == AllVariables.PresentAreaNumber) {
                                breakTowriteinfile = true;
                                continue;
                            } else {
                                doSkip = true;
                                continue;
                            }
                        }
                    }
                }

                System.out.println("the level added is " + (AllVariables.PresentLevelNumber+1));

                //UnlockLevel++;

                //String unlclvl = String.valueOf(UnlockLevel);
                //char[] charUnlclvl = unlclvl.toCharArray();


            }
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
