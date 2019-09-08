package com.kunal.utils.slideShow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;
import com.kunal.PlayGround.Tutorial.tutScreen;
import com.kunal.utils.ReDirectToTheLevel;

import java.util.LinkedList;

public class simpleSlideShow implements Screen {

    MainGame game;
    String fileLoc = "";
    int senderCode;
    int focused, totalImg, yaxis = 50, xaxis = 50;
    Texture presentImg;
    Texture exit;

    private OrthographicCamera cam;
    private Viewport port;

    public simpleSlideShow(MainGame game, int senderCode){
        this.game = game;
        this.senderCode = senderCode;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        port.apply();

        exit = new Texture(Gdx.files.internal("utils/hudX.png"));

        totalImg = slideShowHelper.ImageCount(senderCode);


        focused = 1;

        if (senderCode == 0) {
            fileLoc = "HintImg/HintTwo/area" + AllVariables.PresentAreaNumber +"/level" + AllVariables.PresentLevelNumber+"/";
        }

        presentImg = new Texture(Gdx.files.internal(fileLoc+focused+".jpg"));

        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        input();
        update();

        AllVariables.batch.setProjectionMatrix(cam.combined);

        AllVariables.batch.begin();
        AllVariables.batch.draw(presentImg,xaxis, yaxis);
        AllVariables.batch.draw(exit,1120+(cam.position.x - AllVariables.WIDTH/2), 600+(cam.position.y - AllVariables.HEIGHT/2));
        AllVariables.bitmapFont.draw(AllVariables.batch,"Touch anywhere to continue", 400+(cam.position.x - AllVariables.WIDTH/2), 690+(cam.position.y - AllVariables.HEIGHT/2));

        AllVariables.batch.end();

    }

    private void update(){
        if (focused == totalImg+1){
            //return to the back class
            dispose();
            if (senderCode >= 1)
                game.setScreen(new tutScreen(game));
            else
                ReDirectToTheLevel.Direct(game, true);
            return;
        }

        presentImg = new Texture(Gdx.files.internal(fileLoc+focused+".jpg"));

    }

    private void input(){
        Gdx.input.setInputProcessor(
                new InputProcessor() {
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
                    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                        screenY = Gdx.graphics.getHeight() - screenY;
                        //System.out.println(screenX + "\t" + screenY);
                        //exit when pressed
                        if (screenX > (1110 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (1270 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 605 * AllVariables.inpM && screenY < 720 * AllVariables.inpM){
                            if (senderCode >= 1) {
                                game.setScreen(new tutScreen(game));
                            }else {
                                ReDirectToTheLevel.Direct(game, true);
                                dispose();
                            }
                            return true;
                        }

                        focused++;


                        return false;
                    }

                    @Override
                    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                        return false;
                    }

                    @Override
                    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
