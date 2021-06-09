package com.example.plannerREST.controllers;

import com.example.plannerREST.dto.ContactDTO;
import com.example.plannerREST.entities.CustomResponse;
import com.example.plannerREST.exception.ApiRequestException;
import com.example.plannerREST.services.ContactService;
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
@RequestMapping(value = "/contacts")
public class ContactController {

    private Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @PreAuthorize("hasAuthority('View_Contact')")
    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Getting all contacts",
            notes = "Get all contacts. You can filter/change the number of companies that are returned",
            response = ContactDTO.class
    )
    public List<ContactDTO> getAllContacts(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int size,
                                           @RequestParam(defaultValue = "") String filter) throws ApiRequestException {

        try {
            return contactService.getAllContacts(page, size, filter);
        } catch (Exception e) {
            throw new ApiRequestException("Cannot get contact list!");
        }

    }

    @PreAuthorize("hasAuthority('Add_Contact')")
    @PostMapping(value = "/addContact", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Adding new contact",
            notes = "Adding new contact",
            response = CustomResponse.class
    )
    public CustomResponse addContact(@RequestBody ContactDTO contactDTO) throws ApiRequestException {

        try {
            contactService.saveContact(contactDTO);
            return new CustomResponse("Contact successfully added", OK);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('Edit_Contact')")
    @GetMapping(value = "/editContact/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Edit specific contact",
            notes = "Edit specific contact based on specific id",
            response = ContactDTO.class
    )
    public ContactDTO editContact(@PathVariable("id") int id) throws ApiRequestException {

        try {
            return contactService.getContact(id);
        } catch (Exception e) {
            throw new ApiRequestException("Contact not found!");
        }
    }

    @PreAuthorize("hasAuthority('Edit_Contact')")
    @PutMapping(value = "/updateContact", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Update specific contact",
            notes = "Update specific contact based on specific id",
            response = CustomResponse.class
    )
    public CustomResponse updateContact(@RequestBody ContactDTO contactDTO) throws ApiRequestException {

        try {
            contactService.updateContact(contactDTO);
            return new CustomResponse("Contact successfully updated", OK);
        } catch (Exception e) {
            throw new ApiRequestException("Contact could not be updated!");
        }

    }

    @PreAuthorize("hasAuthority('Delete_Contact')")
    @DeleteMapping(value = "/removeContact/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Delete contact",
            notes = "Delete contact based on specific id",
            response = CustomResponse.class
    )
    public CustomResponse removeContact(@PathVariable(value = "id") int id) throws ApiRequestException {

        try {

            contactService.removeContact(id);
            return new CustomResponse("Contact successfully removed", OK);

        } catch (Exception e) {
            throw new ApiRequestException("Contact could not be deleted!");
        }

    }
}
