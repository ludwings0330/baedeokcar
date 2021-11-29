package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.dto.PostsDTO;
import com.project.baedeokcar.domain.dto.PostsResponseDto;
import com.project.baedeokcar.domain.dto.PostsSaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostsService {

    PostsDTO read(Long id);

    void modify(PostsDTO postsDTO);

    void remove(Long id);

    public Long save(PostsSaveRequestDto postsSaveRequestDto);

    public Page<PostsResponseDto> getPostsList(String option, String keyword, Pageable pageable);
}
