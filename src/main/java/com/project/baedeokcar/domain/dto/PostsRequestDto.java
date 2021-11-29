package com.project.baedeokcar.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PostsRequestDto {
    private String title;
    private String content;
    private String writer;

    @Builder
    public PostsRequestDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
