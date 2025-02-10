package com.georgi.store.model.dto.gameDto;

import com.georgi.store.model.entity.Platform;

import java.util.Set;

public record GameRequest(
        String title, //perform check, to not allowed duplicates
        String categoryId, // need to check that the category exists (relationship with entity)
        Set<String> platforms // need to check that the platforms exists (relationship with entity)
) {
}
