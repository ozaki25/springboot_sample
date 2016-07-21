package com.example.workflow;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceptnistRepository extends JpaRepository<Receptnist, Long> {
    Receptnist findById(Long id);
    List<Receptnist> findAll();
}
