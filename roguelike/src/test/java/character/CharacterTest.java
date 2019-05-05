/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import fi.roguelike.character.Enemy;
import fi.roguelike.character.Player;
import fi.roguelike.character.behavior.AggressiveBehavior;
import fi.roguelike.dao.Database;
import fi.roguelike.game.Action;
import fi.roguelike.game.Game;
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
public class CharacterTest {

    static Game game;
    Player player;
    Enemy enemy;

    public CharacterTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @Before
    public void setUp() {
        game = new Game("Jaska", 0, 0, 0, true);
        player = game.getPlayer();
        player.setHp(10);
        enemy = new Enemy("Antijaska");
        enemy.setBehavior(new AggressiveBehavior(enemy, player));
        enemy.setHp(1);
        enemy.setDamage(1);
        enemy.setAccuracy(100);
        game.getMap().setCharacter(player, 2, 2);
        game.getMap().setCharacter(enemy, 4, 4);
    }

    @Test
    public void characterReturnsNameProperly() {
        assertThat(player.getName(), is(equalTo("Jaska")));
    }

    @Test
    public void characterReturnsHpProperlyAfterReduction() {
        player.reduceHp(1);
        assertThat(player.getHp(), is(equalTo(9)));
    }

    @Test
    public void characterReturnsHpProperlyAfterAddition() {
        player.addHp(2);
        assertThat(player.getHp(), is(equalTo(12)));
    }

    @Test
    public void characterReturnsDamageProperly() {
        assertThat(player.getDamage(), is(equalTo(1)));
    }

    @Test
    public void characterReturnsAccuracyProperly() {
        assertThat(player.getAccuracy(), is(equalTo(90)));
    }

    @Test
    public void playerMovesProperlySoutheast() {
        game.resolveAction(Action.GO_SOUTHEAST);
        assertThat(game.getMap().getCharacter(3, 3), is(equalTo(player)));
    }

    @Test
    public void enemyDamagesPlayerProperlyAfterPlayerHasMoved() {
        game.resolveAction(Action.GO_SOUTHEAST);
        assertThat(player.getHp(), is(equalTo(10 - enemy.getDamage())));
    }
}
