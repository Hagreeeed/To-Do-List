package com.todolist.command;

import com.todolist.dto.UpdateUserPasswordRequest;
import com.todolist.service.UserService;

public class UpdateUserPasswordCommand implements Command<Boolean> {
    private final UserService userService;
    private UpdateUserPasswordRequest request;

    public UpdateUserPasswordCommand(UserService userService, UpdateUserPasswordRequest request) {
        this.userService = userService;
        this.request = request;
    }

    @Override
    public Boolean execute() {
        if (userService.updatePassword(request.getUsername(), request.getNewPassword(), request.getOldPassword())){
            return true;
        }
        return false;
    }
}
