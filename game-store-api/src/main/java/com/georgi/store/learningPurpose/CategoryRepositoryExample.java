package com.georgi.store.learningPurpose;

import com.georgi.store.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepositoryExample extends JpaRepository<Category, String> {

    // fetch all the categories by name starting with ('ac...')
    // SELECT * FROM Category WHERE name LIKE 'a%' ORDER BY name DESC
    List<Category> findAllByNameStartingWithIgnoreCaseOrderByNameDesc(String name);

    // JPQL syntax -> Advantages => it will be validated by Spring Data before the app starts
    // Methods like 'lower' will be supported no matter what the SQL database is MYSQL, MariaDB, PostgresSQL, Microsoft etc.
    // Hibernate will take care of the method 'lower' to be translated to the right query of the database
    @Query("""
            SELECT c FROM Category c
            WHERE c.name LIKE lower(:catName)
            ORDER BY c.name ASC
            """)
    List<Category> findAllByName(@Param("catName") String categoryName);

    // Native Query syntax -> Must use the table name cannot use the Java Object as reference to the table
    // Methods like 'lower' will not work if the SQL database does not support it or the name of the method is different 
    // Will not be validated at start of the app may cause errors in runtime
    @NativeQuery(value = """
            SELECT * FROM categories
            WHERE name LIKE lower(:catName)
            ORDER BY name DESC
            """)
    List<Category> findAllByNameUsingNativeQuery(@Param("catName") String name);

    //Using Named Query from the entity itself
    @Query(name = "Category.findByName")
    List<Category> findByName(@Param("catName") String categoryName);


}
