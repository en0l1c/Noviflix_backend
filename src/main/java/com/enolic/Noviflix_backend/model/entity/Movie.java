package com.enolic.Noviflix_backend.model.entity;

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
public class Movie {
    @Id
    @NotBlank
    private String id;

    // validation annotations
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 1, max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Director cannot be empty")
    @Size(min = 1, max = 100, message = "Director cannot exceed 100 characters")
    private String director;

    @NotBlank(message = "Plot cannot be empty")
    @Size(min = 1, max = 255, message = "Plot cannot exceed 255 characters")
    private String plot;


    @NotNull(message = "Year cannot be empty")
    @Min(value = 1880, message = "Year must be between 1880 and 2025")
    @Max(value = 2025, message = "Year must be between 1880 and 2025")
    private int releaseYear;

    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
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