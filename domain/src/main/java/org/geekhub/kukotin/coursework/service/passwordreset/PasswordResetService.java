package org.geekhub.kukotin.coursework.service.passwordreset;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordResetService {

    private final PasswordResetTokenRepository tokenRepository;

    public PasswordResetService(PasswordResetTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Optional<PasswordResetToken> getByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
