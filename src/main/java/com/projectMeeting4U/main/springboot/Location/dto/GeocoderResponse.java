package com.projectMeeting4U.main.springboot.Location.dto;

import javax.validation.constraints.NotNull;

public class GeocoderResponse {
    @NotNull
    private String result;

    private String x;

    private String y;

    public String getResult() { return result; }

    public String getX() { return x; }

    public String getY() { return y; }

    public void setResult(String result) { this.result = result; }

    public void setX(String X) { this.x = x; }

    public void setY(String y) { this.y = y; }
}
