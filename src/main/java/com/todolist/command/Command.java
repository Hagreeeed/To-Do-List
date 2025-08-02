package com.todolist.command;


public interface Command<T> {
    T execute();
}
