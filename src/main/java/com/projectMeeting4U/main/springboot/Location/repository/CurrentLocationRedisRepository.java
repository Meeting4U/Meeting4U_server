package com.projectMeeting4U.main.springboot.Location.repository;

import com.projectMeeting4U.main.springboot.Location.entity.CurrentLocationRedis;
import org.springframework.data.repository.CrudRepository;

public interface CurrentLocationRedisRepository extends CrudRepository<CurrentLocationRedis, String> {

}
