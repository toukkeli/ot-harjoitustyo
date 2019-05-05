/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import fi.roguelike.character.Enemy;
import fi.roguelike.game.Game;
import fi.roguelike.map.Map;
import fi.roguelike.map.Tile;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author toukk
 */
public class MapTest {

    Game game;
    Map map;

    public MapTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @Before
    public void setUp() {
        game = new Game("Testipelaaja", 0, 0, 0, true);
        map = game.getMap();
    }

    @Test
    public void mapHasATile() {
        assertTrue(map.getTile(0, 0).equals(Tile.WALL));
    }

    @Test
    public void tileCanBeChanged() {
        map.setTile(2, 2, Tile.WALL);
        assertThat(map.getTile(2, 2), is(equalTo(Tile.WALL)));
    }

    @Test
    public void tileRegistersAsFreeWhenItIsFloor() {
        assertThat(map.positionIsEmpty(2, 2), is(true));
    }

    @Test
    public void enemiesCanBePlaced() {
        Enemy enemy = new Enemy("Kissa");
        map.setCharacter(enemy, 2, 2);
        assertThat(map.getCharacter(2, 2), is(equalTo(enemy)));
    }

    @Test
    public void tileRegistersAsFullWhenCharacterIsInIt() {
        Enemy enemy = new Enemy("Kissa");
        map.setCharacter(enemy, 2, 2);
        assertThat(map.positionIsEmpty(2, 2), is(false));
    }

}
