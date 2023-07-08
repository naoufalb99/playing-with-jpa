package com.naoufalb.movies.repository;

import com.naoufalb.movies.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActorRepository extends JpaRepository<Actor, UUID> {
}
