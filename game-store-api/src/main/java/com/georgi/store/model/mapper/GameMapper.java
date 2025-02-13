package com.georgi.store.model.mapper;

import com.georgi.store.model.dto.gameDto.GameRequest;
import com.georgi.store.model.dto.gameDto.GameResponse;
import com.georgi.store.model.entity.Category;
import com.georgi.store.model.entity.Game;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GameMapper {

    public Game toGame(GameRequest gameRequest) {
        return Game.builder()
                .title(gameRequest.title())
                .category(
                        Category.builder()
                                .id(gameRequest.categoryId())
                                .build()
                )
                .build();
    }

    public GameResponse toGameResponse(Game game) {
        return GameResponse.builder()
                .id(game.getId())
                .title(game.getTitle())
                //TODO set the CDN Url
                .imageUrl("Implement Me!")
                .platforms(
                        game.getPlatforms()
                                .stream()
                                .map(platform -> platform.getConsole().name())
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
