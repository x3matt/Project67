package de.telran.blog.service;

import de.telran.blog.dto.AuthorDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AuthorServiceTest {

    @Autowired
    AuthorService authorService;

    @Test
    void WhenFirstNameAndLastNameNotNulls_thenOK() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setFirstName("Vasya");
        authorDto.setLastName("Pupkin");
        Long result = authorService.createAuthor(authorDto);
        assertEquals(1, result);
    }

    @Test()
    void WhenFirstNameAndLastNameIsNulls_thenThrowException() {
        AuthorDto authorDto = new AuthorDto();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            authorService.createAuthor(authorDto);
        });
    }


    @Test
    void getAllAuthors() {
    }
}
