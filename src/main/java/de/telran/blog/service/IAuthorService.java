package de.telran.blog.service;
import de.telran.blog.dto.AuthorDto;
import de.telran.blog.entity.AuthorEntity;

import java.util.List;


public interface IAuthorService {

    AuthorEntity getCurrentAuthor();

    Long createAuthor(AuthorEntity author);

    List<AuthorEntity> getAllAuthors();
}
