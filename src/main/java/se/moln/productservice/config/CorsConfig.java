package se.moln.productservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all endpoints
                .allowedOrigins(
                        "http://localhost:8080", // frontend_v2 (nginx)
                        "http://localhost:8085", // frontend_v2 (nginx)
                        "http://localhost:5173",  // vite dev server
                        "http://localhost:3000",
                        "https://productservice.drillbi.se",
                        "https://userservice.drillbi.se",
                        "https://kebabrolle.drillbi.se",
                        "https://orderservice.drillbi.se"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}