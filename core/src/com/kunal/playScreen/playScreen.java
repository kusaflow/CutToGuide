package com.kunal.playScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.AreaSelection.levelNumberSelection.LevelNumberSelection;
import com.kunal.MainGame;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.temp.temp;

public class playScreen implements Screen {

    MainGame game;

    private OrthographicCamera cam;
    private Viewport port;

    float alpha = 0;
    Sprite playScreen;
    VariablesForPlayArea variablesForPlayArea;


    public playScreen(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        port.apply();
        cam.update();

        variablesForPlayArea = new VariablesForPlayArea();
        variablesForPlayArea.setEndPoint(new Vector2(200,4000));
        variablesForPlayArea.setLevelNumber(2);

        playScreen = new Sprite(new Texture(Gdx.files.internal("playScreen/playscreen.png")));
        playScreen.setPosition(0,0);
        playScreen.setSize(AllVariables.WIDTH, AllVariables.HEIGHT);

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
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        AllVariables.batch.setProjectionMatrix(cam.combined);

        AllVariables.batch.begin();
        playScreen.setAlpha(alpha);
        playScreen.draw(AllVariables.batch);
        AllVariables.batch.end();


    }

    private void update(float dt){
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
                        return false;
                    }

                    @Override
                    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                        if (screenX > (550 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (775 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 285 * AllVariables.inpM && screenY < 460 * AllVariables.inpM) {
                            dispose();
                            game.setScreen(new AreaSelection(game));
                        }
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

        alpha+=0.08f;
        if(alpha>1)
            alpha = 1;
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
        playScreen.getTexture().dispose();
    }
}
