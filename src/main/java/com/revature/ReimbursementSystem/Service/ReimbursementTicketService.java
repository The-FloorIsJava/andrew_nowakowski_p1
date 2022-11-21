package com.revature.ReimbursementSystem.Service;

import com.revature.ReimbursementSystem.DAO.ReimbursementTicketDAO;
import com.revature.ReimbursementSystem.Model.ReimbursementTicket;
import com.revature.ReimbursementSystem.Model.ReimbursementType;
import com.revature.ReimbursementSystem.Model.User;
import com.revature.ReimbursementSystem.Util.DTO.ReimbursementTicketAction;

import java.util.List;

public class ReimbursementTicketService {

    private final ReimbursementTicketDAO reimbursementTicketDAO;
    private int ticketCount = 0;

    public ReimbursementTicketService(ReimbursementTicketDAO reimbursementTicketDAO) {
        this.reimbursementTicketDAO = reimbursementTicketDAO;
        this.initializeTicketCount();
    }

    public ReimbursementTicket submitTicket(ReimbursementTicket ticket, User user) {
        ticket.setUser(user);
        ticket.setId(this.ticketCount++);
        return this.reimbursementTicketDAO.insert(ticket);
    }

    private void initializeTicketCount() {
        this.ticketCount = this.reimbursementTicketDAO.getRowCount();
        this.ticketCount++;
        System.out.println(this.ticketCount);
    }

    public List<ReimbursementTicket> getPendingTickets() {
        return this.reimbursementTicketDAO.getPendingTickets();
    }

    public ReimbursementTicketAction processTicketAction(ReimbursementTicketAction action) {
        return this.reimbursementTicketDAO.updateTicket(action);
    }

    public List<ReimbursementTicket> getPreviousTicketsForUser(User user) {
        return this.reimbursementTicketDAO.getPreviousTicketsForUser(user);
    }

    public List<ReimbursementTicket> getTypeOfTicketsForUser(User user, ReimbursementType type) {
        return this.reimbursementTicketDAO.getTypeOfTicketsForUser(user, type);
    }
}
