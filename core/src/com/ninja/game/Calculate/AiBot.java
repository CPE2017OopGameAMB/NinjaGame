package com.ninja.game.Calculate;

import com.badlogic.gdx.math.Vector2;
import com.ninja.game.Config.Config;
import com.ninja.game.Interfaces.State;
import com.ninja.game.Sprite.Character;

import java.math.BigDecimal;

/**
 * Created by ather on 15/12/2559.
 */
public class AiBot extends Character {
    Character me;
    Character target;

    Vector2 vMe;
    Vector2 vTarget;

    EAiBehavior behavior = EAiBehavior.NORMAL;
    private final float range = 100f;
    State.STATE normal[] = {State.STATE.IDLE, State.STATE.WALK};
    STATE returnState;

    Timer time = new Timer(Config.COOLDOWN_MONSTER);
    boolean isFirst = true;

    public AiBot(Character me) {
        this.me = me;
    }

    public void setTarget(Character target, boolean isTarget) {
        if (isTarget) {
            this.target = target;
            vTarget = new Vector2(BigDecimal.valueOf(target.getX()).floatValue(), BigDecimal.valueOf(target.getY()).floatValue());
        } else {
            this.me = target;
            vMe = new Vector2(BigDecimal.valueOf(me.getX()).floatValue(), BigDecimal.valueOf(me.getY()).floatValue());
        }
    }

    public double chk_distant() {
        return vMe.dst2(vTarget);
    }


    public void runBehavior() {
        switch (behavior) {
//            case NORMAL:
//                walk_random();
//                break;
            case ANGRY:
                walk_to_target();
                break;
            case HIT:
                hit_target();
                break;
            case STUPID:
                returnState = STATE.DIE;
                break;
        }
    }

    public void hit_target() {
        if (target.getHealth() <= 0) {
            return;
        } else if (target.getY() > 100) {
            returnState = STATE.WALK;
        } else {
            System.out.println(target.getHealth());
            //target.setHealth(target.getHealth() - .1);
            attack(target, 0.1);
            returnState = STATE.ATTACK;
        }

    }

    public void walk_to_target() {
        if (me.getX() < target.getX()) {
            me.setX(me.getX() + 0.5*Math.random()*0.6);
            me.setDir(DIR.R);
        } else {
            me.setX(me.getX() - 0.5*Math.random()*0.6);
            me.setDir(DIR.L);
        }
        returnState = STATE.WALK;
    }

//    public void walk_random(){
//        if(Math.random()*100 < 50){
//            me.setX(me.getX()+5);
//            me.setDir(DIR.R);
//            returnState = STATE.WALK;
//        }
//        else if(Math.random()*100 < 50)
//        {
//            me.setX(me.getX()-5);
//            me.setDir(DIR.L);
//            returnState = STATE.WALK;
//        }
//        else
//        {
//            returnState = STATE.IDLE;
//        }
//    }


    public STATE update(Character target, boolean isTarget) {
        setTarget(target, isTarget);
        //System.out.println("RunUpdate Aibot");
        updateBehavior();
        runBehavior();

//        emotion();
        return returnState;
    }

    private void updateBehavior() {
        if (me != null && target != null) {
            if(me.getState() == STATE.DIE)
            {
                behavior = EAiBehavior.STUPID;
            }
            else if (me.getDir() == DIR.L && me.getX() - target.getX() <= range - 50 && me.getX() - target.getX() >= 0) {
                //behavior = EAiBehavior.HIT;
               if (isFirst || time.hasCompleted()) {
                    behavior = EAiBehavior.HIT;
                    isFirst = false;
                    time.start();
                } else {
                    behavior = EAiBehavior.ANGRY;
                }
            } else if (me.getDir() == DIR.R && target.getX() - me.getX() <= range - 50 && target.getX() - me.getX() >= 0) {
                    //behavior = EAiBehavior.HIT;
                    if (isFirst || time.hasCompleted()) {
                    behavior = EAiBehavior.HIT;
                    isFirst = false;
                    time.start();
                    System.out.println(time.hasCompleted());
                } else {
                    behavior = EAiBehavior.ANGRY;
                }

            }
//            else if(me.getDir() == DIR.L && me.getX() - target.getX() <= range && me.getX() - target.getX() >= 0)
//            {
//                System.out.println("C");
//                behavior = EAiBehavior.ANGRY;
//            }
//            else if(me.getDir() == DIR.R && target.getX() - me.getX() <= range && target.getX() - me.getX() >= 0)
//            {
//                System.out.println("D");
//                behavior = EAiBehavior.ANGRY;
//            }
//            else behavior = EAiBehavior.NORMAL;
            else
                behavior = EAiBehavior.ANGRY;
        }
    }

    public Character getChar() {
        return me;
    }
}
