--changeset naoufal:0.1
CREATE TABLE persons
(
    id          UUID        NOT NULL,
    type        VARCHAR(31) NOT NULL,
    first_name  VARCHAR(255),
    last_name   VARCHAR(255),
    birth_date  DATE,
    nationality VARCHAR(2),
    created_at  TIMESTAMP(6),
    updated_at  TIMESTAMP(6),
    PRIMARY KEY (id)
);

CREATE TABLE movies
(
    id           UUID NOT NULL,
    title        VARCHAR(255),
    release_date DATE,
    duration     BIGINT,
    director_id  UUID,
    created_at   TIMESTAMP(6),
    updated_at   TIMESTAMP(6),
    PRIMARY KEY (id),
    FOREIGN KEY (director_id) REFERENCES persons (id)
);

CREATE TABLE genres
(
    id         UUID NOT NULL,
    name       VARCHAR(255),
    created_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    PRIMARY KEY (id)
);

CREATE TABLE movies_genres
(
    movie_id UUID NOT NULL,
    genre_id UUID NOT NULL,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES movies (id),
    FOREIGN KEY (genre_id) REFERENCES genres (id)
);

CREATE TABLE movies_actors
(
    movie_id UUID NOT NULL,
    actor_id UUID NOT NULL,
    PRIMARY KEY (movie_id, actor_id),
    FOREIGN KEY (movie_id) REFERENCES movies (id),
    FOREIGN KEY (actor_id) REFERENCES persons (id)
);

