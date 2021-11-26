package com.project.baedeokcar.controller;

import com.project.baedeokcar.domain.dto.PostsDTO;
import com.project.baedeokcar.domain.dto.PostsListResponseDto;
import com.project.baedeokcar.service.PostsService;
import com.project.baedeokcar.domain.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/posts/read")
    public void read(Long id, Model model) {

        log.info("---------- /posts/read GET 호출 ----------");

        PostsDTO postsDTO = postsService.read(id);

        model.addAttribute("postsDTO", postsDTO);

    }

    @PostMapping("/posts")
    public String save(@ModelAttribute PostsSaveRequestDto requestDto) {
        log.info("post-save controller");
        Long Id = postsService.save(requestDto);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(String factor, String keyword, Model model) {
        List<PostsListResponseDto> postsList = postsService.search(factor, keyword);

        model.addAttribute("posts", postsList);

        return "index";
    }

}
