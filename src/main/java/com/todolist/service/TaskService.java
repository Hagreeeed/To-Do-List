package com.todolist.service;

import com.todolist.dao.TaskDao;
import com.todolist.model.*;
import com.todolist.service.UserService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private final TaskDao taskDao;

    public TaskService(TaskDao taskdao){
        this.taskDao = taskdao;
    }

    public boolean createTask(String title, String description, LocalDate dueDate, int priority, boolean completed) {
        Optional<User> maybeUser = UserService.getCurrentUser();

        if (maybeUser.isEmpty()) {
            System.out.println("Cannot create task: no user is currently logged in.");
            return false;
        }

        User currentUser = maybeUser.get();

        Task task = new Task.Builder()
                .title(title)
                .description(description)
                .dueDate(dueDate)
                .priority(priority)
                .completed(completed)
                .userId(currentUser.getId())
                .build();

        try {
            taskDao.addNewTask(task);
            System.out.println("Task created successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error while inserting task: " + e.getMessage());
            return false;
        }
    }


    public boolean deleteTask(int id) {
        try{
            taskDao.deleteTask(id);
            return true;
        }catch(Exception e){
            System.out.println("Error in deleting task");
            return false;
        }
    }

    public List<Task> getTasksByUserId() {
        Optional<User> maybeUser = UserService.getCurrentUser();

        try {
            if (maybeUser.isPresent()) {
                int userId = maybeUser.get().getId();
                return taskDao.getTasksByUserId(userId);
            }
            else{
                System.out.println("User is not logged in");
            }
        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

        return null;
    }

}
