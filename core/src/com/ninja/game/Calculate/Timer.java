package com.ninja.game.Calculate;

import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by ather on 15/12/2559.
 */
public class Timer
{

    private long start;
    private long secsToWait;

    public Timer(long secsToWait)
    {
        this.secsToWait = start+secsToWait;
    }

    public void start()
    {
        start = TimeUtils.millis() / 1000;
    }


    public boolean hasCompleted()
    {
        return TimeUtils.millis() / 1000 - start >= secsToWait;
    }

    public boolean Cooldown(){
        start();
        return hasCompleted();
    }
}