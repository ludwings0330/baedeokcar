package com.project.baedeokcar.repository;

import com.project.baedeokcar.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
