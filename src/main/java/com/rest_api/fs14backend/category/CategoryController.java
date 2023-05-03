package com.rest_api.fs14backend.category;

import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BookService bookService;

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

    //PERFORMS AN OPERATION TO MATCH A BOOK WITH A CATEGORY (BY IDs)

    @PostMapping("/{categoryId}/books/{bookId}")
    public ResponseEntity<Category> addBookToCategory(@PathVariable UUID categoryId, @PathVariable UUID bookId) {
        System.out.println("addBookToCategory in controller is called");
        Category category = categoryService.findById(categoryId);
        categoryService.addBookToCategory(categoryId,bookId);
        return ResponseEntity.ok().body(category);
    }

}
