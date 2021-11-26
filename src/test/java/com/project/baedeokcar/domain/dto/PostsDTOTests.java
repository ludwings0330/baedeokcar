package com.project.baedeokcar.domain.dto;

import com.project.baedeokcar.domain.posts.Posts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostsDTOTests {

    @Autowired
    ModelMapper modelMapper;

   /* @Test
    public void modelMapperTest() {

        Posts posts = Posts.builder().id(101L).title("ModelMapper 테스트").content("Entity <-> DTO 매핑").writer("MM").pwd("1234").viewCount(0).build();

        result_1 = modelMapper.map(posts, PostsDTO.class);
        Posts result_2 = modelMapper.map(result_1, Posts.class);

        // Entity -> DTO 변환 확인
        Assertions.assertThat(result_1.getId()).isEqualTo(posts.getId());
        Assertions.assertThat(result_1.getTitle()).isEqualTo(posts.getTitle());
        Assertions.assertThat(result_1.getContent()).isEqualTo(posts.getContent());
        Assertions.assertThat(result_1.getWriter()).isEqualTo(posts.getWriter());
        Assertions.assertThat(result_1.getPwd()).isEqualTo(posts.getPwd());
        Assertions.assertThat(result_1.getViewCount()).isEqualTo(posts.getViewCount());

        // DTO -> Entity 변환 확인
        Assertions.assertThat(result_2.getId()).isEqualTo(posts.getId());
        Assertions.assertThat(result_2.getTitle()).isEqualTo(posts.getTitle());
        Assertions.assertThat(result_2.getContent()).isEqualTo(posts.getContent());
        Assertions.assertThat(result_2.getWriter()).isEqualTo(posts.getWriter());
        Assertions.assertThat(result_2.getPwd()).isEqualTo(posts.getPwd());
        Assertions.assertThat(result_2.getViewCount()).isEqualTo(posts.getViewCount());


    }
*/
}
