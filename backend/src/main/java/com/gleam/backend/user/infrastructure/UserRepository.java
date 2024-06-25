package com.gleam.backend.user.infrastructure;

import com.gleam.backend.user.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String name);
}




