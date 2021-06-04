package com.example.plannerREST.repositories;
import com.example.plannerREST.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByUserName(String username);

    @Query("SELECT u FROM Users u WHERE u.firstName LIKE %:filter% OR u.lastName LIKE %:filter% ")
    Page<Users> findAll(Pageable pageable, @Param("filter") String filter);
}
