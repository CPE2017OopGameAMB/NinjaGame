package com.ninja.game.Sprite;

import com.ninja.game.Calculate.CollisionLayer;
import com.ninja.game.Calculate.Timer;
import com.ninja.game.Config.Config;
import com.ninja.game.Interfaces.State;
import com.ninja.game.Item.Wearable;
import com.ninja.game.State.EElements;
import com.ninja.game.State.EState;
import com.ninja.game.State.EType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ather on 19/11/2559.
 */
public class Character implements ICharater, State {

    //interface prop
    String name;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    int level=1;
    int exp =0;


    // Class Prop
    protected EType type = EType.HERO;
    protected EElements element = EElements.NORMAL;
    protected boolean enermy = false;
    protected DIR dir;

    //Life of charector
    protected double health = 1;
    protected double percenHP = 100;
    protected double maxHealth = 1;
    protected double mana = 1;
    protected double maxStack = 4;
    protected double currentStack = 0;
    protected EState state = EState.IDLE;

    //Item Status
    List<Wearable> wearableList = new ArrayList<Wearable>();
    protected double sumItemAtk;
    protected double sumItemDef=0;


    //Arrary of Enemy
    protected List<SEnemy> enemyList = new ArrayList<SEnemy>();


    //Status
    protected double atk = 0;
    protected double def = 0;
    protected double intel = 0;
    int k=0;

    //Position
    protected double x = 0;
    protected double y = 0;
    protected DIR dr = DIR.L;

    //Velocity
    protected double velocityX = 0;
    protected double velocityY = 0;

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public void velocity(double x, double y){
        setVelocityX(x);
        setVelocityY(y);
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }
    /*------------- Coder ----------------*/

    public Character() {
        create_character(10, 10, 10, 1);
        create_status(1, 0, 0);
    }

    public void create_character(double hp, double maxHp,  double mp, double maxStack) {
        setHealth(hp);
        setMaxHealth(maxHp);
        setMana(mp);
        Health2Percent();

    }
    public void create_character(double hp, double maxHp, double mp, double maxStack, EType type) {
        create_character(hp, maxHp, mp, maxStack);
        setType(type);
    }

    public void create_status(double atk, double def, double intel){
        setAtk(atk);
        setIntel(intel);
        setDef(def);
    }

    public void addItem(Wearable item){
        wearableList.add(item);
    }

    public void giveExp(int exp){
        this.exp += exp;
        if(this.exp > level*30){
            this.level++;
            this.atk += this.atk*1.11111;
            //this.def += this.def*1.2;
            //this.setMaxHealth(this.maxHealth*1.3);
            //this.setHealth(this.getHealth()+50);
            this.exp = this.exp - (level*30);
            healthUpdate();
        }
    }

    private void itemCalculateDef(){
        sumItemDef = 0;
        for (Wearable itemDef : wearableList){
            sumItemDef += itemDef.getDefCal();
        }
    }
    private void itemCalculateAtk(){
        sumItemAtk = 0;
        for (Wearable itemAtk : wearableList){
            sumItemAtk += itemAtk.getAtkCal();
        }
    }

    public double getResultDef(){
        // Calculate Every Called
        this.itemCalculateAtk();
        this.itemCalculateDef();
        //System.out.println("Item.Def: "+this.sumItemDef+ " +Char.Def "+this.def+ " = "+(double)(this.sumItemDef+this.def));
        return this.sumItemDef+this.def;
    }

    public double getResultAtk(){
        // Calculate Every Called
        this.itemCalculateAtk();
        this.itemCalculateDef();
        //System.out.println("Item.Def: "+this.sumItemDef+ " +Char.Def "+this.def+ " = "+(double)(this.sumItemDef+this.def));
        return this.sumItemAtk+this.atk;
    }

    private void Health2Percent(){
        this.maxHealth = (this.maxHealth > 0) ? this.maxHealth:1;
        this.percenHP = (this.health / this.maxHealth) *100;
        if(this.percenHP < 0){
            this.percenHP = 0;
        }
        if(this.percenHP > 100){
            this.percenHP = 100;
        }
    }

    public void percenHP2RawHP(){
        this.health = (percenHP/100)*maxHealth;
        if(this.health < 0){
            this.health = 0;
        }
    }

    public double def2PercentDamage(double dmg, double multi){
        double defChk = this.getResultDef();
        if(defChk <= 0 )defChk = 1;
        return (((this.atk+dmg)*multi)/defChk)*Math.abs(Math.random())*10;
    }


    private void healthUpdate(){
        this.percenHP2RawHP();
        this.Health2Percent();
    }

    Timer time = new Timer(Config.COOLDOWN_HERO);
    boolean isFirst = true;


    public void scaningEnemy(List<SEnemy> enemyL){

        CollisionLayer col = new CollisionLayer();
        col.setPlayer(this.getX(), this.getY());
        ArrayList<Integer> em = new ArrayList<Integer>();
        System.out.println("HP:["+health+"]Max["+maxHealth+"]ATK["+atk+"]");
        for (SEnemy s : enemyL){
            col.setOther(s.self.getX(), s.self.getY());
            if(s.self.getPercenHP()<=0){
                System.out.println("Delete Enemy"+enemyL.indexOf(s));
                //System.out.println(s.self.def);
                //s.state = STATE.DIE;
                //s.update(0);
                em.add(enemyL.indexOf(s));
                this.setPercenHP(this.percenHP+20);
                this.giveExp(50+(2*level));
                this.healthUpdate();
                System.out.println("Level: ["+this.level+"] ExP : ["+this.exp+"]");
                s.self.setY(9999);
            }
            if (this.dir != s.self.dir && col.findNearest(90)){
                //when enemry nearest player make damage to enermy
                if (isFirst ||time.hasCompleted()){
                    this.attack(s.self, atk);
                    //System.out.println("gg");
                    isFirst = false;
                    time.start();
                    k=0;
                }else if(!time.hasCompleted()){
                    k++;

                }

            }
        }
        //System.out.println("SIZEEEEEEEEE : "+enemyL.size());
        for (Integer d : em){
            enemyL.remove(d.intValue());
        }
        em.clear();
        //System.out.println("55555555555555555555555 : "+enemyL.size());

    }


    @Override
    public void attack(Character character, double dmg) {
        healthUpdate();
        ElementSystem dmgMultiply = new ElementSystem(this.getElement(), character.getElement());
        double multi = dmgMultiply.getDamge();

        //System.out.println("["+this.getClass().toString()+"] "+character.getHealth() + " perc2dmg: "+def2PercentDamage(dmg,multi ));
        character.attacked(dmg, multi);
    }

    public void attacked(double dmg, double multi) {
        healthUpdate();
        //System.out.println(getPercenHP() + " perc2dmg: "+def2PercentDamage(dmg, multi));
        setPercenHP((getPercenHP() - def2PercentDamage(dmg, multi)));
        System.out.println("["+this.getName()+"] was attacked with ["+dmg+"]+["+multi+"]");
        healthUpdate();
    }

    @Override
    public void heal(double hpStack) {

    }

    @Override
    public double getHealth() {
        return health;
    }


    @Override
    public double getMana() {
        return mana;
    }

    @Override
    public double getStack() {
        return currentStack;
    }

    @Override
    public double getDamage() {
        return 0;
    }

    // Geter Setter

    public boolean isDead() {
        return this.health <= 0;
    }

    public double getAtk() {
        return atk;
    }

    public void setAtk(double atk) {
        this.atk = atk;
    }

    private double getDef() {
        return def;
    }

    private void setDef(double def) {
        this.def = def;
    }

    public double getIntel() {
        return intel;
    }

    public void setIntel(double intel) {
        this.intel = intel;
    }

    public void setHealth(double hp) {
        this.health = (hp > 0) ? hp : 0;
    }

    public void setType(EType type) {
        this.type = type;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public void setMaxStack(){
        this.maxStack = maxStack;
    }

    public double getDefValue(){
        return this.getDef();
    }

    public void setMaxHealth(double maxHp){
        this.maxHealth = maxHp;
    }

    public double getPercenHP() {
        return percenHP;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setPos(double x, double y){
        this.setX(x);
        this.setY(y);
    }

    public void setY(double y) {
        this.y = y;
    }

    public EElements getElement() {
        return element;
    }

    public void setElement(EElements element) {
        this.element = element;
    }

    public void setPercenHP(double percenHP) {
        this.percenHP = percenHP;
    }

    public boolean isEnermy() {
        return enermy;
    }

    public void setEnermy(boolean enermy) {
        this.enermy = enermy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public STATE getState() {
        return null;
    }

    @Override
    public void setState() {
    }

    public void setDir(State.DIR dir)
    {
        this.dir = dir;
    }

    @Override
    public DIR getDir() {
        return dir;
    }
}
