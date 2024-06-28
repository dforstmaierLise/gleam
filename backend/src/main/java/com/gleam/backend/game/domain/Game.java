package com.gleam.backend.game.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Game {
    @Id
    private String id;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date releaseDate;
    private String developer;
    private List<String> reviewIds = new ArrayList<>();
    private int likes;
    private int dislikes;
    private List<String> platforms = new ArrayList<>();
}
