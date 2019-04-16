/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.map;

import static java.lang.Math.floor;

/**
 *
 * @author toukk
 */
public class MapGenerator {

    private Tile[][] tiles;
    private int width;
    private int height;

    public MapGenerator(int width, int height) {
        this.tiles = new Tile[width][height];
        this.width = width;
        this.height = height;
    }

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

    public MapGenerator randomMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile type;
                if (0.5 < Math.random()) {
                    type = Tile.WALL;
                } else {
                    type = Tile.FLOOR;
                }
                tiles[x][y] = type;
            }
        }
        return this;
    }

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

    public Tile[][] getMap() {
        return this.tiles;
    }

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
