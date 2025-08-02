package com.todolist.command;

import com.todolist.dao.TaskDao;
import com.todolist.dto.DeleteTaskRequest;
import com.todolist.service.TaskService;

import java.sql.SQLException;

public class DeleteTaskCommand implements Command<Boolean> {
    private final TaskService taskService;
    private DeleteTaskRequest request;

    public DeleteTaskCommand(TaskService taskService, DeleteTaskRequest request) {
        this.taskService = taskService;
        this.request = request;
    }

    @Override
    public Boolean execute() {
        if (taskService.deleteTask(request.getId())) {
            return true;
        }
        return false;
    }
}
