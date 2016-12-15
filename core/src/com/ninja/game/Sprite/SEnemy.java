package com.ninja.game.Sprite;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ninja.game.Calculate.AiBot;
import com.ninja.game.Interfaces.Element;
import com.ninja.game.Interfaces.State;

import java.util.Random;

/**
 * Created by Aunpyz on 12/15/2016.
 */
public class SEnemy extends Sprite implements Element, State{
    protected ELEMENT element;
    protected DIR direction;
    protected Animation animation;
    protected Character self;
    protected Character target;
    protected AiBot ai;
    protected STATE state;
    protected STATE prevState;
    protected TextureRegion idle[][];
    protected TextureRegion die[][];
    protected TextureRegion attack[][];
    protected TextureRegion walk[][];
    protected float hp = 100;
    protected float delta;

    public SEnemy(Skin skin) {
        super(skin);
        delta = 0;
        self = new Enemy(hp, hp , 0, 0);
        direction = random();
        self.setDir(direction);
        self.setPos(Math.random()*800, (double) PlayerAnimation.groundLV );
        position.x = (float)self.getX();
        position.y = (float)self.getY();
//        position = new Vector2((float)Math.random()*800, PlayerAnimation.groundLV);
        ai = new AiBot(self);
    }

    public static DIR random()
    {
        Random random = new Random();
        return  DIR.values()[random.nextInt(DIR.values().length)];
    }

    @Override
    public ELEMENT getElement() {
        return element;
    }

    @Override
    public void setElement() {
        element = null;
    }

    @Override
    public String getStringElement() {
        return element.toString().toUpperCase();
    }

    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {
        this.delta+=delta;
        setState();
        ai.update(target, true);
        self.setPos(ai.getChar().getX(), ai.getChar().getY());
        position.x = (float)self.getX();
        position.y = (float)self.getY();
    }

    @Override
    public void render(SpriteBatch batch)
    {
        batch.draw(animation.getKeyFrame(delta), (float)self.getX(), (float)self.getY());
    }

    @Override
    public void dispose() {

    }

    @Override
    public STATE getState() {
        return state;
    }

    @Override
    public void setState() {
        prevState = state;
        state = ai.update(self, false);
        setDir(ai.getChar().getDir());
        if(prevState == STATE.DIE)
            return;
        switch (state)
        {
            case ATTACK:
                animation = new Animation(PlayerAnimation.fps, getDir() == DIR.R ? attack[0] : attack[1]);
                animation.setPlayMode(Animation.PlayMode.LOOP);
                break;
            case IDLE:
                animation = new Animation(PlayerAnimation.fps, getDir() == DIR.R ? idle[0] : idle[1]);
                animation.setPlayMode(Animation.PlayMode.LOOP);
                break;
            case DIE:
                delta = 0;
                animation = new Animation(PlayerAnimation.fps, getDir() == DIR.R ? die[0] : die[1]);
                animation.setPlayMode(Animation.PlayMode.NORMAL);
                break;
            case WALK:
                animation = new Animation(PlayerAnimation.fps, getDir() == DIR.R ? walk[0] : walk[1]);
                animation.setPlayMode(Animation.PlayMode.LOOP);
                break;
        }
    }

    @Override
    public void setDir(DIR dir) {
        direction = dir;
    }

    @Override
    public DIR getDir() {
        return direction;
    }

    public void setTarget(Character character)
    {
        this.target = character;
        ai.update(target, true);
    }
}
