package com.jobapplication.example.jobapplication.security;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private List<Roles> roles = new ArrayList<>();

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
