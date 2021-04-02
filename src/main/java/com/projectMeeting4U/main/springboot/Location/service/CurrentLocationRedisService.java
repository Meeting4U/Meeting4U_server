package com.projectMeeting4U.main.springboot.Location.service;

import com.projectMeeting4U.main.springboot.Location.dto.CurrentLocationRedisResponse;
import com.projectMeeting4U.main.springboot.Location.dto.GeocoderResponse;
import com.projectMeeting4U.main.springboot.Location.dto.NewCurrentLocationRedisRequest;
import com.projectMeeting4U.main.springboot.Location.dto.NewCurrentLocationRedisResponse;
import com.projectMeeting4U.main.springboot.Location.entity.CurrentLocationRedis;
import com.projectMeeting4U.main.springboot.Location.repository.CurrentLocationRedisRepository;
import com.projectMeeting4U.main.springboot.Meeting.entity.MeetingUser;
import com.projectMeeting4U.main.springboot.Meeting.repository.MeetingUserRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CurrentLocationRedisService implements CurrentLocationRedisInterface {

    @Autowired
    private CurrentLocationRedisRepository currentLocationRedisRepository;

    @Autowired
    private MeetingUserRepository meetingUserRepository;


    @Override
    public NewCurrentLocationRedisResponse setCurrentLocation(NewCurrentLocationRedisRequest newCurrentRedisLocationRequest) {
        NewCurrentLocationRedisResponse newCurrentRedisLocationResponse = new NewCurrentLocationRedisResponse();

        CurrentLocationRedis currentLocationRedis = new CurrentLocationRedis(
                newCurrentRedisLocationRequest.getUserId(),
                newCurrentRedisLocationRequest.getLatitude(),
                newCurrentRedisLocationRequest.getLongitude());

        try {
            currentLocationRedisRepository.save(currentLocationRedis);
            newCurrentRedisLocationResponse.setResult("true");
            newCurrentRedisLocationResponse.setLatitude(newCurrentRedisLocationRequest.getLatitude());
            newCurrentRedisLocationResponse.setLongitude(newCurrentRedisLocationRequest.getLongitude());
        } catch (Exception e) {
            newCurrentRedisLocationResponse.setResult("false");
        }

        return newCurrentRedisLocationResponse;
    }

    @Override
    public CurrentLocationRedisResponse getCurrentLocationList(Integer meetingId) {
        CurrentLocationRedisResponse currentLocationRedisResponse = new CurrentLocationRedisResponse();

        List<MeetingUser> meetingUserList = meetingUserRepository.findByMeetingId(meetingId);

        List<String> userIdList = meetingUserList.stream().map(meetingUser -> meetingUser.getUser().getUsername()).collect(Collectors.toList());

        if(userIdList == null) {
            currentLocationRedisResponse.setResult("false");
            return currentLocationRedisResponse;
        }

        System.out.println(userIdList);
        List<CurrentLocationRedis> currentLocationRedisList = userIdList.stream().map(userId -> currentLocationRedisRepository.findById(userId).get()).collect(Collectors.toList());

        currentLocationRedisResponse.setResult("true");
        currentLocationRedisResponse.setCurrentLocationRedisList(currentLocationRedisList);

        return currentLocationRedisResponse;
    }

    @Override
    public GeocoderResponse getLocationFromGeocoder(String address) {
        GeocoderResponse geocoderResponse = new GeocoderResponse();
        String apiKey = "126ee4fc9fa71833602932d4ba42d7db";
        String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        try {
            address = URLEncoder.encode(address, "UTF-8");
            headers.add("Authorization", "KakaoAK " + apiKey);
            headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

            URI url = URI.create(apiUrl + "?query=" + address);
            RequestEntity<String> rq = new RequestEntity<>(headers, HttpMethod.GET, url);
            ResponseEntity<Map> re = restTemplate.exchange(rq, Map.class);

            ArrayList<Object> arrayList = new ArrayList();
            arrayList = (ArrayList) re.getBody().get("documents");
            LinkedHashMap linkedHashMap = (LinkedHashMap) arrayList.get(0);
            LinkedHashMap linkedHashMap1 = (LinkedHashMap) linkedHashMap.get("address");

            System.out.println("ASDASDAS" + linkedHashMap.get("address").getClass().getName());
            System.out.println("X = " + linkedHashMap1.get("x").toString());
            System.out.println("Y = " + linkedHashMap1.get("y").toString());
            geocoderResponse.setResult("true");
            geocoderResponse.setLatitude(linkedHashMap1.get("x").toString());
            geocoderResponse.setLongitude(linkedHashMap1.get("y").toString());

        } catch (Exception e) {
            geocoderResponse.setResult("false");
        }

        return geocoderResponse;
    }
}
