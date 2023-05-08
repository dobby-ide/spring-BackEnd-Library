package com.rest_api.fs14backend.controllers;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import com.rest_api.fs14backend.model.AuthorDTO;
import com.rest_api.fs14backend.model.BookDTO;
import com.rest_api.fs14backend.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<AuthorDTO> listAuthors(){
        return authorService.listAuthors();
    }

    @GetMapping("/{authorId}")
    public AuthorDTO getAuthorById(@PathVariable("authorId") UUID authorId){
        return authorService.getAuthorById(authorId).orElseThrow();
    }
    @PostMapping
    public ResponseEntity handlePost(@RequestBody AuthorDTO author){
       AuthorDTO savedAuthor = authorService.saveNewAuthor(author);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/authors/" + savedAuthor.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity updateById(@PathVariable("authorId") UUID authorId, @RequestBody AuthorDTO author){
        if(authorService.updateAuthorById(authorId, author).isEmpty()){
            throw new NotFoundException("Author not found");
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable UUID authorId){
        if(authorService.deleteById(authorId)){
            throw new NotFoundException("book not found");
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
