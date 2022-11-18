package com.revature.ReimbursementSystem;

import com.revature.ReimbursementSystem.Controller.ReimbursementTicketController;
import com.revature.ReimbursementSystem.Controller.UserController;
import com.revature.ReimbursementSystem.DAO.ReimbursementTicketDAO;
import com.revature.ReimbursementSystem.DAO.UserDAO;
import com.revature.ReimbursementSystem.Service.ReimbursementTicketService;
import com.revature.ReimbursementSystem.Service.UserService;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        ReimbursementTicketDAO reimbursementTicketDAO = new ReimbursementTicketDAO();
        UserService userService = new UserService(userDAO);
        ReimbursementTicketService reimbursementTicketService = new ReimbursementTicketService(reimbursementTicketDAO);

        UserController userController = new UserController(userService);
        ReimbursementTicketController reimbursementTicketController = new ReimbursementTicketController(reimbursementTicketService, userService);

        Javalin app = Javalin.create().start(8080);
        userController.startAPI(app);
        reimbursementTicketController.startAPI(app);

    }
}