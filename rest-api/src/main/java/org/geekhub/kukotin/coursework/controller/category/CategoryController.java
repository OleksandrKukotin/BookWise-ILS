package org.geekhub.kukotin.coursework.controller.category;

import org.geekhub.kukotin.coursework.service.entities.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addCategory(@RequestBody CategoryDTO categoryDTO) {
        service.create(CategoryConverter.fromDto(categoryDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping()
    public void updateCategory(@RequestBody CategoryDTO categoryDTO) {
        service.update(CategoryConverter.fromDto(categoryDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void removeCategory(@PathVariable int id) {
        CategoryDTO dto = new CategoryDTO(id, "");
        service.remove(CategoryConverter.fromDto(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(CategoryConverter.toDtos(service.getAll()));
    }
}
