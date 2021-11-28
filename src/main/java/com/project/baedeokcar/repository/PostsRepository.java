package com.project.baedeokcar.repository;

import com.project.baedeokcar.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Transactional(readOnly = true)
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("Select p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    List<Posts> findAllByTitleContainingOrderByIdDesc(String keyword);

    List<Posts> findAllByContentContainingOrderByIdDesc(String keyword);

    List<Posts> findAllByWriterContainingOrderByIdDesc(String keyword);

    @Modifying
    @Query("UPDATE Posts p SET p.viewCount = p.viewCount + 1 WHERE p.id = :id")
    int updateViewCount(Long id);
}
