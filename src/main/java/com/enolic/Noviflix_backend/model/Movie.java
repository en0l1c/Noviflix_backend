package com.enolic.Noviflix_backend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a movie ")
public class Movie {
    @Id
    @Schema(description = "Unique identifier of the movie. API will generated automatically for you", example = "6726ba9e-d327-4f45-82b4-370986911226", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    // validation annotations
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 1, max = 100, message = "Title cannot exceed 100 characters")
    @Schema(description = "Title of the movie", example = "Inception")
    private String title;

    @NotBlank(message = "Director cannot be empty")
    @Size(min = 1, max = 100, message = "Director cannot exceed 100 characters")
    @Schema(description = "Director of the movie", example = "Christopher Nolan")
    private String director;

    @NotBlank(message = "Plot cannot be empty")
    @Size(min = 1, max = 255, message = "Plot cannot exceed 255 characters")
    @Schema(description = "Plot of the movie", example = "A thief who steals corporate secrets through the use of dream-sharing technology...")
    private String plot;


    @NotNull(message = "Year cannot be empty")
    @Min(value = 1880, message = "Year must be between 1880 and 2025")
    @Max(value = 2025, message = "Year must be between 1880 and 2025")
    @Schema(description = "Release year of the movie", example = "A year between 1880 and 2025")
    private int releaseYear;

    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    @Schema(description = "Image URL of the movie (optional)", example = "A year between 1880 and 2025")
    private String url;

    // UUID makes sures that the id is unique
    // it makes more sense if an id will be created in more than one system/server
    // it is more safe
    @PrePersist
    public void generateId() {
        if (this.id == null || this.id.isEmpty()) {
            this.id = UUID.randomUUID().toString();
        }
    }
}