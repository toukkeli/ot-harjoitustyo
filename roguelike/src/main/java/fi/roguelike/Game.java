/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike;

import fi.roguelike.map.Map;
import fi.roguelike.character.Player;
import fi.roguelike.character.Character;
import java.util.ArrayList;

/**
 *
 * @author toukk
 */
public class Game {

    private ArrayList<Map> floors;
    private Player player;
    private int floor;
    private boolean gameIsOver;

    public Game() {
        gameIsOver = false;
        floor = 0;
        floors = new ArrayList<>();
        player = new Player(this, "Default", null, 0, 0);
        Map newMap = new Map(this, 20, 20, 5);
        floors.add(newMap);
        floors.get(floor).addCharacterAtRandomPosition(player);
    }

    public Map getMap() {
        return this.floors.get(floor);
    }

    public void setMap(Map map, int floor) {
        this.floors.set(floor, map);
    }

    public int getFloor() {
        return this.floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String resolveAction(Action action) {
        String result;
        switch (action) {
            case GO_NORTH:
                result = getPlayer().move(0, -1);
                break;
            case GO_NORTHEAST:
                result = getPlayer().move(1, -1);
                break;
            case GO_EAST:
                result = getPlayer().move(1, 0);
                break;
            case GO_SOUTHEAST:
                result = getPlayer().move(1, 1);
                break;
            case GO_SOUTH:
                result = getPlayer().move(0, 1);
                break;
            case GO_SOUTHWEST:
                result = getPlayer().move(-1, 1);
                break;
            case GO_WEST:
                result = getPlayer().move(-1, 0);
                break;
            case GO_NORTHWEST:
                result = getPlayer().move(-1, -1);
                break;
            case GO_DOWN:
                result = getPlayer().moveDown();
                break;
            default:
                result = "You did nothing";
                break;
        }
        result = result + "\n" + update();
        return result;
    }

    public String update() {
        return this.getMap().update();
    }

    public void newFloor() {
        this.floor++;
        if (this.floor >= this.floors.size()) {
            Map newMap = new Map(this, 20, 20, 5);
            floors.add(newMap);
            floors.get(floor).addCharacterAtRandomPosition(player);
        }
    }

    public String gameOver() {
        this.gameIsOver = true;
        return "game over";
    }

    public boolean isOver() {
        return gameIsOver;
    }

}
