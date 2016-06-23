package com.example.workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findById(Long id);
    List<Status> findAll();
}
