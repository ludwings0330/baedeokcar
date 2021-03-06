package com.project.baedeokcar.service;

import com.project.baedeokcar.aop.PerfLogging;
import com.project.baedeokcar.domain.dto.post.PostsDTO;
import com.project.baedeokcar.domain.dto.post.PostsResponseDto;
import com.project.baedeokcar.domain.dto.post.PostsSaveRequestDto;
import com.project.baedeokcar.domain.posts.Posts;
import com.project.baedeokcar.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
// 의존성 자동 주입
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {

    // 반드시 final 로 선언
    private final PostsRepository postsRepository;
    private final ModelMapper modelMapper;

    // 게시글 조회
    @Override
    public PostsDTO read(Long id) {

        // modelMapper 사용
        Optional<Posts> result = postsRepository.findById(id);
        log.info("result : " + result);

        // 조회수 증가
        postsRepository.updateViewCount(id);

        return result.isPresent() ? modelMapper.map(result.get(), PostsDTO.class) : null;

    }

    @Override
    public void modify(PostsDTO postsDTO) {

        Posts posts = modelMapper.map(postsDTO, Posts.class);
        log.info("posts : " + posts);

        postsRepository.save(posts);
    }

    @Override
    public void remove(Long id) {

        log.info("id : " + id);

        postsRepository.deleteById(id);

    }

    @Override
    @PerfLogging
    public Long save(PostsSaveRequestDto postsListRequestDto) {
        return postsRepository.save(postsListRequestDto.toEntity()).getId();
    }

    @Override
    @PerfLogging
    public Page<PostsResponseDto> getPostsList(String option, String keyword, Pageable pageable) {
        Page<Posts> posts = postsRepository.findPosts(option, keyword, pageable);

        Page<PostsResponseDto> dtoPage = posts.map(p -> new PostsResponseDto(p));
        return dtoPage;
    }
}
