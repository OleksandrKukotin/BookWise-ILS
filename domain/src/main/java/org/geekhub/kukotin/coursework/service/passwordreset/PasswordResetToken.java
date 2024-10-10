package org.geekhub.kukotin.coursework.service.passwordreset;

public class PasswordResetToken {

    private final String email;
    private final String token;

    public PasswordResetToken(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }
}
