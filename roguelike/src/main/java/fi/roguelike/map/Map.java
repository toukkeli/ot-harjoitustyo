/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.map;

import fi.roguelike.character.Character;
import static java.lang.Math.floor;
import java.util.ArrayList;
import fi.roguelike.Game;
import fi.roguelike.character.Enemy;

/**
 *
 * @author toukk
 */
public class Map {

    private final Game game;
    private Tile[][] tiles;
    private ArrayList<Character> characters;
    private final int width;
    private final int height;

    public Map(Game game, Tile[][] tiles, ArrayList<Character> characters) {
        this.game = game;
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        this.characters = characters;
    }

    public Map(Game game, int width, int height, int enemies) {
        this.game = game;
        this.width = width;
        this.height = height;
        this.tiles = generateTiles();
        this.characters = new ArrayList<>();
        generateEnemies(enemies);
    }

    public Game getGame() {
        return this.game;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {

        return this.height;
    }

    public Tile[][] getTiles() {
        return this.tiles;
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public void setTile(int x, int y, Tile tile) {
        this.tiles[x][y] = tile;
    }

    public Character getCharacter(int x, int y) {
        for (Character c : characters) {
            if (c.getX() == x && c.getY() == y) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Character> getCharacters() {
        return this.characters;
    }

    public boolean characterIsOnTheMap(Character character) {
        for (Character c : characters) {
            if (c.getMap() == this) {
                return true;
            }
        }
        return false;
    }

    public void setCharacter(Character character) {
        this.characters.add(character);
    }

    public void setCharacter(Character character, int x, int y) {
        this.characters.add(character);
        character.setX(x);
        character.setY(y);
    }

    public void addCharacterAtRandomPosition(Character character) {
        int x;
        int y;
        do {
            x = (int) floor(Math.random() * this.getWidth());
            y = (int) floor(Math.random() * this.getHeight());
        } while (!this.positionIsEmpty(x, y));
        character.setX(x);
        character.setY(y);
        character.setMap(this);
        this.characters.add(character);
    }

    public void removeCharacter(Character c) {
        characters.remove(c);
    }

    public boolean positionIsEmpty(int x, int y) {
        if (getTile(x, y) != Tile.WALL && this.getCharacter(x, y) == null) {
            return true;
        }
        return false;
    }

    public String activate(Character c) {
        return c.activate();
    }

    public String update() {
        ArrayList<Character> toActivate = new ArrayList<Character>(characters);
        String descriptions = "";
        for (Character c : toActivate) {
            String newline = activate(c);
            if (newline != "") {
                descriptions = descriptions + newline + "\n";
            }

        }
        return descriptions;

    }

    private Tile[][] generateTiles() {
        MapGenerator generator = new MapGenerator(width, height);
        //return generator.randomMap().smoothMap(1).getMap();
        return generator.emptyMap().addStairs().getMap();
    }

    private void generateEnemies(int amount) {
        EnemyGenerator generator = new EnemyGenerator(this);
        generator.generateEnemies(amount);
    }

}
