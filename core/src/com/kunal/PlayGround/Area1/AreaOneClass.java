package com.kunal.PlayGround.Area1;

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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;
import com.kunal.PlayGround.CuttingArea.CuttingAreaManager;
import com.kunal.PlayGround.ObjectCreation;
import com.kunal.PlayGround.VariablesForPlayArea;

public class AreaOneClass implements Screen {
    MainGame game;

    private World world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera cam;
    private Viewport port;

    private ShapeRenderer sred;

    private ObjectCreation objectCreation;

    private Sprite Brake, start;
    private Boolean brakeBool = false, startBool = false;

    //tiled map
    private TiledMap map;
    private OrthogonalTiledMapRenderer tmr;

    private float ver[];

    //for projection matrix
    Matrix4 m4;

    Polygon poly;

    public AreaOneClass(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH*1.4f, AllVariables.HEIGHT*1.4f, cam);

        world = new World(new Vector2(0,0f), false);

        b2dr = new Box2DDebugRenderer();

        sred = new ShapeRenderer();

        objectCreation = new ObjectCreation();

        objectCreation.CreateBicycle(world);
        objectCreation.CreateCutouts(world);

        poly = new Polygon();


        //tiled map
        map = new TmxMapLoader().load("playArea/tiledMap/area1/level1.tmx");
        tmr = new OrthogonalTiledMapRenderer(map);

        //cam.position.set(port.getWorldWidth()/2, port.getWorldHeight()/2,0);

        Brake = new Sprite(new Texture(Gdx.files.internal("playArea/BothBrake.png")));
        Brake.setPosition(1050,140);
        Brake.setSize(180,150);
        Brake.setAlpha(0.4f);

        start = new Sprite(new Texture(Gdx.files.internal("playArea/Start.png")));
        start.setPosition(50, 140);
        start.setSize(150, 150);
        start.setAlpha(0.8f);

        //for projection matrix for shape renderer
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(dt);

        b2dr.render(world, cam.combined.scl(AllVariables.PPM));
        //need to fix this

        sred.setProjectionMatrix(cam.combined.scl(1/50f));

        b2dr.setDrawJoints(false);

        // tmr.render();

        sred.begin(ShapeRenderer.ShapeType.Line);

        sred.setColor(1, 1f, 1, 1);
        for (int i = 0; i < VariablesForPlayArea.shapes.size(); i++) {
            ver = new float[(VariablesForPlayArea.shapes.get(i).size() * 2)];
            for (int j = 0, k = 0; j < VariablesForPlayArea.shapes.get(i).size(); j++) {
                ver[k] = VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(j)][0]/2;
                k++;
                ver[k] = VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(j)][1]/2;
                k++;
            }
            //sred.polygon(ver);
            poly = new Polygon(ver);
            //poly.rotate(VariablesForPlayArea.CutOutBodies.get(i).getAngle());
            //poly.setPosition(VariablesForPlayArea.CutOutBodies.get(i).getPosition().x,VariablesForPlayArea.CutOutBodies.get(i).getPosition().y);
            poly.setPosition(VariablesForPlayArea.CutOutBodies.get(i).getWorldCenter().x, VariablesForPlayArea.CutOutBodies.get(i).getWorldCenter().y);
            poly.setScale(0.4f,0.4f);
            poly.dirty();
            sred.polygon(poly.getTransformedVertices());

            ver = null;

        }


        sred.end();

        AllVariables.batch.begin();
        Brake.draw(AllVariables.batch);
        start.draw(AllVariables.batch);
        AllVariables.batch.end();



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
        tmr.setView(cam);

        if (startBool){
            if (brakeBool) {
                //AllVariables.BackWheel.setAngularVelocity(0);
                AllVariables.FrontWheel.setAngularVelocity(0);
            }else{
                if (AllVariables.BackWheel.getAngularVelocity() > -8)
                    AllVariables.BackWheel.setAngularVelocity(-10);
                else
                    AllVariables.BackWheel.setAngularVelocity(AllVariables.BackWheel.getAngularVelocity()-3);
            }
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
                        if (keycode == Input.Keys.P){
                            game.setScreen(new CuttingAreaManager(game));
                        }
                        if (keycode == Input.Keys.SPACE){
                            world.setGravity(new Vector2(0,-10));
                        }

                        if (keycode == Input.Keys.S){
                            Matrix4 m = new Matrix4();
                            m.set(sred.getProjectionMatrix());
                            System.out.println(sred.getProjectionMatrix().scl(0.01f));
                        }
                        if (keycode == Input.Keys.C){
                            System.out.println(cam.projection);
                        }


                        return false;
                    }

                    @Override
                    public boolean keyUp(int keycode) {
                        if (keycode == Input.Keys.SPACE){
                            world.setGravity(new Vector2(0,0));
                        }
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
        world.dispose();
        b2dr.dispose();
        sred.dispose();
    }
}
