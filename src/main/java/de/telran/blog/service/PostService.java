package de.telran.blog.service;

import de.telran.blog.entity.PostEntity;
import de.telran.blog.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

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
}
