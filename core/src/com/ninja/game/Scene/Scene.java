package com.ninja.game.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ninja.game.Interfaces.Element;
import com.ninja.game.Sprite.SEnemy;
import com.ninja.game.Sprite.Sprite;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;

/**
 * Created by Aunpyz on 12/15/2016.
 */
public class Scene extends Sprite implements Element{
    private ELEMENT element;
    private com.badlogic.gdx.graphics.g2d.Sprite water;
    private com.badlogic.gdx.graphics.g2d.Sprite fire[];
    private com.badlogic.gdx.graphics.g2d.Sprite wood[];
    private boolean isEndStage =false;

    ArrayList<com.badlogic.gdx.graphics.g2d.Sprite> enemyList = new ArrayList<com.badlogic.gdx.graphics.g2d.Sprite>();

    public Scene(Skin skin) {
        super(skin);
    }

    @Override
    public ELEMENT getElement() {
        switch (element)
        {
            case WATER:
                return ELEMENT.WOOD;
            case WOOD:
                return ELEMENT.FIRE;
            case FIRE:
                return ELEMENT.WATER;
        }
        return null;
    }

    @Override
    public void setElement() {
        element = getElement();
    }

    @Override
    public String getStringElement() {
        return null;
    }

    @Override
    public void init() {

        water = new com.badlogic.gdx.graphics.g2d.Sprite();
        wood = new com.badlogic.gdx.graphics.g2d.Sprite[2];
        fire = new com.badlogic.gdx.graphics.g2d.Sprite[2];
        water = resource.getSprite("lake");
        for(int i = 0; i < 2; i++)
        {
            wood[i] = resource.getSprite("forest");
            fire[i] = resource.getSprite("fire");
        }
        element = ELEMENT.WATER;
        position = new Vector2(0, 0);
    }

    @Override
    public void update(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.M) || isEndStage)
            setElement();
            isEndStage = false;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        switch (element)
        {
            case WATER:
                batch.draw(water, position.x, position.y);
                break;
            case WOOD:
                batch.draw(wood[0], position.x, position.y);
                batch.draw(wood[1], position.x+wood[1].getWidth(), position.y);
                break;
            case FIRE:
                batch.draw(fire[0], position.x, position.y);
                batch.draw(fire[1], position.x+fire[1].getWidth(), position.y);
                break;
        }
        batch.end();
    }

    @Override
    public void dispose() {

    }

    public void setPosition(Vector2 position)
    {
        if(position.x < 800)
            this.position.x = (position.x < 0? 0:-position.x);
        this.position.y = 0;
    }

    public void isEndStage(boolean t){

    }
}
