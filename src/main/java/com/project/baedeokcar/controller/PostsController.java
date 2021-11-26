package com.project.baedeokcar.controller;

import com.project.baedeokcar.domain.dto.PostsDTO;
import com.project.baedeokcar.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/read")
    public void read(Long id, Model model) {

        log.info("---------- /posts/read GET 호출 ----------");

        PostsDTO postsDTO = postsService.read(id);

        model.addAttribute("postsDTO", postsDTO);

    }

}
