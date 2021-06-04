package com.example.plannerREST.repositories;

import com.example.plannerREST.entities.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("SELECT c FROM Company c WHERE c.name LIKE %:filter% OR c.email LIKE %:filter% ")
    Page<Company> findAll(Pageable pageable, @Param("filter") String filter);
}
