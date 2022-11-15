package controllers;

import Services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import models.User;

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
    }

    private void postUserHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper =new ObjectMapper();
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
