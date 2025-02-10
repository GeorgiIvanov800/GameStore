package com.georgi.store.model.mapper;

import com.georgi.store.model.dto.gameDto.GameRequest;
import com.georgi.store.model.entity.Category;
import com.georgi.store.model.entity.Game;
import org.springframework.stereotype.Component;

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
}
