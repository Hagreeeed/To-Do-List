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

    public void addNewTask(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (title, description, due_date, priority, completed, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        db.executeUpdate(sql, task.getTitle(), task.getDescription(), task.getDueDate(), task.getPriority(), task.isCompleted(), task.getUserId());
    }

    public void deleteTask(int id) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        db.executeUpdate(sql, id);
    }

    public List<Task> getTasksByUserId(int user_id) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE user_id = ?";
        ResultSet rs = db.executeQuery(sql, user_id);
        List<Task> tasks = new ArrayList<>();

        while (rs.next()) {
            Task task = new Task.Builder()
                    .id(rs.getInt("id"))
                    .title(rs.getString("title"))
                    .description(rs.getString("description"))
                    .dueDate(rs.getString("due_date"))
                    .priority(rs.getInt("priority"))
                    .completed(rs.getBoolean("completed"))
                    .userId(user_id)
                    .build();

            tasks.add(task);
        }
        return tasks;
    }

}
