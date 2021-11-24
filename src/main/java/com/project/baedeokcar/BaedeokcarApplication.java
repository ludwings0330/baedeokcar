package com.project.baedeokcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BaedeokcarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaedeokcarApplication.class, args);
    }

}
