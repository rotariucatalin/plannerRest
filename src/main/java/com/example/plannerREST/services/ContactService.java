package com.example.plannerREST.services;

import com.example.plannerREST.dto.ContactDTO;
import com.example.plannerREST.entities.Contact;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface ContactService {

    List<ContactDTO> getAllContacts(int page, int size, String filter);
    @Cacheable(value = "contacts", key = "#contactId")
    ContactDTO getContact(int contactId);
    @CachePut(value = "contacts", key = "#contactDTO.getId()")
    Contact saveContact(ContactDTO contactDTO);
    @CachePut(value = "contacts", key = "#contactDTO.getId()")
    void updateContact(ContactDTO contactDTO);
    @CacheEvict(value = "contacts", allEntries = true)
    void removeContact(int contactId);
}
