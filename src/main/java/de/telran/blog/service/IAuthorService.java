package de.telran.blog.service;
import de.telran.blog.dto.AuthorDto;
import de.telran.blog.entity.AuthorEntity;


public interface IAuthorService {

    AuthorEntity getCurrentAuthor();

    AuthorDto getAuthorEntityById(Long id);

}
