package com.project.baedeokcar.domain.dto.post;

import com.project.baedeokcar.domain.posts.Posts;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
public class
PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDate createDate;
    private int viewCount;


    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
        this.createDate = entity.getCreatedDate().toLocalDate();
        this.viewCount = entity.getViewCount();
    }
}
