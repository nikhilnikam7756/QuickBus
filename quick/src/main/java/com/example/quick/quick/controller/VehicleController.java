package com.example.quick.quick.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quick.quick.entity.Vehicle;
import com.example.quick.quick.service.VehicleService;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/add/{agentId}/{operatorId}")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle, 
                                              @PathVariable Long agentId, 
                                              @PathVariable Long operatorId) {
        Vehicle savedVehicle = vehicleService.addVehicle(vehicle, agentId, operatorId);
        return ResponseEntity.ok(savedVehicle);
    }

    @GetMapping("/operator/{operatorId}")
    public ResponseEntity<?> getVehicleByOperator(@PathVariable Long operatorId) {
        Vehicle vehicle = vehicleService.getVehicleByOperator(operatorId);
        
        if (vehicle == null) {
            return ResponseEntity.noContent().build();  // Returns 204 if no vehicle found
        }
        
        return ResponseEntity.ok(vehicle);  // Returns vehicle details if found
    }

    @PostMapping("/vehicle/save")
    public ResponseEntity<String> saveVehicle(@RequestBody Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return ResponseEntity.ok("Vehicle data saved successfully!");
    }

}
