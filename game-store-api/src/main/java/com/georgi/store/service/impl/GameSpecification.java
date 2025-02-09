package com.georgi.store.service.impl;

import com.georgi.store.model.entity.Game;
import com.georgi.store.model.enums.SupportedPlatforms;
import org.springframework.data.jpa.domain.Specification;

public class GameSpecification {
    // This class is for defining a set(list) of specification and each specification will be considered as separate filter

    public static Specification<Game> byGameTitle(String title) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("title"), title);
    }

    public static Specification<Game> bySupportedPlatforms(SupportedPlatforms platform) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("platform"), platform);
    }

    public static Specification<Game> byCategoryName(String categoryName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category").get("name"), categoryName);
    }
}
