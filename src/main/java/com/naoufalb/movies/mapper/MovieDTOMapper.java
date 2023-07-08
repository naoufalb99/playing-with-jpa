package com.naoufalb.movies.mapper;

import com.naoufalb.movies.domain.Movie;
import com.naoufalb.movies.dto.MovieDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.naoufalb.movies.mapper.ActorDTOMapper.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieDTOMapper {

    public static enum MovieAssociation {
        ACTORS,
        DIRECTOR,
        GENRES,
        ALL,

        NONE
    }

    public static MovieDTO toDTO(Movie movie, Set<MovieAssociation> associations) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setReleaseDate(movie.getReleaseDate().format(MovieDTO.DATE_FORMATTER));
        movieDTO.setDuration(movie.getDuration());
        if (checkAssociation(associations, MovieAssociation.ACTORS)) {
            movieDTO.setActors(ActorDTOMapper.toDTOs(movie.getActors().stream().toList(), ActorAssociation.NONE));
        }
        if(checkAssociation(associations, MovieAssociation.GENRES)) {
            movieDTO.setGenres(movie.getGenres().stream().map(genre -> genre.getName()).collect(Collectors.toList()));
        }
        if (checkAssociation(associations, MovieAssociation.DIRECTOR)) {
            movieDTO.setDirector(movie.getDirector().getFirstName() + " " + movie.getDirector().getLastName());
        }
        return movieDTO;
    }

    public static MovieDTO toDTO(Movie movie, MovieAssociation association) {
        return toDTO(movie, Set.of(association));
    }

    public static MovieDTO toDTO(Movie movie) {
        return toDTO(movie, MovieAssociation.ALL);
    }

    public static List<MovieDTO> toDTOs(List<Movie> movies, Set<MovieAssociation> associations) {
        return movies.stream().map(movie -> toDTO(movie, associations)).collect(Collectors.toList());
    }

    public static List<MovieDTO> toDTOs(List<Movie> movies, MovieAssociation associations) {
        return toDTOs(movies, Set.of(associations));
    }

    public static List<MovieDTO> toDTOs(List<Movie> movies) {
        return toDTOs(movies, MovieAssociation.ALL);
    }


    private static boolean checkAssociation(Set<MovieAssociation> associations, MovieAssociation movieAssociation) {
        return associations.contains(movieAssociation) || associations.contains(MovieAssociation.ALL);
    }

}
