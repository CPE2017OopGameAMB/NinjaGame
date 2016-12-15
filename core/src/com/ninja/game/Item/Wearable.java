package com.ninja.game.Item;

/**
 * Created by ather on 14/12/2559.
 */
public class Wearable extends Item implements IWearable{
    protected double atk;
    protected double def;
    protected double intel;

    public Wearable(){
        this.atk = 0;
        this.def = 0;
        this.intel = 0;
    }

    public Wearable(double atk, double def, double intel) {
        this.atk = atk;
        this.def = def;
        this.intel = intel;
    }

    public Wearable(String name, EItem prop, String url, double atk, double def, double intel) {
        super(name, prop, url);
        this.atk = atk;
        this.def = def;
        this.intel = intel;
    }
    @Override
    public double getAtk() {
        return atk;
    }

    @Override
    public double getDef() {
        return def;
    }

    @Override
    public double getIntel() {
        return intel;
    }

    @Override
    public double getDefCal() {
        return 0.3*def+1;
    }

    public double getAtkCal(){
        return 1.2*atk+2;
    }
}
