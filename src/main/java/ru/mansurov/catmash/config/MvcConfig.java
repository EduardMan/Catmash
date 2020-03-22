package ru.mansurov.catmash.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${pictures.path}")
    private String picturesPath;

    @Value("${registration.enable}")
    private boolean registrationEnable;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("img/**")
                .addResourceLocations("file://" + picturesPath + "/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        if (registrationEnable) {
            registry.addViewController("/login").setViewName("login");
            registry.addViewController("/registration").setViewName("registration");
        }
    }
}
