/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike.domain;

/**
 *
 * @author toukk
 */
public class Map {

    private Tile[][] tiles;
    private Character[][] characters;

    private final int width;
    private final int height;

    public Map(Tile[][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        this.characters = new Character[width][height];
    }

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = generateNewMap();
        this.characters = new Character[width][height];
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
    
    public void setTile(int x, int y, Tile tile){
        this.tiles[x][y] = tile;
    }
    
    public Character[][] getCharacters() {
        return this.characters;
    }
    
    public Character getCharacter(int x, int y) {
        return characters[x][y];
    }
    
    public void setCharacter(int x, int y, Character character){
        this.characters[x][y] = character;
    }

    public Tile[][] generateNewMap() {
        MapGenerator generator = new MapGenerator(width, height);
        return generator.randomMap().smoothMap(1).generateMap();
    }
    
    // Not implemented yet
    public Character[][] generateNewEnemies(){
        return this.characters;
    }

    
    // Not implemented yet
    public void update() {
        
    }

}
