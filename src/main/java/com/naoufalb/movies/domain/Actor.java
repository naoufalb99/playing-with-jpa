package com.naoufalb.movies.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Actor extends Person {
    @ManyToMany(mappedBy = "actors", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<Movie> movies;

    @Override
    public String toString() {
        return "Actor{" +
                "\nfirstName=" + firstName +
                "\nlastName=" + lastName +
                "\nmovies=" + movies +
                '}';
    }
}
