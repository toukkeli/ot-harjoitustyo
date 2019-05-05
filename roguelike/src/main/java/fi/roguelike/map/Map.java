/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.map;

import fi.roguelike.character.Character;
import static java.lang.Math.floor;
import java.util.ArrayList;
import fi.roguelike.game.Game;
import fi.roguelike.dao.Database;

/**
 * Represents a single level in the game
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
        generateEnemies(enemies, this.game.getEnemyDatabase());
    }

    /**
     * Default constructor is for test-class only
     *
     * @param game
     */
    public Map(Game game) {
        this.game = game;
        this.width = 10;
        this.height = 10;
        this.characters = new ArrayList<>();
        MapGenerator testGenerator = new MapGenerator(width, height);
        this.tiles = testGenerator.emptyMap().getMap();
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

    /**
     * Returns what character is at the given position. Return null if there is
     * none
     *
     * @param x
     * @param y
     * @return Character if any was at the position given, null otherwise
     */
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

    /**
     * Checks if the given character is in the level
     *
     * @param character Who are we looking for
     * @return Whether the character was found in the level
     */
    public boolean characterIsOnTheMap(Character character) {
        for (Character c : characters) {
            if (c.getMap() == this) {
                return true;
            }
        }
        return false;
    }

    /**
     * Places the character into the given position on the map
     *
     * @param character Who is to be placed
     * @param x
     * @param y
     */
    public void setCharacter(Character character, int x, int y) {
        character.setX(x);
        character.setY(y);
        character.setMap(this);
        this.characters.add(character);
    }

    /**
     * Places the character into a random empty position on the map
     *
     * @param character Who is to be placed
     */
    public void addCharacterAtRandomPosition(Character character) {
        int x;
        int y;
        do {
            x = (int) floor(Math.random() * this.getWidth());
            y = (int) floor(Math.random() * this.getHeight());
        } while (!this.positionIsEmpty(x, y));
        setCharacter(character, x, y);
    }

    /**
     * Removes character from the map
     *
     * @param c Who to remove
     */
    public void removeCharacter(Character c) {
        characters.remove(c);
    }

    /**
     * Checks if the given position is "empty": Holds no character and is floor
     *
     * @param x
     * @param y
     * @return Whether the place is empty or not
     */
    public boolean positionIsEmpty(int x, int y) {
        return getTile(x, y) != Tile.WALL && this.getCharacter(x, y) == null;
    }

    /**
     * Activates the given character
     *
     * @param c Who is to be activated
     * @return The description of what the activated creature did
     */
    public String activate(Character c) {
        return c.activate();
    }

    /**
     * Updates the map by telling all the creatures in the map to act All the
     * descriptions of what the activated creatures did are linked into a String
     * entity with line-changes between the descriptions Noteworthy: The
     * player-character is also called to act although their act-method does
     * nothing
     *
     * @See fi.roguelike.character.Player#activate()
     *
     * @return A description of what passed when all characters on the map acted
     */
    public String update() {
        ArrayList<Character> toActivate = new ArrayList<>(characters);
        String descriptions = "";
        for (Character c : toActivate) {
            String newline = activate(c);
            if (!newline.equals("")) {
                descriptions = descriptions + newline + "\n";
            }
        }
        return descriptions;

    }

    /**
     * Initializes the map-tiles
     *
     * @See fi.roguelike.map.MapGenerator
     * @return
     */
    private Tile[][] generateTiles() {
        MapGenerator generator = new MapGenerator(width, height);
        return generator.fillMap()
                .makeHallway(7).makeHallway(5).makeHallway(4)
                .makeRoom(7, 4).makeRoom(4, 7)
                .makeHallway(5)
                .makeRoom(3, 3)
                .addStairs()
                .getMap();
    }

    private void generateEnemies(int amount, Database database) {
        EnemyGenerator generator = new EnemyGenerator(this, database);
        generator.generateEnemies(amount);
    }

}
