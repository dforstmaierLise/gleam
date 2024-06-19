package com.gleam.backend.mapper;

import com.gleam.backend.dto.RatingDto;
import com.gleam.backend.model.Rating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    Rating toEntity(RatingDto ratingDto);
}
