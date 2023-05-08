package com.rest_api.fs14backend.repositories;

import com.rest_api.fs14backend.entities.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    @EntityGraph(attributePaths = "books")
    List<Author> findAll();
}
