package org.geekhub.kukotin.coursework.repository.publisher;

import org.geekhub.kukotin.coursework.service.entities.publisher.Publisher;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PublisherMapper implements RowMapper<Publisher> {

    @Override
    public Publisher mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Publisher(
            rs.getInt("publisher_id"),
            rs.getString("publisher_name")
        );
    }
}
