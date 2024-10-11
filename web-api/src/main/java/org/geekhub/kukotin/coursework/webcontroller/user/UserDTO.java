package org.geekhub.kukotin.coursework.webcontroller.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotEmpty(message = "Username can't be empty")
    @Size(min = 5, max = 255)
    private String username;
    @NotEmpty(message = "User's email can't be empty")
    private String email;
    @NotEmpty(message = "User's password can't be empty")
    @Size(min = 8)
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

    public @NotEmpty(message = "User's email can't be empty") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "User's email can't be empty") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "User's password can't be empty") @Size(min = 8) String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "User's password can't be empty") @Size(min = 8) String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
