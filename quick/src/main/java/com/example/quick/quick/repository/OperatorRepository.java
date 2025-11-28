package com.example.quick.quick.repository;


import com.example.quick.quick.entity.Operator;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {
	 boolean existsByMobileNo(String mobileNo);
	    boolean existsByEmail(String email);
	    
	    List<Operator> findByAgentAgentId(Long agentId);
	 
	 // Add these methods for login authentication
	    Optional<Operator> findByEmailOrMobileNo(String email, String mobileNo);
}
