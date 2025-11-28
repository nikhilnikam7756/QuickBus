package com.example.quick.quick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quick.quick.entity.Agent;
import com.example.quick.quick.entity.Operator;
import com.example.quick.quick.entity.Vehicle;
import com.example.quick.quick.repository.AgentRepository;
import com.example.quick.quick.repository.OperatorRepository;
import com.example.quick.quick.repository.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    public Vehicle addVehicle(Vehicle vehicle, Long agentId, Long operatorId) {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));
        Operator operator = operatorRepository.findById(operatorId)
                .orElseThrow(() -> new RuntimeException("Operator not found"));

        vehicle.setAgent(agent);
        vehicle.setOperator(operator);

        return vehicleRepository.save(vehicle);
    }

    public Vehicle getVehicleByOperator(Long operatorId) {
    	return vehicleRepository.findByOperatorId(operatorId);
    }
}

