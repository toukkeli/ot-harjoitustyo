/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.character;

import fi.roguelike.map.Map;

/**
 * Abstract class that represents any entity that can walk around the dungeon
 * Currently has 2 implementations: Player and Enemy, with a potential
 * implementation for npc
 *
 *
 * @author toukk
 */
public abstract class Character {

    private String name;
    private Map map;
    private int x;
    private int y;
    private int hp;
    private int damage;
    private int accuracy;

    public Character(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map getMap() {
        return this.map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(int a) {
        this.accuracy = a;
    }

    /**
     * The character takes damage
     *
     * @param change How much damage was taken
     */
    public void reduceHp(int change) {
        this.hp = this.hp - change;
    }

    /**
     * The character heals hp
     *
     * @param change How much hp was healed
     */
    public void addHp(int change) {
        this.hp = this.hp + change;
    }

    /**
     * @return True if the characters hp <= 0
     */
    public boolean isDead() {
        if (this.hp <= 0) {
            return true;
        }
        return false;
    }

    abstract public String activate();

    abstract public String attack(Character other);

    abstract public void die();

}
