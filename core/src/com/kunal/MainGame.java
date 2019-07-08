package com.kunal;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kunal.temp.temp;

import java.util.LinkedList;

public class MainGame extends Game {

    public MainGame() {
    }

    public MainGame(AdVideoInterface adv) {
		AllVariables.adv = adv;
	}

	@Override
	public void create () {
	    AllVariables.batch = new SpriteBatch();
		AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
		AllVariables.witdth_translation = (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
		AllVariables.bitmapFont = new BitmapFont();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter prams = new FreeTypeFontGenerator.FreeTypeFontParameter();
        prams.size = 24;
        prams.color = Color.ORANGE;
        AllVariables.bitmapFont = generator.generateFont(prams);

        //for testing only
		if (Gdx.files.local("TextFiles/LevelAreaInfo").exists()) {
			Gdx.files.local("TextFiles/LevelAreaInfo").delete();
		}

			//creating local file
		if (!Gdx.files.local("TextFiles/LevelAreaInfo").exists()){
			FileHandle savedata = Gdx.files.local("TextFiles/LevelAreaInfo");
			String data = "1#30#30#231232221312133123123123131231#\n" +
					"2#3#30#320000000000000000000000000000#\n" +
					"3#7#30#312312000000000000000000000000#\n" +
					"4#12#30#321231231230000000000000000000#\n" +
					"5#19#30#321123313213213213000000000000#\n" +
					"6#25#30#321313121311321131231231000000#\n" +
					"$";
			savedata.writeString(data,true);
			System.out.println("writing");
		}

		if (!Gdx.files.local("TextFiles/kusaCoin").exists()){
			FileHandle kusaCoin = Gdx.files.local("TextFiles/kusaCoin");
			String data = "100";

			kusaCoin.writeString(data, false);
			AllVariables.kusaCoin = 100;
		}else {
			FileHandle kusaCoin = Gdx.files.local("TextFiles/kusaCoin");
			AllVariables.kusaCoin = new Short(kusaCoin.readString());
		}

		AllVariables.unlockedCoin = new LinkedList<Byte>();

		AllVariables.unlockedBar = new LinkedList<Byte>();

		AllVariables.unlockedWheel = new LinkedList<Byte>();

		if (!Gdx.files.local("TextFiles/LockUnlock").exists()){
			FileHandle lu = Gdx.files.local("TextFiles/LockUnlock");
			String data = "0\n0\n0\n0#\n0#\n0#\n";
			lu.writeString(data, false);
			AllVariables.tyreType = 0;
			AllVariables.coinType = 0;
			AllVariables.bodyOfCycle = 0;
			AllVariables.unlockedBar.add((byte) 0);
			AllVariables.unlockedCoin.add((byte) 0);
			AllVariables.unlockedWheel.add((byte) 0);
		}else {
			FileHandle lu = Gdx.files.local("TextFiles/LockUnlock");
			char[] data = lu.readString().toCharArray();
			String temp;
			short tracker=0;
			boolean looper;

			//for bar
			looper = true;
			temp = "";
			while (looper){
				if(data[tracker] == '\n'){
					looper = false;
					AllVariables.bodyOfCycle = Byte.valueOf(temp);
				}else {
					temp += data[tracker];
				}
				tracker++;
			}


			//for coin
			looper = true;
			temp = "";
			while (looper){
				if(data[tracker] == '\n'){
					looper = false;
					AllVariables.coinType= Byte.valueOf(temp);
				}else {
					temp += data[tracker];
				}
				tracker++;
			}


			//for wheel
			looper = true;
			temp = "";
			while (looper){
				if(data[tracker] == '\n'){
					looper = false;
					AllVariables.tyreType= Byte.valueOf(temp);
				}else {
					temp += data[tracker];
				}
				tracker++;
			}


			//for unlocked bar
			while (data[tracker] != '\n'){
				temp="";
				while (data[tracker] != '#'){
					temp+=data[tracker];
					tracker++;
				}

				AllVariables.unlockedBar.add(Byte.valueOf(temp));
				temp="";
				tracker++;
			}
			tracker++;

			//for unlocked coin
			while (data[tracker] != '\n'){
				temp="";
				while (data[tracker] != '#'){
					temp+=data[tracker];
					tracker++;
				}

				AllVariables.unlockedCoin.add(Byte.valueOf(temp));
				temp="";
				tracker++;
			}
			tracker++;

			//for unlocked wheel
			while (data[tracker] != '\n'){
				temp="";
				while (data[tracker] != '#'){
					temp+=data[tracker];
					tracker++;
				}

				AllVariables.unlockedWheel.add(Byte.valueOf(temp));
				temp="";
				tracker++;
			}
		}


		this.setScreen(new temp(this));

	}

	@Override
	public void render () {
	    super.render();
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	}

	@Override
	public void dispose () {
		AllVariables.batch.dispose();
		AllVariables.bitmapFont.dispose();
	}
}
