package com.example.plannerREST.repositories;

import com.example.plannerREST.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    @Query("SELECT c FROM Contact c WHERE c.name LIKE %:filter% OR c.email LIKE %:filter% ")
    Page<Contact> findAll(Pageable pageable, @Param("filter") String filter);
}
