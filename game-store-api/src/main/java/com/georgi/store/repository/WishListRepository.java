package com.georgi.store.repository;

import com.georgi.store.model.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, String> {
    @Query("""
            SELECT COUNT(w)
            FROM WishList w
            JOIN w.games g
            WHERE g.id = :gameId
            """)
    long countByGameId(String gameId);


}
