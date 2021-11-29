package com.project.baedeokcar;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@EnableJpaAuditing // AuditingEntityListener 활성화
@SpringBootApplication
public class BaedeokcarApplication {
    @PersistenceContext
    private EntityManager em;

    public static void main(String[] args) {
        SpringApplication.run(BaedeokcarApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper;

    }

    @Bean
    public JPAQueryFactory jpaQueryFactory() {return new JPAQueryFactory(em);}
}
