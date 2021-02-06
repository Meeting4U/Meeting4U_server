package com.projectMeeting4U.main.springboot.Meeting.service;

import com.projectMeeting4U.main.springboot.Meeting.dto.*;
import com.projectMeeting4U.main.springboot.Meeting.entity.Meeting;
import com.projectMeeting4U.main.springboot.Meeting.entity.MeetingUser;

import java.util.HashMap;
import java.util.List;

public interface MeetingInterface {
    public MeetingResponse getMeetingList(String userId);
    public NewMeetingResponse createMeeting(NewMeetingRequest meetingRequest);
    public JoinMeetingResponse joinMeeting(JoinMeetingRequest joinMeetingRequest);
}
