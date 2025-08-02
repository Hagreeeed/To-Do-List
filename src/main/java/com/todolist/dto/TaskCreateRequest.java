package com.todolist.dto;

public class TaskCreateRequest {
    private String title;
    private String description;
    private String DueDate;
    private int priority;

    public TaskCreateRequest(String title, String description, String DueDate, int priority) {
        this.title = title;
        this.description = description;
        this.DueDate = DueDate;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getDueDate() {
        return DueDate;
    }
    public int getPriority() {
        return priority;
    }
}
