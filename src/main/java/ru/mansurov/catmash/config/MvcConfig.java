package ru.mansurov.catmash.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${pictures.path}")
    private String picturesPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/java/catmash/mash/img/**", "img/**",
                "img/*", "*/img/**", "*/img/**", "**/img/**")
                .addResourceLocations("file://" + picturesPath + "/");
    }
}
