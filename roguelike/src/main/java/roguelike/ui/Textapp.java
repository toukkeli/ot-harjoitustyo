/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike.ui;

import roguelike.domain.Game;
import roguelike.domain.Tile;

/**
 *
 * @author toukk
 */
public class Textapp {

    private Game game;

    public Textapp(Game game) {
        this.game = game;
    }

    public void drawMap() {
        for (int x = 0; x < this.game.getMap().getWidth(); x++) {
            for (int y = 0; y < this.game.getMap().getHeight(); y++) {
                Tile type = this.game.getMap().getTile(x, y);
                roguelike.domain.Character character = this.game.getMap().getCharacter(x, y);

                if (character != null) {
                    System.out.print("@");
                } else if (type == null) {
                    System.out.println("0");
                } else {
                    switch (type) {
                        case FLOOR:
                            System.out.print(".");
                            break;
                        case WALL:
                            System.out.print("#");
                            break;
                        case STAIRS:
                            System.out.print("S");
                            break;
                        default:
                            System.out.print("*");
                            break;
                    }
                }
            }
            System.out.println("");
        }

    }
}
