package com.ninja.game.Calculate;

import com.ninja.game.Sprite.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ather on 15/12/2559.
 * ช้ในการตรวจจับวัตถุในรหัสมีการตรวจสอบ
 */
public class CollisionLayer {
    Character player;
    Character other;
    List<Character> characterList;
    public CollisionLayer(){
        player = new Character();
        other = new Character();
        characterList = new ArrayList<Character>();
    }

    public CollisionLayer(Character player, Character other){
        this.player = player;
        this.other = other;
    }

    public CollisionLayer(Character player, List<Character> others){
        this.player = player;
        this.characterList = others;
    }

    public CollisionLayer(double ax, double ay, double bx, double by) {
        setPlayer(ax, ay);
        setOther(bx, by);
    }

    public void setPlayer(double ax, double ay){
        this.player = new Character();
        this.player.setPos(ax, ay);
    }

    public void setOther(double bx, double by){
        this.other = new Character();
        this.other.setPos(bx, by);
    }

    public double point2angle(){
        return Math.toDegrees(Math.atan2(other.getY()-player.getY(), other.getX()- player.getX()));
    }

    public boolean findNearest(double nearest){
        Distance distance;
        distance = new Distance(player.getX(), player.getY(), other.getX(), other.getY());
        double xs = distance.distanct();
        return (xs < nearest);
    }

    public boolean circleNearest(double radian){
        Distance distance;
        distance = new Distance(player.getX(), player.getY(), other.getX(), other.getY());
        double pp = point2angle();
        if (pp < -120 && pp > 120){
            if(distance.distanct() >= radian){
                return true;
            }
        }
        return false;
    }
}
