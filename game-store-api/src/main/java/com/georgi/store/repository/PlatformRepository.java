package com.georgi.store.repository;

import com.georgi.store.model.entity.Platform;
import com.georgi.store.model.enums.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface PlatformRepository extends JpaRepository<Platform, String> {

    @Query("""
            SELECT p from Platform p
            WHERE p.console in :consoles
            """)
    List<Platform> findAllByConsoleIn(@Param("consoles") List<Console> selectedConsoles);
}
