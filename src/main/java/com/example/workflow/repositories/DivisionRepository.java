package com.example.workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DivisionRepository extends JpaRepository<Division, Long> {
    Division findById(Long id);
    List<Division> findByCategory(Long categoryId);
}
