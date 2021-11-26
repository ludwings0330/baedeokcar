package com.project.baedeokcar.controller;

import com.project.baedeokcar.service.PostsService;
import com.project.baedeokcar.domain.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        return "index";
    }


    @GetMapping("/posts/save")
    public String saveForm() {
        return "posts/posts-save";
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            String title = "test title" + i;
            String content = "test content" + i;
            String writer = "test writer";
            String pwd = "password";

            PostsSaveRequestDto postsSaveRequestDto = PostsSaveRequestDto.builder()
                    .title(title)
                    .content(content)
                    .writer(writer)
                    .pwd(pwd)
                    .build();

            postsService.save(postsSaveRequestDto);
        }

        log.info("[init] Test 용 데이터 추가");
    }
}