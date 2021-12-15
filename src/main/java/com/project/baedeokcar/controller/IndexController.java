package com.project.baedeokcar.controller;

import com.project.baedeokcar.aop.PerfLogging;
import com.project.baedeokcar.domain.dto.post.PostsDTO;
import com.project.baedeokcar.domain.dto.post.PostsResponseDto;
import com.project.baedeokcar.service.PostsService;
import com.project.baedeokcar.domain.dto.post.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 한 블럭에 존재하는 페이지 번호 수

    /**
     * find all posts list
     * @param model
     * @param pageable
     * @return
     */
    @GetMapping("/posts")
    public String index(Model model,
                        @PageableDefault(size=20, sort="id", direction= Sort.Direction.DESC) Pageable pageable,
                        @RequestParam(defaultValue="writer") String option,
                        @RequestParam(defaultValue="") String keyword) {

        Page<PostsResponseDto> all = postsService.getPostsList(option, keyword, pageable);
//        Page<PostsResponseDto> all = postsService.getPostsList(pageable);

        model.addAttribute("option", option);
        model.addAttribute("keyword", keyword);

        model.addAttribute("page", all);
        model.addAttribute("maxPage", BLOCK_PAGE_NUM_COUNT);
        model.addAttribute("posts", all.getContent());

        return "posts/posts-list";
    }


    @GetMapping("/posts/save")
    public String saveForm() {
        return "posts/posts-save";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "join";
    }

    @GetMapping("/car")
    public String carForm() {
        return "/car/car";
    }

    @GetMapping("/reservations")
    public String reservationForm() {
        return "/reservation/reservations";
    }

}