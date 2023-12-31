package com.rest_api.fs14backend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest_api.fs14backend.entities.Book;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class AuthorDTO {
    private UUID id;
    private String authorName;
    private LocalDate dateOfBirth;
    private Set<Book> books;

}
