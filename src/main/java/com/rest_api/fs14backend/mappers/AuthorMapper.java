package com.rest_api.fs14backend.mappers;

import com.rest_api.fs14backend.entities.Author;
import com.rest_api.fs14backend.model.AuthorDTO;
import org.mapstruct.Mapper;

/**
 * results of all mappers can be seen in generated target classes
 */
@Mapper
public interface AuthorMapper {

    Author authorDtoToAuthor(AuthorDTO dto);
    AuthorDTO authorToAuthorDto(Author author);
}
