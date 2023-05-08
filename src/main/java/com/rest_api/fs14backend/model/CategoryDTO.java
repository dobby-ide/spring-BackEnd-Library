package com.rest_api.fs14backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rest_api.fs14backend.entities.Book;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
@Builder
public class CategoryDTO {
    private UUID id;
    private String categoryName;
    @JsonManagedReference
    @OneToMany(mappedBy = "category",  fetch = FetchType.EAGER)
    private List<Book> books;

}
