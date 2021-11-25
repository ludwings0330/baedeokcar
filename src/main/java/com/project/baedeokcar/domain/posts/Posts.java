package com.project.baedeokcar.domain.posts;

import com.project.baedeokcar.domain.BaseTimeEntity;
import com.project.baedeokcar.web.dto.PostsListResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Posts extends BaseTimeEntity {

//    @Id @GeneratedValue
//    private Long no;
    @Id @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private String writer;
    private String pwd;

    private int viewCount;

    @Builder
    public Posts(String title, String content, String writer, String pwd) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.pwd = pwd;
        this.viewCount = 0;
    }
}
