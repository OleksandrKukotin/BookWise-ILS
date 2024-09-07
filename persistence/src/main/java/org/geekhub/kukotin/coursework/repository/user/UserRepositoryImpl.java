package org.geekhub.kukotin.coursework.repository.user;

import org.geekhub.kukotin.coursework.service.user.User;
import org.geekhub.kukotin.coursework.service.user.UserRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    public static final String USERNAME_PARAM = "username";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    public UserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder,
                              UserMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public void save(User user) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue(USERNAME_PARAM, user.username())
            .addValue("password", user.password())
            .addValue("enabled", true);
        String query = "insert into users(username, password, enabled) values(:username, :password, :enabled)";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void deleteByUsername(String username) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(
            USERNAME_PARAM, username);
        jdbcTemplate.update("delete from users where username = :username", parameterSource);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", mapper);
    }

    @Override
    public User findByUsername(String username) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(USERNAME_PARAM, username);
        return jdbcTemplate.queryForObject("select from users where username = :username", parameterSource, mapper);
    }

    @Override
    public void update(String currentUsername, User newUserData) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("targetUsername", currentUsername)
            .addValue("newName", newUserData.username())
            .addValue("newPassword", passwordEncoder.encode(newUserData.password()))
            .addValue("newStatus", newUserData.enabled());
        String query = "update users set username=:newName, password=:newPassword, " +
            "enabled=:newStatus where username=:targetUsername";
        jdbcTemplate.update(query, parameterSource);
    }
}
