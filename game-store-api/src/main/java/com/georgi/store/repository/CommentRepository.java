package com.georgi.store.repository;

import com.georgi.store.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    long countByGameId(String gameId);

}
