package org.geekhub.kukotin.coursework.repository.author;

import org.geekhub.kukotin.coursework.service.entities.author.Author;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Author(
            rs.getInt("author_id"),
            rs.getString("author_name"),
            rs.getString("author_surname")
        );
    }
}
