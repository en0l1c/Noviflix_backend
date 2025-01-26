package com.enolic.Noviflix_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class SwaggerConfig {

    @Value("${server.url}")
    private String serverUrl;

    @Bean
    OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl(serverUrl);


        return new OpenAPI()
                .info(new Info()
                        .title("Noviflix API")
                        .version("1.0")
                        .description("Here you can find the Noviflix API with all the /endpoints you will need")
                )
                .servers(List.of(server));
    }

    @Bean
    public OpenApiCustomizer customizer() {
        // taake all the /endpoints and store them to the Paths object
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