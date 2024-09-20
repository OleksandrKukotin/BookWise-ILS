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
            .addValue(USERNAME_PARAM, user.getUsername())
            .addValue("password", passwordEncoder.encode(user.getPassword()))
            .addValue("enabled", true)
            .addValue("authority", "ROLE_USER");
        String query = "insert into users(username, password, enabled) values(:username, :password, :enabled);" +
            "insert into authorities(username, authority) values(:username, :authority);";
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
    public User findByUsername(String username) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(USERNAME_PARAM, username);
        String sql = "select * from users where username = :username;";
        return jdbcTemplate.queryForObject(sql, parameterSource, mapper);
    }

    @Override
    public void toggleUser(String username) {
        User user = findByUsername(username);
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue(USERNAME_PARAM, username)
            .addValue("enabled", !user.isEnabled());
        jdbcTemplate.update("update users set enabled = :enabled where username = :username", parameterSource);
    }
}
