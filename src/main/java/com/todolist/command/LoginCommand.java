package com.todolist.command;

import com.todolist.dto.LoginRequest;
import com.todolist.service.UserService;

public class LoginCommand implements Command<Boolean> {
    private final UserService userService;
    private LoginRequest loginRequest;

    public LoginCommand(UserService userService, LoginRequest loginRequest) {
        this.userService = userService;
        this.loginRequest = loginRequest;
    }

    @Override
    public Boolean execute() {
        if (userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword())) {
            return true;
        }
        return false;
    }
}
