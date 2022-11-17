package com.revature.ReimbursementSystem.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ReimbursementSystem.Model.User;
import com.revature.ReimbursementSystem.Service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class UserController {
    UserService userService;
    public UserController() {
        userService = new UserService();
    }

    /**
     *
     * @param app Javalin app passed to establish endpoint.
     */
    public void startAPI(Javalin app) {
        app.get("/", this::helloHandler);
        app.get("users", this::getUserHandler);
        app.post("users", this::postUserHandler);
        app.post("login", this::postLoginHandler);
        app.post("register", this::postRegisterHandler);
    }

    private void postRegisterHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(context.body(), User.class);
        if (this.userService.registerUser(user)) context.json(user);
    }

    private void postLoginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(context.body(), User.class);
        if (this.userService.loginUser(user)) context.json(user);
    }

    private void postUserHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(context.body(), User.class);
        if (userService.addUser(user)) context.json(user);
    }

    private void getUserHandler(Context context) {
        List<User> users = userService.getAllUsers();
        context.json(users);
    }

    public void helloHandler(Context context) {
        context.result("Hello World!");
    }
}
