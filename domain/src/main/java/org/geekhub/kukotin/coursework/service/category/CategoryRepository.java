package org.geekhub.kukotin.coursework.service.category;

import java.util.List;

public interface CategoryRepository {

    void save(Category category);
    void delete(Category category);
    void update(Category category);
    List<Category> getAllCategories();
}
