package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.dto.PostsDTO;
import com.project.baedeokcar.domain.dto.PostsListResponseDto;
import com.project.baedeokcar.domain.dto.PostsSaveRequestDto;

import java.util.List;
import java.util.stream.Collectors;

public interface PostsService {

    PostsDTO read(Long id);

    public List<PostsListResponseDto> findAllDesc();

    public Long save(PostsSaveRequestDto postsSaveRequestDto);

    public List<PostsListResponseDto> search(String keyword);

    public List<PostsListResponseDto> search(String factor, String keyword);
}
