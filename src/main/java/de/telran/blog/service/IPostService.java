package de.telran.blog.service;
import de.telran.blog.dto.PostDto;
import de.telran.blog.entity.PostEntity;
import de.telran.blog.exception.RegexException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IPostService {

    Page<PostEntity> findAllPage(Pageable pageable);

    List<PostEntity> getPostsListByAuthor(int page, int size, String sortDir, String sort);

    PostEntity createPost(PostEntity post);

    PostEntity getPost(Long id);

    void updatePost(PostEntity post);

    List<PostEntity> getAllPosts();

    List<PostDto> getPostsByKeyWords(String toLowerCase) throws RegexException;

}
