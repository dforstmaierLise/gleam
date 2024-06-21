package com.gleam.backend.game.mapper;

import com.gleam.backend.game.domain.Game;
import com.gleam.backend.game.ui.dto.GameDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {

    GameDto toDto(Game game);

    List<GameDto> toDtoList(List<Game> games);
}
