package com.example.workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findById(Long id);
    List<Category> findAll();
}
