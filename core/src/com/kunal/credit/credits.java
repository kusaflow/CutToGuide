package com.kunal.credit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.MainGame;

public class credits implements Screen {

    MainGame game;
    OrthographicCamera cam;
    Viewport port;

    private Texture cross, insta, twitter, kusacoin;
    BitmapFont font;


    public credits(MainGame game){
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        kusacoin = new Texture(Gdx.files.internal("utils/kusaCoin.png"));

        cross = new Texture(Gdx.files.internal("utils/hudX.png"));

        insta = new Texture(Gdx.files.internal("referenceAppLogos/insta.png"));
        twitter = new Texture(Gdx.files.internal("referenceAppLogos/Twitter.png"));

        font = new BitmapFont();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font2.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter prams = new FreeTypeFontGenerator.FreeTypeFontParameter();
        prams.size = 30;
        prams.color = Color.WHITE;
        font = generator.generateFont(prams);


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
        AllVariables.batch.draw(cross,0+cam.position.x-AllVariables.WIDTH/2,720-128);

        font.draw(AllVariables.batch,"Follow me at :", 104+100,400+100);

        AllVariables.batch.draw(insta, 200, 200+100, 100, 100);
        AllVariables.batch.draw(twitter, 700, 200+100, 100, 100);


        font.draw(AllVariables.batch,"@kusaflow", 350,250+100);
        font.draw(AllVariables.batch,"@kusaflow", 850,250+100);

        font.setColor(Color.WHITE);
        if (AllVariables.showRewardForInsta){
            AllVariables.batch.draw(kusacoin, 218, 395, 50,50);
            font.draw(AllVariables.batch, "+" + "         100", 200, 430);
        }
        if (AllVariables.showRewardFortwitter){
            AllVariables.batch.draw(kusacoin, 718, 395, 50,50);
            font.draw(AllVariables.batch, "+" + "         100", 700, 430);
        }
        font.setColor(Color.ORANGE);



        AllVariables.batch.end();
    }

    private void input(){
        if (Gdx.input.justTouched()){
            System.out.println(Gdx.input.getX()+"\t"+Gdx.input.getY());

            //cross
            if (Gdx.input.getX() >= (0 * AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getX() < (128 * AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getY() >= 0 * AllVariables.inpM && Gdx.input.getY() < 128 * AllVariables.inpM) {
                dispose();
                game.setScreen(new AreaSelection(game, true));
                return;
            }

            //insta
            if (Gdx.input.getX() >= (182 * AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getX() < (440* AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getY() >= 290 * AllVariables.inpM && Gdx.input.getY() < 530 * AllVariables.inpM) {
                //dispose();
                if (AllVariables.showRewardForInsta) {
                    AllVariables.showRewardForInsta = false;
                    writeFile("insta");
                }
                try {
                    AllVariables.openApps.OpenApp("https://www.instagram.com/kusaflow/?igshid=8y5b86e3yp6f");
                }catch (Exception e){}
            }
            //twitter
            if (Gdx.input.getX() >= (680* AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getX() < (1025* AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getY() >= 290 * AllVariables.inpM && Gdx.input.getY() < 530 * AllVariables.inpM) {
                //dispose();
                if (AllVariables.showRewardFortwitter) {
                    AllVariables.showRewardFortwitter = false;
                    writeFile("twitter");
                }
                try {
                    AllVariables.openApps.OpenApp("https://twitter.com/kusaflow?s=08");
                }catch (Exception e){}

            }
        }
    }

    private void writeFile(String s){
        FileHandle file = Gdx.files.local("TextFilesToDelete/followme");
        char[] d = file.readString().toCharArray();
        if (s.equals("twitter")){
            d[2] = '1';
            file.writeString(new String(d),false);
        }else {
            d[0] = '1';
            file.writeString(new String(d),false);
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
        cross.dispose();
        insta.dispose();
        twitter.dispose();
    }
}
