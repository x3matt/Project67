package de.telran.blog.entity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Table(name="author")
@Entity
@Data
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorEntity")
    private Set<PostEntity> postEntities;
}
