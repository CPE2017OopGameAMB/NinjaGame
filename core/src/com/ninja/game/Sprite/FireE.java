package com.ninja.game.Sprite;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Aunpyz on 12/15/2016.
 */
public class FireE extends SEnemy {
    public FireE(Skin skin) {
        super(skin);
    }

    @Override
    public void init()
    {
        idle = new TextureRegion[2][31];
        die = new TextureRegion[2][41];
        attack = new TextureRegion[2][31];
        walk = new TextureRegion[2][31];

        for(int i=0; i<2; i++)
        {
            for(int j=0; j<31; j++)
            {
                idle[i][j] = resource.getRegion((i==0?"fbi":"ffi")+String.format("%02d", j+1));
            }
        }
        for(int i=0; i<2; i++)
        {
            for(int j=0; j<41; j++)
            {
                die[i][j] = resource.getRegion((i==0?"fbd":"ffd")+String.format("%02d", j+1));
            }
        }
        for(int i=0; i<2; i++)
        {
            for(int j=0; j<31; j++)
            {
                attack[i][j] = resource.getRegion((i==0?"fba":"ffa")+String.format("%02d", j+1));
            }
        }
        for(int i=0; i<2; i++)
        {
            for(int j=0; j<31; j++)
            {
                walk[i][j] = resource.getRegion((i==0?"fbw":"ffw")+String.format("%02d", j+1));
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
            for(int j= 0; i<31; j++)
            {
                idle[i][j].getTexture().dispose();
                attack[i][j].getTexture().dispose();
                walk[i][j].getTexture().dispose();
            }
        }
        for(int i = 0; i<2; i++)
        {
            for(int j= 0; i<41; j++)
            {
                die[i][j].getTexture().dispose();
            }
        }
    }

}
