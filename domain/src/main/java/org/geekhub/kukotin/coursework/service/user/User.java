package org.geekhub.kukotin.coursework.service.user;

public class User {

    private String username;
    private String email;
    private String role;
    private String password;
    private boolean enabled;

    public User(String username, String email, String role, String password, boolean enabled) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
