package com.project.baedeokcar;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // AuditingEntityListener 활성화
@SpringBootApplication
public class BaedeokcarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaedeokcarApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper;

    }

}
