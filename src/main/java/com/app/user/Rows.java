package com.app.user;

import lombok.Data;

import java.util.List;

@Data
public class Rows {
    private List<Users> users;

    public List<Users> getUsers() {
        return users;
    }

    public void setUser(List<Users> user) {
        this.users = users;
    }
}
