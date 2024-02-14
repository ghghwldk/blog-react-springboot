package com.m.blog.global.config;

import com.m.blog.global.interceptor.LogInterceptor;
import com.m.blog.global.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
        /*
    //web root가 아닌 외부 경로에 있는 리소스를 url로 불러올 수 있도록 설정
    //현재 localhost:8090/summernoteImage/1234.jpg
    //로 접속하면 C:/summernote_image/1234.jpg 파일을 불러온다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/download/**")
                .addResourceLocations("file:///C:/summernote_image/");
    }*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error","/js/**","/images/**");

        registry.addInterceptor(new SecurityInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login/**", "/user/logout/**",
                        "/css/**", "/*.ico", "/error","/js/**","/images/**","/file");
    }

    @Bean
    public SecurityInterceptor loginCheckInterceptor() {
        return new SecurityInterceptor();
    }
}