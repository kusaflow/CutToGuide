package com.kunal.AreaSelection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

    Sprite settings , shop, credit, back;


    float tapDetx, tapDety;

    public AreaSelection(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        AreaList = new LinkedList<Sprite>();

        settings = new Sprite(new Texture(Gdx.files.internal("AreaSelection/settings.png")));
        settings.setSize(50,50);

        shop = new Sprite(new Texture(Gdx.files.internal("AreaSelection/shop.png")));
        shop.setSize(50,50);

        credit = new Sprite(new Texture(Gdx.files.internal("AreaSelection/credit.png")));
        credit.setSize(50,50);

        Sprite s;
        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/Area1.png")));
        s.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/1.6f);
        s.setPosition(100,200);
        AreaList.add(s);

        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/Area2.png")));
        s.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/1.6f);
        s.setPosition(500,200);
        AreaList.add(s);

        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/Area3.png")));
        s.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/1.6f);
        s.setPosition(900,200);
        AreaList.add(s);

        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/Area4.png")));
        s.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/1.6f);
        s.setPosition(1300,200);
        AreaList.add(s);

        s = new Sprite(new Texture(Gdx.files.internal("AreaSelection/Area5.png")));
        s.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/1.6f);
        s.setPosition(1700,200);
        AreaList.add(s);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        input(delta);

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        AllVariables.batch.setProjectionMatrix(cam.combined);
        cam.update();



        AllVariables.batch.begin();
        for (Sprite s : AreaList)
            s.draw(AllVariables.batch);
        settings.draw(AllVariables.batch);
        //shop.draw(AllVariables.batch);
        //credit.draw(AllVariables.batch);
        AllVariables.batch.end();

    }

    private void update(float dt){
        credit.setPosition(100,100);
        shop.setPosition(500,500);
        settings.setPosition(942+(cam.position.x - AllVariables.WIDTH/2), 535+(cam.position.y - AllVariables.HEIGHT/2));

    }

    private void input(float dt){
        if (cam.position.x < 400)
            cam.position.set(400, cam.position.y, cam.position.z);


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
                        tapDetx = screenX;
                        tapDety = screenY;
                        return false;
                    }

                    @Override
                    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                        if(tapDetx == screenX && tapDety == screenY){
                            for (int i=0; i < AreaList.size(); i++) {
                                if (tapDetx + (cam.position.x - 640) > AreaList.get(i).getX() && tapDetx + (cam.position.x - 640) < AreaList.get(i).getX() + AreaList.get(i).getWidth()) {
                                    System.out.println(i+1);
                                }
                            }
                        }
                        return false;
                    }

                    @Override
                    public boolean touchDragged(int screenX, int screenY, int pointer) {
                        //cam.position.set(cam.position.x+(tapDetx-screenX)/5, cam.position.y, cam.position.z);

                        if (cam.position.x < 0)
                            cam.position.set(0, cam.position.y, cam.position.z);


                        if (cam.position.x > AreaList.getLast().getX())
                            cam.position.set(AreaList.getLast().getX(), cam.position.y, cam.position.z);

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
