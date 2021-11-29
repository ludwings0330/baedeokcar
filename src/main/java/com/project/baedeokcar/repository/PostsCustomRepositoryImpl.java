package com.project.baedeokcar.repository;

import com.project.baedeokcar.domain.posts.Posts;
import com.project.baedeokcar.domain.posts.QPosts;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.project.baedeokcar.domain.posts.QPosts.*;

@Repository
@Transactional
@RequiredArgsConstructor
public class PostsCustomRepositoryImpl implements PostsCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Posts> findPosts(String option, String keyword, Pageable pageable) {
        QueryResults<Posts> results = queryFactory.selectFrom(posts)
                .where(titleEq(option, keyword),
                        writerEq(option, keyword),
                        contentEq(option, keyword))
                .orderBy(posts.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Posts> posts = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(posts, pageable, total);
    }

    private com.querydsl.core.types.dsl.BooleanExpression titleEq(String option, String keyword) {
        return option.equals("title")? posts.title.contains(keyword) : null;
    }
    private com.querydsl.core.types.dsl.BooleanExpression writerEq(String option, String keyword) {
        return option.equals("writer")? posts.writer.contains(keyword) : null;
    }
    private com.querydsl.core.types.dsl.BooleanExpression contentEq(String option, String keyword) {
        return option.equals("content")? posts.content.contains(keyword) : null;
    }
}
