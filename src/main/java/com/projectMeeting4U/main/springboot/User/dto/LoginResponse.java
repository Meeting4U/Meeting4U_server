package com.projectMeeting4U.main.springboot.User.dto;

import com.projectMeeting4U.main.springboot.Meeting.entity.Meeting;
import com.projectMeeting4U.main.springboot.User.entity.User;

import javax.validation.constraints.NotNull;
import java.util.List;

public class LoginResponse {
    @NotNull
    private String loginResult;

    private String errorMsg;

    private String jwtToken;

    private String userId;

    private List<MeetingInfo> meetingInfoList;

    public String getLoginResult() { return loginResult; }

    public String getErrorMsg() { return errorMsg; }

    public String getJwtToken() { return jwtToken; }

    public String getUserId() { return userId; }

    public List<MeetingInfo> getMeetingInfoList() { return meetingInfoList; }

    public void setLoginResult(String loginResult) { this.loginResult = loginResult; }

    public void setErrorMsg(String errorMsg) { this.errorMsg = errorMsg; }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public void setUserId(String userId) { this.userId = userId; }

    public void setMeetingInfoList(List<MeetingInfo> meetingInfoList) { this.meetingInfoList = meetingInfoList; }

}
