package com.project.baedeokcar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/login")
                .addPathPatterns("/read-car/**")
                .addPathPatterns("/car-list/**")
                .addPathPatterns("/car/**")
                .addPathPatterns("/own-car-list/**")
                .addPathPatterns("/posts/**")
                .addPathPatterns("/save-reservation/**")
                .addPathPatterns("/reservations/**");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
