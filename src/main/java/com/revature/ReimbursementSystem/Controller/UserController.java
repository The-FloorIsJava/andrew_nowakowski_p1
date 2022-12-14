package com.revature.ReimbursementSystem.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ReimbursementSystem.Model.User;
import com.revature.ReimbursementSystem.Service.UserService;
import com.revature.ReimbursementSystem.Util.DTO.LoginCredentials;
import com.revature.ReimbursementSystem.Util.DTO.RoleChange;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param app Javalin app passed to establish endpoint.
     */
    public void startAPI(Javalin app) {
        app.post("login", this::postLoginHandler);
        app.post("register", this::postRegisterHandler);
        app.delete("logout", this::deleteLogoutHandler);
        app.post("change_roles", this::postChangeRolesHandler);
    }

    private void postChangeRolesHandler(Context context) throws JsonProcessingException {
        if (this.userService.isNotAnAdmin()) {
            context.json("You are not authorized to view this page.");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        RoleChange action = mapper.readValue(context.body(), RoleChange.class);
        if (this.userService.changeRole(action) == null) context.json("Failed to change role.");
        else context.json(String.format("user %s has successfully changed their role to %s", action.getUsername(), action.getRole().toString()));

    }

    private void deleteLogoutHandler(Context context) {
        User sessionUser = this.userService.getSessionUser();
        if (sessionUser == null) {
            context.json("You cannot log out if you are not logged in.");
            return;
        }

        String username = sessionUser.getUsername();
        this.userService.logout();
        context.json(String.format("%s has logged out successfully.", username));
    }

    private void postRegisterHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(context.body(), User.class);

        if (!user.isValidUser()) {
            context.json("Invalid input.");
            return;
        }

        if (this.userService.registerUser(user) == null) context.json(String.format("Failed to register %s. Please try a different username.", user.getUsername()));
        else context.json(String.format("%s has successfully registered. Please log in.", user.getUsername()));
    }

    private void postLoginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LoginCredentials loginCredentials = mapper.readValue(context.body(), LoginCredentials.class);
        if (this.userService.loginUser(loginCredentials) == null) context.json(String.format("%s has failed to login.", loginCredentials.getUsername()));
        else context.json(String.format("%s has logged in successfully.", loginCredentials.getUsername()));
    }
}
