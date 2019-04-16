/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.character;

import fi.roguelike.map.Map;
import fi.roguelike.map.Tile;

/**
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

    public Character(String name, Map map, int x, int y) {
        this.name = name;
        this.map = map;
        this.x = x;
        this.y = y;
        this.hp = 1;
        this.damage = 1;
    }

    public String getname() {
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

    public void reduceHp(int change) {
        this.hp = this.hp - change;
    }

    public void addHp(int change) {
        this.hp = this.hp + change;
    }

    public boolean isDead() {
        if (this.hp <= 0) {
            return true;
        }
        return false;
    }

    abstract public String activate();

    abstract public String attack(Character other);

    abstract public void die();

    public int getDamage() {
        return this.damage;
    }

    public void setDamage() {
        this.damage = damage;
    }

}
