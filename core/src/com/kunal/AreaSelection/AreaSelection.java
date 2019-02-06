package com.kunal.AreaSelection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;

import java.util.LinkedList;

public class AreaSelection implements Screen {

    MainGame game;

    LinkedList<Sprite> AreaList;
    OrthographicCamera cam;
    Viewport port;

    float xtemp;

    public AreaSelection(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        AreaList = new LinkedList<Sprite>();

        Sprite s;
        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/Area1.jpg")));
        s.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/1.6f);
        s.setPosition(100,200);
        AreaList.add(s);

        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/Area2.jpg")));
        s.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/1.6f);
        s.setPosition(500,200);
        AreaList.add(s);

        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/Area3.jpg")));
        s.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/1.6f);
        s.setPosition(900,200);
        AreaList.add(s);

        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/Area4.jpg")));
        s.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/1.6f);
        s.setPosition(1300,200);
        AreaList.add(s);

        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/Area5.jpg")));
        s.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/1.6f);
        s.setPosition(1700,200);
        AreaList.add(s);



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        input(delta);

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        AllVariables.batch.setProjectionMatrix(cam.combined);
        cam.update();


        AllVariables.batch.begin();
        for (Sprite s : AreaList)
            s.draw(AllVariables.batch);
        AllVariables.batch.end();

    }

    private void input(float dt){
        if(Gdx.input.isTouched()){
            //cam.position.set(cam.position.x+10, cam.position.y, cam.position.z);
        }

        Gdx.input.setInputProcessor(
                new GestureDetector(
                        new GestureDetector.GestureListener() {
                            @Override
                            public boolean touchDown(float x, float y, int pointer, int button) {
                                xtemp = x;
                                return false;
                            }

                            @Override
                            public boolean tap(float x, float y, int count, int button) {
                                return false;
                            }

                            @Override
                            public boolean longPress(float x, float y) {
                                return false;
                            }

                            @Override
                            public boolean fling(float velocityX, float velocityY, int button) {
                                //cam.position.set(cam.position.x+10, cam.position.y, cam.position.z);

                                return false;
                            }

                            @Override
                            public boolean pan(float x, float y, float deltaX, float deltaY) {
                                cam.position.set(cam.position.x+(xtemp-x), cam.position.y, cam.position.z);
                                System.out.println((x-deltaX)/AllVariables.PPM);
                                return false;
                            }

                            @Override
                            public boolean panStop(float x, float y, int pointer, int button) {
                                return false;
                            }

                            @Override
                            public boolean zoom(float initialDistance, float distance) {
                                return false;
                            }

                            @Override
                            public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
                                return false;
                            }

                            @Override
                            public void pinchStop() {

                            }
                        }
                )
        );
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
