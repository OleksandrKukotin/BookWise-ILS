package org.geekhub.kukotin.coursework.repository.user;

import org.geekhub.kukotin.coursework.service.user.AuthorityRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthorityRepositoryImpl implements AuthorityRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AuthorityRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addAuthority(String username, String authority) {
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("username", username)
            .addValue("authority", authority);
        String sql = "INSERT INTO authorities (username, authority) VALUES (:username, :authority)";
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void removeAuthority(String username, String authority) {
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("username", username)
            .addValue("authority", authority);
        String sql = "DELETE FROM authorities WHERE username = :username AND authority = :authority";
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void changeAuthority(String username, String newAuthority) {
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("username", username)
            .addValue("authority", newAuthority);
        String sql = "UPDATE authorities SET authority = :authority WHERE username = :username";
        jdbcTemplate.update(sql, params);
    }

    @Override
    public String getAuthority(String username) {
        MapSqlParameterSource params = new MapSqlParameterSource("username", username);
        String sql = "SELECT authority FROM authorities WHERE username = :username";
        return jdbcTemplate.queryForObject(sql, params, String.class);
    }

    @Override
    public boolean isUserHaveAuthority(String username, String authority) {
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("username", username)
            .addValue("authority", authority);
        String sql = "SELECT authority FROM authorities WHERE username = :username AND authority = :authority";
        Optional<String> actualAuthority = Optional.ofNullable(jdbcTemplate.queryForObject(sql, params, String.class));
        return actualAuthority.isPresent();
    }
}
