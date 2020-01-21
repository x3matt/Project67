package de.telran.blog.service;

import de.telran.blog.dto.PostDto;
import de.telran.blog.entity.PostEntity;
import de.telran.blog.exception.RegexException;
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

    public List<PostDto> getPostsByKeyWords(String keyWord) throws RegexException {
        if(keyWord.contains("&") && keyWord.contains("/")) throw new RegexException("You can`t use two regexes in one string");
        List<PostDto> result = new ArrayList<>();
        String[] keyWords;
        if(keyWord.contains("/")){
            keyWords = keyWord.split("/");
            for(PostEntity post: postRepository.findAll()){
                if(containsKeyWords(keyWords,"/",post)) result.add(new PostDto(post));
            }
        }
        if(keyWord.contains("&")){
            keyWords = keyWord.split("&");
            for(PostEntity post: postRepository.findAll()){
                if(containsKeyWords(keyWords,"&",post)) result.add(new PostDto(post));
            }
        }
        else {
            for(PostEntity post: postRepository.findAll()){
                if(
                        post.getTitle().toLowerCase().contains(keyWord) ||
                                post.getBody().toLowerCase().contains(keyWord))
                    result.add(new PostDto(post));
            }
        }
        return result;
    }

    public boolean containsKeyWords(String[] keyWords,String regex,PostEntity entity){
        if(regex.equals("/")) {
            for (String keyWord : keyWords) {
                if (
                        entity.getTitle().toLowerCase().contains(keyWord) ||
                                entity.getBody().toLowerCase().contains(keyWord)) return true;
            }
        }
        if(regex.equals("&")) {
            List<Boolean> result = new ArrayList<>();
            for (String keyWord : keyWords) {
                result.add(
                        entity.getTitle().toLowerCase().contains(keyWord) ||
                                entity.getBody().toLowerCase().contains(keyWord));
            }
            return validate(result);
        }
        return false;
    }
    public boolean validate(List<Boolean> booleans) {
        for(Boolean b: booleans){
            if(!b) return false;
        }
        return true;
    }
}
