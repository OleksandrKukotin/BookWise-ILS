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
    private String matchingPassword;

    public UserDTO(String username, String password, String matchingPassword) {
        this.username = username;
        this.password = password;
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


    public String getMatchingPassword() {
        return matchingPassword;
    }

}
