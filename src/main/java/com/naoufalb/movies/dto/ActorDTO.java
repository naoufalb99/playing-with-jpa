package com.naoufalb.movies.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class ActorDTO {
    private String firstName;
    private String lastName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MovieDTO> movies;
}
