package com.enolic.Noviflix_backend.controller;

import com.enolic.Noviflix_backend.annotations.swagger.*;
import com.enolic.Noviflix_backend.model.dto.MovieDTO;
import com.enolic.Noviflix_backend.service.interfaces.MovieServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies")
@Tag(name = "Movies", description = "Movie management APIs")
public class MovieController {

    private final MovieServiceInterface movieService; // service injection

    @GetMapping
    @GetAllMoviesResponses
    @ServerErrorResponses
    @Operation(summary = "Get all movies", description = "Returns a list of all available movies.")
    public List<MovieDTO> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    @GetMovieByIdResponses
    @ServerErrorResponses
    @Operation(summary = "Get movie by ID", description = "Fetches a movie based on the provided ID.")
    public MovieDTO getMovieById(@PathVariable UUID id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    @AddMovieResponses
    @ServerErrorResponses
    @Operation(summary = "Add a new movie", description = "Adds a new movie to the database.")
    public MovieDTO addMovie(MovieDTO movieDTO) {
        return movieService.addMovie(movieDTO);
    }

    @PutMapping("/{id}")
    @UpdateMovieResponses
    @ServerErrorResponses
    @Operation(summary = "Update a movie", description = "Updates the details of an existing movie.")
    public MovieDTO updateMovie(UUID id, MovieDTO updatedMovie) {
        return movieService.updateMovie(id, updatedMovie);
    }

    @DeleteMapping("/{id}")
    @DeleteMovieResponses
    @ServerErrorResponses
    @Operation(summary = "Delete a movie", description = "Deletes a movie from the database using its unique ID.")
    public String deleteMovie(@PathVariable UUID id) {
        return movieService.deleteMovie(id);
    }

    @GetMapping("/whatsnext")
    @GetRandomMovieResponses
    @ServerErrorResponses
    @Operation(summary = "Get a random movie", description = "Fetches a random movie from the database.")
    public MovieDTO getRandomMovie() {
        return movieService.getRandomMovie();
    }

    @PostMapping("/loadmovies")
    @LoadMoviesResponses
    @ServerErrorResponses
    @Operation(summary = "Load hardcoded movies", description = "Loads predefined movies into the database.")
    public String loadMovies() {
        return movieService.loadMovies();
    }
}
