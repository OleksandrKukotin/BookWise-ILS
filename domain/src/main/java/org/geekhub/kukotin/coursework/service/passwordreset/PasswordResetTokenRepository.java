package org.geekhub.kukotin.coursework.service.passwordreset;

import java.util.Optional;

public interface PasswordResetTokenRepository {

    Optional<PasswordResetToken> findByToken(String token);
}
