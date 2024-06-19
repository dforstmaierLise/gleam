package com.gleam.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public record GameDto (
        @Id String id,
        String title,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") Date releaseDate,
        String developer,
        List<String> reviewIds,
        int likes,
        int dislikes) {}
