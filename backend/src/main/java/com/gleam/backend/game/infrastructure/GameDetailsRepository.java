package com.gleam.backend.game.infrastructure;

import com.gleam.backend.game.domain.GameDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface GameDetailsRepository extends MongoRepository<GameDetails, String> {
    Optional<GameDetails> findByGameId(@Param("gameId") String gameId);
}
