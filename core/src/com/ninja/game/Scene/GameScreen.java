package com.ninja.game.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ninja.game.Scene.StageController;
import com.ninja.game.Sprite.FireE;
import com.ninja.game.Sprite.PlayerAnimation;
import com.ninja.game.Sprite.SEnemy;
import com.ninja.game.Sprite.WoodE;
import com.uwsoft.editor.renderer.SceneLoader;

/**
 * Created by Aunpyz on 12/14/2016.
 */
public class GameScreen extends ScreenAdapter {
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

    private final float WORLD_WIDTH = 12.8f;
    private final float WORLD_HEIGHT = 7.5f;

    private boolean isLoaded;

    SEnemy monst;

    public GameScreen()
    {
        this.isLoaded = false;
    }

    @Override
    public void show () {
        batch = new SpriteBatch();
        stageController = new StageController();
        assetManager = new AssetManager();
        assetManager.load("packed/animation.atlas", TextureAtlas.class);
        assetManager.load("packed/scene.atlas",TextureAtlas.class);

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
        //wait until asset is loaded
        if (assetManager.update() && TimeUtils.millis() > splash_time) {
            if (!isLoaded)
                init();
            scene.update(delta);
            scene.setPosition(player.getPosition());
            scene.render(batch);
            monst.update(delta);
            monst.render(batch);
            player.update(delta);
            player.render(batch);
        }
        else
        {

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
    }
}
