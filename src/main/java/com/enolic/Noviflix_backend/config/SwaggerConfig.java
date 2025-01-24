package com.enolic.Noviflix_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.List;
import java.util.Map;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8080/api"); // how the baseurl is displayed in inputbox

        return new OpenAPI()
                .info(new Info()
                        .title("Novifliiiiix")
                        .version("1.0")
                        .description("Novifliiiiix API")
                )
                .servers(List.of(server));
    }

    @Bean
    public OpenApiCustomizer customizer() {
        return openApi -> {
            var paths = openApi.getPaths();
            var orderedPaths = new Paths();

            // paths in right order
            orderedPaths.addPathItem("/movies", paths.get("/api/movies"));
            orderedPaths.addPathItem("/movies/{id}", paths.get("/api/movies/{id}"));
            orderedPaths.addPathItem("/movies/whatsnext", paths.get("/api/movies/whatsnext"));
            orderedPaths.addPathItem("/movies/loadmovies", paths.get("/api/movies/loadmovies"));

            openApi.setPaths(orderedPaths);
        };
    }
}