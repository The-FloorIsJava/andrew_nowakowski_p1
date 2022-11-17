package com.revature.ReimbursementSystem.Model;

public class User {
    private String username;
    private String password;
    private Position position;

    public  User() {
        this.position = Position.Employee;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.position = Position.Employee;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isValidPassword(String password) {
        if (password == null) return false;

        return this.password != null && this.password.equals(password);
    }

    public boolean isValidPassword(User user) {
        if (user == null) return false;

        if (user.getPassword() == null) return false;

        return this.password != null && this.password.equals(user.getPassword());
    }

    public boolean isValidUser() {
        return (this.username != null) & (this.password != null);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", position=" + position +
                '}';
    }
}
