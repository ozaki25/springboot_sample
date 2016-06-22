package sample;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    Request findById(Long id);
    List<Request> findAll();
}