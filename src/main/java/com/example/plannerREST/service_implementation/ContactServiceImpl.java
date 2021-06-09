package com.example.plannerREST.service_implementation;

import com.example.plannerREST.dto.ActivityDTO;
import com.example.plannerREST.dto.CompanyDTO;
import com.example.plannerREST.dto.ContactDTO;
import com.example.plannerREST.entities.Company;
import com.example.plannerREST.entities.Contact;
import com.example.plannerREST.repositories.CompanyRepository;
import com.example.plannerREST.repositories.ContactRepository;
import com.example.plannerREST.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<ContactDTO> getAllContacts(int page, int size, String filter) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Contact> contactPage = contactRepository.findAll(pageable, filter);

        List<Contact> contacts = contactPage.getContent();

        return contacts.stream().map(this::convertContactDTO).collect(Collectors.toList());
    }

    @Override
    public ContactDTO getContact(int contactId) {

        Contact contact = contactRepository.findById(contactId).get();

        return convertContactDTO(contact);
    }


    @Override
    @Transactional
    public Contact saveContact(ContactDTO contactDTO) {

        List<Company> companies = new ArrayList<>();

        Contact contact = new Contact();
        contact.setId(contactDTO.getId());
        contact.setName(contactDTO.getName());
        contact.setPosition(contactDTO.getPosition());
        contact.setDepartment(contactDTO.getDepartment());
        contact.setCountry(contactDTO.getCountry());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone(contactDTO.getPhone());
        contact.setStatus(contactDTO.getStatus());
        contact.setConsent(contactDTO.getConsent());

        contactDTO.getCompany().stream().forEach(companyDTO -> {

            companies.add(companyRepository.findById(companyDTO.getId()).get());

        });

        contact.setCompanies(companies);

        contactRepository.save(contact);

        return contact;
    }


    @Override
    @Transactional
    public void updateContact(ContactDTO contactDTO) {
        saveContact(contactDTO);
    }

    @Override
    public void removeContact(int contactId) {
        contactRepository.deleteById(contactId);
    }

    private ContactDTO convertContactDTO(Contact contact) {

        List<ActivityDTO> activityDTOList = new ArrayList<>();
        List<CompanyDTO> companyDTOList = new ArrayList<>();

        contact.getActivityList().stream().forEach(activity -> {

            ActivityDTO activityDTO = new ActivityDTO(
                    activity.getId(),
                    activity.getSubject(),
                    activity.getReference(),
                    activity.getType(),
                    activity.getDate(),
                    activity.getStatus(),
                    activity.getLocation());

            activityDTOList.add(activityDTO);
        });

        contact.getCompanies().stream().forEach(company -> {

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

            companyDTOList.add(companyDTO);
        });


        ContactDTO contactDTO = new ContactDTO(
                contact.getId(),
                contact.getName(),
                contact.getPosition(),
                contact.getDepartment(),
                contact.getCountry(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getStatus(),
                contact.getConsent(),
                activityDTOList,
                companyDTOList
                );

        return contactDTO;
    }
}
