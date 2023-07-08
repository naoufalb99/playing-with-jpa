package com.naoufalb.movies.service;

import com.naoufalb.movies.domain.*;
import com.naoufalb.movies.dto.MovieDTO;
import com.naoufalb.movies.mapper.MovieDTOMapper;
import com.naoufalb.movies.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final GenreRepository genreRepository;
    private final PersonRepository personRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<MovieDTO> getAllMoviesUsingFetch() {
        return MovieDTOMapper.toDTOs(movieRepository.findAllMovies());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<MovieDTO> getAllMovies() {
        return MovieDTOMapper.toDTOs(movieRepository.findAll());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<MovieDTO> getAllMoviesAvoidingCartesianProduct() {
        movieRepository.findAllMoviesWithGenres();
        movieRepository.findAllMoviesWithActors();
        return MovieDTOMapper.toDTOs(movieRepository.findAllMoviesWithDirector());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<MovieDTO> getAllMoviesUsingEntityGraphs() {
        return MovieDTOMapper.toDTOs(movieRepository.findAllUsingEntityGraphs());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Page<MovieDTO> getAllMoviesUsingFetchPaginated(int page, int size) {
        return movieRepository.findAllMoviesPaginated(PageRequest.of(page, size)).map(MovieDTOMapper::toDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Page<MovieDTO> getAllMoviesPaginated(int page, int size) {
        return movieRepository.findAll(PageRequest.of(page, size)).map(MovieDTOMapper::toDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Page<MovieDTO> getAllMoviesAvoidingCartesianProductPaginated(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<UUID> allMoviesIds = movieRepository.findAllMoviesIds(pageRequest);
        movieRepository.findAllByIdWithActors(allMoviesIds.getContent());
        movieRepository.findAllByIdWithGenres(allMoviesIds.getContent());
        List<MovieDTO> movies = MovieDTOMapper.toDTOs(movieRepository.findAllByIdWithDirector(allMoviesIds.getContent()));
        return new PageImpl<>(movies, pageRequest, allMoviesIds.getTotalElements());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addMovies() {
        // Actors
        Actor actor1 = new Actor();
        actor1.setFirstName("Chris");
        actor1.setLastName("Evans");
        actor1.setBirthDate(LocalDate.parse("1981-06-13"));
        actor1.setNationality("US");
        Actor actor2 = new Actor();
        actor2.setFirstName("Robert");
        actor2.setLastName("Downey Jr.");
        actor2.setBirthDate(LocalDate.parse("1965-04-04"));
        actor2.setNationality("US");

        // Director
        Director director = new Director();
        director.setFirstName("Anthony");
        director.setLastName("Russo");
        director.setBirthDate(LocalDate.parse("1970-02-03"));
        director.setNationality("US");

        // Genres
        Genre genre1 = new Genre();
        genre1.setName("Action");
        Genre genre2 = new Genre();
        genre2.setName("Adventure");

        // Movies

        IntStream.range(0, 10000).forEach(i -> {
            Movie movie = new Movie();
            movie.setTitle("Avengers: Endgame " + i);
            movie.setReleaseDate(LocalDate.parse("2019-04-26"));
            movie.setDuration(181L);
            movie.setActors(Set.of(actor1, actor2));
            movie.setDirector(director);
            movie.setGenres(Set.of(genre1, genre2));

            movieRepository.save(movie);
        });


        movieRepository.flush();
    }

    // Doesn't belong here, but it's just for testing purposes
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addPerson() {
        Person person = new Person();
        person.setBirthDate(LocalDate.now());
        person.setFirstName("Naoufal");
        person.setLastName("Bidari");
        person.setNationality("MA");

        personRepository.save(person);
        personRepository.flush();
    }


}
