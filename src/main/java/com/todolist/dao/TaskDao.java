package com.todolist.dao;

import com.todolist.model.Task;
import com.todolist.database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class TaskDao {
    private final Database db;

    public TaskDao(Database db) {
        this.db = db;
    }

    public void addNewTask(String title, String description, LocalDate dueDate, int priority, Boolean completed) throws SQLException {
        String sql = "INSERT INTO tasks (title, description, due_date, priority, completed) VALUES (?, ?, ?, ?, ?)";
        db.executeUpdate(sql, title, description, dueDate, priority, completed);
    }

    public List<Task> getAllTasks(int user_id) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE user_id = ?";
        ResultSet rs = db.executeQuery(sql);
        List<Task> tasks = new ArrayList<>();

        while (rs.next()) {
            Task task = new Task.Builder()
                    .id(rs.getInt("id"))
                    .title(rs.getString("title"))
                    .description(rs.getString("description"))
                    .dueDate(rs.getDate("due_date") != null ? rs.getDate("due_date").toLocalDate() : null)
                    .priority(rs.getInt("priority"))
                    .completed(rs.getBoolean("completed"))
                    .userId(user_id)
                    .build();

            tasks.add(task);
        }
        return tasks;
    }

}
