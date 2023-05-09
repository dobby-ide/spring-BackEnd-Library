package com.rest_api.fs14backend.bootstrap;

import com.rest_api.fs14backend.entities.Author;
import com.rest_api.fs14backend.entities.Book;
import com.rest_api.fs14backend.entities.Category;
import com.rest_api.fs14backend.entities.User;
import com.rest_api.fs14backend.repositories.AuthorRepository;
import com.rest_api.fs14backend.repositories.BookRepository;
import com.rest_api.fs14backend.repositories.CategoryRepository;
import com.rest_api.fs14backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Order(1)
public class BootStrapData implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        if(bookRepository.count()==0){
            try{
                loadBookData();
            }catch(DataIntegrityViolationException e){
                System.out.println("error " + e.getMessage());
            }
        }
        if(userRepository.count()==0){
            try{
                loadUserData();
            } catch(DataIntegrityViolationException e){
                System.out.println("exception on user BootData " + e.getMessage());
            }
        }


    }
    private void loadUserData() {
        if(userRepository.count()==0){
        User user1 = User.builder()
                .name("Mark Zucchina")
                .email("mark_zucchina@email.com")
                .password("112233")
                .build();

        User user2 = User.builder()
                .name("Maria Callas")
                .email("maria_callina@email.com")
                .password("112234")
                .build();

        userRepository.saveAll(Arrays.asList(user1,user2));

        }
    }

    private void loadBookData() {


            Category cat1 = Category.builder()
                    .categoryName("science")
                    .build();

            Category cat3 = Category.builder()
                    .categoryName("biographic")
                    .build();
            Category cat4 = Category.builder()
                    .categoryName("fiction")
                    .build();
            Category cat5 = Category.builder()
                    .categoryName("novel")
                    .build();
            Category cat6 = Category.builder()
                    .categoryName("sport")
                    .build();
            Category cat2 = Category.builder()
                    .categoryName("computer science")
                    .build();
            categoryRepository.save(cat2);
            categoryRepository.save(cat1);

            categoryRepository.save(cat3);
            categoryRepository.save(cat4);
            categoryRepository.save(cat5);
            categoryRepository.save(cat6);


        if(bookRepository.count()==0){


        Book book1 = Book.builder()
                .title("Java for Dummies")
                .category(cat2)
                .description("easy to understand or not for beginner java programmers")
                .publishedDate(LocalDate.of(2015,5,5))
                .quantity(3)
                .build();

        Book book2 = Book.builder()
                .title("Javascript for Dummies")
                .category(cat2)
                .description("easy to understand or not for beginner javascript programmers")
                .publishedDate(LocalDate.of(2012,12,3))
                .quantity(6)
                .build();

        Author author1 = Author.builder()
                    .authorName("Math Matteoli")
                    .dateOfBirth(LocalDate.of(2000, 4, 16))
                    .build();

        Author author2 = Author.builder()
                    .authorName("Zladn Ibrahimovich")
                    .dateOfBirth(LocalDate.of(1980, 12, 31))
                    .build();

        authorRepository.saveAll(Arrays.asList(author1, author2));

        book1.addAuthor(author1);
        book2.addAuthor(author2);

            User user3 = User.builder()
                    .name("Fabietto Marainnologo")
                    .email("fabietto_marterilogoa@email.com")
                    .password("11226634")
                    .build();
            user3.addBook(book1);
            userRepository.save(user3);


        bookRepository.saveAll(Arrays.asList(book1,book2));

        }
    }
}
