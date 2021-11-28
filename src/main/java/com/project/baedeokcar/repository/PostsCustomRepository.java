package com.project.baedeokcar.repository;

import com.project.baedeokcar.domain.posts.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostsCustomRepository {

    public Page<Posts> findPosts(String option, String keyword, Pageable pageable);
}
