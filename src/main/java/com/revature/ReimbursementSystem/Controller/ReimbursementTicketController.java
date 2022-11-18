package com.revature.ReimbursementSystem.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ReimbursementSystem.Model.ReimbursementTicket;
import com.revature.ReimbursementSystem.Model.User;
import com.revature.ReimbursementSystem.Service.ReimbursementTicketService;
import com.revature.ReimbursementSystem.Service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class ReimbursementTicketController {

    private final ReimbursementTicketService reimbursementTicketService;
    private final UserService userService;

    public ReimbursementTicketController(ReimbursementTicketService reimbursementTicketService, UserService userService) {
        this.reimbursementTicketService = reimbursementTicketService;
        this.userService = userService;
    }
    
    public void startAPI(Javalin app) {
        app.post("ticket/submit", this::postSubmitTicketHandler);
    }

    private void postSubmitTicketHandler(Context context) throws JsonProcessingException {
        if (this.userService.getSessionUser() == null) {
            context.json("You must be logged in to submit a ticket.");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        ReimbursementTicket ticket = mapper.readValue(context.body(), ReimbursementTicket.class);
        User user = this.userService.getSessionUser();
        if (this.reimbursementTicketService.submitTicket(ticket, user) == null) context.json("Failed to create ticket.");
        else context.json(String.format("Successfully created ticket number #%d. Please remember this number.", ticket.getId()));
    }
}
