package com.example.plannerREST.services;

import com.example.plannerREST.dto.ContactDTO;
import com.example.plannerREST.entities.Contact;

import java.util.List;

public interface ContactService {

    List<ContactDTO> getAllContacts(int page, int size, String filter);
    ContactDTO getContact(int contactId);
    Contact saveContact(ContactDTO contactDTO);
    void updateContact(ContactDTO contactDTO);

}
