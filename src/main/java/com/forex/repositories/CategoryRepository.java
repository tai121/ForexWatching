package com.forex.repositories;

import com.forex.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("select c.name from Category c")
    public List<String> listName();
    @Query("select c from Category c where c.name=?1")
    public Category findByName(String name);
}
