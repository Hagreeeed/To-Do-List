package com.todolist.dto;

public class DeleteTaskRequest {
    private int id;

    public DeleteTaskRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

