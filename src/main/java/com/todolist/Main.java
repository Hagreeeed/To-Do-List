package com.todolist;

import com.todolist.database.Database;
import java.sql.Connection;
import com.todolist.util.DatabaseInitializer;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        initDb();
    }

    public static void initDb() {
        try (Database db = new Database()) {
            DatabaseInitializer init = new DatabaseInitializer(db.getConnection());
            init.initialize();
            System.out.println("Database connected, and initialized");
        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}