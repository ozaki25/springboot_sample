package com.example.workflow;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Document findById(Long id);
    List<Document> findByRequestId(Long requestId);
    List<Document> findByRequestIdIsNull();
    List<Document> findAll();
}
