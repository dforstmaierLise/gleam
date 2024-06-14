package com.gleam.backend.mapper;

import com.gleam.backend.dto.GameDto;
import com.gleam.backend.model.Game;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {

    GameDto toDto(Game game);

    List<GameDto> toDtoList(List<Game> games);
}
