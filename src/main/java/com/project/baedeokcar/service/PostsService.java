package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.dto.PostsDTO;
import com.project.baedeokcar.domain.dto.PostsListResponseDto;
import com.project.baedeokcar.domain.dto.PostsSaveRequestDto;

import java.util.List;

public interface PostsService {

    PostsDTO read(Long id);

    public List<PostsListResponseDto> findAllDesc();

    public Long save(PostsSaveRequestDto postsSaveRequestDto);

<<<<<<< HEAD
=======
    //    public Long save(Posts post) {
//        return postsRepository.save(post).getId();
//    }
    public Long save(PostsSaveRequestDto postsListRequestDto) {
        return postsRepository.save(postsListRequestDto.toEntity()).getId();
    }

    public List<PostsListResponseDto> search(String keyword) {
        return postsRepository.findAllByTitleContainingOrderByIdDesc(keyword).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<PostsListResponseDto> search(String factor, String keyword) {
        List<Posts> responseDtos = null;

        if (factor.equals("title")) {
            responseDtos = postsRepository.findAllByTitleContainingOrderByIdDesc(keyword);
        } else if (factor.equals("writer")) {
            responseDtos = postsRepository.findAllByWriterContainingOrderByIdDesc(keyword);
        } else if (factor.equals("content")) {
            responseDtos = postsRepository.findAllByContentContainingOrderByIdDesc(keyword);
        }


        return responseDtos.stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
>>>>>>> addfunction
}
