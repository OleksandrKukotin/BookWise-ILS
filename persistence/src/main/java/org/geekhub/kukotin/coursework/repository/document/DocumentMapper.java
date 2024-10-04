package org.geekhub.kukotin.coursework.repository.document;

import org.geekhub.kukotin.coursework.service.entities.document.Document;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DocumentMapper implements RowMapper<Document> {

    @Override
    public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Document(
            rs.getInt("document_id"),
            rs.getString("document_title"),
            rs.getInt("available_copies"),
            rs.getTimestamp("last_updated_time").toInstant(),
            rs.getInt("updated_by"),
            rs.getInt("publisher_id"),
            rs.getInt("information_id")
        );
    }
}
