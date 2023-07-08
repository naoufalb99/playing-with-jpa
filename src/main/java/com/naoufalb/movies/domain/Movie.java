package com.naoufalb.movies.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.naoufalb.movies.holder.ColumnNames;
import com.naoufalb.movies.holder.TableNames;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = TableNames.MOVIES)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, resolver = SimpleObjectIdResolver.class, property = "id")
public class Movie extends AbstractEntity {
    @Column(name = ColumnNames.MOVIE_TITLE)
    String title;

    @Temporal(TemporalType.DATE)
    @Column(name = ColumnNames.MOVIE_RELEASE_DATE)
    LocalDate releaseDate;

    @Column(name = ColumnNames.MOVIE_DURATION)
    Long duration;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = TableNames.MOVIES_ACTORS,
            joinColumns = @JoinColumn(name = ColumnNames.MOVIE_ACTOR_MOVIE_ID),
            inverseJoinColumns = @JoinColumn(name = ColumnNames.MOVIE_ACTOR_ACTOR_ID)
    )
    Set<Actor> actors;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = ColumnNames.MOVIE_DIRECTOR_ID)
    Director director;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = TableNames.MOVIES_GENRES,
            joinColumns = @JoinColumn(name = ColumnNames.MOVIE_GENRE_MOVIE_ID),
            inverseJoinColumns = @JoinColumn(name = ColumnNames.MOVIE_GENRE_GENRE_ID)
    )
    Set<Genre> genres;

    @Override
    public String toString() {
        return "Movie{" +
                "\ntitle=" + title +
                "\nreleaseDate=" + releaseDate +
                "\nduration=" + duration +
                "\nactors=[" + actors + "]" +
                "\ndirector=" + director +
                "\ngenres=" + genres +
                '}';
    }
}
