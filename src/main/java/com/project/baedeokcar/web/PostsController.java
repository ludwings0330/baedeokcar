package com.project.baedeokcar.web;

import com.project.baedeokcar.service.PostsService;
import com.project.baedeokcar.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

}
