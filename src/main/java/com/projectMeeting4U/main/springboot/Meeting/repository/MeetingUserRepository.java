package com.projectMeeting4U.main.springboot.Meeting.repository;

import com.projectMeeting4U.main.springboot.Meeting.entity.MeetingUser;
import com.projectMeeting4U.main.springboot.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingUserRepository extends JpaRepository<MeetingUser, Integer> {
    List<MeetingUser> findByUser(User user);
}
