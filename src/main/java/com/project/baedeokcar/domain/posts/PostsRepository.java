package com.project.baedeokcar.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("Select p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
