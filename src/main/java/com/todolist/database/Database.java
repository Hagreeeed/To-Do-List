package com.todolist.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database implements AutoCloseable {
    private final Connection conn;

    public Database() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:database/database.db");
        if (conn != null) {
            System.out.println("Database connection established");
        }
    }

    public Connection getConnection(){
        return conn;
    }

    public int executeUpdate(String query, Object... params) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeUpdate();
        }
    }

    public ResultSet executeQuery(String query, Object... params) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(query);
        for (int i = 0; i < params.length; i++){
            statement.setObject(i + 1, params[i]);
        }
        return statement.executeQuery();
    }

    @Override
    public void close() throws SQLException {
        if(conn != null && !conn.isClosed()){
            conn.close();
            System.out.println("Connection closed");
        }
    }
}
