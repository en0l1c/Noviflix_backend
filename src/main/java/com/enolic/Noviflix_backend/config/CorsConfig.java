package com.enolic.Noviflix_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration // includes all the settings for spring to use
public class CorsConfig {
    @Value("${server.url}")
    private String serverUrl; // spring doesnt like static variables!!

    @Bean // bean tells spring to create an object of this method and managing (as bean)
    public CorsFilter corsFilter() {
        CorsConfiguration config = getCorsConfiguration(serverUrl);


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // it connects url paths with cors configuration
        source.registerCorsConfiguration("/**", config); // configurations applying to all endpoints
        return new CorsFilter(source);  // CorsFilter uses source object for check requests from the api
    }

    private static CorsConfiguration getCorsConfiguration(String serverUrl) {
        CorsConfiguration config = new CorsConfiguration(); // the object config of CorsConfiguration contains all the CORS configurations
        config.setAllowedOrigins(List.of(serverUrl, "http://localhost:4200", "https://konstantinosgialantzis.github.io/"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // alows cookies or authentication token between frontend and backend
        return config;
    }
}
