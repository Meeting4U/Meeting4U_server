package com.projectMeeting4U.main.springboot.User.repository;

import com.projectMeeting4U.main.springboot.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(String userId);

//    Optional<User> findByUserId(String email);
}
