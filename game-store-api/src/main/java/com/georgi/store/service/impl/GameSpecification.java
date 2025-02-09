package com.georgi.store.service.impl;

import com.georgi.store.model.entity.Game;
import org.springframework.data.jpa.domain.Specification;

public class GameSpecification {

    public static Specification<Game> byGameId(String title) {
        return (root, query, cb) -> cb.equal(root.get("title"), title);
    }
}
