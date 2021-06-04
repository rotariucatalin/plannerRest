package com.example.plannerREST.services;

import com.example.plannerREST.dto.ActivityDTO;
import com.example.plannerREST.entities.Activity;

import java.util.List;

public interface ActivityService {

    List<ActivityDTO> getAllActivities(int page, int size, String filter);
    Activity saveActivity(ActivityDTO activityDTO);
    ActivityDTO getActivity(int activityId);
    Activity updateActivity(ActivityDTO activityDTO);
    void removeActivity(int activityId);
}
