package com.gleam.backend.game.mapper;

import com.gleam.backend.common.dto.GameDetailsDto;
import com.gleam.backend.game.domain.GameDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameDetailsMapper {

    GameDetailsDto toDto(GameDetails gameDetails);
}
