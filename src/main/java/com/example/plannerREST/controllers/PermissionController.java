package com.example.plannerREST.controllers;

import com.example.plannerREST.dto.PermissionDTO;
import com.example.plannerREST.entities.CustomResponse;
import com.example.plannerREST.exception.ApiRequestException;
import com.example.plannerREST.services.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/permissions")
public class PermissionController {

    private Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private PermissionService permissionService;

    @PreAuthorize("hasAuthority('View_Permission')")
    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public List<PermissionDTO> getAllPermissions() throws ApiRequestException {

        try {
            return permissionService.getAllPermissions();
        } catch (Exception e) {
            throw new ApiRequestException("Cannot get permission list!");
        }
    }

    @PreAuthorize("hasAuthority('Add_Permission')")
    @PostMapping(value = "/addPermission", produces = APPLICATION_JSON_VALUE)
    public CustomResponse addPermission(@RequestBody PermissionDTO permissionDTO) throws ApiRequestException {

        try {
            permissionService.savePermission(permissionDTO);
            return new CustomResponse("Permission successfully added", OK);
        } catch (Exception  e) {
            throw new ApiRequestException("Permission could not be added!");
        }
    }

    @PreAuthorize("hasAuthority('Edit_Permission')")
    @GetMapping(value = "/editPermission/{id}", produces = APPLICATION_JSON_VALUE)
    public PermissionDTO editPermission(@PathVariable("id") int id) throws ApiRequestException {

        try {
            return permissionService.getPermission(id);
        } catch (Exception e) {
            throw new ApiRequestException("Permission not found");
        }
    }

    @PreAuthorize("hasAuthority('Edit_Permission')")
    @PutMapping(value = "/updatePermission", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public CustomResponse updatePermission(@RequestBody PermissionDTO permissionDTO) throws ApiRequestException {

        try {
            permissionService.updatePermission(permissionDTO);
            return new CustomResponse("Permission successfully updated", OK);
        } catch (Exception e) {
            throw new ApiRequestException("Permission could not be updated!");
        }
    }

    @PreAuthorize("hasAuthority('Delete_Permission')")
    @DeleteMapping(value = "/deletePermission/{id}", produces = APPLICATION_JSON_VALUE)
    public CustomResponse removePermission(@PathVariable("id") int id) throws ApiRequestException {

        try {
            permissionService.removePermission(id);
            return new CustomResponse("Permission successfully deleted", OK);
        } catch (Exception e) {
            throw new ApiRequestException("Permission could not be deleted");
        }
    }

}
