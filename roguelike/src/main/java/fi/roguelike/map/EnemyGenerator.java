/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.map;

import fi.roguelike.character.Enemy;
import fi.roguelike.character.behavior.AggressiveBehavior;
import fi.roguelike.dao.Database;
import fi.roguelike.dao.EnemyDao;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
 * Used to generate the enemies to a map when Map-class calls it Generates
 * enemies either by communicating with EemyDao or using the default template
 *
 * @author toukk
 */
public class EnemyGenerator {

    final private Map map;
    final private EnemyDao enemyDao;

    public EnemyGenerator(Map map, Database database) {
        this.map = map;
        this.enemyDao = new EnemyDao(database);
    }

    /**
     * Generates random enemies at random locations on the map
     *
     * @param amount How many enemies are generated
     */
    public void generateEnemies(int amount) {
        for (int i = 0; i < amount; i++) {
            Enemy enemy;
            try {
                enemy = makeEnemy();
            } catch (SQLException ex) {
                enemy = defaultEnemy();
            }
            this.map.addCharacterAtRandomPosition(enemy);
        }
    }

    /**
     * Generates specific enemies (found in the database) to a random locations
     * on the map
     *
     * @param amount How many enemies are generated
     * @param name What enemies shall be made (stats are fetched from the
     * database)
     */
    public void generateEnemies(int amount, String name) {
        for (int i = 0; i < amount; i++) {
            Enemy enemy;
            try {
                enemy = makeEnemy(name);
            } catch (SQLException ex) {
                enemy = defaultEnemy();
            }
            this.map.addCharacterAtRandomPosition(enemy);
        }
    }

    /**
     * Adds a specific enemy (found in the database) to a specific location on
     * the map
     *
     * @param name What enemy (stats are fetched from the database). Creates a
     * default enemy instead if the specified enemy is not found
     * @param x
     * @param y
     */
    public void addEnemy(String name, int x, int y) {
        Enemy enemy;
        try {
            enemy = makeEnemy(name);
        } catch (SQLException ex) {
            enemy = defaultEnemy();
        }
        this.map.setCharacter(enemy, x, y);
    }

    private Enemy makeEnemy() throws SQLException {
        Enemy enemy;
        List<Enemy> enemies = enemyDao.findAll();
        Random random = new Random();
        if (enemies.isEmpty()) {
            enemies.add(defaultEnemy());
        }
        enemy = enemies.get(random.nextInt(enemies.size()));
        enemy.setBehavior(new AggressiveBehavior(enemy, this.map.getGame().getPlayer()));
        return enemy;
    }

    private Enemy makeEnemy(String name) throws SQLException {
        Enemy enemy = enemyDao.findOne(name);
        enemy.setBehavior(new AggressiveBehavior(enemy, this.map.getGame().getPlayer()));
        return enemy;
    }

    private Enemy defaultEnemy() {
        Enemy enemy = new Enemy("Goblin");
        enemy.setHp(1);
        enemy.setDamage(1);
        enemy.setAccuracy(50);
        enemy.setBehavior(new AggressiveBehavior(enemy, this.map.getGame().getPlayer()));
        return enemy;
    }

}
