/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import fi.roguelike.character.Enemy;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author toukk
 */
public class EnemyDao implements Dao<Enemy, String> {

    private final Database database;

    public EnemyDao(Database database) {
        this.database = database;
    }

    @Override
    public Enemy findOne(String key) throws SQLException {
        Enemy a;
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Enemy WHERE name = ?");
            stmt.setString(1, key);
            ResultSet rs = stmt.executeQuery();
            boolean hasOne = rs.next();
            if (!hasOne) {
                return null;
            }
            a = new Enemy(rs.getString("name"));
            a.setHp(rs.getInt("hp"));
            a.setDamage(rs.getInt("dmg"));
            a.setAccuracy(rs.getInt("acc"));
            stmt.close();
            rs.close();
            conn.close();
        } catch (NullPointerException e) {
            System.out.println("For reasons unknown, connection to the database was not made. Spawning Goblin instead");
            a = new Enemy("Failure");
        }
        return a;
    }

    @Override
    public List<Enemy> findAll() throws SQLException {
        List<Enemy> enemies = new ArrayList<>();
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Enemy");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Enemy a = new Enemy(rs.getString("name"));
                a.setHp(rs.getInt("hp"));
                a.setDamage(rs.getInt("dmg"));
                a.setAccuracy(rs.getInt("acc"));
                enemies.add(a);
            }

            stmt.close();
            rs.close();
            conn.close();
        } catch (NullPointerException e) {
            System.out.println("For reasons unknown, connection to the database was not made. Spawning Goblin instead");
        }

        return enemies;
    }

    @Override
    public Enemy saveOrUpdate(Enemy object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
