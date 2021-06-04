package com.example.plannerREST.repositories;

import com.example.plannerREST.entities.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    @Query("SELECT a FROM Activity a WHERE a.subject LIKE %:filter% OR a.reference LIKE %:filter% ")
    Page<Activity> findAll(Pageable pageable, @Param("filter") String filter);

}