package com.todolist.dao;

import com.todolist.database.Database;
import com.todolist.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final Database db;

    public UserDao(Database db){
        this.db = db;
    }

    public void register(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        db.executeUpdate(sql, user.getUsername(), user.getPassword());
    }

    public User findUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (ResultSet rs = db.executeQuery(sql,  username)){
            if (rs.next()) {
                return new User.Builder()
                        .id(rs.getInt("id"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .build();
            }
        }
        return null;
    }

    public void updatePasswordByUsername(String username, String password) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        db.executeUpdate(sql, password, username);
    }

}
