package org.geekhub.kukotin.coursework.service.passwordreset;

import org.geekhub.kukotin.coursework.service.user.User;

import java.time.Instant;

public class PasswordResetToken {

    private Long id;
    private String username;
    private String token;
    private Instant expiryDate;

    public PasswordResetToken(Long id, String username, String token, Instant expiryDate) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.expiryDate = expiryDate;
    }
}
