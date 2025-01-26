package com.enolic.Noviflix_backend.repository;

import com.enolic.Noviflix_backend.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
    boolean existsByTitle(String title);
}