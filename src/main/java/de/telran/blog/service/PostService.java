package de.telran.blog.service;

import de.telran.blog.dto.PostDto;
import de.telran.blog.entity.PostEntity;
import de.telran.blog.repository.AuthorRepository;
import de.telran.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostService(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

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

    public List<PostDto> getPostsByKeyWords(String keyWords){
        List<PostDto> result = new ArrayList<>();
        for(PostEntity post: postRepository.findAll()){
            if(post.getTitle().toLowerCase().contains(keyWords) || post.getBody().toLowerCase().contains(keyWords)){
                result.add(new PostDto(post));
            }
        }
        return result;
    }
}
