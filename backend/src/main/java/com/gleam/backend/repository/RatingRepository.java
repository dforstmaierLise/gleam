package com.gleam.backend.repository;

import com.gleam.backend.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RatingRepository extends MongoRepository<Rating, String> {
}
