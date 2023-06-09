package com.rest_api.fs14backend.controllers;

import com.rest_api.fs14backend.entities.Author;
import com.rest_api.fs14backend.entities.Book;
import com.rest_api.fs14backend.entities.Category;
import com.rest_api.fs14backend.exceptions.NotFoundException;
import com.rest_api.fs14backend.mappers.AuthorMapper;
import com.rest_api.fs14backend.mappers.BookMapper;
import com.rest_api.fs14backend.model.AuthorDTO;
import com.rest_api.fs14backend.model.BookDTO;
import com.rest_api.fs14backend.services.AuthorService;
import com.rest_api.fs14backend.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    @Autowired(required = false)
    private Book book;
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/category/{categoryName}")
    public List<BookDTO> listBookByCategory(@PathVariable String categoryName){
        return bookService.findBookByCategory(categoryName);
    }

    @GetMapping
    @CrossOrigin(origins = "*")
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
    public ResponseEntity updateById(@PathVariable("bookId") UUID bookId, @ModelAttribute BookDTO book){
        if(bookService.updateBookById(bookId, book).isEmpty()){
            throw new NotFoundException("the book with id " + bookId + "could not be found, so update was not possible");
        }
        bookService.updateBookById(bookId,book);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity handlePost(@ModelAttribute("book") Book book){

        BookDTO savedBook = bookService.saveNewBook(book);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/books/" + savedBook.getId().toString());

        return new ResponseEntity(savedBook,headers,HttpStatus.CREATED);
    }
//    @PostMapping
//    public ResponseEntity handleBookPost(@RequestParam String title,
//                                         @RequestParam String ISBN,
//                                         @RequestParam UUID authorId,
//                                         @RequestParam(required = false) String description,
//                                         @RequestParam(required=false) Integer quantity){
//        AuthorDTO authorDto = authorService.getAuthorById(authorId).orElseThrow();
//        Book book = Book.builder()
//                .title(title)
//                .ISBN(ISBN)
//                .description(description)
//                .quantity(quantity != null ? quantity : 1)
//                .build();
//        Author author = authorMapper.authorDtoToAuthor(authorDto);
//        book.addAuthor(author);
//        BookDTO savedBook = bookService.saveNewBook(bookMapper.bookToBookDto(book));
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Location", "api/v1/books/" + savedBook.getId().toString());
//
//        return new ResponseEntity(HttpStatus.CREATED);
//    }


    @DeleteMapping("/{bookId}")
    public ResponseEntity deleteById(@PathVariable UUID bookId) {
        if(!bookService.deleteById(bookId)){
            throw new NotFoundException("book not found");
        };
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
