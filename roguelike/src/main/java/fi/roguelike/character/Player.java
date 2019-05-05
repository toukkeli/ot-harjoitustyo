/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.character;

import fi.roguelike.game.Game;
import fi.roguelike.map.Tile;

/**
 *
 * @author toukk
 */
public class Player extends Character {

    private Game game;

    public Player(Game game, String name) {
        super(name);
        this.game = game;
        this.setHp(10);
        this.setDamage(1);
        this.setAccuracy(90);
    }

    private Game getGame() {
        return this.game;
    }

    @Override
    public String activate() {
        return "";
    }

    public String move(int mx, int my) {
        int newX = getX() + mx;
        int newY = getY() + my;
        Character other = this.getMap().getCharacter(newX, newY);
        if (other != null) {
            return attack(other);
        } else if (this.getMap().getTile(newX, newY) == Tile.WALL) {
            return "You could not move there.";
        } else {
            setX(newX);
            setY(newY);
            return "You moved";
        }
    }

    @Override
    public String attack(Character other) {
        String result;
        result = this.getName() + " hits " + other.getName() + " for " + this.getDamage() + " damage.1";
        other.reduceHp(getDamage());
        if (other.isDead()) {
            result = result + " " + other.getName() + " dies.";
            other.die();
        }
        return result;
    }

    public String moveDown() {
        if (getMap().getTile(getX(), getY()) == Tile.STAIRS) {
            this.getMap().getGame().newFloor();
            return this.getName() + " descended a floor.";

        } else {
            return "There is nothing to go down to.";
        }
    }

    @Override
    public void die() {
        this.getGame().gameOver();
    }

    public String getStats() {
        String stats = ""
                + "\n HP: " + this.getHp()
                + "\n Damage: " + this.getDamage()
                + "\n Level: " + this.getGame().getFloor()
                + "\n";
        return stats;
    }

}
