package org.geekhub.kukotin.coursework.webcontroller.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotEmpty(message = "Username can't be empty")
    @Size(min = 5, max = 255)
    private String username;
    private String email;
    private String password;
    private String matchingPassword;

    public UserDTO(String username, String email, String password, String matchingPassword) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.matchingPassword = matchingPassword;
    }

    public UserDTO() {
    }

    public @NotEmpty(message = "Username can't be empty") @Size(min = 5, max = 255) String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "Username can't be empty") @Size(min = 5, max = 255) String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
