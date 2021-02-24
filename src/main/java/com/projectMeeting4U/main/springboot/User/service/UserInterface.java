package com.projectMeeting4U.main.springboot.User.service;

import com.projectMeeting4U.main.springboot.Meeting.entity.MeetingUser;
import com.projectMeeting4U.main.springboot.User.dto.*;
import com.projectMeeting4U.main.springboot.User.entity.User;

public interface UserInterface {
    public NewUserResponse setUser(NewUserRequest newUserRequest); // 유저 등록
    public UserResponse getUser(User user); // 유저 조회
    public LoginResponse getMeetingInfo(User user, LoginRequest loginRequest);

}
