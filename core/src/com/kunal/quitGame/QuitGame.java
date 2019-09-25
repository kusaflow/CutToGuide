package com.kunal.quitGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.MainGame;

public class QuitGame implements Screen {

    MainGame game;

    OrthographicCamera cam;
    Viewport port;

    BitmapFont font, font2;

    public QuitGame(MainGame game){
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        font = new BitmapFont();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font2.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter prams = new FreeTypeFontGenerator.FreeTypeFontParameter();
        prams.size = 50;
        prams.color = Color.RED;
        font = generator.generateFont(prams);

        font2 = new BitmapFont();
        prams = new FreeTypeFontGenerator.FreeTypeFontParameter();
        prams.size = 50;
        prams.color = Color.WHITE;
        font2 = generator.generateFont(prams);




    }

    @Override
    public void show() {
        AllVariables.inpM = (float) Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.3f, .6f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        AllVariables.batch.setProjectionMatrix(cam.combined);
        input();

        cam.update();

        AllVariables.batch.begin();
        font.draw(AllVariables.batch, "Sure you want to quit" ,380, 500);
        font2.draw(AllVariables.batch, "Yes" ,400, 300);
        font2.draw(AllVariables.batch, "No" ,800, 300);
        AllVariables.batch.end();
    }

    public void input(){
        if (Gdx.input.justTouched()) {
            System.out.println(Gdx.input.getX() + "\t" + Gdx.input.getY());

            //yes
            if (Gdx.input.getX() >= (387 * AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getX() < (497 * AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getY() >= 396* AllVariables.inpM && Gdx.input.getY() < 468    * AllVariables.inpM) {
                dispose();
                Gdx.app.exit();
                return;
            }
            //no
            if (Gdx.input.getX() >= (754 * AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getX() < (895 * AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getY() >= 373 * AllVariables.inpM && Gdx.input.getY() < 490 * AllVariables.inpM) {
                dispose();
                game.setScreen(new AreaSelection(game,true));
                return;
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
