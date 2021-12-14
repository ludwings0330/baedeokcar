package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.dto.post.PostsDTO;
import com.project.baedeokcar.domain.dto.post.PostsResponseDto;
import com.project.baedeokcar.domain.dto.post.PostsSaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostsService {

    PostsDTO read(Long id);

    void modify(PostsDTO postsDTO);

    void remove(Long id);

    public Long save(PostsSaveRequestDto postsSaveRequestDto);

    public Page<PostsResponseDto> getPostsList(String option, String keyword, Pageable pageable);
}
