package com.example.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    final String FILE_PATH = "/app/image/";
    final String FILE_URL = "http://3.35.30.191:4040/file/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/files/**")
                .addResourceLocations("file:" + FILE_PATH);

        registry
                .addResourceHandler("/file/**")
                .addResourceLocations(FILE_URL);
    }
}