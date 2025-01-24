package com.enolic.Noviflix_backend.annotations;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "A list of movies",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Object.class),
                        examples = @ExampleObject(value = "[\n" +
                                "  {\n" +
                                "    \"id\": \"6726ba9e-d327-4f45-82b4-370986911226\",\n" +
                                "    \"title\": \"Inception\",\n" +
                                "    \"director\": \"Christopher Nolan\",\n" +
                                "    \"plot\": \"A thief who steals corporate secrets...\",\n" +
                                "    \"releaseYear\": 2010,\n" +
                                "    \"url\": \"https://example.com/inception.jpg\"\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"id\": \"d73692e8-573f-4a4b-9e8a-2385a1b2c5b8\",\n" +
                                "    \"title\": \"The Matrix\",\n" +
                                "    \"director\": \"Wachowski Sisters\",\n" +
                                "    \"plot\": \"A computer hacker learns...\",\n" +
                                "    \"releaseYear\": 1999,\n" +
                                "    \"url\": \"https://example.com/matrix.jpg\"\n" +
                                "  }\n" +
                                "]"
                        )
                )
        ),
        @ApiResponse(responseCode = "204", description = "No movies in list", content = @Content(mediaType = ""))
})
public @interface GetAllMoviesResponses {}
