package de.telran.blog.controller;

import de.telran.blog.dto.PostDto;
import de.telran.blog.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@Slf4j
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Long createNewPost(@RequestBody PostDto postDto) {
        log.info("Request received. posdtDto={}", postDto);
        return postService.createPost(postDto);
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/get")
    public List<PostDto> getPostsByKeyWords(@RequestParam(value = "keywords", required = false) String keyWords){
        return postService.getPostsByKeyWords(keyWords.toLowerCase());
    }
}
