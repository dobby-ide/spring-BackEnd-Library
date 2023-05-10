package com.rest_api.fs14backend.services;

import com.rest_api.fs14backend.model.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserDTO> listUsers(String userName);

    Optional<UserDTO> getUserById(UUID id);

    UserDTO saveNewUser(UserDTO user);

    Optional<UserDTO> updateUserById(UUID userId, UserDTO user);

    Boolean deleteById(UUID userId);

    void patchUserById(UUID userId, UserDTO user);

    void borrowBook(UUID userId, UUID bookId);
}
