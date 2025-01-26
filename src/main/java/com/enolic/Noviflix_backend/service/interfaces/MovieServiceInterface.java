package com.enolic.Noviflix_backend.service.interfaces;

import com.enolic.Noviflix_backend.annotations.*;
import com.enolic.Noviflix_backend.model.dto.MovieDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/movies")
@Tag(name = "Movies", description = "Movie management APIs")
public interface MovieServiceInterface {

    @GetMapping
    @Operation(summary = "Get all movies", description = "Returns a list of all available movies.")
    @GetAllMoviesResponses
    @ServerErrorResponses
    ResponseEntity<List<MovieDTO>> getAllMovies();

    @GetMapping("/{id}")
    @Operation(summary = "Get movie by ID", description = "Fetches a movie based on the provided ID.")
    @GetMovieByIdResponses
    @ServerErrorResponses
    ResponseEntity<MovieDTO> getMovieById(@PathVariable UUID id);

    @PostMapping
    @Operation(summary = "Add a new movie", description = "Adds a new movie to the database.")
    @AddMovieResponses
    @ServerErrorResponses
    ResponseEntity<MovieDTO> addMovie(@Valid @RequestBody MovieDTO movieDTO);

    @PutMapping("/{id}")
    @Operation(summary = "Update a movie", description = "Updates the details of an existing movie.")
    @UpdateMovieResponses
    @ServerErrorResponses
    ResponseEntity<MovieDTO> updateMovie(@PathVariable UUID id, @Valid @RequestBody MovieDTO updatedMovie);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a movie", description = "Deletes a movie from the database using its unique ID.")
    @DeleteMovieResponses
    @ServerErrorResponses
    ResponseEntity<String> deleteMovie(@PathVariable UUID id);

    @GetMapping("/whatsnext")
    @Operation(summary = "Get a random movie", description = "Fetches a random movie from the database.")
    @GetRandomMovieResponses
    @ServerErrorResponses
    ResponseEntity<MovieDTO> getRandomMovie();

    @PostMapping("/loadmovies")
    @Operation(summary = "Load hardcoded movies", description = "Loads predefined movies into the database.")
    @LoadMoviesResponses
    @ServerErrorResponses
    ResponseEntity<String> loadMovies();
}
