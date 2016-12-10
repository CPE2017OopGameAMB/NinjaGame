package com.ninja.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

public class GameMain extends ApplicationAdapter {
	private SceneLoader sceneLoader;
	
	@Override
	public void create () {
		Viewport viewport = new FitViewport(12.8f, 7.5f);
		sceneLoader = new SceneLoader();
		sceneLoader.loadScene("Lake", viewport);

		Player player = new Player();

		ItemWrapper root = new ItemWrapper(sceneLoader.getRoot());
		root.getChild("player").addScript(player);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());
	}
}
