package com.projectMeeting4U.main.springboot.Location.controller;

import com.projectMeeting4U.main.springboot.Location.dto.NewDepartLocationRequest;
import com.projectMeeting4U.main.springboot.Location.dto.NewDestLocationRequest;
import com.projectMeeting4U.main.springboot.Location.entity.CurrentLocation;
import com.projectMeeting4U.main.springboot.Location.entity.DepartLocation;
import com.projectMeeting4U.main.springboot.Location.entity.DestinationLocation;
import com.projectMeeting4U.main.springboot.Location.repository.CurrentLocationRepository;
import com.projectMeeting4U.main.springboot.Location.repository.DepartLocationRepository;
import com.projectMeeting4U.main.springboot.Location.repository.DestLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "api")
public class LocationController {
    @Autowired
    DestLocationRepository destLocationRepository;

    @Autowired
    DepartLocationRepository departLocationRepository;

    @Autowired
    CurrentLocationRepository currentLocationRepository;

    @PostMapping("/location/destination")
    @Transactional
    public DestinationLocation newDestLocation(@Valid @RequestBody NewDestLocationRequest newDestinationRequest) {
        DestinationLocation desLoc = new DestinationLocation(newDestinationRequest.getAddress());
        destLocationRepository.save(desLoc);

        return desLoc;
    }

    @PostMapping("/location/department")
    @Transactional
    public DepartLocation newDepartLocation(@Valid @RequestBody NewDepartLocationRequest newDepartLocationRequest) {
        DepartLocation departLoc = new DepartLocation(newDepartLocationRequest.getAddress());
        departLocationRepository.save(departLoc);

        return departLoc;
    }

    @PostMapping("/location/current")
    @Transactional
    public CurrentLocation newCurrentLoacion() {
        CurrentLocation curLoc = new CurrentLocation();
        currentLocationRepository.save(curLoc);

        return curLoc;
    }
}
