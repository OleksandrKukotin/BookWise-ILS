package org.geekhub.kukotin.coursework.service.entities.category;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public void create(Category category) {
        repository.save(category);
    }

    public void remove(Category category) {
        repository.delete(category);
    }

    public void update(Category category) {
        repository.update(category);
    }

    public List<Category> getAll() {
        return repository.getAllCategories();
    }
}
