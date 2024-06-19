package com.gleam.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public record GameDto (String title, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") Date releaseDate, String developer, List<RatingDto> ratings) {}
