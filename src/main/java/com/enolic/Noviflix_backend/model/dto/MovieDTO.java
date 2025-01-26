package com.enolic.Noviflix_backend.model.dto;

import com.enolic.Noviflix_backend.model.entity.Movie;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Movie Schema", description = "Represents a movie ")
public class MovieDTO {

    @Schema(description = "Unique identifier of the movie", example = "6726ba9e-d327-4f45-82b4-370986911226", accessMode = Schema.AccessMode.READ_ONLY)

    private String id;

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    @Schema(description = "Title of the movie", example = "Inception")

    private String title;

    @NotBlank(message = "Director cannot be empty")
    @Size(max = 100, message = "Director cannot exceed 100 characters")
    @Schema(description = "Director of the movie", example = "Christopher Nolan")
    private String director;

    @NotBlank(message = "Plot cannot be empty")
    @Size(max = 255, message = "Plot cannot exceed 255 characters")
    @Schema(description = "Plot of the movie", example = "A thief who steals corporate secrets...")
    private String plot;

    @NotNull(message = "Year cannot be empty")
    @Min(value = 1880, message = "Year must be between 1880 and 2025")
    @Max(value = 2025, message = "Year must be between 1880 and 2025")
    @Schema(description = "Release year of the movie", example = "2010")
    private int releaseYear;

    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    @Schema(description = "Image URL of the movie", example = "https://example.com/inception.jpg")
    private String url;

    // convert dto to entity
    public Movie toEntity() {
        return new Movie(
                this.id,
                this.title,
                this.director,
                this.plot,
                this.releaseYear,
                this.url
        );
    }

    // convert movie entity to movie dto
    public static MovieDTO fromEntity(Movie movie) {
        return new MovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getPlot(),
                movie.getReleaseYear(),
                movie.getUrl()
        );
    }
}
