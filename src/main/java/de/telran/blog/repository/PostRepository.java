package de.telran.blog.repository;

import de.telran.blog.entity.AuthorEntity;
import de.telran.blog.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long>, PagingAndSortingRepository<PostEntity, Long>{

    @Query("select p from PostEntity p where p.authorEntity.id=:authorEntity_id")
    Page<PostEntity> findByAuthorEntity(Long authorEntity_id, Pageable pageReq);

    default Page<PostEntity> findByAuthorEntity(AuthorEntity authorEntity, Pageable pageReq) {
        return findByAuthorEntity(authorEntity.getId(), pageReq);
    }
    @Query("select p from PostEntity p")
    Page<PostEntity> findAllPage(Pageable pageable);
}
