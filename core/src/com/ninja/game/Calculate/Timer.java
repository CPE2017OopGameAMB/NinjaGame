package com.ninja.game.Calculate;

import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by ather on 15/12/2559.
 */
public class Timer
{

    public long start;
    public long secsToWait;

    public Timer(long secsToWait)
    {
        this.secsToWait = start+secsToWait;
    }

    public void start()
    {
        start = TimeUtils.millis() / 100;
    }


    public boolean hasCompleted()
    {
        return TimeUtils.millis() / 100 - start >= secsToWait;
    }

    public boolean Cooldown(){
        start();
        return hasCompleted();
    }
}