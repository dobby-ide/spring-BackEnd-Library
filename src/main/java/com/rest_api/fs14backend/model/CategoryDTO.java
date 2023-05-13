package com.rest_api.fs14backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rest_api.fs14backend.entities.Book;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;
@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
public class CategoryDTO {
    private UUID id;
    private String categoryName;
    private List<Book> booksInCategory;

    public CategoryDTO(){};
}
