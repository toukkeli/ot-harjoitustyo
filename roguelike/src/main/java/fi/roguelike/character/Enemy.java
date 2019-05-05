/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.character;

import java.util.List;
import fi.roguelike.character.behavior.Behavior;
import fi.roguelike.map.Map;
import fi.roguelike.map.Tile;

/**
 *
 * @author toukk
 */
public class Enemy extends Character {

    private Behavior ai;

    public Enemy(String name) {
        super(name);
        this.ai = null;
    }

    public Behavior getBehavior() {
        return this.ai;
    }

    public void setBehavior(Behavior ai) {
        this.ai = ai;
    }

    @Override
    public String activate() {
        return this.getBehavior().onActivate();
    }

    @Override
    public String attack(Character other) {
        return this.getBehavior().attack(other);
    }

    @Override
    public void die() {
        this.getMap().removeCharacter(this);
    }

}
