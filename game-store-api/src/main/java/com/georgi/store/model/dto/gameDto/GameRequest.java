package com.georgi.store.model.dto.gameDto;

import com.georgi.store.model.entity.Platform;

import java.util.Set;

public record GameRequest(
        String name,
        String categoryId,
        Set<Platform> platforms
) {
}
