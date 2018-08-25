package com.pccw.assessment.model;

import javax.validation.constraints.NotNull;

public class CreateUserRequest {
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String username;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
