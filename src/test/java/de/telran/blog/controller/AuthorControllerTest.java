package de.telran.blog.controller;

import de.telran.blog.dto.AuthorDto;
import de.telran.blog.entity.AuthorEntity;
import de.telran.blog.repository.AuthorRepository;
import de.telran.blog.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthorControllerTest {


    @Test
    void createNewAuthor() {
        AuthorService service = Mockito.mock(AuthorService.class);
        when(service.createAuthor(any())).thenReturn(1L);
        AuthorController controller = new AuthorController(service);
        AuthorDto authorDto = new AuthorDto();
        authorDto.setFirstName("Vasya");
        authorDto.setLastName("Pupkin");
        controller.createNewAuthor(authorDto);
        Long result = controller.createNewAuthor(authorDto);
        assertEquals(1,result);
    }

    @Test
    void getAllAuthors() {
    }
}
