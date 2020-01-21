package de.telran.blog.service;

import de.telran.blog.dto.AuthorDto;
import de.telran.blog.dto.PostDto;
import de.telran.blog.entity.AuthorEntity;
import de.telran.blog.entity.PostEntity;
import de.telran.blog.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService implements IAuthorService{

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Long createAuthor(AuthorEntity author) {
       return authorRepository.save(author).getId();
    }

    @Override
    public List<AuthorEntity> getAllAuthors() {
      return authorRepository.findAll();
    }

    @Override
    public AuthorEntity getCurrentAuthor() {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(1L);
        authorEntity.setFirstName("FirstName");
        authorEntity.setLastName("LastName");
        return authorEntity;
    }
}
