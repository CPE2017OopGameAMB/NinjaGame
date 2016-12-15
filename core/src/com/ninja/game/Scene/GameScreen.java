package com.ninja.game.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ninja.game.Sprite.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aunpyz on 12/14/2016.
 */
public class GameScreen extends ScreenAdapter {
    enum GAMESTATE{MENU, PLAY, OVER};
    //background
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private AssetManager assetManager;
    private StageController stageController;
    private Skin resource;
    private PlayerAnimation player;
    private Scene scene;
    private long splash_time;
    private Texture mainmenu;
    private Texture loading;
    private GAMESTATE gamestate;

    private final float WORLD_WIDTH = 12.8f;
    private final float WORLD_HEIGHT = 7.5f;

    private boolean isLoaded;

    SEnemy monst;
    List<SEnemy> monsta = new ArrayList<SEnemy>();

    public GameScreen()
    {
        this.isLoaded = false;
        gamestate = GAMESTATE.MENU;
    }

    @Override
    public void show () {
        batch = new SpriteBatch();
        stageController = new StageController();
        assetManager = new AssetManager();
        assetManager.load("packed/animation.atlas", TextureAtlas.class);
        assetManager.load("packed/scene.atlas",TextureAtlas.class);
        mainmenu = new Texture(Gdx.files.internal("scene/main.png"));
        loading = new Texture(Gdx.files.internal("scene/loading.png"));

        //camera and viewport initialize
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(WORLD_WIDTH/2, WORLD_HEIGHT/2, 0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT,camera);

        splash_time = TimeUtils.millis() + 1000 * 3;
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        update(delta);
        //wait until asset is loaded
        if (assetManager.update() && TimeUtils.millis() > splash_time && gamestate == GAMESTATE.PLAY) {
            if (!isLoaded)
                init();
            scene.update(delta);
            scene.setPosition(player.getPosition());
            scene.render(batch);
            monst.update(delta);
            monst.render(batch);

            for (int i=0; i<monsta.size(); i++){
                monsta.get(i).update(delta);
                monsta.get(i).render(batch);
            }






            player.update(delta);
            player.render(batch);
        }
        else if(gamestate == GAMESTATE.MENU)
        {
            batch.draw(mainmenu, 0, 0, 1024, 600);
        }
        else if(gamestate == GAMESTATE.PLAY)
        {
            batch.draw(loading, 0, 0, 1024, 600);
        }
        batch.end();
    }

    private void init()
    {
        isLoaded = true;
        resource = new Skin();
        resource.addRegions(assetManager.get("packed/scene.atlas", TextureAtlas.class));
        resource.addRegions(assetManager.get("packed/animation.atlas", TextureAtlas.class));
        scene = new Scene(resource);
        player = new PlayerAnimation(resource);

        monst = new WoodE(resource);
        monst.setTarget(player.getMe());

        int maxGen = 5;


        for (int i=0; i<=maxGen; i++){
            int ff = 1 + (int)(Math.random() * ((3 - 1) + 1));
            System.out.println(ff);
            monsta.add(classReturn(ff));
            monsta.get(i).setTarget(player.getMe());
        }

    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        resource.dispose();
        scene.dispose();
        player.dispose();
        monst.dispose();
        mainmenu.dispose();
        loading.dispose();
    }

    public void update(float delta)
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            gamestate = GAMESTATE.PLAY;
    }

    private SEnemy classReturn(int num){
        switch (num){
            case 1: return new FireE(resource);
            case 2: return new WaterE(resource);
            case 3: return new WoodE(resource);
            default:return new FireE(resource);
        }
    }
}
