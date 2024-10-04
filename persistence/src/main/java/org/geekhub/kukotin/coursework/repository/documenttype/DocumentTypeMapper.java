package org.geekhub.kukotin.coursework.repository.documenttype;

import org.geekhub.kukotin.coursework.service.entities.documenttype.DocumentType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DocumentTypeMapper implements RowMapper<DocumentType> {

    @Override
    public DocumentType mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DocumentType(rs.getInt("type_id"), rs.getString("type_name"));
    }
}
