package com.kunal.PlayGround;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kunal.AllVariables;
import com.kunal.MainGame;
import com.kunal.PlayGround.Area1.AreaOneClass;

public class ShapeChooser implements Screen {

    MainGame game;
    ShapeRenderer sred;
    int x=90,y=287;

    public ShapeChooser(MainGame game) {
        this.game = game;
        sred = new ShapeRenderer();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {
        input(dt);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sred.begin(ShapeRenderer.ShapeType.Line);
        sred.line(40,720,40,0);
        sred.line(315,720,315,0);
        sred.line(590,720,590,0);
        sred.line(865,720,865,0);
        sred.line(1140,720,1140,0);

        sred.line(0,710,1280,710);
        sred.line(0,507,1280,507);
        sred.line(0,304,1280,304);
        sred.line(0,101,1280,101);

        sred.end();

        sred.begin(ShapeRenderer.ShapeType.Filled);
        sred.rect(x, y,275,203);

        sred.end();

    }

    private void input(float dt){

        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > 40 && Gdx.input.getX() < 315) {
                if (AllVariables.HEIGHT - Gdx.input.getY() > 101 && AllVariables.HEIGHT -  Gdx.input.getY() < 304) {
                    x=40;y=101;
                } else if (AllVariables.HEIGHT - Gdx.input.getY() > 304 && AllVariables.HEIGHT -  Gdx.input.getY() < 507) {
                    x=40;y=304;
                } else if (AllVariables.HEIGHT - Gdx.input.getY() > 507 && AllVariables.HEIGHT -  Gdx.input.getY() < 710) {
                    x=40;y=507;
                }
            } else if (Gdx.input.getX() > 315 && Gdx.input.getX() < 590) {
                if (AllVariables.HEIGHT - Gdx.input.getY() > 101 && AllVariables.HEIGHT -  Gdx.input.getY() < 304){
                    x=315;y=101;
                }else if (AllVariables.HEIGHT - Gdx.input.getY() > 304 && AllVariables.HEIGHT -  Gdx.input.getY() < 507){
                    x=315;y=304;
                }else if (AllVariables.HEIGHT - Gdx.input.getY() > 507 && AllVariables.HEIGHT -  Gdx.input.getY() < 710){
                    x=315;y=507;
                }
            } else if (Gdx.input.getX() > 590 && Gdx.input.getX() < 865) {
                if (AllVariables.HEIGHT - Gdx.input.getY() > 101 && AllVariables.HEIGHT -  Gdx.input.getY() < 304){
                    x=590;y=101;
                }else if (AllVariables.HEIGHT - Gdx.input.getY() > 304 && AllVariables.HEIGHT -  Gdx.input.getY() < 507){
                    x=590;y=304;
                }else if (AllVariables.HEIGHT - Gdx.input.getY() > 507 && AllVariables.HEIGHT -  Gdx.input.getY() < 710){
                    x=590;y=507;
                }
            } else if (Gdx.input.getX() > 865 && Gdx.input.getX() < 1140) {
                if (AllVariables.HEIGHT - Gdx.input.getY() > 101 && AllVariables.HEIGHT -  Gdx.input.getY() < 304){
                    x=865;y=101;
                }else if (AllVariables.HEIGHT - Gdx.input.getY() > 304 && AllVariables.HEIGHT -  Gdx.input.getY() < 507){
                    x=865;y=304;
                }else if (AllVariables.HEIGHT - Gdx.input.getY() > 507 && AllVariables.HEIGHT -  Gdx.input.getY() < 710){
                    x=865;y=507;
                }
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
