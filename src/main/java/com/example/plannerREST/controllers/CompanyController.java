package com.example.plannerREST.controllers;

import com.example.plannerREST.dto.CompanyDTO;
import com.example.plannerREST.entities.CustomException;
import com.example.plannerREST.entities.CustomResponse;
import com.example.plannerREST.exception.ApiRequestException;
import com.example.plannerREST.services.CompanyService;
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
@RequestMapping(value = "/companies")
public class CompanyController {

    private Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @PreAuthorize("hasAuthority('View_Company')")
    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Get all companies",
            notes = "Get all companies. You can filter/change the number of companies that are returned",
            response = CompanyDTO.class
    )
    public List<CompanyDTO> getAllCompanies(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "5") int size,
                                            @RequestParam(defaultValue = "") String filter) throws ApiRequestException {

        try {
            return companyService.getAllCompanies(page, size, filter);
        } catch (Exception e) {
            throw new ApiRequestException("Cannot get company list!");
        }
    }

    @PreAuthorize("hasAuthority('Add_Company')")
    @PostMapping(value = "/addCompany", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Adding new company",
            notes = "Adding new company",
            response = CustomException.class
    )
    public CustomResponse addCompany(@RequestBody CompanyDTO companyDTO) throws ApiRequestException {

        try {
            companyService.saveCompany(companyDTO);
            return new CustomResponse("Company added successfully", OK);
        } catch (Exception e) {
            throw new ApiRequestException("Company could not be added!");
        }
    }

    @PreAuthorize("hasAuthority('Edit_Company')")
    @GetMapping(value = "/editCompany/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Edit specific company",
            notes = "Edit specific company. Getting the information for a specific company",
            response = CompanyDTO.class
    )
    public CompanyDTO editCompany(@PathVariable("id") int id) throws ApiRequestException {

        try {
            return companyService.editCompany(id);
        } catch (Exception e) {
            throw new ApiRequestException("Company not found");
        }
    }

    @PreAuthorize("hasAuthority('Edit_Company')")
    @PutMapping(value = "/updateCompany", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Update specific company",
            notes = "Update specific company based on new values",
            response = CustomResponse.class
    )
    public CustomResponse updateCompany(@RequestBody CompanyDTO companyDTO) throws ApiRequestException {

        try {
            companyService.updateCompany(companyDTO);
            return new CustomResponse("Company updated successfully", OK);
        } catch (Exception e) {
            throw new ApiRequestException("Company could not be updated");
        }
    }

    @PreAuthorize("hasAuthority('Delete_Company')")
    @DeleteMapping(value = "/removeCompany/{id}")
    @ApiOperation(
            value = "Delete specific company",
            notes = "Delete specific company based on id",
            response = CustomResponse.class
    )
    public CustomResponse removeCompany(@PathVariable("id") int id) throws ApiRequestException {

        try {
            companyService.removeCompany(id);
            return new CustomResponse("Company successfully removed", OK);
        } catch (Exception e) {
            throw new ApiRequestException("Company could not be deleted!");
        }
    }
}
