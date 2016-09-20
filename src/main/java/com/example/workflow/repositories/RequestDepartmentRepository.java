package com.example.workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RequestDepartmentRepository extends JpaRepository<RequestDepartment, Long> {
    RequestDepartment findByName(String name);
    List<RequestDepartment> findAll();
}
