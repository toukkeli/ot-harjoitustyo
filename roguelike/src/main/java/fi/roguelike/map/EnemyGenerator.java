/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.map;

import java.util.ArrayList;
import fi.roguelike.character.Character;
import fi.roguelike.character.behavior.Behavior;
import fi.roguelike.map.Map;
import fi.roguelike.character.Enemy;
import fi.roguelike.character.Enemy;
import fi.roguelike.character.behavior.AggressiveBehavior;

/**
 *
 * @author toukk
 */
public class EnemyGenerator {

    private Map map;

    public EnemyGenerator(Map map) {
        this.map = map;
    }

    public void generateEnemies(int amount) {
        for (int i = 0; i < amount; i++) {
            Enemy enemy = makeEnemy();
            this.map.addCharacterAtRandomPosition(enemy);
        }
    }

    private Enemy makeEnemy() {
        Enemy enemy;
        enemy = new Enemy("DefaultEnemy", this.map, 0, 0);
        enemy.setBehavior(new AggressiveBehavior(enemy, this.map.getGame().getPlayer()));
        return enemy;
    }

}
