package com.projectMeeting4U.main.springboot.Meeting.repository;

import com.projectMeeting4U.main.springboot.Meeting.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Integer> {
}
