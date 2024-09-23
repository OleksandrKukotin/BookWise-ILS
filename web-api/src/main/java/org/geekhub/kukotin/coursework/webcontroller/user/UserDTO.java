package org.geekhub.kukotin.coursework.webcontroller.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserDTO {

    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
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

    public @NotNull @NotEmpty String getUsername() {
        return username;
    }

    public void setUsername(@NotNull @NotEmpty String username) {
        this.username = username;
    }

    public @NotNull @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @NotEmpty String email) {
        this.email = email;
    }

    public @NotNull @NotEmpty String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @NotEmpty String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
