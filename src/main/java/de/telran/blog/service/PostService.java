package de.telran.blog.service;

import de.telran.blog.dto.PostDto;
import de.telran.blog.entity.PostEntity;
import de.telran.blog.repository.AuthorRepository;
import de.telran.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService{

    private final PostRepository postRepository;
    private AuthorRepository authorRepository;

    public PostService(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    //--------------------------- old Functions -----------------------------

    public Long createPost(PostDto postDto) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setBody(postDto.getBody());
        postEntity.setDate(postDto.getDate());
        postEntity.setAuthorEntity(authorRepository.getOne(1L)); // TODO just for cresting first post
        return postRepository.save(postEntity).getId();
    }

    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostDto::new)
                .collect(Collectors.toList());
    }

    public PostDto getPost(Long id) {
        return new PostDto(postRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Post with id=" + id + "not found")
                ));
    }

    //--------------------------- New Functions -----------------------------

    @Autowired
    private IAuthorService authorService;

    @Override
    public Page<PostDto> findAllPage(Pageable pageable) {

        Page<PostEntity> posts = postRepository.findAllPage(pageable);
        return posts.map(this::convertToDto);
    }

    @Override
    public List<PostDto> getPostsListByAuthor( int page, int size, String sortDir, String sort) {

        PageRequest pageReq
                = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);

        Page<PostEntity> posts = postRepository.findByAuthorEntity(authorService.getCurrentAuthor(), pageReq);
        List<PostEntity> content = posts.getContent();
        return content.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createNewPost(PostDto postDto) {
        PostEntity post = convertToEntity(postDto);
        postRepository.save(post);
        return convertToDto(post);
    }

    @Override
    public PostDto getPostById(Long id) {
        return convertToDto(postRepository.getOne(id));
    }

    @Override
    public void updatePost(PostDto postDto) {
        postRepository.save(convertToEntity(postDto));
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
