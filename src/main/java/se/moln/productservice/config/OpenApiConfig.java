package se.moln.productservice.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI productServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product Service API")
                        .version("v1")
                        .description("Product endpoints"))
                .servers(List.of(
                        new io.swagger.v3.oas.models.servers.Server()
                                .url("https://productservice.drillbi.se")
                                .description("Production server")
                ));
    }
}
