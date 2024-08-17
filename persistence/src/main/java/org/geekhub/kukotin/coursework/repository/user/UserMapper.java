package org.geekhub.kukotin.coursework.repository.user;

import org.geekhub.kukotin.coursework.service.user.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
            rs.getString("username"),
            rs.getString("password"),
            rs.getBoolean("enabled")
        );
    }
}
