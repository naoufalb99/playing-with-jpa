package com.naoufalb.movies.repository;

import com.naoufalb.movies.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.genres  JOIN FETCH m.actors LEFT JOIN FETCH m.director")
    List<Movie> findAllMovies();

    @Query(
            value = "SELECT m FROM Movie m LEFT JOIN FETCH m.genres  JOIN FETCH m.actors LEFT JOIN FETCH m.director",
            countQuery = "SELECT count(m) FROM Movie m"
    )
    Page<Movie> findAllMoviesPaginated(Pageable pageable);

    @Query(
            value = "SELECT m.id FROM Movie m",
            countQuery = "SELECT count(m) FROM Movie m"
    )
    Page<UUID> findAllMoviesIds(Pageable pageable);

    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.actors WHERE m.id IN :ids")
    List<Movie> findAllByIdWithActors(List<UUID> ids);

    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.genres WHERE m.id IN :ids")
    List<Movie> findAllByIdWithGenres(List<UUID> ids);

    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.director WHERE m.id IN :ids")
    List<Movie> findAllByIdWithDirector(List<UUID> ids);

    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.genres")
    List<Movie> findAllMoviesWithGenres();

    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.actors")
    List<Movie> findAllMoviesWithActors();

    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.director")
    List<Movie> findAllMoviesWithDirector();

    @EntityGraph(attributePaths = {"genres", "actors", "director"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT m FROM Movie m")
    List<Movie> findAllUsingEntityGraphs();
}
