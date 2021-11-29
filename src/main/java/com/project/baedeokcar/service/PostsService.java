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

    public List<PostsResponseDto> findAllDesc();

    public Long save(PostsSaveRequestDto postsSaveRequestDto);

    public List<PostsResponseDto> search(String keyword);

    public List<PostsResponseDto> search(String factor, String keyword);

    public Page<PostsResponseDto> search(String factor, String keyword, Pageable pageable);

    public Page<PostsResponseDto> getPostsList(Pageable pageable);
    public Page<PostsResponseDto> getPostsList(String option, String keyword, Pageable pageable);
}
