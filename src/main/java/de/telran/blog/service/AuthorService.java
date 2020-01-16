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


    public Long createAuthor(AuthorDto author) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setFirstName(author.getFirstName());
        authorEntity.setLastName(author.getLastName());
        return authorRepository.save(authorEntity).getId();
    }


    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorEntity -> {
                    AuthorDto authorDto =  new AuthorDto();
                    authorDto.setFirstName(authorEntity.getFirstName());
                    authorDto.setLastName(authorEntity.getLastName());
                    authorDto.setId(authorEntity.getId());
                    return authorDto;})
                .collect(Collectors.toList());
    }

    //--------------------------------------------------------------------------

    @Override
    public AuthorDto getAuthorEntityById(Long id) {
        return convertToDto(authorRepository.getOne(id));
    }

    @Override
    public AuthorEntity getCurrentAuthor() {

        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(1L);
        authorEntity.setFirstName("FirstName");
        authorEntity.setLastName("LastName");
        return authorEntity;
    }

    private AuthorDto convertToDto(AuthorEntity authorEntity) {
        AuthorDto authorDto = new AuthorDto();

        authorDto.setId(authorEntity.getId());
        authorDto.setFirstName(authorEntity.getFirstName());
        authorDto.setLastName(authorEntity.getLastName());

        return authorDto;
    }

}
