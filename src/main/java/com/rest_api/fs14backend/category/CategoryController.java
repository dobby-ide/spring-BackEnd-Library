package com.rest_api.fs14backend.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable UUID id){
        Category category = categoryService.findById(id);
        return category;
    }

    @PostMapping("/")
    public Category addOneCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable UUID id){
        categoryService.deleteById(id);
    }
}
