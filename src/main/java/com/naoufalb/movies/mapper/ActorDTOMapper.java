package com.naoufalb.movies.mapper;

import com.naoufalb.movies.domain.Actor;
import com.naoufalb.movies.dto.ActorDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ActorDTOMapper {

    public enum ActorAssociation {
        MOVIES,
        ALL,
        NONE
    }

    public static ActorDTO toDTO(Actor actor, Set<ActorAssociation> associations) {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setFirstName(actor.getFirstName());
        actorDTO.setLastName(actor.getLastName());
        if(checkAssociation(associations, ActorAssociation.MOVIES)) {
            actorDTO.setMovies(MovieDTOMapper.toDTOs(actor.getMovies(), MovieDTOMapper.MovieAssociation.NONE));
        }
        return actorDTO;
    }



    public static ActorDTO toDTO(Actor actor, ActorAssociation association) {
        return toDTO(actor, Set.of(association));
    }

    public static ActorDTO toDTO(Actor actor) {
        return toDTO(actor, ActorAssociation.ALL);
    }

    public static List<ActorDTO> toDTOs(List<Actor> actors, Set<ActorAssociation> associations) {
        return actors.stream().map((actor) -> toDTO(actor, associations)).collect(Collectors.toList());
    }

    public static List<ActorDTO> toDTOs(List<Actor> actors, ActorAssociation association) {
        return toDTOs(actors, Set.of(association));
    }

    public static List<ActorDTO> toDTOs(List<Actor> actors) {
        return toDTOs(actors, ActorAssociation.ALL);
    }

    private static boolean checkAssociation(Set<ActorAssociation> associations, ActorAssociation actorAssociation) {
        return associations.contains(actorAssociation) || associations.contains(ActorAssociation.ALL);
    }

}
