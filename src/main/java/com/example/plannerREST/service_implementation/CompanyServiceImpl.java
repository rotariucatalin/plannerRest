package com.example.plannerREST.service_implementation;

import com.example.plannerREST.dto.CompanyDTO;
import com.example.plannerREST.dto.ContactDTO;
import com.example.plannerREST.entities.Company;
import com.example.plannerREST.entities.Contact;
import com.example.plannerREST.repositories.CompanyRepository;
import com.example.plannerREST.repositories.ContactRepository;
import com.example.plannerREST.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<CompanyDTO> findAllCompanies() {
        return companyRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CompanyDTO> getAllCompanies(int page, int size, String filter) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Company> companyPage = companyRepository.findAll(pageable, filter);
        List<Company> companies = companyPage.getContent();

        return companies.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Company saveCompany(CompanyDTO companyDTO) {

        Company company = setDefaultFieldsValues(companyDTO);

        if(companyDTO.getSalesContact() != null) {

            Contact salesContact = new Contact();
            salesContact.setId(companyDTO.getSalesContact().getId());
            company.setSalesContact(salesContact);
        }

        if(companyDTO.getSalesCompany() != null) {

            Company salesCompany = new Company();
            salesCompany.setId(companyDTO.getSalesCompany().getId());
            company.setSalesCompany(salesCompany);
        }

        companyRepository.save(company);

        return company;
    }

    @Override
    public CompanyDTO editCompany(int companyId) {

        Company company = companyRepository.findById(companyId).get();

        return convertToDTO(company);
    }

    @Override
    public Company updateCompany(CompanyDTO companyDTO) {

        Company company = setDefaultFieldsValues(companyDTO);

        if(companyDTO.getSalesContact() != null) {

            Contact salesContact = contactRepository.findById(companyDTO.getSalesContact().getId()).get();
            company.setSalesContact(salesContact);
        }

        if(companyDTO.getSalesCompany() != null) {

            Company salesCompany = companyRepository.findById(companyDTO.getSalesCompany().getId()).get();
            company.setSalesCompany(salesCompany);
        }
        companyRepository.save(company);

        return company;
    }

    @Override
    public void removeCompany(int companyId) {
        companyRepository.deleteById(companyId);
    }

    /*
    *   Setting default values for mandatory fields ( to avoid duplicates )
    * */
    private Company setDefaultFieldsValues(CompanyDTO companyDTO) {

        Company company = new Company();
        company.setId(companyDTO.getId());
        company.setName(companyDTO.getName());
        company.setType(companyDTO.getType());
        company.setEmail(companyDTO.getEmail());
        company.setPhone(companyDTO.getPhone());
        company.setPostAddress(companyDTO.getPostAddress());
        company.setCity(companyDTO.getCity());
        company.setWebsite(companyDTO.getWebsite());
        company.setDescription(companyDTO.getDescription());

        return company;
    }

    /*
    *   Converting Company object to CompanyDTO object
    * */
    private CompanyDTO convertToDTO(Company company) {

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        companyDTO.setType(company.getType());
        companyDTO.setEmail(company.getEmail());
        companyDTO.setPhone(company.getPhone());
        companyDTO.setPostAddress(company.getPostAddress());
        companyDTO.setCity(company.getCity());
        companyDTO.setWebsite(company.getWebsite());
        companyDTO.setDescription(company.getDescription());

        if(company.getSalesContact() != null) {
            ContactDTO salesContactDTO = new ContactDTO();
            salesContactDTO.setId(company.getSalesContact().getId());
            companyDTO.setSalesContact(salesContactDTO);
        }

        if(company.getSalesCompany() != null) {
            CompanyDTO salesCompanyDTO = new CompanyDTO();
            salesCompanyDTO.setId(company.getSalesCompany().getId());
            companyDTO.setSalesCompany(salesCompanyDTO);
        }

        return companyDTO;
    }
}
