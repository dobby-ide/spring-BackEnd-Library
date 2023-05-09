package com.rest_api.fs14backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
//dev learning path: what is the @DATA annotation

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(
            length = 36,
            columnDefinition = "varchar(36)",
            updatable = false,
            nullable = false
    )
    private UUID id;
    private String ISBN;
    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(length = 50)
    private String title;
    private String description;
    private Integer quantity;
    private LocalDate publishedDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id", referencedColumnName = "id")
    @JsonBackReference
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_user",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();



    public void addAuthor(Author author) {
        if (this.authors == null) {
            this.authors = new HashSet<>();
        }
        if (author.getBooks() == null) {
            author.setBooks(new HashSet<>());
        }
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void setBorrower(User user) {
        if (user != null) {
            if(this.users == null){
                this.users = new HashSet<>();
            this.users.add(user);
            user.getBooks().add(this);
            this.quantity -= 1;
        }}
    }
}


