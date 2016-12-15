package com.ninja.game.Sprite;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Aunpyz on 12/15/2016.
 */
public class WaterE extends SEnemy {
    public WaterE(Skin skin) {
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
                idle[i][j] = resource.getRegion((i==0?"wfi":"wbi")+String.format("%02d", j+1));
            }
        }
        for(int i=0; i<2; i++)
        {
            for(int j=0; j<30; j++)
            {
                die[i][j] = resource.getRegion((i==0?"wfd":"wbd")+String.format("%02d", j+1));
            }
        }
        for(int i=0; i<2; i++)
        {
            for(int j=0; j<30; j++)
            {
                attack[i][j] = resource.getRegion((i==0?"wfa":"wba")+String.format("%02d", j+1));
            }
        }
        for(int i=0; i<2; i++)
        {
            for(int j=0; j<30; j++)
            {
                walk[i][j] = resource.getRegion((i==0?"wfw":"wbw")+String.format("%02d", j+1));
            }
        }
        animation = new Animation(PlayerAnimation.fps, idle[0]);
        animation.setPlayMode(Animation.PlayMode.LOOP);
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
