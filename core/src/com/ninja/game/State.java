package com.ninja.game;

/**
 * Created by Aunpyz on 12/13/2016.
 */
public interface State {
    enum STATE{
        IDLE,
        WALK,
        ATTCK,
        JUNP,
        DIE
    }

    STATE getState();
}
