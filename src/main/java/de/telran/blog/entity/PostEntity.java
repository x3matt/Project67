package de.telran.blog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "post")
@Data
@Entity
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "body", length = 4096)
    private String body;

    @Column(name = "date")
    private Date date;

    @ManyToOne(targetEntity = AuthorEntity.class)
    @JoinColumn(name="author_id", nullable=false)
    private AuthorEntity authorEntity;
}
