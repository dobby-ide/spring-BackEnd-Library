package com.rest_api.fs14backend.model;

import com.rest_api.fs14backend.entities.Book;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private String password;

}
