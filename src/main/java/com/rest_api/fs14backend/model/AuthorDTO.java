package com.rest_api.fs14backend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest_api.fs14backend.entities.Book;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
@Data
@Builder
public class AuthorDTO {
    private UUID id;
    private String authorName;
    private LocalDate dateOfBirth;
    private Set<Book> books;
}
