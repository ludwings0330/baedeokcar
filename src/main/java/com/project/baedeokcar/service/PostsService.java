package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.posts.Posts;
import com.project.baedeokcar.domain.posts.PostsRepository;
import com.project.baedeokcar.web.dto.PostsListResponseDto;
import com.project.baedeokcar.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    //    public Long save(Posts post) {
//        return postsRepository.save(post).getId();
//    }
    public Long save(PostsSaveRequestDto postsListRequestDto) {
        return postsRepository.save(postsListRequestDto.toEntity()).getId();
    }
}
