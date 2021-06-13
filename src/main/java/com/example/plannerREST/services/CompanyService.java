package com.example.plannerREST.services;

import com.example.plannerREST.dto.CompanyDTO;
import com.example.plannerREST.entities.Company;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface CompanyService {

    List<CompanyDTO> findAllCompanies();
    List<CompanyDTO> getAllCompanies(int page, int size, String filter);
    @CachePut(value = "companies", key = "#companyDTO.getId()")
    Company saveCompany(CompanyDTO companyDTO);
    @Cacheable(value = "companies", key = "#companyId")
    CompanyDTO editCompany(int companyId);
    @CachePut(value = "companies", key = "#companyDTO.getId()")
    Company updateCompany(CompanyDTO companyDTO);
    @CacheEvict(value = "companies", allEntries = true)
    void removeCompany(int companyId);

}
