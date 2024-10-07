package org.geekhub.kukotin.coursework.repository.passwordresettoken;

import org.geekhub.kukotin.coursework.service.passwordreset.PasswordResetToken;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PasswordResetTokenMapper implements RowMapper<PasswordResetToken> {

    @Override
    public PasswordResetToken mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PasswordResetToken(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("token"),
            rs.getTimestamp("expiry_date").toInstant()
            );
    }
}
