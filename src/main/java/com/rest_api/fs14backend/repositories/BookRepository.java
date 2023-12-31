package com.rest_api.fs14backend.repositories;

import com.rest_api.fs14backend.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findAllByTitleIsLikeIgnoreCase(String title);

    @Query("SELECT b FROM Book b JOIN FETCH b.category")
    List<Book> findAllWithCategory();

    List<Book> findByCategoryCategoryNameIsLikeIgnoreCase(String category);

}
