package com.rest_api.fs14backend.controllers;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import com.rest_api.fs14backend.model.CategoryDTO;
import com.rest_api.fs14backend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public List<CategoryDTO> listCategories(){

        return categoryService.listCategory();
    }

    @GetMapping("/{categoryId}")
    public CategoryDTO getCategoryById(@PathVariable("categoryId") UUID id){
        return categoryService.getCategoryById(id).orElseThrow();
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity updateById(@PathVariable("categoryId") UUID categoryId, @RequestBody CategoryDTO category){
        if(categoryService.updateCategoryById(categoryId, category).isEmpty()){
            throw new NotFoundException("the category could not be found, so update was not possible");
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteById(@PathVariable("categoryId") UUID categoryId) {
        if(!categoryService.deleteById(categoryId)){
            throw new NotFoundException("category not found");
        };
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @PostMapping
    public ResponseEntity handlePost(@ModelAttribute CategoryDTO category) {
        CategoryDTO savedCategory = categoryService.saveNewCategory(category);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/categories" + savedCategory.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

}
