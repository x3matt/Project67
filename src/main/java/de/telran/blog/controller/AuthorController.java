package de.telran.blog.controller;

import de.telran.blog.dto.AuthorDto;
import de.telran.blog.dto.PostDto;
import de.telran.blog.service.AuthorService;
import de.telran.blog.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return authorService.createAuthor(author);
    }

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }
}
