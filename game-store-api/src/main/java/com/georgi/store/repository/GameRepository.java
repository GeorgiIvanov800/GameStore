package com.georgi.store.repository;

import com.georgi.store.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, String> {
    boolean existsByTitle(String title);
}
