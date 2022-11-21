package com.revature.ReimbursementSystem.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ReimbursementSystem.Model.ReimbursementTicket;
import com.revature.ReimbursementSystem.Model.ReimbursementType;
import com.revature.ReimbursementSystem.Model.User;
import com.revature.ReimbursementSystem.Service.ReimbursementTicketService;
import com.revature.ReimbursementSystem.Service.UserService;
import com.revature.ReimbursementSystem.Util.DTO.ReimbursementTicketAction;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class ReimbursementTicketController {

    private final ReimbursementTicketService reimbursementTicketService;
    private final UserService userService;

    public ReimbursementTicketController(ReimbursementTicketService reimbursementTicketService, UserService userService) {
        this.reimbursementTicketService = reimbursementTicketService;
        this.userService = userService;
    }
    
    public void startAPI(Javalin app) {
        app.post("ticket/submit", this::postSubmitTicketHandler);
        app.get("ticket/pending/all", this::getAllPendingTicketsHandler);
        app.post("ticket/pending/process", this::postProcessTicketHandler);
        app.get("ticket/history", this::getAllPreviousTicketsForUser);
        app.get("ticket/history/{type}", this::getTypeOfTicketsForUser);
    }

    private void getTypeOfTicketsForUser(Context context) {
        User user = this.userService.getSessionUser();
        if (user == null) {
            context.json("You must be logged in to view ticket history.");
            return;
        }

        ReimbursementType type = ReimbursementType.valueOf(context.pathParam("type"));

        List<ReimbursementTicket> tickets = this.reimbursementTicketService.getTypeOfTicketsForUser(user, type);

        if (tickets == null) {
            context.json("No previous tickets.");
            return;
        }

        context.json(tickets);
    }

    private void getAllPreviousTicketsForUser(Context context) {
        User user = this.userService.getSessionUser();
        if (user == null) {
            context.json("You must be logged in to view ticket history.");
            return;
        }

        List<ReimbursementTicket> tickets = this.reimbursementTicketService.getPreviousTicketsForUser(user);

        if (tickets == null) {
            context.json("No previous tickets.");
            return;
        }

        context.json(tickets);
    }

    private void postProcessTicketHandler(Context context) throws JsonProcessingException {
        if (this.userService.isNotAManager()) {
            context.json("You are not authorized to view this page.");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        ReimbursementTicketAction action = mapper.readValue(context.body(), ReimbursementTicketAction.class);
        if (this.reimbursementTicketService.processTicketAction(action) == null) context.json("Ticket failed to update.");
        else context.json(String.format("Ticket number %d was successfully processed.", action.getTicketId()));
    }

    private void getAllPendingTicketsHandler(Context context) {
        if (this.userService.isNotAManager()) {
            context.json("You are not authorized to view this page.");
            return;
        }

        List<ReimbursementTicket> tickets = this.reimbursementTicketService.getPendingTickets();

        if (tickets == null) {
            context.json("Nothing to show.");
            return;
        }

        context.json(tickets);
    }

    private void postSubmitTicketHandler(Context context) throws JsonProcessingException {
        User user = this.userService.getSessionUser();
        if (user == null) {
            context.json("You must be logged in to submit a ticket.");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        ReimbursementTicket ticket = mapper.readValue(context.body(), ReimbursementTicket.class);
        if (this.reimbursementTicketService.submitTicket(ticket, user) == null) context.json("Failed to create ticket.");
        else context.json(String.format("Successfully created ticket number #%d. Please remember this number.", ticket.getId()));
    }
}
