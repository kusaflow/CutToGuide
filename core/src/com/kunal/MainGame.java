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
