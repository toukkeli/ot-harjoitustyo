/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.ui;

import java.util.Scanner;
import fi.roguelike.Action;
import fi.roguelike.Game;
import fi.roguelike.map.Tile;

/**
 *
 * @author toukk
 */
public class Textapp {

    private Game game;
    private Scanner input;

    public Textapp(Game game) {
        this.game = game;
        input = new Scanner(System.in);
    }

    public void drawMap() {
        for (int y = 0; y < this.game.getMap().getHeight(); y++) {
            for (int x = 0; x < this.game.getMap().getWidth(); x++) {
                Tile type = this.game.getMap().getTile(x, y);
                fi.roguelike.character.Character character = this.game.getMap().getCharacter(x, y);
                if (character == game.getPlayer()) {
                    System.out.print("P");
                } else if (character != null) {
                    System.out.print("E");
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

    void start() {
        drawMap();
        System.out.println("Welcome to the dungeon!");
        System.out.println("Write your commands to the text-line.");
        System.out.println("Commands: 1,2,3,4,5,6,7,8,9,x");
        System.out.println("What is your name?");
        game.getPlayer().setName(input.nextLine());
        System.out.println("Hello " + game.getPlayer().getname() + ". Let the games begin!");
        while (!this.game.isOver()) {
            String command = input.nextLine();
            String description;
            switch (command) {
                case "8":
                    description = game.resolveAction(Action.GO_NORTH);
                    break;
                case "9":
                    description = game.resolveAction(Action.GO_NORTHEAST);
                    break;
                case "6":
                    description = game.resolveAction(Action.GO_EAST);
                    break;
                case "3":
                    description = game.resolveAction(Action.GO_SOUTHEAST);
                    break;
                case "2":
                    description = game.resolveAction(Action.GO_SOUTH);
                    break;
                case "1":
                    description = game.resolveAction(Action.GO_SOUTHWEST);
                    break;
                case "4":
                    description = game.resolveAction(Action.GO_WEST);
                    break;
                case "7":
                    description = game.resolveAction(Action.GO_NORTHWEST);
                    break;
                case "5":
                    description = game.resolveAction(Action.GO_DOWN);
                    break;
                case "x":
                    description = game.gameOver();
                    break;
                default:
                    description = "Unknown command";
                    break;
            }
            System.out.println(description);
            drawMap();
        }
    }
}
