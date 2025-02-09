package com.georgi.store.service.impl;

import com.georgi.store.model.entity.Game;
import com.georgi.store.model.enums.SupportedPlatforms;
import org.springframework.data.jpa.domain.Specification;

public class GameSpecification {
    // This class represent something like a Set of Filters for the query

    public static Specification<Game> byGameTitle(String title) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("title"), title);
    }

    public static Specification<Game> bySupportedPlatforms(SupportedPlatforms platform) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("platform"), platform);
    }


}
