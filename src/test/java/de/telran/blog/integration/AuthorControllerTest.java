package de.telran.blog.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AuthorControllerTest {

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void createNewAuthor() throws URISyntaxException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        RequestEntity<String> requestEntity =  new RequestEntity<>(
                "{ \"firstName\": \"Vasya\", \"lastName\": \"Pupkin\" }",
                httpHeaders,
                HttpMethod.POST,
                new URI("http://localhost:8080/api/author")
        );

        ResponseEntity<String> responseEntity  = restTemplate.exchange(
                requestEntity,
                String.class
        );

        assertEquals("1", responseEntity.getBody());
    }

    @Test
    void getAllAuthors() {
    }
}
