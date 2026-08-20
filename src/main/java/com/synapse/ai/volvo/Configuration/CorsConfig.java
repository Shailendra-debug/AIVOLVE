package com.synapse.ai.volvo.Configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins(
                        "http://127.0.0.1:3000",
                        "http://127.0.0.1:5501",
                        "http://127.0.0.1:5500",
                        "http://localhost:3000",
                        "http://localhost:5500",
                        "https://ubiquitous-shortbread-e875ba.netlify.app",
                        "https://aivolve.skushwaha.in/",
                        "https://sprint.skushwaha.in/",
                        "http://localhost:5501"
                )
                .allowedMethods(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "PATCH",
                        "OPTIONS"
                )
                .allowedHeaders("*");
    }
}