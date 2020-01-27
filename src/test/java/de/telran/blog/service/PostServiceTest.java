package de.telran.blog.service;

import de.telran.blog.dto.PostDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PostServiceTest {

    @Autowired
    PostService service;

    @Test
    void getPostsByKeyWords() {
        PostDto dto = new PostDto();
        dto.setId(1L);
        dto.setBody("Angular");
        dto.setTitle("Lesson");
        List<PostDto> list = service.getPostsByKeyWords("Angular");
        assertEquals(dto.getBody(),list.get(0).getBody());
        assertEquals(dto.getId(),list.get(0).getId());
        assertEquals(dto.getTitle(),list.get(0).getTitle());

        list = service.getPostsByKeyWords("Java&Script");
        dto.setId(2L);
        dto.setTitle("Script");
        dto.setBody("Java");
        assertEquals(dto.getBody(),list.get(0).getBody());
        assertEquals(dto.getId(),list.get(0).getId());
        assertEquals(dto.getTitle(),list.get(0).getTitle());

        list = service.getPostsByKeyWords("Java/Thema");
        PostDto dto1 = new PostDto();
        dto1.setBody("Tree");
        dto1.setTitle("Thema");
        dto1.setId(3L);
        assertEquals(dto.getBody(),list.get(0).getBody());
        assertEquals(dto.getId(),list.get(0).getId());
        assertEquals(dto.getTitle(),list.get(0).getTitle());
        assertEquals(dto1.getBody(),list.get(1).getBody());
        assertEquals(dto1.getId(),list.get(1).getId());
        assertEquals(dto1.getTitle(),list.get(1).getTitle());


    }
}
