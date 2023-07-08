package com.naoufalb.movies.holder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ColumnNames {
    // Commons
    public static final String ID = "id";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    // Persons
    public static final String PERSON_TYPE = "type";
    public static final String PERSON_FIRST_NAME = "first_name";
    public static final String PERSON_LAST_NAME = "last_name";
    public static final String PERSON_BIRTH_DATE = "birth_date";
    public static final String PERSON_NATIONALITY = "nationality";

    // Movies
    public static final String MOVIE_TITLE = "title";
    public static final String MOVIE_RELEASE_DATE = "release_date";
    public static final String MOVIE_DURATION = "duration";
    public static final String MOVIE_DIRECTOR_ID = "director_id";

    // Genres
    public static final String GENRE_NAME = "name";

    // MoviesActors
    public static final String MOVIE_ACTOR_MOVIE_ID = "movie_id";
    public static final String MOVIE_ACTOR_ACTOR_ID = "actor_id";

    // MoviesGenres
    public static final String MOVIE_GENRE_MOVIE_ID = "movie_id";
    public static final String MOVIE_GENRE_GENRE_ID = "genre_id";
}
