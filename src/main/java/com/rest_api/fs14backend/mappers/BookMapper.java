package com.rest_api.fs14backend.mappers;

import com.rest_api.fs14backend.entities.Book;
import com.rest_api.fs14backend.entities.Category;
import com.rest_api.fs14backend.entities.User;
import com.rest_api.fs14backend.model.BookDTO;
import com.rest_api.fs14backend.model.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {

    Book bookDtoToBook(BookDTO dto);

    Category map(String value);

    BookDTO bookToBookDto(Book book);
}
