package com.example.workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(Long id);
    User findByUid(String uid);
    List<User> findByTeam(String team);
    List<User> findByJobLevelLessThanEqual(Integer jobLevel);
    List<User> findByTeamAndJobLevelLessThanEqual(String team, Integer jobLevel);
    List<User> findAll();
}
