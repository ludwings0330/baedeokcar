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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @GetMapping({"/posts/read", "/posts/modify"})
    public void read(Long id, Model model) {

        log.info("---------- /posts{\"/read\", \"/modify\"} GET 호출 ----------");
        log.info("id : " + id);

        PostsDTO postsDTO = postsService.read(id);

        log.info("postDTO : " + postsDTO);

        model.addAttribute("postsDTO", postsDTO);

    }

    @PostMapping("/posts/modify")
    public String modify(PostsDTO postsDTO, RedirectAttributes redirectAttributes) {

        log.info("---------- /posts/modify POST 호출 ----------");
        log.info("postDTO : " + postsDTO);

        postsService.modify(postsDTO);

        redirectAttributes.addAttribute("id", postsDTO.getId());

        return "redirect:/posts/read";

    }

    @PostMapping("/posts/remove")
    public String remove(Long id) {

        log.info("---------- /posts/modify POST 호출 ----------");
        log.info("id : " + id);

        postsService.remove(id);

        return "redirect:/";

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

        return "posts/posts-list";
    }

}
