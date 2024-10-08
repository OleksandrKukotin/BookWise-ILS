package org.geekhub.kukotin.coursework.service.passwordreset;

import java.time.Instant;

public class PasswordResetToken {

    private Long id;
    private String email;
    private String token;
    private Instant expiryDate;

    public PasswordResetToken(Long id, String email, String token, Instant expiryDate) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public String getEmail() {
        return email;
    }
}
