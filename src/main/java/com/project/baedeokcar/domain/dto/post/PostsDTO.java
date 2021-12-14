package com.project.baedeokcar.domain.dto.post;

import com.project.baedeokcar.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostsDTO {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private String pwd;
    private int viewCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostsDTO(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.writer = posts.getWriter();
        this.pwd = posts.getPwd();
        this.viewCount = posts.getViewCount();
        this.createdDate = posts.getCreatedDate();
        this.modifiedDate = posts.getModifiedDate();

    }

}
