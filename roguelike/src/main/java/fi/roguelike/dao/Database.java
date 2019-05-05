/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.dao;

import java.sql.*;

/**
 *
 * @author toukk
 */
public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(databaseAddress);
        return conn;
    }

}
