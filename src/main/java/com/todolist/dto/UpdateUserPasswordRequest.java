package com.todolist.dto;

public class UpdateUserPasswordRequest {
    private String username;
    private String newPassword;
    private String oldPassword;

    public UpdateUserPasswordRequest(String username, String newPassword, String oldPassword) {
        this.username = username;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public String getUsername() {
        return username;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public String getOldPassword() {
        return oldPassword;
    }
}
