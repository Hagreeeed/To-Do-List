package com.todolist.service;

import com.todolist.dao.UserDao;
import com.todolist.util.Validator;
import com.todolist.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class UserService {
    private final UserDao userDao;
    private final Validator validator;
    private User user;

    public UserService(UserDao userDao, Validator validator) {
        this.userDao = userDao;
        this.validator = validator;
    }

    public boolean registerUser(String username, String password) {
        if (!validator.validPassword(password)) {
            System.out.println("Passwords don't match");
            return false;
        }

        String hash = hashPassword(password);

        User user = new User.Builder()
                .username(username)
                .password(hash)
                .build();

        try {
            userDao.register(user);
            System.out.println("User registered successfully");
            return true;
        } catch (SQLException e) {
            System.err.println("User don't be registered " + e.getMessage());
            return false;
        }
    }

    public boolean loginUser(String username, String password) {
        try {
            User user = userDao.findUserByUsername(username);

            if (user != null) {
                if (checkPassword(password, user.getPassword())) {
                    System.out.println("User successfully logged in");
                    this.user = user;
                    return true;
                } else {
                    System.out.println("Invalid username or password");
                    return false;
                }
            } else {
                System.out.println("User not found");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error during login: " + e.getMessage());
            return false;
        }
    }

    public void updatePassword(String username, String newPassword, String oldPassword) {
        try {
            if (user != null && validator.validPassword(newPassword) && checkPassword(oldPassword, user.getPassword())) {
                String hash = hashPassword(newPassword);
                userDao.updatePasswordByUsername(username, hash);
                System.out.println("Password updated successfully");
            }
        }catch (SQLException e) {
            System.err.println("Error during updatePassword: " + e.getMessage());
        }

    }

    public User getCurrentUser() {
        return user.withoutPassword();
    }

    public String hashPassword (String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public boolean checkPassword (String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
