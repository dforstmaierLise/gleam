package com.gleam.backend.mapper;

import com.gleam.backend.dto.ReviewDto;
import com.gleam.backend.model.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    Review toEntity(ReviewDto reviewDto);
}
