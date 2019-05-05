/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.game;

import fi.roguelike.map.Map;
import fi.roguelike.character.Player;
import fi.roguelike.dao.Database;
import java.util.ArrayList;

/**
 * The roguelike game itself
 *
 * @author toukk
 */
public class Game {

    private ArrayList<Map> floors;
    private Player player;
    private int floor;
    private boolean gameIsOver;
    private Database enemyDatabase;

    public Game() {
        gameIsOver = false;
        floor = 0;
        floors = new ArrayList<>();
        player = new Player(this, "You");
        Map newMap = new Map(this, 20, 20, 3);
        floors.add(newMap);
        floors.get(floor).addCharacterAtRandomPosition(player);
    }

    public Game(String name, int width, int height, int enemies, boolean emptyRoom) {
        gameIsOver = false;
        floor = 0;
        floors = new ArrayList<>();
        player = new Player(this, name);
        Map newMap;
        if (emptyRoom) {
            newMap = new Map(this);
        } else {
            newMap = new Map(this, width, height, enemies);
        }
        floors.add(newMap);
        floors.get(floor).addCharacterAtRandomPosition(player);
    }

    public Map getMap() {
        return this.floors.get(floor);
    }

    public void addMap(Map map) {
        this.floors.add(map);
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

    public String getStats() {
        return getPlayer().getStats();
    }

    public Database getEnemyDatabase() {
        return this.enemyDatabase;
    }

    public void setEnemyDatabase(Database database) {
        this.enemyDatabase = database;
    }

    public boolean isOver() {
        return gameIsOver;
    }

    /**
     * Resolves some Action the player has given to the game Afterwards, if
     * actual action was given, also update() the game UI handles defining what
     * imputs are intrepited as what actions
     *
     * @See fi.roguelike.game.Action
     * @See fi.roguelike.character.Player#move(Action)
     *
     * @param action The desired action for the player to take
     * @return String that describes what the player and updated characters did
     */
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
            case QUIT_GAME:
                result = gameOver();
                break;
            default:
                result = "You did nothing";
                break;
        }
        result = result + "\n" + update();
        return result;
    }

    /**
     * Calls all characters in the map to act
     *
     * @See fi.roguelike.map.Map#update()
     *
     * @return String that describes what has passed in the level
     */
    public String update() {
        return this.getMap().update();
    }

    /**
     * Creates a new floor after the player has walked down the stairs
     */
    public void newFloor() {
        this.floor++;
        if (this.floor >= this.floors.size()) {
            Map newMap = new Map(this, 20, 20, 3);
            floors.add(newMap);
            floors.get(floor).addCharacterAtRandomPosition(player);
        }
    }

    /**
     * Sets the game to be over
     *
     * @return String that tells the player what has happened
     */
    public String gameOver() {
        this.gameIsOver = true;
        return "game over";
    }

}
