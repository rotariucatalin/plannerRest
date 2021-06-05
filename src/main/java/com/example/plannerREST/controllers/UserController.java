package com.example.plannerREST.controllers;

import com.example.plannerREST.dto.UsersDTO;
import com.example.plannerREST.entities.CustomResponse;
import com.example.plannerREST.exception.ApiRequestException;
import com.example.plannerREST.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('View_User')")
    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public List<UsersDTO> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size,
                                      @RequestParam(defaultValue = "") String filter) throws ApiRequestException {

        try {
            return userService.getAllUsers(page, size, filter);
        } catch (Exception e) {
            throw new ApiRequestException("Could not load users list");
        }
    }

    @PreAuthorize("hasAuthority('Add_User')")
    @PostMapping(value = "/addUser", produces = APPLICATION_JSON_VALUE)
    public CustomResponse addUser(@RequestBody UsersDTO usersDTO) throws ApiRequestException{

        try {
            userService.saveUser(usersDTO);
            return new CustomResponse("User added successfully!", OK);
        } catch (Exception e) {
            throw new ApiRequestException("User could not be added");
        }
    }

    @PreAuthorize("hasAuthority('Edit_User')")
    @GetMapping(value = "/editUser/{id}", produces = APPLICATION_JSON_VALUE)
    public UsersDTO editUser(@PathVariable("id") int id) throws ApiRequestException {

        try {
            return userService.getUser(id);
        } catch (Exception e) {
            throw new ApiRequestException("User not found");
        }
    }

    @PreAuthorize("hasAuthority('Edit_User')")
    @PutMapping(value = "/updateUser", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public CustomResponse updateUser(@RequestBody UsersDTO usersDTO) throws ApiRequestException{

        try {
            userService.updateUser(usersDTO);
            return new CustomResponse("User updated successfully", OK);
        } catch (Exception e) {
            throw new ApiRequestException("User could not be updated");
        }
    }

    @PreAuthorize("hasAuthority('Delete_User')")
    @DeleteMapping(value = "/deleteUser/{id}", produces = APPLICATION_JSON_VALUE)
    public CustomResponse removeUser(@PathVariable("id") int id) throws ApiRequestException {

        try {
            userService.removeUser(id);
            return new CustomResponse("User deleted successfully", OK);
        } catch (Exception e) {
            throw new ApiRequestException("User could not be deleted");
        }
    }
}
