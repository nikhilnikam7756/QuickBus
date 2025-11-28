package com.example.quick.quick.repository;

import com.example.quick.quick.model.Station;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

	List<Station> findAll();

}
