package de.telran.blog.controller;

import de.telran.blog.dto.AuthorDto;
import de.telran.blog.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthorControllerUnitTest {

    @Test
    void createNewAuthor() {
        AuthorService authorService = Mockito.mock(AuthorService.class);

        when(authorService.createAuthor(any())).thenReturn(1L);

        AuthorController authorController = new AuthorController(authorService);

        AuthorDto authorDto = new AuthorDto();
        authorDto.setFirstName("Vasya");
        authorDto.setLastName("Pupkin");

        Long result = authorController.createNewAuthor(authorDto);

        assertEquals(1, result);
    }

    @Test
    void getAllAuthors() {
    }
}
