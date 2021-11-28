package com.project.baedeokcar.repository;

import com.project.baedeokcar.domain.posts.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PostsRepository extends JpaRepository<Posts, Long>, PostsCustomRepository {

    @Query("Select p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    List<Posts> findAllByTitleContainingOrderByIdDesc(String keyword);
    Page<Posts> findAllByTitleContainingOrderByIdDesc(String keyword, Pageable pageable);
    List<Posts> findAllByContentContainingOrderByIdDesc(String keyword);
    Page<Posts> findAllByContentContainingOrderByIdDesc(String keyword, Pageable pageable);
    List<Posts> findAllByWriterContainingOrderByIdDesc(String keyword);
    Page<Posts> findAllByWriterContainingOrderByIdDesc(String keyword, Pageable pageable);
}
