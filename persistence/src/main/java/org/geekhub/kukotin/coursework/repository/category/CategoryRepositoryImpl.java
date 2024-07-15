package org.geekhub.kukotin.coursework.repository.category;

import org.geekhub.kukotin.coursework.service.category.Category;
import org.geekhub.kukotin.coursework.service.category.CategoryRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CategoryMapper mapper;

    public CategoryRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, CategoryMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public void save(Category category) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("categoryName", category.categoryName());
        String query = "insert into categories (category_name) values (:categoryName)";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void delete(Category category) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("categoryId", category.categoryId());
        String query = "delete from categories where category_id = :categoryId";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void update(Category category) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("categoryId", category.categoryId())
            .addValue("categoryNewName", category.categoryName());
        String query = "update categories set category_name = :categoryNewName where category_id = :categoryId";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query("select * from categories", mapper);
    }
}
