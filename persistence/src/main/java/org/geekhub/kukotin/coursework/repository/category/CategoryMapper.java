package org.geekhub.kukotin.coursework.repository.category;

import org.geekhub.kukotin.coursework.service.category.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CategoryMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Category(rs.getInt("category_id"), rs.getString("category_name"));
    }
}
