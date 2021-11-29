package com.project.baedeokcar.domain.posts;

import com.project.baedeokcar.domain.BaseTimeEntity;
import com.project.baedeokcar.domain.dto.PostsDTO;
import lombok.*;

import javax.persistence.*;

@Entity
// ModelMapper 사용을 위해
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
