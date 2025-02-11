package com.enolic.Noviflix_backend.annotations.swagger;

import com.enolic.Noviflix_backend.exception.ApiError;
import com.enolic.Noviflix_backend.model.dto.MovieDTO;
import io.swagger.v3.oas.annotations.media.Content;
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
        @ApiResponse(responseCode = "200", description = "OK.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid Movie UUID.", content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class))),
        @ApiResponse(responseCode = "404", description = "Movie not found.", content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class))),
})
public @interface GetMovieByIdResponses {}

