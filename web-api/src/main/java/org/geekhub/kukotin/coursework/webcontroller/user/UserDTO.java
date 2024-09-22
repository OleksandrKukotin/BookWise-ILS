package org.geekhub.kukotin.coursework.webcontroller.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserDTO {

    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String password;
    private String email;
    private String matchingPassword;

    public UserDTO(String username, String password, String email, String matchingPassword) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.matchingPassword = matchingPassword;
    }

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setUsername(@NotNull @NotEmpty String username) {
        this.username = username;
    }

    public void setPassword(@NotNull @NotEmpty String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
