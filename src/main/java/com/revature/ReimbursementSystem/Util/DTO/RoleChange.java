package com.revature.ReimbursementSystem.Util.DTO;

import com.revature.ReimbursementSystem.Model.Position;

public class RoleChange {
    String username;
    Position role;

    public RoleChange() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Position getRole() {
        return role;
    }

    public void setRole(Position role) {
        this.role = role;
    }
}
