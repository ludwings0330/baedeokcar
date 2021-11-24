package com.project.baedeokcar.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostsRepositoryTest {

    private final PostsRepository postsRepository;

    @Autowired
    public PostsRepositoryTest(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }


    @Test
    public void DB연동테스트() throws Exception {
        //given
        String title = "test Title";
        String content = "test content";
        String writer = "test writer";
        String pwd = "test pwd";

        Posts post = Posts.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .pwd(pwd)
                .build();

        //when
        postsRepository.save(post);
        List<Posts> all = postsRepository.findAll();
        Posts findPost = all.get(0);
        //then
        System.out.println("findPost.toString() = " + findPost.toString());
        Assertions.assertThat(findPost.getTitle()).isEqualTo(title);
    }
}