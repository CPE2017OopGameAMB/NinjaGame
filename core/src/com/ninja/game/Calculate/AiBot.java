package com.ninja.game.Calculate;

import com.badlogic.gdx.math.Vector2;
import com.ninja.game.Interfaces.State;
import com.ninja.game.Sprite.Character;

import java.math.BigDecimal;

/**
 * Created by ather on 15/12/2559.
 */
public class AiBot extends Character{
    Character me;
    Character target;

    Vector2 vMe;
    Vector2 vTarget;

    EAiBehavior behavior = EAiBehavior.NORMAL;
    private final float range = 100f;
    State.STATE normal[] = {State.STATE.IDLE, State.STATE.WALK};
    STATE returnState;

    public AiBot(Character me){
        this.me = me;
    }

    public void setTarget(Character target, boolean isTarget){
        if(isTarget)
        {
            this.target = target;
            vTarget = new Vector2(BigDecimal.valueOf(target.getX()).floatValue(), BigDecimal.valueOf(target.getY()).floatValue());
        }
        else
        {
            this.me = target;
            vMe = new Vector2(BigDecimal.valueOf(me.getX()).floatValue(), BigDecimal.valueOf(me.getY()).floatValue());
        }
    }

    public double chk_distant(){
        return vMe.dst2(vTarget);
    }

    public double chk_angle(){
        double x = vTarget.y - vMe.y;
        double y = vTarget.x - vMe.x;
        System.out.println(x +" : "+y);
        return Math.toDegrees(Math.atan2(x, y));
    }

    public void emotion(){
//       if (chk_angle() > 70 && chk_angle() < 120){
//           behavior = EAiBehavior.SCARE;
//       }else if (chk_distant() < 40){
//           behavior = EAiBehavior.ANGRY;
//       }else {
//           behavior = EAiBehavior.STUPID;
//       }

    }

    public void runBehavior(){
        switch (behavior){
//            case NORMAL:
//                walk_random();
//                break;
            case ANGRY:
                walk_to_target();
                break;
            case HIT:
                hit_target();
                break;
        }
    }

    public void hit_target()
    {
        if(target.getHealth() <= 0)
        {
            return;
        }
        else if(target.getY() > 100)
        {
            returnState = STATE.WALK;
        }
        else
        {
            System.out.println(target.getHealth());
            target.setHealth(target.getHealth()-.1);
            returnState = STATE.ATTACK;
        }

    }

    public void walk_to_target()
    {
        if(me.getX() < target.getX()){
            me.setX(me.getX()+0.5);
            me.setDir(DIR.R);
        }

        else{
            me.setX(me.getX()-0.5);
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


    public STATE update(Character target, boolean isTarget){
        setTarget(target, isTarget);
        //System.out.println("RunUpdate Aibot");
        updateBehavior();
        runBehavior();
//        emotion();
        return returnState;
    }

    private void updateBehavior()
    {
        if(me != null && target !=null)
        {
            if(me.getDir() == DIR.L && me.getX() - target.getX() <= range-50 && me.getX() - target.getX() >= 0)
            {
                behavior = EAiBehavior.HIT;
            }
            else if(me.getDir() == DIR.R && target.getX() - me.getX() <= range-50 && target.getX() - me.getX() >= 0)
            {
                behavior = EAiBehavior.HIT;
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

    public Character getChar()
    {
        return me;
    }
}
