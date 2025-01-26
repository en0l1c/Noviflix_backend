package com.enolic.Noviflix_backend.annotations.swagger;

import com.enolic.Noviflix_backend.exception.ApiError;
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
@ApiResponses({
        @ApiResponse(responseCode = "500", description = "Internal server error. (An unexpected error occurred. Please try again later.)", content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class))),
        @ApiResponse(responseCode = "503", description = "The server is currently under maintenance. Please try again later.", content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class)))
})
public @interface ServerErrorResponses {}
