package com.georgi.store.repository;

import com.georgi.store.model.entity.Category;
import com.georgi.store.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {

    // fetch all games by category (v1)
    List<Game> findAllByCategory(Category category);

    // fetch all games by category (v2)
    List<Game> findAllByCategoryId(String categoryId);

    /*
 fetch all games where the category equals 'Action'
     SELECT g.* FROM category c INNER JOIN game g on g.category_id = c.id
    WHERE c.name = 'Action'
*/
    List<Game> findAllByCategoryName(String categoryName);

    //JPQL syntax
    @Query("""
            SELECT c FROM Category c
            WHERE c.name LIKE :name
            ORDER BY c.name ASC
            """)
    List<Category> findAllByName(String name);
}
