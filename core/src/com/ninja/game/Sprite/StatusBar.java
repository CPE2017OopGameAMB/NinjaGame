package com.ninja.game.Sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by ather on 19/11/2559.
 */
public class StatusBar {
    ShapeRenderer shapeRenderer;
    BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/arial.fnt"));
    GlyphLayout layout;
    float scale = 0.3f;

    public StatusBar(){
        shapeRenderer = new ShapeRenderer();
        layout = new GlyphLayout();
    }

    void show(float x, float y, float hp, float mp, String text, SpriteBatch batch){
        float maxHP = (hp)*1.5f-2;
        float maxMP = (mp)*1.5f-2;

        layout.setText(font, text);
        font.setColor(Color.WHITE);
        font.draw(batch, text, x+layout.width/2, y+30);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.identity();
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(x-4f, y-4f, 154, 18);
        shapeRenderer.setColor(1, 1, 1, 0.7f);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(x-2f, y-2f, 152, 15);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(x, y, maxMP,5);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x,y+5, maxHP ,5);
        shapeRenderer.end();
    }

}
