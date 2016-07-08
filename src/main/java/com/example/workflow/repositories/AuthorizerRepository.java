package com.example.workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuthorizerRepository extends JpaRepository<Authorizer, Long> {
    Authorizer findById(Long id);
    List<Authorizer> findAll();
}
