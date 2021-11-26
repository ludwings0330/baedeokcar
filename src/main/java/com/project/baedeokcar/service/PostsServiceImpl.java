package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.dto.PostsDTO;
import com.project.baedeokcar.domain.posts.Posts;
import com.project.baedeokcar.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
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

        log.info("---------- read 호출 ----------");
        log.info("id : " + id);

        Optional<Posts> result = postsRepository.findById(id);

        return result.isPresent() ? modelMapper.map(result.get(), PostsDTO.class) : null;

    }
}
