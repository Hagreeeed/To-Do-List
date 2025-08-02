package com.todolist.command;

import com.todolist.model.Task;
import com.todolist.service.TaskService;
import java.util.List;

public class GetTasksByUserCommand implements Command<List<Task>>{
    private final TaskService taskService;

    public GetTasksByUserCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public List<Task> execute() {
        List<Task> tasks = taskService.getTasksByUserId();
        return tasks;
    }

}
