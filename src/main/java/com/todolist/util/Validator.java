package com.todolist.util;

public class Validator {

    public boolean validPassword(String password) {
        if (password.length() <= 8) {
            return false;
        }

        boolean hasUpperCase = false;
        boolean hasSpecialChar = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            }

            if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }

            if (hasUpperCase && hasSpecialChar) {
                break;
            }
        }

        return hasUpperCase && hasSpecialChar;
    }
}