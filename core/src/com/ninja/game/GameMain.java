package com.ninja.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.ninja.game.Scene.GameScreen;

public class GameMain extends Game {

	Music music;



	@Override
	public void create () {
		music = Gdx.audio.newMusic(Gdx.files.internal("audio/barge.ogg"));
		music.play();


		setScreen(new GameScreen());
	}
}