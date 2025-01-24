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
        @ApiResponse(responseCode = "201", description = "Movie added successfully.", content = @Content(mediaType = "")),
        @ApiResponse(responseCode = "400", description = "Invalid input data. (Empty title, director or plot or invalid releaseYear)", content = @Content(mediaType = "")),
        @ApiResponse(responseCode = "409", description = "Conflict. Duplicate title.", content = @Content(mediaType = "")),
})
public @interface AddMovieResponses {}
