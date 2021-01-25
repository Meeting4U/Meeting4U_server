package com.projectMeeting4U.main.springboot.User.service;

import com.projectMeeting4U.main.springboot.User.dto.LoginResponse;
import com.projectMeeting4U.main.springboot.User.dto.NewUserRequest;
import com.projectMeeting4U.main.springboot.User.dto.NewUserResponse;
import com.projectMeeting4U.main.springboot.User.dto.UserResponse;
import com.projectMeeting4U.main.springboot.User.entity.User;

public interface UserInterface {
    public NewUserResponse setUser(NewUserRequest newUserRequest); // 유저 등록
    public UserResponse getUser(User user); // 유저 조회

}
