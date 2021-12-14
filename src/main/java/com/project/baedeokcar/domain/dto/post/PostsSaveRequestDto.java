package com.project.baedeokcar.domain.dto.post;

import com.project.baedeokcar.domain.posts.Posts;
import lombok.*;

@Data
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String writer;
    private String pwd;

    @Builder
    public PostsSaveRequestDto(String title, String content, String writer, String pwd) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.pwd = pwd;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .pwd(pwd)
                .build();
    }
}
