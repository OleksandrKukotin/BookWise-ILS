package org.geekhub.kukotin.coursework.repository.passwordresettoken;

import org.geekhub.kukotin.coursework.service.passwordreset.PasswordResetToken;
import org.geekhub.kukotin.coursework.service.passwordreset.PasswordResetTokenRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Repository
public class PasswordResetTokenRepositoryImpl implements PasswordResetTokenRepository {

    private static final String TOKEN_PARAM = "token";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final PasswordResetTokenMapper mapper;

    public PasswordResetTokenRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, PasswordResetTokenMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public void save(String token, String username) {
        Timestamp expiryDate = Timestamp.from(Instant.now().plusSeconds(60L * 60 * 2));
        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(TOKEN_PARAM, token)
            .addValue("username", username)
            .addValue("expiryDate", expiryDate); // 2 hours
        String sql = """
            INSERT INTO password_reset_tokens (token, username, expiry_date)
            values (:token, :username, :expiryDate);
            """;
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(String username) {
        SqlParameterSource params = new MapSqlParameterSource("username", username);
        jdbcTemplate.update("DELETE FROM password_reset_tokens WHERE username = :username", params);
    }

    @Override
    public Optional<PasswordResetToken> findByToken(String token) throws EmptyResultDataAccessException {
        SqlParameterSource parameterSource = new MapSqlParameterSource(TOKEN_PARAM, token);
        String sql = "select * from password_reset_tokens where token = :token";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, parameterSource, mapper));
    }

    @Override
    public boolean isTokenExpired(String token) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(TOKEN_PARAM, token);
        String sql = "select expiry_date from password_reset_tokens where token = :token";
        Optional<Timestamp> expiryDate = Optional.ofNullable(jdbcTemplate.queryForObject(sql, parameterSource, Timestamp.class));
        return expiryDate.map(timestamp -> timestamp.before(Timestamp.from(Instant.now()))).orElse(true);
    }
}
