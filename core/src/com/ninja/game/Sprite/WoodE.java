package com.ninja.game.Sprite;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Aunpyz on 12/15/2016.
 */
public class WoodE extends SEnemy{
    public WoodE(Skin skin) {
        super(skin);
    }
    @Override
    public void init()
    {
        idle = new TextureRegion[2][30];
        die = new TextureRegion[2][30];
        attack = new TextureRegion[2][30];
        walk = new TextureRegion[2][30];

        for(int i=0; i<2; i++)
        {
            for(int j=0; j<30; j++)
            {
                idle[i][j] = resource.getRegion((i==0?"gfi":"gbi")+String.format("%02d", j+1));
            }
        }
        for(int i=0; i<2; i++)
        {
            for(int j=0; j<30; j++)
            {
                die[i][j] = resource.getRegion((i==0?"gfd":"gbd")+String.format("%02d", j+1));
            }
        }
        for(int i=0; i<2; i++)
        {
            for(int j=0; j<30; j++)
            {
                attack[i][j] = resource.getRegion((i==0?"gfa":"gba")+String.format("%02d", j+1));
            }
        }
        for(int i=0; i<2; i++)
        {
            for(int j=0; j<30; j++)
            {
                walk[i][j] = resource.getRegion((i==0?"gfw":"gbw")+String.format("%02d", j+1));
            }
        }
        animation = new Animation(fps, idle[0]);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void render(SpriteBatch batch)
    {
        batch.draw(animation.getKeyFrame(delta),position.x,position.y);
    }

    @Override
    public void update(float delta) {
        this.delta += delta;
    }

    @Override
    public void dispose()
    {
        for(int i = 0; i<2; i++)
        {
            for(int j= 0; i<30; j++)
            {
                idle[i][j].getTexture().dispose();
                die[i][j].getTexture().dispose();
                attack[i][j].getTexture().dispose();
                walk[i][j].getTexture().dispose();
            }
        }
    }
}
