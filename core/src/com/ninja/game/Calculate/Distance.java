package com.ninja.game.Calculate;

import com.badlogic.gdx.math.Vector2;

import java.math.BigDecimal;

/**
 * Created by ather on 9/12/2559.
 * this function for calculate distance of position of 2 objects
 */
public class Distance {
    Vector2 va;
    Vector2 vb;


    public Distance(Vector2 va, Vector2 vb){
        this.va = va;
        this.vb = vb;
    }

    public Distance(double vax, double vay){
        setVa(vax,vay);
        setVb(0,0);
    }

    public Distance(double vax, double vay, double vbx, double vby){
        float ax = BigDecimal.valueOf(vax).floatValue();
        float ay = BigDecimal.valueOf(vay).floatValue();
        float bx = BigDecimal.valueOf(vbx).floatValue();
        float by = BigDecimal.valueOf(vby).floatValue();
        this.va = new Vector2(ax, ay);
        this.vb = new Vector2(bx, by);
    }

    public void setVa(double vax, double vay){
        float ax = BigDecimal.valueOf(vax).floatValue();
        float ay = BigDecimal.valueOf(vay).floatValue();
        this.va = new Vector2(ax, ay);
    }

    public void setVb(double vbx, double vby){
        float bx = BigDecimal.valueOf(vbx).floatValue();
        float by = BigDecimal.valueOf(vby).floatValue();
        this.vb = new Vector2(bx,by);
    }

    public Vector2 delta(){
        return va.sub(vb);
    }

    public Double distanct(){
        return (double) va.dst2(vb);
    }

    public Vector2 findCenterOfRect(double xa, double ya, double width, double height){
        double x, y;
        x = xa + (width/2);
        y = ya + (height/2);
        float d = BigDecimal.valueOf(x).floatValue();
        float f = BigDecimal.valueOf(y).floatValue();
        return new Vector2(d,f);
    }


}

