package com.kunal.credit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.MainGame;

public class credits implements Screen {

    MainGame game;
    OrthographicCamera cam;
    Viewport port;

    private Texture cross, insta, twitter;


    public credits(MainGame game){
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        cross = new Texture(Gdx.files.internal("utils/hudX.png"));

        insta = new Texture(Gdx.files.internal("referenceAppLogos/insta.png"));
        twitter = new Texture(Gdx.files.internal("referenceAppLogos/Twitter.png"));


    }

    @Override
    public void show() {
        AllVariables.inpM = (float) Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.3f, .1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        AllVariables.batch.setProjectionMatrix(cam.combined);
        input();

        cam.update();

        AllVariables.batch.begin();
        AllVariables.batch.draw(cross,0+cam.position.x-AllVariables.WIDTH/2,720-128);

        AllVariables.bitmapFont.draw(AllVariables.batch,"Hello, \n             |/\n", 100+100,600);
        AllVariables.bitmapFont.draw(AllVariables.batch,"     I m |\\unal and hope you are enjoying this game :) ", 102+100,560);
        AllVariables.bitmapFont.draw(AllVariables.batch,"             .", 104+100,571);
        AllVariables.bitmapFont.draw(AllVariables.batch,"Follow us at :", 104+100,400);

        AllVariables.batch.draw(insta, 200, 200, 100, 100);
        AllVariables.batch.draw(twitter, 700, 200, 100, 100);


        AllVariables.bitmapFont.draw(AllVariables.batch,"@kusaflow", 350,250);
        AllVariables.bitmapFont.draw(AllVariables.batch,"@kusaflow", 850,250);






        AllVariables.batch.end();
    }

    private void input(){
        if (Gdx.input.justTouched()){
            System.out.println(Gdx.input.getX()+"\t"+Gdx.input.getY());

            //cross
            if (Gdx.input.getX() >= (0 * AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getX() < (128 * AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getY() >= 0 * AllVariables.inpM && Gdx.input.getY() < 128 * AllVariables.inpM) {
                //dispose();
                game.setScreen(new AreaSelection(game));
                return;
            }

            //insta
            if (Gdx.input.getX() >= (187 * AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getX() < (525* AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getY() >= 400 * AllVariables.inpM && Gdx.input.getY() < 552 * AllVariables.inpM) {
                //dispose();
                AllVariables.openApps.OpenApp("https://www.instagram.com/kusaflow/?igshid=8y5b86e3yp6f");
                return;
            }
            //twitter
            if (Gdx.input.getX() >= (685* AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getX() < (1052* AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getY() >= 400 * AllVariables.inpM && Gdx.input.getY() < 552 * AllVariables.inpM) {
                //dispose();
                AllVariables.openApps.OpenApp("https://twitter.com/kusaflow?s=08");
                return;
            }
        }
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
