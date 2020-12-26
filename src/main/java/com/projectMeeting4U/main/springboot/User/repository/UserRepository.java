package com.projectMeeting4U.main.springboot.User.repository;

import com.projectMeeting4U.main.springboot.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(String userId);
}
