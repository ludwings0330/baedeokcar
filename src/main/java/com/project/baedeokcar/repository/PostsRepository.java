package com.project.baedeokcar.repository;

import com.project.baedeokcar.domain.posts.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PostsRepository extends JpaRepository<Posts, Long>, PostsCustomRepository {
    @Modifying
    @Query("UPDATE Posts p SET p.viewCount = p.viewCount + 1 WHERE p.id = :id")
    int updateViewCount(@Param("id") Long id);

}
