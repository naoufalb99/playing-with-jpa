package com.naoufalb.movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naoufalb.movies.dto.MovieDTO;
import com.naoufalb.movies.repository.MovieRepository;
import com.naoufalb.movies.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Slf4j
@AllArgsConstructor
public class MoviesApplication implements CommandLineRunner {

    private final MovieService movieService;
    private final MovieRepository movieRepository;

    public static void main(String[] args) {
        SpringApplication.run(MoviesApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.info("Adding movies");
        movieService.addMovies();
        log.info("Adding person");
        movieService.addPerson();

        long startTime, endTime;

//        startTime = System.currentTimeMillis();
//        movieService.getAllMoviesUsingFetch();
//        endTime = System.currentTimeMillis();
//        log.info("(getAllMoviesUsingFetch) Total execution time: {} ms", (endTime - startTime));

//        startTime = System.currentTimeMillis();
//        movieService.getAllMovies();
//        endTime = System.currentTimeMillis();
//        log.info("(getAllMovies) Total execution time: {} ms", (endTime - startTime));

//        startTime = System.currentTimeMillis();
//        movieService.getAllMoviesAvoidingCartesianProduct();
//        endTime = System.currentTimeMillis();
//        log.info("(getAllMoviesAvoidingCartesianProduct) Total execution time: {} ms", (endTime - startTime));

//        startTime = System.currentTimeMillis();
//        movieService.getAllMoviesUsingEntityGraphs();
//        endTime = System.currentTimeMillis();
//        log.info("(getAllMoviesUsingEntityGraphs) Total execution time: {} ms", (endTime - startTime));

        // Pagination
//        startTime = System.currentTimeMillis();
//        Page<MovieDTO> page = movieService.getAllMoviesUsingFetchPaginated(0, 10);
//        endTime = System.currentTimeMillis();
//        log.info("(getAllMoviesUsingFetchPaginated) Total execution time: {} ms", (endTime - startTime));

        startTime = System.currentTimeMillis();
        Page<MovieDTO> movies = movieService.getAllMoviesPaginated(0, 2);
        endTime = System.currentTimeMillis();
        log.info("(getAllMoviesPaginated) Total execution time: {} ms", (endTime - startTime));
//
//        startTime = System.currentTimeMillis();
//        movieService.getAllMoviesAvoidingCartesianProductPaginated(0, 500);
//        endTime = System.currentTimeMillis();
//        log.info("(getAllMoviesAvoidingCartesianProductPaginated) Total execution time: {} ms", (endTime - startTime));

        log.info("Movies: {}", getJson(movies));
    }

    private static String getJson(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
}
