package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.dto.PostsDTO;
import com.project.baedeokcar.domain.posts.Posts;
import com.project.baedeokcar.repository.PostsRepository;
import com.project.baedeokcar.domain.dto.PostsListResponseDto;
import com.project.baedeokcar.domain.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(PostsSaveRequestDto postsListRequestDto) {
        return postsRepository.save(postsListRequestDto.toEntity()).getId();
    }

    @Override
    public List<PostsListResponseDto> search(String keyword) {
        return postsRepository.findAllByTitleContainingOrderByIdDesc(keyword).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
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
}
