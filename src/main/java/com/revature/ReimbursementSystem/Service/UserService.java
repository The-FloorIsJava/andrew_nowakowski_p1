package com.revature.ReimbursementSystem.Service;

import com.revature.ReimbursementSystem.Model.User;

import java.util.LinkedList;
import java.util.List;

public class UserService {

    List<User> users;

    public UserService() {
        this.users = new LinkedList<>();
    }

    public List<User> getAllUsers() {
        return this.users;
    }


    public boolean addUser(User user) {
        if (!user.isValidUser()) return false;

        this.users.add(user);
        return true;
    }

    public boolean loginUser(User user) {
        if (!user.isValidUser()) return false;

        for (User value : this.users) {
            if (user.getUsername().equals(value.getUsername())) return user.isValidPassword(value);
        }

        return false;
    }

    public boolean registerUser(User newUser) {
        if (!newUser.isValidUser()) return false;

        for (User user : this.users) {
            if (newUser.getUsername().equals(user.getUsername())) return false;
        }

        this.users.add(newUser);
        return true;
    }
}
