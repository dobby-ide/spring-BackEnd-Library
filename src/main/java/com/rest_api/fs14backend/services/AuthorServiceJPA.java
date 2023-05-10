package com.rest_api.fs14backend.services;

import com.rest_api.fs14backend.entities.Author;
import com.rest_api.fs14backend.mappers.AuthorMapper;
import com.rest_api.fs14backend.model.AuthorDTO;
import com.rest_api.fs14backend.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary
public class AuthorServiceJPA implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    public List<AuthorDTO> listAuthors(String authorName) {

        List<Author> authorList;
        if(StringUtils.hasText(authorName)){
            authorList = listAuthorByName(authorName);
        } else {
            authorList = authorRepository.findAll();
        }

        return authorList
                .stream()
                .map(authorMapper::authorToAuthorDto)
                .collect(Collectors.toList());
    }

    private List<Author> listAuthorByName(String authorName) {

        return authorRepository.findAllByAuthorNameIsLikeIgnoreCase("%"+authorName+"%");
    }

    @Override
    public Optional<AuthorDTO> getAuthorById(UUID id) {

        return Optional.ofNullable(authorMapper.authorToAuthorDto(authorRepository.findById(id).orElse(null)));
    }

    @Override
    public AuthorDTO saveNewAuthor(AuthorDTO author) {

        return authorMapper.authorToAuthorDto(authorRepository.save(authorMapper.authorDtoToAuthor(author)));
    }

    @Override
    public Optional<AuthorDTO> updateAuthorById(UUID authorId, AuthorDTO author) {

        AtomicReference<Optional<AuthorDTO>> ref = new AtomicReference<>();

        authorRepository.findById(authorId).ifPresentOrElse(foundAuthor -> {
           foundAuthor.setAuthorName(author.getAuthorName());
           foundAuthor.setDateOfBirth(author.getDateOfBirth());
           ref.set(Optional.of(authorMapper.authorToAuthorDto(authorRepository.save(foundAuthor))));
        }, () -> {
            ref.set(Optional.empty());
        });

        return ref.get();
    }

    @Override
    public Boolean deleteById(UUID authorId) {
        if(authorRepository.existsById(authorId)){
            authorRepository.deleteById(authorId);
            return true;
        }
        return false;
    }

    @Override
    public void patchAuthorById(UUID authorId, AuthorDTO author) {

    }
}
