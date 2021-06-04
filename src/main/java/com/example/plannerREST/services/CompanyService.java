package com.example.plannerREST.services;

import com.example.plannerREST.dto.CompanyDTO;
import com.example.plannerREST.entities.Activity;
import com.example.plannerREST.entities.Company;

import java.util.List;

public interface CompanyService {

    List<CompanyDTO> findAllCompanies();
    List<CompanyDTO> getAllCompanies(int page, int size, String filter);
    Company saveCompany(CompanyDTO companyDTO);
    CompanyDTO editCompany(int companyId);
    Company updateCompany(CompanyDTO companyDTO);
    void removeCompany(int companyId);

}
