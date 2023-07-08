package com.naoufalb.movies.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class MovieDTO {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String title;
    private List<String> genres;
    private String releaseDate;
    private String director;
    private Long duration;
    @JsonInclude(Include.NON_NULL)
    private List<ActorDTO> actors;

}
