package com.kunal.PlayGround;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;
import com.kunal.temp.temp;
import com.kunal.utils.BodyGenerator;

public class PlayArea implements Screen {

    MainGame game;

    private World world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera cam;
    private Viewport port;

    private ShapeRenderer sred;

    private ObjectCreation objectCreation;

    //PlayingButtons
    private Sprite speedSprite;
    private int XofspeedSprite =65, YofspeedSprit=150;
    private Boolean isspeedSpritePressed = false;

    //brakes
    private Sprite BackandBrake, FrontBrake, PlayerLaunch;
    Boolean isBackandBrakePressed = false, isFrontBrakePressed = false, isPlayerLaunchPressed = false;



    Body mover;

    public PlayArea(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        world = new World(new Vector2(0,-10f), false);

        b2dr = new Box2DDebugRenderer();

        sred = new ShapeRenderer();

        objectCreation = new ObjectCreation();

        objectCreation.CreateBicycle(world);

        mover = BodyGenerator.BodyAssembleKin(world, false, "mover", new Vector2(523,-50), new Vector2(10,10),AllVariables.Bit_land,AllVariables.Bit_Bicycle);

        //sprites
        speedSprite = new Sprite(new Texture(Gdx.files.internal("playArea/BikeSpeed.png")));
        speedSprite.setAlpha(0.7f);
        speedSprite.setSize(80,80);
        speedSprite.setPosition(XofspeedSprite - speedSprite.getWidth()/2,YofspeedSprit - speedSprite.getHeight()/2);

        PlayerLaunch = new Sprite(new Texture(Gdx.files.internal("playArea/BothBrake.png")));
        PlayerLaunch.setPosition(1100,500);
        PlayerLaunch.setSize(130,130);
        PlayerLaunch.setAlpha(0.4f);

        FrontBrake = new Sprite(new Texture(Gdx.files.internal("playArea/BackBrake.png")));
        FrontBrake.setPosition(1100,200);
        FrontBrake.setSize(130,130);
        FrontBrake.setAlpha(0.4f);

        BackandBrake = new Sprite(new Texture(Gdx.files.internal("playArea/FrontBrake.png")));
        BackandBrake.setPosition(1000,340);
        BackandBrake.setSize(180,150);
        BackandBrake.setAlpha(0.4f);



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

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        //shape renderer
        sred.begin(ShapeRenderer.ShapeType.Filled);
        //speed controller for bicycle
        sred.rect(50, 150, 30,450,new Color(0,0,1,0.4f), new Color(0,0,1,0.4f), new Color(1,0,0,0.4f), new Color(1,0,0,0.4f));
        sred.setColor(0,0.2f,1,0.6f);
        sred.circle(65, 150,15);
        sred.setColor(0.8f,0.2f,0,0.6f);
        sred.circle(65, 600,15);

        //brakes for bicycle
        sred.setColor(0.8f,0.1f,0.1f,0.4f);
        sred.rect(1100, 500, 130, 130); // LauchPlayer
        sred.rect(1000, 340, 180, 150);
        sred.rect(1100, 200, 130, 130); // FrontBrake



        sred.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        AllVariables.batch.begin();
        speedSprite.draw(AllVariables.batch);
        PlayerLaunch.draw(AllVariables.batch);
        FrontBrake.draw(AllVariables.batch);
        BackandBrake.draw(AllVariables.batch);
        AllVariables.batch.end();



        if (Gdx.input.isKeyPressed(Input.Keys.B))
            game.setScreen(new temp(game));



    }


    private void update(float dt){
        input(dt);
        //if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        world.step(1/(1/dt), 6,2);




        Vector3 campos = cam.position;
        campos.x = AllVariables.BackWheel.getPosition().x*AllVariables.PPM;
        campos.y = AllVariables.BackWheel.getPosition().y*AllVariables.PPM;
        cam.position.set(campos);
        cam.update();

        //positions of images
        speedSprite.setPosition(XofspeedSprite - speedSprite.getWidth()/2,YofspeedSprit - speedSprite.getHeight()/2);

        //if isSpeedSpriteinMotion is true then only the cycle will move

    }

    private void input(float dt){
        float velx =0, vely =0;

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            velx -=1;
        }if(Gdx.input.isKeyPressed(Input.Keys.D)){
            velx +=1;
        }if(Gdx.input.isKeyPressed(Input.Keys.S)){
            vely -=1;
        }if(Gdx.input.isKeyPressed(Input.Keys.W)){
            vely +=1;
        }
        mover.setLinearVelocity(velx, vely);

        if (Gdx.input.isKeyPressed(Input.Keys.Z)){
            AllVariables.BackWheel.setAngularVelocity(40);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.X)){
            AllVariables.BackWheel.setAngularVelocity(-40);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.M)){
            AllVariables.FrontWheel.setAngularVelocity(0);
        }

        if (Gdx.input.isTouched()){
            AllVariables.BackWheel.setAngularVelocity(-40);
        }
        Gdx.input.setInputProcessor(
                new InputProcessor() {
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
                    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                        screenY = AllVariables.HEIGHT - screenY;
                        //inputs for cycle speedometer
                        if (screenX < 200 && screenY > 30 && screenY < 300){
                            isspeedSpritePressed = true;
                            speedSprite.setSize(70, 70);
                            speedSprite.setAlpha(0.4f);
                        }
                        System.out.println(screenY);

                        if (screenX > 1080 && screenX < 1250){
                            //front Brake
                            if (screenY > 200 && screenY < 330){
                                FrontBrake.setAlpha(0.9f);
                                isFrontBrakePressed = true;
                            }

                            //Launch Player
                            if (screenY > 500 && screenY < 630){
                                PlayerLaunch.setAlpha(0.9f);
                                isPlayerLaunchPressed = true;
                            }
                        }

                        //Back
                        if (screenX > 980 && screenX < 1200 && screenY >340 && screenY <500){
                            BackandBrake.setAlpha(0.9f);
                            isBackandBrakePressed = true;
                        }




                            return false;
                    }

                    @Override
                    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                        //resizing things to their normal size
                        if (isspeedSpritePressed) {
                            speedSprite.setSize(80, 80);
                            YofspeedSprit = 150;
                            isspeedSpritePressed = false;
                            speedSprite.setAlpha(0.6f);
                        }
                        if (isPlayerLaunchPressed){
                            PlayerLaunch.setAlpha(0.4f);
                            isPlayerLaunchPressed = false;
                        }
                        if (isBackandBrakePressed){
                            BackandBrake.setAlpha(0.4f);
                            isBackandBrakePressed = false;
                        }
                        if (isFrontBrakePressed){
                            FrontBrake.setAlpha(0.4f);
                            isFrontBrakePressed = false;
                        }
                        return false;
                    }

                    @Override
                    public boolean touchDragged(int screenX, int screenY, int pointer) {
                        screenY = AllVariables.HEIGHT - screenY;

                        if (isspeedSpritePressed){
                            if (screenY < 150)
                                speedSprite.setPosition(speedSprite.getX(), 150);
                            else if (screenY > 600)
                                speedSprite.setPosition(speedSprite.getX(), 600);
                            else
                                YofspeedSprit = screenY;
                        }
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
        game.dispose();
        world.dispose();
        b2dr.dispose();
        sred.dispose();
    }
}
