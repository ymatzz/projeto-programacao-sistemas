package br.com.matheusfernandes.web.service.controller;

import br.com.matheusfernandes.web.service.entity.Category;
import br.com.matheusfernandes.web.service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    List<Category> list() {
        return categoryService.list();
    }

    @GetMapping("/{id}")
    Category getCategory(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @PutMapping("/{id}")
    List<Category> update(@PathVariable("id") Long id, @RequestBody Category category) {
        category.setId(id);
        return categoryService.update(category);
    }

    @DeleteMapping("/{id}")
    List<Category> delete(@PathVariable("id") Long id) {
        return categoryService.delete(id);
    }
}
