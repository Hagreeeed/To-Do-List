package com.todolist;

import com.todolist.database.Database;
import com.todolist.model.*;
import com.todolist.util.DatabaseInitializer;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.todolist.util.Validator;
import com.todolist.service.*;
import com.todolist.dao.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        Validator validator = new Validator();
        UserDao userDao = new UserDao(new Database());
        TaskDao taskDao = new TaskDao(new Database());
        UserService userService = new UserService(userDao, validator);
        TaskService taskService = new TaskService(taskDao);
        List<Task> tasks = new ArrayList<>();

        taskService.deleteTask(3);
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