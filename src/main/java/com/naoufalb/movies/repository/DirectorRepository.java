package com.naoufalb.movies.repository;

import com.naoufalb.movies.domain.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DirectorRepository extends JpaRepository<Director, UUID> {
}
