package com.example.quick.quick.repository;

import com.example.quick.quick.model.BusSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Integer> {

    @Query("SELECT b FROM BusSchedule b WHERE b.fromStation.id = :fromStationId AND b.toStation.id = :toStationId")
    List<BusSchedule> findByFromAndToStations(@Param("fromStationId") int fromStationId, @Param("toStationId") int toStationId);
}

