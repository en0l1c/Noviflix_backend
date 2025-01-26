package com.enolic.Noviflix_backend.annotations.swagger;

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
        @ApiResponse(responseCode = "204", description = "No movies in list", content = @Content(mediaType = ""))
})
public @interface GetRandomMovieResponses {}

