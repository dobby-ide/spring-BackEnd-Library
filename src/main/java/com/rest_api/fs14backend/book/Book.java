package com.rest_api.fs14backend.book;

import jakarta.persistence.*;

import java.util.UUID;
//dev learning path: what is the @DATA annotation

@Entity(name = "books")
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false,unique = true, columnDefinition = "varchar(17)")
    private String ISBN;
    @Column(nullable = false,columnDefinition = "varchar(100)")
    private String title;

    public Book() {
    }

    public Book(String ISBN, String title) {
        this.ISBN = ISBN;
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
