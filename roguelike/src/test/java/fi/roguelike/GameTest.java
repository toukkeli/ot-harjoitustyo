/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike;

import fi.roguelike.character.Enemy;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import fi.roguelike.character.Player;
import fi.roguelike.character.behavior.AggressiveBehavior;
import fi.roguelike.map.Map;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author toukk
 */
public class GameTest {

    Game game;

    public GameTest() {
    }

    @Before
    public void setUp() {
        game = new Game();
        Map map = new Map(game, 10, 10, 0);
        Player player = game.getPlayer();
        map.setCharacter(player, 3, 3);
        game.setMap(map, 0);
    }

    @Test
    public void gameIsCreatedProperly() {
        assertTrue(game != null);
    }

    @Test
    public void playerExists() {
        assertTrue(game.getPlayer() != null);
    }

    @Test
    public void mapExists() {
        assertTrue(game.getMap() != null);
    }

    @Test
    public void mapCanBeChanged() {
        Map map = new Map(game, 5, 5, 5);
        game.setMap(map, 0);
        assertEquals(map, game.getMap());
    }

    @Test
    public void newFloorCanBeMade() {
        game.newFloor();
        game.setFloor(1);
        assertTrue(game.getMap() != null);
    }

    @Test
    public void playerCanMoveNorth() {
        game.resolveAction(Action.GO_NORTH);
        assertThat(game.getPlayer().getY(), is(equalTo(2)));
    }

    @Test
    public void playerCanMoveSouthWest() {
        game.resolveAction(Action.GO_SOUTHWEST);
        assertThat(game.getPlayer().getX(), is(equalTo(2)));
        assertThat(game.getPlayer().getY(), is(equalTo(4)));
    }
    
    @Test
    public void aggressiveEnemyMovesTowardsPlayerWhenThereIsSpace(){
        Enemy enemy = new Enemy("Testi",game.getMap(),5,5);
        enemy.setBehavior(new AggressiveBehavior(enemy,game.getPlayer()));
        game.getMap().setCharacter(enemy);
        game.resolveAction(Action.GO_SOUTH);
        assertThat(enemy.getX(), is(equalTo(4)));
        assertThat(enemy.getY(), is(equalTo(4)));
    }
}
