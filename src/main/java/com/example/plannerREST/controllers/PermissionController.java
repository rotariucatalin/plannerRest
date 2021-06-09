package com.example.plannerREST.controllers;

import com.example.plannerREST.dto.PermissionDTO;
import com.example.plannerREST.entities.CustomResponse;
import com.example.plannerREST.exception.ApiRequestException;
import com.example.plannerREST.services.PermissionService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(
            value = "Getting all permissions",
            notes = "Get all permissions. You can filter/change the number of companies that are returned",
            response = PermissionDTO.class
    )
    public List<PermissionDTO> getAllPermissions() throws ApiRequestException {

        try {
            return permissionService.getAllPermissions();
        } catch (Exception e) {
            throw new ApiRequestException("Cannot get permission list!");
        }
    }

    @PreAuthorize("hasAuthority('Add_Permission')")
    @PostMapping(value = "/addPermission", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Add new permission",
            notes = "Add new permission",
            response = CustomResponse.class
    )
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
    @ApiOperation(
            value = "Edit specific permission",
            notes = "Edit specific permission based on specific id",
            response = PermissionDTO.class
    )
    public PermissionDTO editPermission(@PathVariable("id") int id) throws ApiRequestException {

        try {
            return permissionService.getPermission(id);
        } catch (Exception e) {
            throw new ApiRequestException("Permission not found");
        }
    }

    @PreAuthorize("hasAuthority('Edit_Permission')")
    @PutMapping(value = "/updatePermission", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Update specific permission",
            notes = "Update specific permission based on specific id",
            response = CustomResponse.class
    )
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
    @ApiOperation(
            value = "Delete specific permission",
            notes = "Delete specific permission based on specific id",
            response = CustomResponse.class
    )
    public CustomResponse removePermission(@PathVariable("id") int id) throws ApiRequestException {

        try {
            permissionService.removePermission(id);
            return new CustomResponse("Permission successfully deleted", OK);
        } catch (Exception e) {
            throw new ApiRequestException("Permission could not be deleted");
        }
    }

}
