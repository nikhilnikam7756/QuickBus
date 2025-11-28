package com.example.quick.quick.controller;

import com.example.quick.quick.model.BusSchedule;
import com.example.quick.quick.repository.BusScheduleRepository;
import com.example.quick.quick.repository.StationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BusScheduleController {

    private final BusScheduleRepository busScheduleRepository;
    private final StationRepository stationRepository;

    public BusScheduleController(BusScheduleRepository busScheduleRepository, StationRepository stationRepository) {
        this.busScheduleRepository = busScheduleRepository;
        this.stationRepository = stationRepository;
    }

    @GetMapping("/search")
    public List<BusSchedule> searchBuses(
            @RequestParam int fromStationId,
            @RequestParam int toStationId
    ) {
        return busScheduleRepository.findByFromAndToStations(fromStationId, toStationId);
    }
}

