package com.enolic.Noviflix_backend.controller;

import com.enolic.Noviflix_backend.annotations.*;
import com.enolic.Noviflix_backend.exception.ResourceNotFoundException;
import com.enolic.Noviflix_backend.model.Movie;
import com.enolic.Noviflix_backend.repository.MovieRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Tag(name = "Movies", description = "Movie management APIs")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /** ENDPOINTS **/

    // get all movies
    @Operation(summary = "Get all movies", description = "Returns a list of all available movies.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK."), // display the through swagger example
//            @ApiResponse(responseCode = "204", description = "No movies found", content = @Content(mediaType = ""))
//    })
    @GetAllMoviesResponses // 200, 204 (to display them properly in swagger)
    @ServerErrorResponses // 500, 503 errors ( to display them properly in swagger)
    @GetMapping("/movies")
    public ResponseEntity<?> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.ok(movies); // 200
    }

    // get movie by id
    @Operation(summary = "Get movie by ID", description = "Fetches a movie based on the provided ID.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK."),
//            @ApiResponse(responseCode = "400", description = "Invalid Movie UUID."),
//            @ApiResponse(responseCode = "404", description = "Movie not found."),
//    })
    @GetMovieByIdResponses // 200, 400, 404
    @ServerErrorResponses // 500, 503 errors
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable UUID id) {
        Movie movie = movieRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Movie with id <" + id + "> not found.")); // 404
        return ResponseEntity.ok(movie); // 200
    }


    // it requires validation because user is responsible for data
    // it checks for validation of data before process
    @Operation(summary = "Add a new movie", description = "Adds a new movie to the database.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "201", description = "Movie added successfully."),
//            @ApiResponse(responseCode = "400", description = "Invalid input data. (Empty title, director or plot or invalid releaseYear)"),
//            @ApiResponse(responseCode = "409", description = "Conflict. Duplicate title."),
//    })
    @AddMovieResponses
    @ServerErrorResponses // 500, 503 errors
    @PostMapping("/movies")
    public ResponseEntity<?> addMovie(@Valid @RequestBody Movie movie) {
        if (movieRepository.existsByTitle(movie.getTitle())) {
            return ResponseEntity.status(409).body("Movie with this title already exists");
        }
        return ResponseEntity.status(201).body(movieRepository.save(movie));
    }

    @Operation(summary = "Update a movie", description = "Updates the details of an existing movie.")

//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK."),
//            @ApiResponse(responseCode = "400", description = "Invalid Movie UUID."),
//            @ApiResponse(responseCode = "404", description = "Movie not found."),
//    })
    @UpdateMovieResponses
    @ServerErrorResponses // 500, 503 errors
    @PutMapping("/movies/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable UUID id, @Valid @RequestBody Movie updatedMovie) {
        Optional<Movie> movieOptional = movieRepository.findById(String.valueOf(id));
        if (movieOptional.isPresent()) {
            Movie existingMovie = movieOptional.get();
            existingMovie.setTitle(updatedMovie.getTitle());
            existingMovie.setDirector(updatedMovie.getDirector());
            existingMovie.setPlot(updatedMovie.getPlot());
            existingMovie.setReleaseYear(updatedMovie.getReleaseYear());
            existingMovie.setUrl(updatedMovie.getUrl());
            return ResponseEntity.ok(movieRepository.save(existingMovie)); // 200
        }
        throw new ResourceNotFoundException("Movie with id <" + id + "> not found."); //404
    }

    @Operation(summary = "Delete a movie", description = "Deletes a movie from the database using its unique ID.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK."),
//            @ApiResponse(responseCode = "400", description = "Invalid Movie UUID."),
//            @ApiResponse(responseCode = "404", description = "Movie not found."),
//    })
    @DeleteMovieResponses
    @ServerErrorResponses // 500, 503 errors
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable UUID id) {
        if (movieRepository.existsById(String.valueOf(id))) {
            movieRepository.deleteById(String.valueOf(id));
            return ResponseEntity.ok("Movie with id <" + id + "> deleted successfully."); // 200
        }
        throw new ResourceNotFoundException("Movie with id <" + id + "> not found."); // 404
    }

    @Operation(summary = "Get a random movie", description = "Fetches a random movie from the database.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK."),
//            @ApiResponse(responseCode = "204", description = "No movies in list."),
//    })
    @GetRandomMovieResponses
    @ServerErrorResponses // 500, 503 errors
    @GetMapping("/movies/whatsnext")
    public ResponseEntity<Movie> getRandomMovie() {
        List<Movie> movies = movieRepository.findAll();
        // checks if the movies list is empty
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        Movie randomMovie = movies.get(new Random().nextInt(movies.size()));
        return ResponseEntity.ok(randomMovie); // 200
    }

    // hardcoded movies
    @Operation(summary = "Load hardcoded movies", description = "Loads predefined movies into the database.")
    @ApiResponse(responseCode = "200", description = "OK.", content = @Content(mediaType = ""))
    @ServerErrorResponses // 500, 503 errors
    @PostMapping("/movies/loadmovies")
    public ResponseEntity<?> loadMovies() {
        movieRepository.save(new Movie(
                UUID.randomUUID().toString(),
                "Inception",
                "Christopher Nolan",
                "A thief who steals corporate secrets...",
                2010,
                "https://example.com/inception.jpg"
        ));

        movieRepository.save(new Movie(
                UUID.randomUUID().toString(),
                "The Matrix",
                "Wachowski Sisters",
                "A computer hacker learns...",
                1999,
                "https://example.com/matrix.jpg"
        ));

        return ResponseEntity.ok("Movies loaded successfully!"); // 200
    }
}















