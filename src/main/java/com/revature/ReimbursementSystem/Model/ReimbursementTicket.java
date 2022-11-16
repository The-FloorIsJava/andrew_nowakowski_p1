package com.revature.ReimbursementSystem.Model;

public class ReimbursementTicket {
    private int id;
    private User user;
    private double amount;
    private String description;
    private TicketStatus status;
    private ReimbursementType type;

    public ReimbursementTicket() {}

    public ReimbursementTicket(int id, User user, double amount, String description, ReimbursementType type) {
        this.id = id;
        this.user = user;
        this.amount = amount;
        this.description = description;
        this.status = TicketStatus.Pending;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public void setType(ReimbursementType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public ReimbursementType getType() {
        return type;
    }
}
