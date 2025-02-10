package com.georgi.store.repository;

import com.georgi.store.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsById(String id);
}
