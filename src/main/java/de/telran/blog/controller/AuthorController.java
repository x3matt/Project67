package de.telran.blog.controller;

import de.telran.blog.dto.AuthorDto;
import de.telran.blog.dto.PostDto;
import de.telran.blog.entity.AuthorEntity;
import de.telran.blog.entity.PostEntity;
import de.telran.blog.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/author")
@Slf4j
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public Long createNewAuthor(@RequestBody AuthorDto author){
        log.info(
                "Request received. First name = {}, Last name = {}",
                author.getFirstName(),
                author.getLastName()
        );
        AuthorEntity authorEntity = convertToEntity(author);
        return authorService.createAuthor(authorEntity);
    }

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        List<AuthorEntity> authors = authorService.getAllAuthors();
        return authors.
                stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AuthorDto convertToDto(AuthorEntity authorEntity) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setFirstName(authorEntity.getFirstName());
        authorDto.setLastName(authorEntity.getLastName());
        authorDto.setId(authorEntity.getId());
        return authorDto;
    }

    private AuthorEntity convertToEntity(AuthorDto authorDto){
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setFirstName(authorDto.getFirstName());
        authorEntity.setLastName(authorDto.getLastName());
        return authorEntity;
    }
}
