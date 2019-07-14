package com.kunal.PlayGround.constScreen.levelUpScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.AreaSelection.levelNumberSelection.LevelNumberSelection;
import com.kunal.MainGame;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.ReDirectToTheLevel;

import java.util.LinkedList;

public class LevelCompleted implements Screen {
    MainGame game;

    OrthographicCamera cam;
    Viewport port;
    FileHandle file, kusaCoinFile;

    int timmer, star1,star2,star3, coinsEarned=0;
    Texture kusaCoin, menu, retry, next;

    BitmapFont bigText;

    Sprite starTex;

    Boolean drawButton=false;

    public LevelCompleted(MainGame game){
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        file = Gdx.files.local("TextFiles/areas/area"+AllVariables.PresentAreaNumber);
        kusaCoinFile = Gdx.files.local("TextFiles/kusaCoin");

        timmer = 0;
        star1 =0;
        star2 =0;
        star3 =0;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter prams = new FreeTypeFontGenerator.FreeTypeFontParameter();
        prams.size = 102;
        prams.color = Color.FIREBRICK;
        bigText = generator.generateFont(prams);

        kusaCoin = new Texture(Gdx.files.internal("utils/kusaCoin.png"));
        next = new Texture(Gdx.files.internal("utils/arrowRight.png"));
        menu = new Texture(Gdx.files.internal("utils/menu.png"));
        retry = new Texture(Gdx.files.internal("utils/retry.png"));

        starTex = new Sprite(new Texture(Gdx.files.internal("AreaSelection/levelSelection/star.png")));
        starTex.setOriginCenter();

        if (VariablesForPlayArea.starsGained == 0){
            coinsEarned = 10;
        }else if (VariablesForPlayArea.starsGained == 1){
            coinsEarned = 40;
        }else if (VariablesForPlayArea.starsGained == 2){
            coinsEarned = 70;
        }else if (VariablesForPlayArea.starsGained == 3){
            coinsEarned = 100;
        }

        System.out.println(VariablesForPlayArea.starsGained);

        AllVariables.inpM = (float) Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        AllVariables.batch.setProjectionMatrix(cam.combined);

        AllVariables.batch.begin();
        bigText.draw(AllVariables.batch, "GG", AllVariables.WIDTH/2-70, AllVariables.HEIGHT/2 + 202);//1
        starTex.setPosition(AllVariables.WIDTH/2-100-60,250);
        starTex.setSize(star1,star1);
        starTex.draw(AllVariables.batch);

        starTex.setPosition(AllVariables.WIDTH/2-60,250);
        starTex.setSize(star2,star2);
        starTex.draw(AllVariables.batch);

        starTex.setPosition(AllVariables.WIDTH/2+100-60,250);
        starTex.setSize(star3,star3);
        starTex.draw(AllVariables.batch);

        AllVariables.batch.draw(kusaCoin, 1100,668,50,50);
        AllVariables.bitmapFont.draw(AllVariables.batch, ">"+AllVariables.kusaCoin, 1150, 700);

        if (drawButton){
            AllVariables.batch.draw(menu, 400,130);
            AllVariables.batch.draw(retry, 600, 130);
            AllVariables.batch.draw(next, 800,130);
        }

        AllVariables.batch.draw(kusaCoin, 530,400,50,50);
        if (timmer<coinsEarned)
            AllVariables.bitmapFont.draw(AllVariables.batch, timmer+"", 580, 432);
        else
            AllVariables.bitmapFont.draw(AllVariables.batch, coinsEarned+"", 580, 432);

        AllVariables.bitmapFont.draw(AllVariables.batch, " earned", 630, 432);




        AllVariables.batch.end();

    }

    public void update(){
        input();
        timmer++;

        if (VariablesForPlayArea.starsGained == 0){
            if (timmer >= 10)
                drawButton=true;
        }else if (VariablesForPlayArea.starsGained == 1){
            if (timmer >= 0){
                star1+=15;
                if (star1>=108) {
                    drawButton = true;
                }
            }
        }else if(VariablesForPlayArea.starsGained == 2){
            if (timmer >= 0 && timmer<10){
                star1+=15;
            }else if (timmer >= 10 && timmer<20) {
                star2 += 15;
                if (star2>=108)
                    drawButton = true;
            }
        }else if (VariablesForPlayArea.starsGained == 3){
            if (timmer >= 0 && timmer<10){
                star1+=15;
            }else if (timmer >= 10 && timmer<20) {
                star2 += 15;
            }else if (timmer >= 20 && timmer<30) {
                star3 += 15;
                if (star3>=108)
                    drawButton = true;
            }
        }
        if (star1>=120)
            star1 = 120;
        if (star2>=120)
            star2 = 120;
        if (star3>=120)
            star3 = 120;

    }

    public void input(){
        if (Gdx.input.justTouched()) {
            //menu
            if (Gdx.input.getX() > (380 * AllVariables.inpM) + AllVariables.witdth_translation
                    && Gdx.input.getX() < (540 * AllVariables.inpM) + AllVariables.witdth_translation
                    && Gdx.input.getY() > 470 * AllVariables.inpM && Gdx.input.getY() < 600 * AllVariables.inpM) {
                AllVariables.kusaCoin+=coinsEarned;
                changeFile();
                game.setScreen(new LevelNumberSelection(game));
            }
            //retry
            if (Gdx.input.getX() > (560* AllVariables.inpM)+AllVariables.witdth_translation
                    && Gdx.input.getX() < (710* AllVariables.inpM)+AllVariables.witdth_translation
                    && Gdx.input.getY() > 470*AllVariables.inpM && Gdx.input.getY() < 600*AllVariables.inpM){
                AllVariables.kusaCoin+=coinsEarned;
                changeFile();
                ReDirectToTheLevel.Direct(game, true);
            }
            //next
            if (Gdx.input.getX() > (770* AllVariables.inpM)+AllVariables.witdth_translation
                    && Gdx.input.getX() < (910* AllVariables.inpM)+AllVariables.witdth_translation
                    && Gdx.input.getY() > 470*AllVariables.inpM && Gdx.input.getY() < 600*AllVariables.inpM){
                AllVariables.kusaCoin+=coinsEarned;
                changeFile();
                if (AllVariables.PresentAreaNumber == 1){
                    if (AllVariables.PresentLevelNumber < 30)
                        AllVariables.PresentLevelNumber++;
                }
                ReDirectToTheLevel.Direct(game, false);
            }
        }

        Gdx.input.setInputProcessor(null);
    }

    private void changeFile(){
        kusaCoinFile.writeString(String.valueOf(AllVariables.kusaCoin), false);

        char[] data = file.readString().toCharArray();
        String tempDAta="";

        int UnlockedLevel, TotalLevel;
        LinkedList<Short> stars = new LinkedList<Short>();
        int i=0;

        //collecting data
        while (data[i] != '\n'){
            i++;
        }
        i++;
        tempDAta = "";
        while (data[i] != '\n'){
            tempDAta+=data[i];
            i++;
        }
        UnlockedLevel = new Short(tempDAta);
        i++;
        tempDAta = "";
        while (data[i] != '\n'){
            tempDAta+=data[i];
            i++;
        }
        TotalLevel = new Short(tempDAta);
        i++;
        tempDAta = "";
        while (data[i] != '\n'){
            tempDAta+=data[i];
            stars.add(new Short(tempDAta));
            tempDAta = "";
            i++;
        }

        //to check to update unlockLEvel or not
        if (UnlockedLevel == AllVariables.PresentLevelNumber && UnlockedLevel != 30){
            UnlockedLevel++;
            tempDAta = AllVariables.PresentAreaNumber + "\n" + UnlockedLevel + "\n" + TotalLevel + "\n";
            stars.set(UnlockedLevel-1,(short) VariablesForPlayArea.starsGained);
            for (int k=0; k< stars.size(); k++){
                tempDAta+=String.valueOf(stars.get(k));
            }
            tempDAta+="\n$";
            file.writeString(tempDAta, false);
        }else {
            tempDAta = AllVariables.PresentAreaNumber + "\n" + UnlockedLevel + "\n" + TotalLevel + "\n";
            short t = stars.get(AllVariables.PresentLevelNumber-1);
            if (t < VariablesForPlayArea.starsGained){
                stars.set(AllVariables.PresentLevelNumber-1,(short) VariablesForPlayArea.starsGained);
            }
            System.out.println(t);
            for (int k=0; k< stars.size(); k++){
                tempDAta+=String.valueOf(stars.get(k));
            }
            tempDAta+="\n$";
            file.writeString(tempDAta, false);
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
