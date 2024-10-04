package org.geekhub.kukotin.coursework.repository.publisher;

import org.geekhub.kukotin.coursework.service.entities.publisher.Publisher;
import org.geekhub.kukotin.coursework.service.entities.publisher.PublisherRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final PublisherMapper mapper;

    public PublisherRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, PublisherMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public void save(Publisher publisher) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(
            "publisherName", publisher.publisherName());
        String query = "insert into publishers(publisher_name) values (:publisherName)";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public List<Publisher> findAll() {
        return jdbcTemplate.query("select * from publishers", mapper);
    }

    @Override
    public void update(Publisher publisher) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("publisherId", publisher.publisherId())
            .addValue("newName", publisher.publisherName());
        String query = " update publishers set publisher_name = :newName where publisher_id = :publisherId";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void delete(Publisher publisher) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(
            "publisherId", publisher.publisherId());
        jdbcTemplate.update("delete from publishers where publisher_id = :publisherId", parameterSource);
    }
}
