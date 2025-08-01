package com.todolist.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private final Connection conn;

    public DatabaseInitializer(Connection conn) {
        this.conn = conn;
    }

    public void initialize() throws SQLException {
        String sql = """
        CREATE TABLE IF NOT EXISTS users (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            username TEXT NOT NULL UNIQUE,
            password TEXT NOT NULL
        );
        
        CREATE TABLE IF NOT EXISTS tasks (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT NOT NULL,
            description TEXT,
            due_date TEXT,
            priority INTEGER,
            completed BOOLEAN DEFAULT FALSE,
            user_id INTEGER,
            FOREIGN KEY (user_id) REFERENCES users(id)
        );
        """;

        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

}
