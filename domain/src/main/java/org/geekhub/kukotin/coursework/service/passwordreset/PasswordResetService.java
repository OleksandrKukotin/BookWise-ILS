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

    public void createToken(String token, String username) {
        tokenRepository.save(token, username);
    }

    public boolean isTokenValid(String token) {
        return tokenRepository.isTokenExpired(token);
    }

    public void deleteToken(String token) {
        tokenRepository.delete(token);
    }
}
