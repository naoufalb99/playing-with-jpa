package com.naoufalb.movies.domain;

import com.naoufalb.movies.holder.ColumnNames;
import com.naoufalb.movies.holder.TableNames;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = TableNames.GENRES)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Genre extends AbstractEntity {

    @Column(name = ColumnNames.GENRE_NAME)
    String name;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    List<Movie> movies;
}
