package com.rest_api.fs14backend.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Handles HTTP requests related to books.
 */
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> findAll(){
        return bookService.findAll();
    }
    @GetMapping("/{id}")
    public Book findById(@PathVariable UUID id){
        Book book = bookService.findById(id);
        return book;
    }

    @PostMapping("/")
    public Book addOneBook(@RequestBody Book book){
        return bookService.createOne(book);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable UUID id) {
        bookService.deleteById(id);
    }

}
