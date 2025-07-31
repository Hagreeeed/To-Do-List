package com.todolist.model;

import java.time.LocalDate;

public class Task {
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private int priority;
    private boolean completed;
    private int userId;

    public Task(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.dueDate = builder.dueDate;
        this.priority = builder.priority;
        this.completed = builder.completed;
        this.userId = builder.userId;
    }

    //Getters
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public LocalDate getDueDate(){
        return dueDate;
    }
    public int getPriority(){
        return priority;
    }
    public boolean isCompleted(){
        return completed;
    }
    public int getUserId(){
        return userId;
    }

    // toString()
    @Override
    public String toString() {
        return String.format("Завдання #%d: %s\nОпис: %s\nДедлайн: %s\nПріоритет: %d\nСтатус: %s\n",
                id, title, description, dueDate, priority, completed ? "виконано" : "не виконано");
    }
    public static class Builder{
        private int id;
        private String title;
        private String description;
        private LocalDate dueDate;
        private int priority;
        private boolean completed;
        private int userId;

        public Builder (){

        }
        public Builder id(int id){
            this.id = id;
            return this;
        }
        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder dueDate(LocalDate dueDate){
            this.dueDate = dueDate;
            return this;
        }
        public Builder priority(int priority){
            this.priority = priority;
            return this;
        }
        public Builder completed(boolean completed){
            this.completed = completed;
            return this;
        }
        public Builder userId(int userId){
            this.userId = userId;
            return this;
        }

        public Task build(){
            if(title == null || dueDate == null){
                throw new NullPointerException("Missing required arguments Task model");
            }
            return new Task(this);
        }
    }

}
