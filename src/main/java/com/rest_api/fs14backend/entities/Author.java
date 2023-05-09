package com.rest_api.fs14backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy="org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(
            length = 36,
            columnDefinition = "varchar(36)",
            updatable = false,
            nullable = false
    )
    private UUID id;
    private String authorName;
    private LocalDate dateOfBirth;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("authors")
    private Set<Book> books;

    public Author(String authorName, LocalDate dateOfBirth) {
        this.authorName = authorName;
        this.dateOfBirth = dateOfBirth;
        this.books = new HashSet<>();
    }

    public void addBook(Book book) {
        if (this.books == null) {
            this.books = new HashSet<>();
        }
        if (book.getAuthors() == null) {
            book.setAuthors(new HashSet<>());
        }
        this.books.add(book);
        book.getAuthors().add(this);
    }
}
