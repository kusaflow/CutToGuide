package com.kunal.PlayGround.Area1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;
import com.kunal.PlayGround.ObjectCreation;
import com.kunal.utils.BodyGenerator;

public class AreaOneClass implements Screen {
    MainGame game;

    private World world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera cam;
    private Viewport port;

    private ShapeRenderer sred;

    private ObjectCreation objectCreation;

    private Vector2 endPoint;

    private Sprite Brake, start;
    private Boolean brakeBool = false, startBool = false;


    public AreaOneClass(MainGame game, Vector2 endPoint) {
        this.game = game;
        this.endPoint = endPoint;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH*2, AllVariables.HEIGHT*2, cam);

        world = new World(new Vector2(0,-10f), false);

        b2dr = new Box2DDebugRenderer();

        sred = new ShapeRenderer();

        objectCreation = new ObjectCreation();

        objectCreation.CreateBicycle(world);

        Brake = new Sprite(new Texture(Gdx.files.internal("playArea/BothBrake.png")));
        Brake.setPosition(1050,140);
        Brake.setSize(180,150);
        Brake.setAlpha(0.4f);

        start = new Sprite(new Texture(Gdx.files.internal("playArea/Start.png")));
        start.setPosition(50, 140);
        start.setSize(150, 150);
        start.setAlpha(0.8f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {
        update(dt);
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(world, cam.combined.scl(AllVariables.PPM));

        AllVariables.batch.begin();
        Brake.draw(AllVariables.batch);
        start.draw(AllVariables.batch);
        AllVariables.batch.end();

    }

    private void update(float dt){
        input(dt);
        input(dt);
        //if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        world.step(1/(1/dt), 6,2);


        Vector3 campos = cam.position;
        campos.x = AllVariables.BackWheel.getPosition().x*AllVariables.PPM;
        campos.y = AllVariables.BackWheel.getPosition().y*AllVariables.PPM;
        cam.position.set(campos);
        cam.update();

        if (startBool){
            if (brakeBool)
                AllVariables.BackWheel.setAngularVelocity(0);
            else
                AllVariables.BackWheel.setAngularVelocity(-20);
        }

    }

    private void input(float dt){
        Gdx.input.setInputProcessor(
                new InputProcessor() {
                    @Override
                    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                        screenY = AllVariables.HEIGHT - screenY;
                        if (startBool) {
                            if (screenX > 1020 && screenX < 1250 && screenY > 120 && screenY < 310) {
                                Brake.setAlpha(0.9f);
                                brakeBool = true;
                            }
                        }
                        if (!startBool){
                            if (screenX > 40 && screenX < 220 && screenY > 120 && screenY < 310) {
                                start.setAlpha(0);
                                startBool = true;
                            }
                        }

                        System.out.println(screenX);


                        return false;
                    }

                    @Override
                    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                        if (brakeBool) {
                            Brake.setAlpha(0.4f);
                            brakeBool = false;
                        }
                        return false;
                    }

                    @Override
                    public boolean touchDragged(int screenX, int screenY, int pointer) {
                        return false;
                    }

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
                    public boolean mouseMoved(int screenX, int screenY) {
                        return false;
                    }

                    @Override
                    public boolean scrolled(int amount) {
                        return false;
                    }
                }
        );


    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
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
