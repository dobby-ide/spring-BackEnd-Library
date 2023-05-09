package com.rest_api.fs14backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
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
    private String name;
    private String email;
    private String password;


    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book){
        if(book!=null) {
            if(books == null){
                books = new HashSet<>();
                books.add(book);
            }

        };
    }
}
