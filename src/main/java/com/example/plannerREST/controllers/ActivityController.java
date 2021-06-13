package com.example.plannerREST.controllers;

import com.example.plannerREST.dto.ActivityDTO;
import com.example.plannerREST.dto.CompanyDTO;
import com.example.plannerREST.entities.CustomResponse;
import com.example.plannerREST.exception.ApiRequestException;
import com.example.plannerREST.services.ActivityService;
import com.example.plannerREST.services.CompanyService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/activities")
public class ActivityController {

    private Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CompanyService companyService;

    @PreAuthorize("hasAuthority('View_Activity')")
    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Get all activities",
            notes = "Get all activities. You can filter/change the number of companies that are returned",
            response = ActivityDTO.class
    )
    public List<ActivityDTO> getAllActivities(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "5") int size,
                                              @RequestParam(defaultValue = "") String filter) {
        return activityService.getAllActivities(page, size, filter);
    }

    @PreAuthorize("hasAuthority('Add_Activity')")
    @GetMapping(value = "/getAllCompanies", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Getting all companies",
            notes = "Getting all companies",
            response = CompanyDTO.class
    )
    public List<CompanyDTO> getAllCompanies() throws ApiRequestException {

        try {
            return companyService.findAllCompanies();
        } catch (Exception e) {
            throw new ApiRequestException("Cannot get activity list!");
        }
    }


    @PreAuthorize("hasAuthority('Add_Activity')")
    @PostMapping(value = "/addActivity", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Add new activity",
            notes = "Add new activity",
            response = CustomResponse.class
    )
    public CustomResponse saveActivity(@RequestBody ActivityDTO activityDTO) throws ApiRequestException {

        try {

            activityService.saveActivity(activityDTO);
            return new CustomResponse("Activity successfully added", OK);

        } catch (Exception e) {
            throw new ApiRequestException("Activity could not be added!");
        }
    }

    @PreAuthorize("hasAuthority('Edit_Activity')")
    @GetMapping(value = "/editActivity/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Edit specific activity",
            notes = "Edit specific activity based on specific id",
            response = ActivityDTO.class
    )
    public ActivityDTO editActivity(@PathVariable(value = "id") int id) throws ApiRequestException {

        try {
            return activityService.getActivity(id);
        } catch (Exception e) {
            throw new ApiRequestException("Activity not found!");
        }
    }

    @PreAuthorize("hasAuthority('Edit_Activity')")
    @PutMapping(value = "/updateActivity", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Update activity",
            notes = "Update activity based on specif id",
            response = CustomResponse.class
    )
    public CustomResponse updateActivity(@RequestBody ActivityDTO activityDTO) throws ApiRequestException {

        try {
            activityService.updateActivity(activityDTO);
            return new CustomResponse("Activity successfully updated", OK);
        } catch (Exception e) {
            throw new ApiRequestException("Activity could not be updated!");
        }

    }

    @PreAuthorize("hasAuthority('Delete_Activity')")
    @DeleteMapping(value = "/removeActivity/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Delete activity",
            notes = "Delete activity based on specific id",
            response = ActivityDTO.class
    )
    public CustomResponse removeActivity(@PathVariable(value = "id") int id) throws ApiRequestException {

        try {

            activityService.removeActivity(id);
            return new CustomResponse("Activity successfully removed", OK);

        } catch (Exception e) {
            throw new ApiRequestException("Activity could not be deleted!");
        }

    }
}
