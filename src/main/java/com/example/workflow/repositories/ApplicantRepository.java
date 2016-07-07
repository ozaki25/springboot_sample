package com.example.workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Applicant findById(Long id);
    List<Applicant> findAll();
}
