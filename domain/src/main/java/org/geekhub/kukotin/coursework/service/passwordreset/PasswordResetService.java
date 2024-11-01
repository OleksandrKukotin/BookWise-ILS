package org.geekhub.kukotin.coursework.service.passwordreset;

import org.geekhub.kukotin.coursework.service.exceptions.PasswordResetException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordResetService {

    private final PasswordResetTokenRepository tokenRepository;

    public PasswordResetService(PasswordResetTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Optional<PasswordResetToken> getByToken(String token) {
        try {
            return tokenRepository.findByToken(token);
        } catch (EmptyResultDataAccessException e) {
            String errorMessage = "Token not found " + token;
            throw new PasswordResetException(errorMessage, e);
        }
    }

    public void createToken(String token, String username) {
        tokenRepository.save(token, username);
    }

    public boolean isTokenExpired(String token) {
        return tokenRepository.isTokenExpired(token);
    }

    public void deleteToken(String token) {
        tokenRepository.delete(token);
    }
}
