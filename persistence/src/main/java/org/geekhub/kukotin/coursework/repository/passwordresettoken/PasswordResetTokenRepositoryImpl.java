package org.geekhub.kukotin.coursework.repository.passwordresettoken;

import org.geekhub.kukotin.coursework.service.passwordreset.PasswordResetToken;
import org.geekhub.kukotin.coursework.service.passwordreset.PasswordResetTokenRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PasswordResetTokenRepositoryImpl implements PasswordResetTokenRepository {

    NamedParameterJdbcTemplate jdbcTemplate;
    PasswordResetTokenMapper mapper;

    public PasswordResetTokenRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, PasswordResetTokenMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public Optional<PasswordResetToken> findByToken(String token) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("token", token);
        String sql = "select * from password_reset_tokens where token = :token";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, parameterSource, mapper));
    }
}
