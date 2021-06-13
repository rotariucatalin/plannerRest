package com.example.plannerREST.service_implementation;

import com.example.plannerREST.dto.ActivityDTO;
import com.example.plannerREST.dto.ContactDTO;
import com.example.plannerREST.entities.Activity;
import com.example.plannerREST.entities.Contact;
import com.example.plannerREST.repositories.ActivityRepository;
import com.example.plannerREST.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<ActivityDTO> getAllActivities(int page, int size, String filter) {

        Pageable paging = PageRequest.of(page, size);

        Page<Activity> activitiesPage = activityRepository.findAll(paging, filter);
        List<Activity> activities = activitiesPage.getContent();

        return activities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Activity saveActivity(ActivityDTO activityDTO) {

        Activity activity = new Activity();
        activity.setSubject(activityDTO.getSubject());
        activity.setReference(activityDTO.getReference());
        activity.setType(activityDTO.getType());
        activity.setDate(activityDTO.getDate());
        activity.setStatus(activityDTO.getStatus());
        activity.setLocation(activityDTO.getLocation());
        activity.setLocation(activityDTO.getLocation());

        Contact contact = new Contact();
        contact.setId(activityDTO.getContact().getId());

        activity.setContact(contact);

        activityRepository.save(activity);

        return activity;
    }

    @Override
    public ActivityDTO getActivity(int activityId) {

        Activity activity = activityRepository.findById(activityId).get();

        return convertToDTO(activity);
    }

    @Override
    @Transactional
    public Activity updateActivity(ActivityDTO activityDTO) {
        return this.saveActivity(activityDTO);
    }

    @Override
    @Transactional
    public void removeActivity(int activityId) {

        activityRepository.deleteById(activityId);
    }

    private ActivityDTO convertToDTO(Activity activity) {

        ContactDTO contactDTO = new ContactDTO(
                activity.getContact().getId(),
                activity.getContact().getName(),
                activity.getContact().getPosition(),
                activity.getContact().getDepartment(),
                activity.getContact().getCountry(),
                activity.getContact().getEmail(),
                activity.getContact().getPhone(),
                activity.getContact().getStatus(),
                activity.getContact().getConsent());

        ActivityDTO activityDTO = new ActivityDTO(
                activity.getId(),
                activity.getSubject(),
                activity.getReference(),
                activity.getType(),
                activity.getDate(),
                activity.getStatus(),
                activity.getLocation(),
                contactDTO
                );

        return activityDTO;
    }
}
