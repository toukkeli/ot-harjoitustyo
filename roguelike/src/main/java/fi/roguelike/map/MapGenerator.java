/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.map;

import static java.lang.Math.floor;
import java.util.Random;

/**
 * Used to generate different random random caves represented by Map-class
 *
 * All methods return a new MapGenerator that holds a new set of tiles (
 * Tile[][] ) based on what the method did. Further methods are called to the
 * new entity to change the Tiles further.
 *
 * Example of use: MapGenerator gene = new MapGenerator(10,10); Tile[][] tiles =
 * gene.fillMap()..makeRoom(3,6).getTiles();
 *
 * @author toukk
 */
public class MapGenerator {

    private Tile[][] tiles;
    private final int width;
    private final int height;

    public MapGenerator(int width, int height) {
        this.tiles = new Tile[width][height];
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the map generated. After this has been called, this instance of
     * MapGenerator should not be used further.
     *
     * @return A Tile[][] map
     */
    public Tile[][] getMap() {
        return this.tiles;
    }

    /**
     * Fills the map with walls and then clears a single tile in the middle of
     * the room to floor. Should be called as the first method if makeHallway()
     * and makeRoom() are planned to be used.
     *
     * Currently part of the primary map-generation process
     *
     * @return A MapGenerator with a map with only walls and a single empty
     * point
     */
    public MapGenerator fillMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = Tile.WALL;
            }
        }
        tiles[Math.floorDiv(width, 2)][Math.floorDiv(height, 2)] = Tile.FLOOR;
        return this;
    }

    /**
     * Carves a new hallway into the map. Chooses a point in the map with floor,
     * then chooses a random direction and changes lenght-amount of tiles into
     * floor in the direction given Only changes tiles to floor-tiles and thus
     * might end up changing existing floor into floor and no actual change
     * happens Also, the algorithm does not changes tiles on the edges of the
     * map.
     *
     * @param lenght How long in tiles the hallway should be (first tile
     * overlaps with existing empty space)
     * @return A MapGenerator with a new hallway
     */
    public MapGenerator makeHallway(int lenght) {
        int[] dir = randomLongitutionalDirection();
        int x = (int) floor(Math.random() * width);
        int y = (int) floor(Math.random() * height);
        while (tiles[x][y] != Tile.FLOOR) {
            x = (int) floor(Math.random() * width);
            y = (int) floor(Math.random() * height);
        }
        if (dir[1] == 0) {
            for (int i = 0; i < lenght; i++) {
                if (i * dir[0] + x > 0 && i * dir[0] + x < width - 1) {
                    tiles[x + i * dir[0]][y] = Tile.FLOOR;
                }
            }
        } else {
            for (int i = 0; i < lenght; i++) {
                if (i * dir[1] + y > 0 && i * dir[1] + y < height - 1) {
                    tiles[x][y + i * dir[1]] = Tile.FLOOR;
                }
            }
        }
        return this;
    }

    private int[] randomLongitutionalDirection() {
        int[] dir = new int[2];
        dir[0] = 0;
        dir[1] = 0;
        Random random = new Random();
        int r = random.nextInt(4);
        switch (r) {
            case 0:
                dir[0] = -1;
                break;
            case 1:
                dir[0] = 1;
                break;
            case 2:
                dir[1] = -1;
                break;
            case 3:
                dir[1] = 1;
                break;
        }
        return dir;
    }

    /**
     * Carves a new room into the map. Chooses a point in the map with floor,
     * then chooses a random direction and changes width*height -amount of tiles
     * in square shape into floor in the direction given Only changes tiles to
     * floor-tiles and thus might end up changing existing floor into floor and
     * no actual change happens Also, the algorithm does not changes tiles on
     * the edges of the map.
     *
     * @param width How large the room is in tiles in x-axis
     * @param height How large the room is in tiles in y-axis
     * @return A MapGenerator with a new room
     */
    public MapGenerator makeRoom(int width, int height) {
        int[] dir = randomDiagonalDirection();

        int x = (int) floor(Math.random() * this.width);
        int y = (int) floor(Math.random() * this.height);
        while (tiles[x][y] != Tile.FLOOR) {
            x = (int) floor(Math.random() * this.width);
            y = (int) floor(Math.random() * this.height);
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i * dir[0] + x > 0 && i * dir[0] + x < this.width - 1 && j * dir[1] + y > 0 && j * dir[1] + y < this.height - 1) {
                    tiles[x + i * dir[0]][y + j * dir[1]] = Tile.FLOOR;
                }
            }
        }
        return this;
    }

    private int[] randomDiagonalDirection() {
        int[] dir = new int[2];
        Random random = new Random();
        int r = random.nextInt(4);
        switch (r) {
            case 0:
                dir[0] = -1;
                dir[1] = -1;
                break;
            case 1:
                dir[0] = 1;
                dir[1] = -1;
                break;
            case 2:
                dir[0] = -1;
                dir[1] = 1;
                break;
            case 3:
                dir[0] = 1;
                dir[1] = 1;
                break;
        }
        return dir;
    }

    /**
     * Generates an empty map with walls only on the edges Currently not used in
     * gameplay and only exists for debugging purposes
     *
     * @return A MapGenerator with a map that has only walls on the edges
     */
    public MapGenerator emptyMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile type;
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    type = Tile.WALL;
                } else {
                    type = Tile.FLOOR;
                }
                tiles[x][y] = type;
            }
        }
        return this;
    }

    /**
     * "Smooths" the map by rounding the areas already made Turns floors
     * surrounded by several walls into walls and vice-versa Should not be used
     * in conjunction with makeHallways() as there is a risk that hallways close
     * up and become separated
     *
     * Currently obsolete
     *
     * @param times How many times the process is to be repeated
     * @return A MapGenerator with smoothed map
     */
    public MapGenerator smoothMap(int times) {
        Tile[][] tiles2 = new Tile[width][height];
        for (int time = 0; time < times; time++) {

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    tiles2[x][y] = smoothTile(x, y);
                }
            }
            tiles = tiles2;
        }
        return this;
    }

    private Tile smoothTile(int x, int y) {
        int floors = 0;
        int rocks = 0;

        for (int ox = -1; ox < 2; ox++) {
            for (int oy = -1; oy < 2; oy++) {
                if (x + ox < 0 || x + ox >= width || y + oy < 0
                        || y + oy >= height) {
                    continue;
                }

                if (tiles[x + ox][y + oy] == Tile.FLOOR) {
                    floors++;
                } else {
                    rocks++;
                }
            }
        }
        Tile type = floors >= rocks ? Tile.FLOOR : Tile.WALL;
        return type;
    }

    /**
     * Adds stairs to a random point in the map with floor Should always be
     * called right before calling getTiles() in order to make the map
     * completeable
     *
     * @return A MapGenerator with a now-ready tiles to be used in Map-class
     */
    public MapGenerator addStairs() {
        int x = (int) floor(Math.random() * width);
        int y = (int) floor(Math.random() * height);
        while (tiles[x][y] != Tile.FLOOR) {
            x = (int) floor(Math.random() * width);
            y = (int) floor(Math.random() * height);
        }
        tiles[x][y] = Tile.STAIRS;
        return this;
    }

}
