package com.rest_api.fs14backend.services;

import com.rest_api.fs14backend.entities.Book;
import com.rest_api.fs14backend.entities.User;
import com.rest_api.fs14backend.exceptions.BookNotFoundException;
import com.rest_api.fs14backend.mappers.BookMapper;
import com.rest_api.fs14backend.mappers.UserMapper;
import com.rest_api.fs14backend.model.BookDTO;
import com.rest_api.fs14backend.repositories.BookRepository;
import com.rest_api.fs14backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BookServiceJPA implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private final UserRepository userRepository;
    private final UserMapper userMapper;



    @Override
    public List<BookDTO> listBooksWithCategory() {
        List<Book> bookList;
        bookList = bookRepository.findAllWithCategory();
        return bookList.stream()
                .map(bookMapper::bookToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> listBooks(String bookTitle) {

        List<Book> bookList;

        if(StringUtils.hasText(bookTitle)){
            bookList = listBooksByName(bookTitle);
        } else{
            bookList= bookRepository.findAll();
        }

        return bookList
                .stream()
                .map(bookMapper::bookToBookDto)
                .collect(Collectors.toList());
    }

    public List<Book> listBooksByName(String title){

        return bookRepository.findAllByTitleIsLikeIgnoreCase("%"+title+"%");
    }

    @Override
    public Optional<BookDTO> getBookById(UUID id) {

        return Optional.ofNullable(bookMapper.bookToBookDto(bookRepository.findById(id).orElse(null)));
    }

    @Override
    public BookDTO saveNewBook(BookDTO book) {

        return bookMapper.bookToBookDto(bookRepository.save(bookMapper.bookDtoToBook(book)));
    }

    @Override
    public Optional<BookDTO> updateBookById(UUID bookId, BookDTO book) {

        AtomicReference<Optional<BookDTO>> ref = new AtomicReference<>();

        bookRepository.findById(bookId).ifPresentOrElse(foundBook ->{
            foundBook.setTitle(book.getTitle());
            foundBook.setDescription(book.getDescription());
            foundBook.setPublishedDate(book.getPublishedDate());
            ref.set(Optional.of(bookMapper
                    .bookToBookDto(bookRepository.save(foundBook))));

        }, () -> {
            ref.set(Optional.empty());
    });
        return ref.get();
    }

    @Override
    public Boolean deleteById(UUID bookId) {
        if(bookRepository.existsById(bookId)){
            bookRepository.deleteById(bookId);
            return true;
        }
        return false;
    }

    @Override
    public void patchBookById(UUID bookId, BookDTO book) {

    }
}