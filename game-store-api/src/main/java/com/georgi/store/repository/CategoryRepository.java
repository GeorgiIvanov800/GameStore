package com.georgi.store.repository;

import com.georgi.store.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category> findAllByNameStartingWithIgnoreCaseOrderByNameDesc(String name);
}
