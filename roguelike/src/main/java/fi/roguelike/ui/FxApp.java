/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.ui;

import fi.roguelike.dao.Database;
import fi.roguelike.game.Action;
import fi.roguelike.game.Game;
import fi.roguelike.map.Tile;
import java.io.File;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 *
 * @author toukk
 */
public class FxApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        final Game game = new Game();
        File file = new File("db", "enemies.db");
        Database enemyDatabase = new Database("jdbc:sqlite:enemies.db");
        game.setEnemyDatabase(enemyDatabase);

        VBox layout = new VBox();
        Pane grid = drawGrid(game);
        grid.setPrefSize(600, 400);

        TextArea textBox = new TextArea("Welcome adventurer!");

        textBox.setEditable(false);
        textBox.setMouseTransparent(true);
        textBox.setFocusTraversable(false);

        layout.getChildren().add(grid);
        layout.getChildren().add(textBox);

        Scene scene = new Scene(layout);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                TextArea a = textBox;
                String toPrint;
                clearText(textBox);
                Action action = getAction(event);
                toPrint = game.resolveAction(action);
                toPrint = game.getStats() + toPrint;
                update(game, layout, toPrint, textBox);
                if (game.isOver()) {
                    System.exit(0);
                };
            }
        });

        stage.setTitle("Roguelike");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }

    private Pane drawGrid(Game game) {
        Pane grid = new Pane();

        int firstX = 0;
        int lastX = game.getMap().getWidth();
        int firstY = 0;
        int lastY = game.getMap().getHeight();
        int imageSize = 20;

        for (int i = firstX; i < lastX; i++) {
            for (int j = firstY; j < lastY; j++) {
                Polygon sprite = getSprite(game, i, j);
                sprite.setTranslateX(i * imageSize);
                sprite.setTranslateY(j * imageSize);
                grid.getChildren().add(sprite);
            }
        }

        return grid;
    }

    private Polygon getSprite(Game game, int x, int y) {
        Polygon sprite;
        Tile type = game.getMap().getTile(x, y);
        fi.roguelike.character.Character character = game.getMap().getCharacter(x, y);
        if (character == game.getPlayer()) {
            sprite = new Polygon(10, 0, 20, 10, 10, 20, 0, 10);
            sprite.setFill(Color.AQUA);
        } else if (character != null) {
            sprite = new Polygon(10, 0, 20, 10, 10, 20, 0, 10);
            sprite.setFill(Color.RED);
        } else {
            switch (type) {
                case FLOOR:
                    sprite = new Polygon(0, 0, 0, 20, 20, 20, 20, 0);
                    sprite.setFill(Color.LIGHTGOLDENRODYELLOW);
                    break;
                case WALL:
                    sprite = new Polygon(0, 0, 0, 20, 20, 20, 20, 0);
                    sprite.setFill(Color.GRAY);
                    break;
                case STAIRS:
                    sprite = new Polygon(0, 0, 0, 20, 20, 20, 20, 0);
                    sprite.setFill(Color.DARKKHAKI);
                    break;
                default:
                    sprite = new Polygon(0, 0, 0, 20, 20, 20, 20, 0);
                    sprite.setFill(Color.BLACK);
                    break;
            }
        }
        return sprite;
    }

    private void printText(TextArea textBox, String text) {
        textBox.setText(textBox.getText() + "\n" + text);
    }

    private void clearText(TextArea textBox) {
        textBox.setText("");
    }

    private Action getAction(KeyEvent event) {
        KeyCode code = event.getCode();
        switch (code) {
            case NUMPAD8:
                return Action.GO_NORTH;
            case NUMPAD9:
                return Action.GO_NORTHEAST;
            case NUMPAD6:
                return Action.GO_EAST;
            case NUMPAD3:
                return Action.GO_SOUTHEAST;
            case NUMPAD2:
                return Action.GO_SOUTH;
            case NUMPAD1:
                return Action.GO_SOUTHWEST;
            case NUMPAD4:
                return Action.GO_WEST;
            case NUMPAD7:
                return Action.GO_NORTHWEST;
            case NUMPAD5:
                return Action.GO_DOWN;
            case ESCAPE:
                return Action.QUIT_GAME;
            default:
                return null;
        }
    }

    private void update(Game game, VBox layout, String toPrint, TextArea textBox) {
        Pane newGrid = drawGrid(game);
        newGrid.setPrefSize(600, 400);
        layout.getChildren().remove(0);
        layout.getChildren().add(0, newGrid);
        printText(textBox, toPrint);
    }

}
