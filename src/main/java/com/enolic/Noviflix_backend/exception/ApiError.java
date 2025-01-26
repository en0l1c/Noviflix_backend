package com.enolic.Noviflix_backend.exception;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(hidden = true)
public class ApiError {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime timestamp;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int status;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String error;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String message;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String path;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String methodName;
}
