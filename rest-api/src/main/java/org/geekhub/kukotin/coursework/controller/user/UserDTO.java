package org.geekhub.kukotin.coursework.controller.user;

public class UserDTO {

    private String firstName;
    private String password;
    private String matchingPassword;

    public UserDTO(String firstName, String password, String matchingPassword) {
        this.firstName = firstName;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public UserDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
