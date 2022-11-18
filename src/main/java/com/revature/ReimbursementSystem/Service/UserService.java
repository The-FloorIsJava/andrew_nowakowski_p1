package com.revature.ReimbursementSystem.Service;

import com.revature.ReimbursementSystem.DAO.UserDAO;
import com.revature.ReimbursementSystem.Model.User;
import com.revature.ReimbursementSystem.Util.DTO.LoginCredentials;

public class UserService {

    private final UserDAO userDAO;
    private User currentSessionUser = null;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User loginUser(LoginCredentials loginCredentials) {
        if (this.currentSessionUser != null) return null;
        this.currentSessionUser = this.userDAO.loginCheck(loginCredentials.getUsername(), loginCredentials.getPassword());
        return this.currentSessionUser;
    }

    public User registerUser(User newUser) {
        return this.userDAO.insert(newUser);
    }

    public User getSessionUser() {
        return this.currentSessionUser;
    }

    public void logout() {
        this.currentSessionUser = null;
    }
}
