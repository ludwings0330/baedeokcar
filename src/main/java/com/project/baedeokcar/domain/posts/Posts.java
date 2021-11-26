package com.project.baedeokcar.domain.posts;

import com.project.baedeokcar.domain.BaseTimeEntity;
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

    // 게시글 수정을 위한 메소드
    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeWriter(String writer) {
        this.writer = writer;
    }

    public void changePwd(String pwd) {
        this.pwd = pwd;
    }


}
