package com.rest_api.fs14backend.services;

import com.rest_api.fs14backend.model.AuthorDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorService {

    List<AuthorDTO> listAuthors();

    Optional<AuthorDTO> getAuthorById(UUID id);

    AuthorDTO saveNewAuthor(AuthorDTO Author);

    Optional<AuthorDTO> updateAuthorById(UUID authorId, AuthorDTO author);

    Boolean deleteById(UUID authorId);

    void patchAuthorById(UUID authorId, AuthorDTO author);
}
