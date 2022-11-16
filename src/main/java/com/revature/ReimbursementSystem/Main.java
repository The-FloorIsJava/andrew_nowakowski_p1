package com.revature.ReimbursementSystem;

import com.revature.ReimbursementSystem.Controller.UserController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();

        Javalin app = Javalin.create().start(8080);
        userController.startAPI(app);

    }
}