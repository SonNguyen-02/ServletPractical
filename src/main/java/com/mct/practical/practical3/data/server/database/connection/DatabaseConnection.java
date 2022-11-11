/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mct.practical.practical3.data.server.database.connection;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sonnc
 */
public abstract class DatabaseConnection {

    private final ConnectionInfo info;

    private Connection connection = null;

    protected DatabaseConnection(@NotNull ConnectionInfo info) {
        this.info = info;
    }

    public Connection open() {
        if (connection == null) {
            synchronized (this) {
                try {
                    String url = "jdbc:mysql://" + info.hostname + "/" + info.database + "?useUnicode=yes&characterEncoding=utf8";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection(url, info.username, info.password);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return connection;
    }

    // run statement select
    public ResultSet execSelect(String sql) {
        open();
        ResultSet rs = null;
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    // run statement update, delete | insert
    public int execUpdate(String sql) {
        open();
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    // run statement insert and return last id inserted
    public long execInsert(String sql) {
        open();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
