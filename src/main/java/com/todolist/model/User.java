package com.todolist.model;

public class User {
    private int id;
    private String username;
    private String password;

    public User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
    }

    //Getter
    public int getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

    public User withoutPassword(){
        return new Builder()
                .id(this.id)
                .username(this.username)
                .build();
    }

    @Override
    public String toString(){
        return "User{id=" + id + ", username='" + username + "'}";
    }

    public static class Builder {
        private int id;
        private String username;
        private String password;

        public Builder() {
            // Порожній конструктор
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            if (username == null) {
                throw new NullPointerException("Username cannot be null.");
            }
            return new User(this);
        }
    }
}