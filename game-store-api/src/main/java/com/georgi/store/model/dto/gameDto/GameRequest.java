package com.georgi.store.model.dto.gameDto;
import java.util.List;

public record GameRequest(
        String title, //perform check, to not allowed duplicates
        String categoryId, // need to check that the category exists (relationship with entity)
        List<String> platforms // need to check that the platforms exists (relationship with entity)
) {
}
