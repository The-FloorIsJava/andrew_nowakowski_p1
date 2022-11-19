package com.revature.ReimbursementSystem.Util.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.revature.ReimbursementSystem.Model.TicketStatus;

public class ReimbursementTicketAction {

    @JsonAlias(value = {"id",})
    private int ticketId;
    @JsonAlias(value = {"status",})
    private TicketStatus action;
    public ReimbursementTicketAction() {}

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public TicketStatus getAction() {
        return action;
    }

    public void setAction(TicketStatus action) {
        this.action = action;
    }
}
