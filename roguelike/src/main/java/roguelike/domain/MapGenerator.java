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
public class MapGenerator {

    private Tile[][] tiles;
    private int width;
    private int height;

    public MapGenerator(int width, int height) {
        this.tiles = new Tile[width][height];
        this.width = width;
        this.height = height;
    }

    public MapGenerator(Tile[][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
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
                    tiles2[x][y] = type;
                }
            }
            tiles = tiles2;
        }
        return this;
    }

    public Tile[][] generateMap() {

        return this.tiles;
    }

    // There will be other generateMap()-methods for customized creation in the future
}
