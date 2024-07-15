package org.geekhub.kukotin.coursework.repository.document;

import org.geekhub.kukotin.coursework.service.document.Document;
import org.geekhub.kukotin.coursework.service.document.DocumentRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DocumentMapper mapper;

    public DocumentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, DocumentMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public void save(Document document) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("docTitle", document.documentTitle())
            .addValue("availableCopies", document.availableCopies())
            .addValue("lastUpdateTime", Timestamp.from(document.lastUpdatedTime()))
            .addValue("updatedBy", document.updatedBy())
            .addValue("publisherId", document.publisherId())
            .addValue("informationId", document.informationId());
        String query = """
            insert into documents(document_title, available_copies, last_update_time,
            updated_by, publisher_id, information_id)
            values (:docTitle, :availableCopies, :lastUpdateTime, :updatedBy, :publisherId, :informationId)
            """;
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void updateDocument(Document updatedDocument) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("docId", updatedDocument.documentId())
            .addValue("docTitle", updatedDocument.documentTitle())
            .addValue("availableCopies", updatedDocument.availableCopies())
            .addValue("now", Timestamp.from(Instant.now()))
            .addValue("updatedBy", updatedDocument.updatedBy());
        String query = """
            update documents
            set document_title = :docTitle, available_copies = :availableCopies,
            last_update_time = :now, updated_by = current_timestamp
            where document_id = :docId
            """;
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void removeDocument(Document documentToRemove) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(
            "docId", documentToRemove.documentId()
        );
        String query = "delete from documents where document_id = :docId";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public List<Document> findAllDocuments() {
        String query = "select * from documents";
        return jdbcTemplate.query(query, mapper);
    }
}
