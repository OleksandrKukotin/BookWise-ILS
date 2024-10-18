package org.geekhub.kukotin.coursework.repository.user;

import org.geekhub.kukotin.coursework.service.user.User;
import org.geekhub.kukotin.coursework.service.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    public static final String USERNAME_PARAM = "username";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    public UserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder,
                              UserMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public void save(User user) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue(USERNAME_PARAM, user.getUsername())
            .addValue("email", user.getEmail())
            .addValue("password", passwordEncoder.encode(user.getPassword()))
            .addValue("enabled", true)
            .addValue("authority", "ROLE_USER");
        String query = "insert into users(username, email, password, enabled) " +
            "values(:username, :email, :password, :enabled);";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void deleteByUsername(String username) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(USERNAME_PARAM, username);
        String sql = "delete from authorities where username = :username;" +
            "delete from users where username = :username;";
        jdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", mapper);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(USERNAME_PARAM, username);
        String sql = "select * from users where username = :username;";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, parameterSource, mapper));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("email", email);
        String sql = "select * from users where email = :email;";
        try {

            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, parameterSource, mapper));
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void toggleUser(String username) {
        Optional<User> user = findByUsername(username);
        if (user.isPresent()) {
            SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(USERNAME_PARAM, username)
                .addValue("enabled", !user.get().isEnabled());
            jdbcTemplate.update("update users set enabled = :enabled where username = :username", parameterSource);
        }
    }

    @Override
    public void updatePassword(String username, String newPassword) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue(USERNAME_PARAM, username)
            .addValue("password", passwordEncoder.encode(newPassword));
        String sql = "update users set password = :password where username = :username;";
        jdbcTemplate.update(sql, parameterSource);
    }
}
