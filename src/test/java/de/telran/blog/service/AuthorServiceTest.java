package de.telran.blog.service;

import de.telran.blog.dto.AuthorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorServiceTest {

    @Autowired
    AuthorService service;

    @Test()
    void createAuthor() {
        AuthorDto authorDto1 = new AuthorDto();
        authorDto1.setLastName("Pupkin");
        authorDto1.setFirstName("Vasya");
        Long result1 =  service.createAuthor(authorDto1);
        assertEquals(1,result1);
        AuthorDto authorDto2 = new AuthorDto();
        authorDto2.setLastName("Pushkin");
        authorDto2.setFirstName("Sasha");
        Long result2 =  service.createAuthor(authorDto2);
        assertEquals(2,result2);
    }

    @Test
    void getAllAuthors() {
    }
}
