package com.gleam.backend.game.infrastructure;

import com.gleam.backend.game.domain.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GameRepository extends MongoRepository<Game, String> {
}
