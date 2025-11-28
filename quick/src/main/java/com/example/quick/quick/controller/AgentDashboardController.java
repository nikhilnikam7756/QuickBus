package com.example.quick.quick.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quick.quick.service.OperatorService;
import com.example.quick.quick.dto.OperatorDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:your-frontend-port", allowCredentials = "true")
public class AgentDashboardController {

    @Autowired
    private OperatorService operatorService;

    @GetMapping("/operators/details")
    public ResponseEntity<List<OperatorDTO>> getAllOperatorsByAgent(HttpSession session) {
        Long agentId = (Long) session.getAttribute("loggedInAgentId");
        System.out.println("Agent ID from session: " + agentId); // Add this line


        if (agentId == null) {
            return ResponseEntity.status(401).build(); // Unauthorized if no agentId in session
        }

        List<OperatorDTO> operators = operatorService.getOperatorsByAgent(agentId);
        return ResponseEntity.ok(operators);
    }
}
