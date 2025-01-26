package com.enolic.Noviflix_backend.service.implementation;

import com.enolic.Noviflix_backend.exception.exceptions.ConflictException;
import com.enolic.Noviflix_backend.exception.exceptions.NoContentException;
import com.enolic.Noviflix_backend.exception.exceptions.ResourceNotFoundException;
import com.enolic.Noviflix_backend.model.dto.MovieDTO;
import com.enolic.Noviflix_backend.model.entity.Movie;
import com.enolic.Noviflix_backend.repository.MovieRepository;
import com.enolic.Noviflix_backend.service.interfaces.MovieServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService implements MovieServiceInterface {

    private final MovieRepository movieRepository;

    @Override
    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        if (movies.isEmpty()) {
            throw new NoContentException(); // 204
        }
        // convert Movie to MovieDTO
        List<MovieDTO> movieDTOs = movies.stream()
                .map(MovieDTO::fromEntity)
                .collect(Collectors.toList());
        return movieDTOs; // 200
    }

    @Override
    public MovieDTO getMovieById(UUID id) {
        Movie movie = movieRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Movie with id <" + id + "> not found.")); // 404
        return MovieDTO.fromEntity(movie); // 200
    }

    @Override
    public MovieDTO addMovie(MovieDTO movieDTO) {
        // check if movie exists with the same title
        if (movieRepository.existsByTitle(movieDTO.getTitle())) {
            throw new ConflictException("A movie with this title already exists."); // 409 Conflict
        }

        Movie movie = movieDTO.toEntity(); // convert from DTO to Entity
        Movie savedMovie = movieRepository.save(movie); // save the entity

        // convert from Entity to DTO for the response
        //return ResponseEntity.status(201).body(MovieDTO.fromEntity(savedMovie)); // 201
        return MovieDTO.fromEntity(savedMovie);
    }

    @Override
    public MovieDTO updateMovie(UUID id, MovieDTO updatedMovieDTO) {
        Movie existingMovie = movieRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Movie with id <" + id + "> not found.")); // 404
        // update movie details
        existingMovie.setTitle(updatedMovieDTO.getTitle());
        existingMovie.setDirector(updatedMovieDTO.getDirector());
        existingMovie.setPlot(updatedMovieDTO.getPlot());
        existingMovie.setReleaseYear(updatedMovieDTO.getReleaseYear());
        existingMovie.setUrl(updatedMovieDTO.getUrl());

        Movie savedMovie = movieRepository.save(existingMovie);
        return MovieDTO.fromEntity(savedMovie); // 200
    }

    @Override
    public String deleteMovie(UUID id) {
        if (!movieRepository.existsById(String.valueOf(id))) {
            throw new ResourceNotFoundException("Movie with id <" + id + "> not found."); // 404
        }
        movieRepository.deleteById(String.valueOf(id));
        return "Movie with id <" + id + "> deleted successfully.";
    }

    @Override
    public MovieDTO getRandomMovie() {
        List<Movie> movies = movieRepository.findAll();
        if (movies.isEmpty()) {
            throw new NoContentException(); // 204
        }
        Movie randomMovie = movies.get(new Random().nextInt(movies.size()));
       // return ResponseEntity.ok(MovieDTO.fromEntity(randomMovie)); // 200
        return MovieDTO.fromEntity(randomMovie);
    }

    @Override
    public String loadMovies() {
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

        //return ResponseEntity.ok("Movies loaded successfully!"); // 200
        return "Movies loaded successfully: " + movieRepository.count();
    }
}



























