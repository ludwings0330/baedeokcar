package com.project.baedeokcar.web;

import com.project.baedeokcar.domain.posts.Posts;
import com.project.baedeokcar.service.PostsService;
import com.project.baedeokcar.web.dto.PostsListResponseDto;
import com.project.baedeokcar.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class PostsController {

    private final PostsService postsService;

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

//    GetMapping("/search")
//    public String search(String keyword, Model model) {
//        List<PostsListResponseDto> postsList = postsService.search(keyword);
//
//        model.addAttribute("posts", postsList);
//
//        return "index";
//    }
}
