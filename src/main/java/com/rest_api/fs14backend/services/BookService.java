package com.rest_api.fs14backend.services;

import com.rest_api.fs14backend.entities.Book;
import com.rest_api.fs14backend.model.BookDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    List<BookDTO> findBookByCategory(String category);

    List<BookDTO> listBooksWithCategory();

    List<BookDTO> listBooks(String bookName);

    Optional<BookDTO> getBookById(UUID id);

    BookDTO saveNewBook(Book book);

    BookDTO saveNewBook(BookDTO book);

    Optional<BookDTO> updateBookById(UUID bookId, BookDTO book);

    Boolean deleteById(UUID bookId);

    void patchBookById(UUID bookId, BookDTO book);

}
