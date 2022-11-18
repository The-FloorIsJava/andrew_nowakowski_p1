package com.revature.ReimbursementSystem.Service;

import com.revature.ReimbursementSystem.DAO.ReimbursementTicketDAO;
import com.revature.ReimbursementSystem.Model.ReimbursementTicket;
import com.revature.ReimbursementSystem.Model.User;

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
}
