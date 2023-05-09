package com.rest_api.fs14backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest_api.fs14backend.entities.Book;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category", uniqueConstraints = {@UniqueConstraint(columnNames = {"category_name"})})
public class Category {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "category_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String categoryName;
    @OneToMany(mappedBy = "category",  fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Book> booksInCategory = new ArrayList<>();

    public Category(String categoryName){
        this.categoryName = categoryName;
        this.booksInCategory = new ArrayList<>();
    }

}



