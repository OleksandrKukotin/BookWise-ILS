package org.geekhub.kukotin.coursework.repository.documenttype;

import org.geekhub.kukotin.coursework.service.entities.documenttype.DocumentType;
import org.geekhub.kukotin.coursework.service.entities.documenttype.DocumentTypeRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentTypeRepositoryImpl implements DocumentTypeRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DocumentTypeMapper mapper;

    public DocumentTypeRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, DocumentTypeMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public void addType(DocumentType dto) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("typeName", dto.getTypeName());
        String query = "insert into document_types(type_name) values (:typeName)";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void updateType(DocumentType dto) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("typeId", dto.getTypeId())
            .addValue("typeName", dto.getTypeName());
        String query = "update document_types set type_name = :typeName where type_id = :typeId";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void deleteType(DocumentType dto) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("typeId", dto.getTypeId());
        String query = "delete from document_types where type_id = :typeId";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public List<DocumentType> getAllTypes() {
        String query = "select * from document_types";
        return jdbcTemplate.query(query, mapper);
    }
}
