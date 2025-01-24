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
        @ApiResponse(responseCode = "200", description = "OK."),
        @ApiResponse(responseCode = "400", description = "Invalid Movie UUID.", content = @Content(mediaType = "")),
        @ApiResponse(responseCode = "404", description = "Movie not found.", content = @Content(mediaType = "")),
})
public @interface GetMovieByIdResponses {}

