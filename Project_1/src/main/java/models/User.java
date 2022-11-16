package models;

public class User {
    private String username;
    private String password;
    private Position position;

    public  User() {}
    public User(String username, String password, Position position) {
        this.username = username;
        this.password = password;
        this.position = position;
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

    public boolean isValid() {
        return (this.username != null) & (this.position != null);
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
