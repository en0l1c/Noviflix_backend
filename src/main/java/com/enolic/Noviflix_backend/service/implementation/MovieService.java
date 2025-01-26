package com.enolic.Noviflix_backend.service.implementation;

import com.enolic.Noviflix_backend.exception.ResourceNotFoundException;
import com.enolic.Noviflix_backend.model.dto.MovieDTO;
import com.enolic.Noviflix_backend.model.entity.Movie;
import com.enolic.Noviflix_backend.repository.MovieRepository;
import com.enolic.Noviflix_backend.service.interfaces.MovieServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        // Μετατροπή των Movie σε MovieDTO
        List<MovieDTO> movieDTOs = movies.stream()
                .map(MovieDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(movieDTOs); // 200
    }

    @Override
    public ResponseEntity<MovieDTO> getMovieById(UUID id) {
        Movie movie = movieRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Movie with id <" + id + "> not found.")); // 404
        return ResponseEntity.ok(MovieDTO.fromEntity(movie)); // 200
    }

    @Override
    public ResponseEntity<MovieDTO> addMovie(MovieDTO movieDTO) {
        // Έλεγχος αν υπάρχει ήδη ταινία με τον ίδιο τίτλο
        if (movieRepository.existsByTitle(movieDTO.getTitle())) {
            return ResponseEntity.status(409).body(null); // 409 Conflict
        }

        // Μετατροπή από DTO σε Entity
        Movie movie = movieDTO.toEntity();

        // Αποθήκευση της οντότητας
        Movie savedMovie = movieRepository.save(movie);

        // Μετατροπή από Entity σε DTO για την απόκριση
        return ResponseEntity.status(201).body(MovieDTO.fromEntity(savedMovie)); // 201
    }

    @Override
    public ResponseEntity<MovieDTO> updateMovie(UUID id, MovieDTO updatedMovieDTO) {
        Movie existingMovie = movieRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Movie with id <" + id + "> not found.")); // 404
        // Ενημέρωση των δεδομένων της ταινίας
        existingMovie.setTitle(updatedMovieDTO.getTitle());
        existingMovie.setDirector(updatedMovieDTO.getDirector());
        existingMovie.setPlot(updatedMovieDTO.getPlot());
        existingMovie.setReleaseYear(updatedMovieDTO.getReleaseYear());
        existingMovie.setUrl(updatedMovieDTO.getUrl());

        Movie savedMovie = movieRepository.save(existingMovie);
        return ResponseEntity.ok(MovieDTO.fromEntity(savedMovie)); // 200
    }

    @Override
    public ResponseEntity<String> deleteMovie(UUID id) {
        if (movieRepository.existsById(String.valueOf(id))) {
            movieRepository.deleteById(String.valueOf(id));
            return ResponseEntity.ok("Movie with id <" + id + "> deleted successfully."); // 200
        }
        throw new ResourceNotFoundException("Movie with id <" + id + "> not found."); // 404
    }

    @Override
    public ResponseEntity<MovieDTO> getRandomMovie() {
        List<Movie> movies = movieRepository.findAll();
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        Movie randomMovie = movies.get(new Random().nextInt(movies.size()));
        return ResponseEntity.ok(MovieDTO.fromEntity(randomMovie)); // 200
    }

    @Override
    public ResponseEntity<String> loadMovies() {
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
