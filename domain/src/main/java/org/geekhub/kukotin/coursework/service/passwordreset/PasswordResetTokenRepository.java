package org.geekhub.kukotin.coursework.service.passwordreset;

import java.util.Optional;

public interface PasswordResetTokenRepository {

    void save(String token, String username);
    void delete(String token);
    Optional<PasswordResetToken> findByToken(String token);
    boolean isTokenExpired(String token);
}
