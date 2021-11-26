package com.project.baedeokcar.domain.posts;

import com.project.baedeokcar.repository.PostsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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

    // 더미 데이터 생성
    // 등록 테스트
    @Test
    public void testInsertDummies() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            Posts posts = Posts.builder().title("테스트 " + i).content("더미 텍스트..." + i).writer("작성자 " + i).pwd("1234").build();

            postsRepository.save(posts);

        });

    }

    // 조회 테스트 (findById)
    // Optional 타입으로 반환되기 때문에 한번 더 결과가 존재하는지를 체크
    // findById를 실행한 순간에 이미 SQL 처리
    @Test
    public void testSelectFindById() {

        // DB에 존재하는 Id
        Long id = 100L;

        Optional<Posts> result = postsRepository.findById(id);

        System.out.println("==================================");

        // 결과 체크
        if (result.isPresent()) {

            Posts posts = result.get();

            System.out.println(posts);

        }

    }

    // 조회 테스트 (getById)
    // getById는 @Transactional 필요
    // 리턴 값은 해당 객체, 실제 객체가 필요한 순간까지 SQL 실행 X
    @Transactional
    @Test
    public void testSelectGetById() {

        // DB에 존재하는 Id
        Long id = 100L;

        Posts posts = postsRepository.getById(id);

        System.out.println("==================================");
        System.out.println(posts);

    }

    // 수정 테스트
    // 등록과 동일한 save() 이용
 /*   @Test
    public void testUpdate() {

        Optional<Posts> result = postsRepository.findById(1L);

        if (result.isPresent()) {

            Posts posts = result.get();

            posts.changeTitle("수정 테스트");
            posts.changeContent("내용 수정");
            posts.changeWriter("작성자 수정");
            posts.changePwd("4321");

            postsRepository.save(posts);
        }

    }*/

    // 삭제 테스트
    @Test
    public void testDelete() {

        Long id = 101L;

        postsRepository.deleteById(id);

    }

}