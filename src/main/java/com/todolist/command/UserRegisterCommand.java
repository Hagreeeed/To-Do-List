package com.todolist.command;

import com.todolist.service.UserService;
import com.todolist.dto.UserRegisterRequest;

public class UserRegisterCommand implements Command<Boolean> {
    private final UserService userService;
    private UserRegisterRequest request;

    public UserRegisterCommand(UserService userService, UserRegisterRequest request) {
        this.userService = userService;
        this.request = request;
    }

    @Override
    public Boolean execute(){

        if(userService.registerUser(request.getUsername(), request.getPassword())){
            return true;
        }
        return false;
    }
}


