package com.example.plannerREST.services;

import com.example.plannerREST.dto.ActivityDTO;
import com.example.plannerREST.entities.Activity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface ActivityService {

    List<ActivityDTO> getAllActivities(int page, int size, String filter);
    @CachePut(value = "activities", key = "#activityDTO.getId()")
    Activity saveActivity(ActivityDTO activityDTO);
    @Cacheable(value = "activities", key = "#activityId")
    ActivityDTO getActivity(int activityId);
    @CachePut(value = "activities", key = "#activityDTO")
    Activity updateActivity(ActivityDTO activityDTO);
    @CacheEvict(value = "activities", allEntries = true)
    void removeActivity(int activityId);
}
