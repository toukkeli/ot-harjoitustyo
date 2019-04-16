/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.character.behavior;

import fi.roguelike.character.behavior.Behavior;
import fi.roguelike.character.Character;
import java.util.ArrayList;
import fi.roguelike.map.Tile;

/**
 *
 * @author toukk
 */
public class AggressiveBehavior extends Behavior {

    private Character target;

    public AggressiveBehavior(Character c, Character target) {
        super(c);
        this.target = target;
    }

    @Override
    public String onActivate() {
        ArrayList<Integer> direction = chooseDirection();
        int newX = this.getCharacter().getX() + direction.get(0);
        int newY = this.getCharacter().getY() + direction.get(1);
        Character other = this.getCharacter().getMap().getCharacter(newX, newY);
        if (other == target) {
            return attack(other);
        } else if (other != null) {
            return "";
        } else if (this.getCharacter().getMap().getTile(newX, newY) == Tile.WALL) {
            return getCharacter().getname() + "";
        } else {
            getCharacter().setX(newX);
            getCharacter().setY(newY);
            return getCharacter().getname() + " moved";
        }
    }

    @Override
    public String attack(Character other) {
        String result;
        result = getCharacter().getname() + " hits " + other.getname() + " for" + this.getCharacter().getDamage() + " damage.";
        other.reduceHp(this.getCharacter().getDamage());
        if (other.isDead()) {
            result = result + " " + other.getname() + " dies.";
            other.die();
        }
        return result;
    }

    private ArrayList<Integer> chooseDirection() {
        ArrayList<Integer> direction = new ArrayList<>();
        direction.add(0);
        direction.add(0);
        if (target != null) {
            if (getCharacter().getX() < target.getX()) {
                direction.set(0, 1);
            } else if (getCharacter().getX() > target.getX()) {
                direction.set(0, -1);
            }
            if (getCharacter().getY() < target.getY()) {
                direction.set(1, 1);
            } else if (getCharacter().getY() > target.getY()) {
                direction.set(1, -1);
            }
        }
        return direction;
    }
}
