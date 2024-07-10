package com.gleam.backend.game.infrastructure;

import com.gleam.backend.game.domain.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface GameRepository extends MongoRepository<Game, String> {

    @Query("{ 'platforms': { $in: ?0 }, 'title': { $regex: '^?1', $options: 'i' } }")
    List<Game> findByPlatformsAndTitle(List<String> platforms, String prefix);
}
