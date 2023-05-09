package com.rest_api.fs14backend.services;

import com.rest_api.fs14backend.entities.Book;
import com.rest_api.fs14backend.entities.User;
import com.rest_api.fs14backend.exceptions.BookNotFoundException;
import com.rest_api.fs14backend.mappers.BookMapper;
import com.rest_api.fs14backend.mappers.UserMapper;
import com.rest_api.fs14backend.model.BookDTO;
import com.rest_api.fs14backend.model.UserDTO;
import com.rest_api.fs14backend.repositories.BookRepository;
import com.rest_api.fs14backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class UserServiceJPA implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Transactional
    public void borrowBook(UUID userId, UUID bookId) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        if(book.getQuantity() <= 0){
            throw new BookNotFoundException("the book is not available");
        }
        book.setQuantity(book.getQuantity()-1);
        book.setBorrower(user);
        bookRepository.save(book);
        user.addBook(book);
        user.getBooks().add(book);
        userRepository.save(user);

        BookDTO bookDTO = bookMapper.bookToBookDto(book);
        bookDTO.setBorrower(userMapper.userToUserDto(user));

        bookRepository.save(bookMapper.bookDtoToBook(bookDTO));
    }
    @Override
    public List<UserDTO> listUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(UUID id) {

        User user = userRepository.findById(id).orElseThrow();
        System.out.println(user.getName());


        return Optional.ofNullable(userMapper.userToUserDto(userRepository.findById(id).orElse(null)));
    }

    @Override
    public UserDTO saveNewUser(UserDTO user) {

        return userMapper.userToUserDto(userRepository.save(userMapper.userDtoToUser(user)));
    }

    @Override
    public Optional<UserDTO> updateUserById(UUID userId, UserDTO user) {
        AtomicReference<Optional<UserDTO>> ref = new AtomicReference<>();
        userRepository.findById(userId).ifPresentOrElse(foundUser -> {
            foundUser.setName(user.getName());
            foundUser.setEmail(user.getEmail());
            foundUser.setPassword(user.getPassword());
            ref.set(Optional.of(userMapper.userToUserDto(userRepository.save(foundUser))));
        },()->{
            ref.set(Optional.empty());
        });
        return ref.get();
    }

    @Override
    public Boolean deleteById(UUID userId) {
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public void patchUserById(UUID userId, UserDTO user) {

    }
}
