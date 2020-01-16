package de.telran.blog.controller;

import de.telran.blog.dto.PostDto;
import de.telran.blog.service.IPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Slf4j
public class PostRestController {

    private IPostService postService;

    public PostRestController(IPostService postService) {
        this.postService = postService;
    }

    /*
    request    http://localhost:8080/api/posts?page=0&size=2&sortDir=asc&sort=id
    or         http://localhost:8080/api/posts    with default parameters
     */
    @GetMapping()
    public Page<PostDto> loadPostsPage(
            @PageableDefault(page = 0, size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "title", direction = Sort.Direction.DESC)
            })
                    Pageable pageable) {
        return postService.findAllPage(pageable);
    }

    // request  http://localhost:8080/api/posts/currentAuthor?page=0&size=2&sortDir=asc&sort=id
    @GetMapping("/currentAuthor")
    public List<PostDto> getPostsCustomAuthor(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortDir") String sortDir,
            @RequestParam("sort") String sort) {
        return postService.getPostsListByAuthor(page, size, sortDir, sort);
    }

    @GetMapping(value = "/{id}")
    public PostDto getPost(@PathVariable("id") Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@RequestBody PostDto postDto){
        return postService.createNewPost(postDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@RequestBody PostDto postDto){
        postService.updatePost(postDto);
    }
}
