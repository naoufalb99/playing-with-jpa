package com.naoufalb.movies.repository;

import com.naoufalb.movies.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
}
