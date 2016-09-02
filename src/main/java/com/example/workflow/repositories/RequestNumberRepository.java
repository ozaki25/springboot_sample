package com.example.workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RequestNumberRepository extends JpaRepository<RequestNumber, Long> {
    RequestNumber findByYear(int year);
}
