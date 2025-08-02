package com.todolist.command;

import com.todolist.dto.TaskCreateRequest;
import com.todolist.service.TaskService;

public class TaskCreateCommand implements Command<Boolean> {
    private final TaskService taskService;
    private TaskCreateRequest request;

    public TaskCreateCommand(TaskService taskService, TaskCreateRequest request) {
        this.taskService = taskService;
        this.request = request;
    }

    @Override
    public Boolean execute() {
        if (taskService.createTask(request.getTitle(),  request.getDescription(), request.getDueDate(), request.getPriority())){
            return true;
        }
        return false;
    }
}
