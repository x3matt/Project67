package de.telran.blog.controller;

import de.telran.blog.dto.PostDto;
import de.telran.blog.exception.RegexException;
import de.telran.blog.service.PostService;
import de.telran.blog.entity.PostEntity;
import de.telran.blog.service.IAuthorService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/post")
@Slf4j
public class PostController {

    private IPostService postService;

    private IAuthorService authorService;

    public PostController(IPostService postService, IAuthorService authorService) {
        this.postService = postService;
        this.authorService = authorService;
    }

    /*
        request    http://localhost:8080/api/post/pages?page=0&size=2&sortDir=asc&sort=id
        or         http://localhost:8080/api/post/pages    with default parameters
         */
    @GetMapping("/pages")
    public Page<PostDto> loadPostsPage(
            @PageableDefault(page = 0, size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "title", direction = Sort.Direction.DESC)
            })
                    Pageable pageable) {

        Page<PostEntity> posts = postService.findAllPage(pageable);
        return posts.map(this::convertToDto);
    }

    // request  http://localhost:8080/api/post/currentAuthor?page=0&size=2&sortDir=asc&sort=id
    @GetMapping("/currentAuthor")
    public List<PostDto> getPostsCustomAuthor(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortDir") String sortDir,
            @RequestParam("sort") String sort) {
        List<PostEntity> posts = postService.getPostsListByAuthor(page, size, sortDir, sort);

        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public PostDto getPostById(@PathVariable("id") Long id) {
        return convertToDto(postService.getPost(id));
    }

    @GetMapping("/get")
    public List<PostDto> getPostsByKeyWords(@RequestParam(value = "keywords", required = false) String keyWords) throws RegexException {
        return postService.getPostsByKeyWords(keyWords.toLowerCase());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createNewPost(@RequestBody PostDto postDto){
        PostEntity post = convertToEntity(postDto);
        PostEntity postCreated = postService.createPost(post);
        return convertToDto(postCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@RequestBody PostDto postDto){
        PostEntity post = convertToEntity(postDto);
        postService.updatePost(post);
    }

    @GetMapping()
    public List<PostDto> getAllPosts() {
        List<PostEntity> posts = postService.getAllPosts();
        return posts
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PostDto convertToDto(PostEntity post) {
        PostDto postDto = new PostDto();
        postDto.setBody(post.getBody());
        postDto.setTitle(post.getTitle());
        postDto.setAuthorId(post.getAuthorEntity().getId());
        postDto.setDate(post.getDate());
        postDto.setId(post.getId());
        return postDto;
    }

    private PostEntity convertToEntity(PostDto postDto){
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setBody(postDto.getBody());
        postEntity.setDate(postDto.getDate());
        postEntity.setAuthorEntity(authorService.getCurrentAuthor());
        return postEntity;
    }
}
