package de.telran.blog.dto;
import de.telran.blog.entity.PostEntity;
import lombok.*;

import java.util.Date;

@Data
public class PostDto {

    private Long id;
    private String title;
    private String body;
    private Date date;
    private Long authorId;

    public PostDto() {
    }

    public PostDto(PostEntity postEntity) {
        this.body = postEntity.getBody();
        this.id = postEntity.getId();
        this.title = postEntity.getTitle();
        this.date = postEntity.getDate();
    }

}
