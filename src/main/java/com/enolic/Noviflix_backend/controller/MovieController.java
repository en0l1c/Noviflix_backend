package com.enolic.Noviflix_backend.controller;

import com.enolic.Noviflix_backend.model.dto.MovieDTO;
import com.enolic.Noviflix_backend.service.interfaces.MovieServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MovieController implements MovieServiceInterface {

    private final MovieServiceInterface movieService;

    @Override
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        return movieService.getAllMovies();
    }

    @Override
    public ResponseEntity<MovieDTO> getMovieById(UUID id) {
        return movieService.getMovieById(id);
    }

    @Override
    public ResponseEntity<MovieDTO> addMovie(MovieDTO movieDTO) {
        return movieService.addMovie(movieDTO);
    }

    @Override
    public ResponseEntity<MovieDTO> updateMovie(UUID id, MovieDTO updatedMovie) {
        return movieService.updateMovie(id, updatedMovie);
    }

    @Override
    public ResponseEntity<String> deleteMovie(UUID id) {
        return movieService.deleteMovie(id);
    }

    @Override
    public ResponseEntity<MovieDTO> getRandomMovie() {
        return movieService.getRandomMovie();
    }

    @Override
    public ResponseEntity<String> loadMovies() {
        return movieService.loadMovies();
    }
}
