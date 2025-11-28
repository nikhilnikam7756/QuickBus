package com.example.quick.quick.controller;

import com.example.quick.quick.model.Station;
import com.example.quick.quick.service.StationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
@CrossOrigin("http://localhost:8082") // Allow access from your frontend
public class StationController {
	
	 private final StationService stationService;

	    public StationController(StationService stationService) {
	        this.stationService = stationService;
	    }

	    @GetMapping
	    public List<Station> getAllStations() {
	        return stationService.getAllStations();
	    }

}
