package com.example.quick.quick.repository;



import com.example.quick.quick.entity.Agent;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

	
@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    boolean existsByMobileNo(String mobileNo);
    boolean existsByEmail(String email);
    boolean existsByAadharNo(String aadharNo);
    boolean existsByPanNo(String panNo);
    
 
 // Add these methods for login authentication
    Optional<Agent> findByEmailOrMobileNo(String email, String mobileNo);
}
