package de.telran.blog.dto;
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
}
