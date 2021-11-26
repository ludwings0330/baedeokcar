package com.project.baedeokcar.domain.dto;

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

}
