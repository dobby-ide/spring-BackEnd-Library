package com.rest_api.fs14backend.controllers;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import com.rest_api.fs14backend.model.BookDTO;
import com.rest_api.fs14backend.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<BookDTO> listBooks(@RequestParam(required=false) String title){

        return bookService.listBooks(title);
    }

    @GetMapping("/category")
    public List<BookDTO> listBooksWithCategory(){

        return bookService.listBooksWithCategory();
    }

    @GetMapping("/{bookId}")
    public BookDTO getBookById(@PathVariable("bookId") UUID bookId){
        return bookService.getBookById(bookId).orElseThrow();
    }

    @PutMapping("/{bookId}")
    public ResponseEntity updateById(@PathVariable("bookId") UUID bookId, @RequestBody BookDTO book){
        if(bookService.updateBookById(bookId, book).isEmpty()){
            throw new NotFoundException("the book with id " + bookId + "could not be found, so update was not possible");
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @PostMapping
    public ResponseEntity handlePost(@RequestBody BookDTO book){
        BookDTO savedBook = bookService.saveNewBook(book);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/books/" + savedBook.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable UUID bookId) {
        if(!bookService.deleteById(bookId)){
            throw new NotFoundException("book not found");
        };
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
