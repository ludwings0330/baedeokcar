package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.dto.PostsDTO;
import com.project.baedeokcar.web.dto.PostsListResponseDto;
import com.project.baedeokcar.web.dto.PostsSaveRequestDto;

import java.util.List;

public interface PostsService {

    PostsDTO read(Long id);

    public List<PostsListResponseDto> findAllDesc();

    public Long save(PostsSaveRequestDto postsSaveRequestDto);

}
