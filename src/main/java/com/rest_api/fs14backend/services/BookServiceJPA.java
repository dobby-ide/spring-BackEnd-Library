package com.rest_api.fs14backend.services;

import com.rest_api.fs14backend.entities.Book;
import com.rest_api.fs14backend.mappers.BookMapper;
import com.rest_api.fs14backend.model.BookDTO;
import com.rest_api.fs14backend.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@AllArgsConstructor
public class BookServiceJPA implements BookService {


    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDTO> findBookByCategory(String category) {
        return bookRepository.findByCategoryCategoryNameIsLikeIgnoreCase(category)
                .stream()
                .map(bookMapper::bookToBookDto)
                .collect(Collectors.toList());
    }

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
    public BookDTO saveNewBook(Book book) {
        System.out.println("YOU ARE HERE---------------------------------------------");
        System.out.println(book.getId());
        System.out.println(book.getTitle());

        return bookMapper.bookToBookDto(bookRepository.save((book)));
    }

    @Override
    public BookDTO saveNewBook(BookDTO book) {
        Book savedBook = bookMapper.bookDtoToBook(book);
        return bookMapper.bookToBookDto(bookRepository.save((savedBook)));
    }

    @Override
    public Optional<BookDTO> updateBookById(UUID bookId, BookDTO book) {
//DEV TO-DO: adds some variables that holds the status ot the book in body request:
        //i.e. if(book.getAuthor) has already an author and so on so forth to check what has been sent
        AtomicReference<Optional<BookDTO>> ref = new AtomicReference<>();

        bookRepository.findById(bookId).ifPresentOrElse(foundBook ->{
            if(foundBook.getTitle()!=null){
                foundBook.setTitle(book.getTitle());
            }
            foundBook.setQuantity(book.getQuantity());
            foundBook.setAuthors(book.getAuthors());
            foundBook.setISBN(book.getISBN());
            foundBook.setQuantity(1);

            foundBook.setDescription(book.getDescription());
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
