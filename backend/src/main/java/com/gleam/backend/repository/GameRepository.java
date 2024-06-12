package com.gleam.backend.repository;

import com.gleam.backend.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GameRepository extends MongoRepository<Game, String> {
}
