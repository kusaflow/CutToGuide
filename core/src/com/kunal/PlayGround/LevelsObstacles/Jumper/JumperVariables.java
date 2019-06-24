package com.kunal.PlayGround.LevelsObstacles.Jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class JumperVariables {
    Texture texture = new Texture(Gdx.files.internal("playArea/LevelObstacles/Jumper/closed.png"));
    public short x;
    public short y;
    public Boolean textureChanged = false;
}
