package de.telran.blog.service;

import de.telran.blog.entity.PostEntity;
import de.telran.blog.dto.PostDto;
import de.telran.blog.exception.RegexException;
import de.telran.blog.repository.AuthorRepository;
import de.telran.blog.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService{

    private final PostRepository postRepository;
    private IAuthorService authorService;

    public PostService(PostRepository postRepository, IAuthorService authorService) {
        this.postRepository = postRepository;
        this.authorService = authorService;
    }

    @Override
    public Page<PostEntity> findAllPage(Pageable pageable) {

       return postRepository.findAllPage(pageable);
    }

    @Override
    public List<PostEntity> getPostsListByAuthor( int page, int size, String sortDir, String sort) {

        PageRequest pageReq
                = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);

        Page<PostEntity> posts = postRepository.findByAuthorEntity(authorService.getCurrentAuthor(), pageReq);
        return posts.getContent();
    }

    @Override
    public PostEntity createPost(PostEntity post) {
        return postRepository.save(post);
    }

    @Override
    public PostEntity getPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Post with id=" + id + "not found")
        );
    }

    @Override
    public void updatePost(PostEntity post) {
        postRepository.save(post);
    }

    @Override
    public List<PostEntity> getAllPosts() {
        return postRepository.findAll();
    }

    public List<PostDto> getPostsByKeyWords(String keyWord){
        keyWord = keyWord.toLowerCase();
        if(keyWord.contains("&") && keyWord.contains("/")) throw new RegexException("You can`t use two regexes in one string");
        List<PostDto> result;
        if(keyWord.contains("/")){
            result = createPostDtoListByKeywords(keyWord, "/");
            return result;
        }
        if(keyWord.contains("&")){
            result = createPostDtoListByKeywords(keyWord,"&");
        }
        else{
            result = createPostDtoListWithoutKeywords(keyWord);
        }
        return result;
    }

    public List<PostDto> createPostDtoListByKeywords(String keyword,String regex){
        List<PostDto> result = new ArrayList<>();
        String[] keyWords = keyword.split(regex);
        for(PostEntity post: postRepository.findAll()){
            if(containsKeyWords(keyWords,regex,post)) result.add(new PostDto(post));
        }
        return result;
    }
    public List<PostDto> createPostDtoListWithoutKeywords(String keyword){
        List<PostDto> result = new ArrayList<>();
        for(PostEntity post: postRepository.findAll()){
            if(
                    post.getTitle().toLowerCase().contains(keyword) ||
                            post.getBody().toLowerCase().contains(keyword))
                result.add(new PostDto(post));
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
