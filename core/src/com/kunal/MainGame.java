package com.kunal;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
