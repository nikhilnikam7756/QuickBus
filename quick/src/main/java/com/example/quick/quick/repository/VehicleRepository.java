package com.example.quick.quick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


import com.example.quick.quick.entity.Vehicle;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	Optional<Vehicle> findByOperatorId(Long operatorId);
}
