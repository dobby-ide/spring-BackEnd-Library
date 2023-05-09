package com.rest_api.fs14backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rest_api.fs14backend.entities.Author;
import com.rest_api.fs14backend.entities.Book;
import com.rest_api.fs14backend.entities.Category;
import com.rest_api.fs14backend.entities.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class BookDTO {
    private UUID id;
    private String ISBN;
    @NotNull
    @NotBlank
    private String title;
    private String description;
    private Integer quantity;
    private LocalDate publishedDate;
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;
    @JsonIgnoreProperties("books")
    private Set<Author> authors;
    @JsonIgnoreProperties("books")
    private Set<User> users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id")
    @JsonIgnoreProperties(value = {"books"})
    private UserDTO borrower;

    public void setBorrower(UserDTO borrower) {
        this.borrower = borrower;
    }
}
