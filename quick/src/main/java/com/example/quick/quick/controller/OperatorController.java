package com.example.quick.quick.controller;

import com.example.quick.quick.dto.LoginRequestDTO;
import com.example.quick.quick.dto.LoginResponseDTO;
import com.example.quick.quick.dto.OperatorDTO;
import com.example.quick.quick.entity.Operator;
import com.example.quick.quick.service.OperatorService;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/operators")
@CrossOrigin
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @PostMapping("/register")
    public ResponseEntity<?> registerOperator(
            @Valid @ModelAttribute Operator operator,
            @RequestParam("drivingLicenceFile") MultipartFile drivingLicenceFile,
            HttpSession session) {

        Long agentId = (Long) session.getAttribute("loggedInAgentId");

        if (agentId == null) {
            return ResponseEntity.badRequest().body("Error: Agent is not logged in.");
        }

        try {
            if (drivingLicenceFile.isEmpty()) {
                return ResponseEntity.badRequest().body("Driving license file is required.");
            }

            String drivingLicenceFilePath = saveFile(drivingLicenceFile, "uploads/licence");
            operator.setDrivingLicenceFilePath(drivingLicenceFilePath);

            Operator registeredOperator = operatorService.registerOperator(operator, drivingLicenceFile, agentId);

            return ResponseEntity.ok("Operator registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

	    @PostMapping("/login")
	    public ModelAndView loginOperator(
	            @RequestParam String emailOrMobile,
	            @RequestParam String password,
	            HttpSession session) {
	        try {
	            boolean isEmail = emailOrMobile.contains("@");
	            String email = isEmail ? emailOrMobile : null;
	            String mobileNo = isEmail ? null : emailOrMobile;
	
	            LoginRequestDTO loginRequestDTO = new LoginRequestDTO(email, mobileNo, password);
	            LoginResponseDTO response = operatorService.authenticateOperator(loginRequestDTO, session);
	
	            if (response.isSuccess()) {
	                session.setAttribute("loggedInOperator", response.getFullName());
	                session.setAttribute("loggedInOperatorId", response.getId());
	                
	                System.out.println("LoggedInOperatorId set: " + response.getId());
	                System.out.println("Session ID at login: " + session.getId());
	
	                return new ModelAndView("redirect:/operator/operatorDashboard/operatordashboard.html");
	            } else {
	                ModelAndView modelAndView = new ModelAndView("redirect:/operator/operatorLogin/operatorlogin.html");
	                modelAndView.addObject("error", "Invalid credentials. Please try again.");
	                return modelAndView;
	            }
	        } catch (Exception e) {
	            ModelAndView modelAndView = new ModelAndView("redirect:/operator/operatorLogin/operatorlogin.html");
	            modelAndView.addObject("error", "Error: " + e.getMessage());	
	            return modelAndView;
	        }
	    }

    private String saveFile(MultipartFile file, String directory) throws Exception {
        Path uploadDir = Paths.get(directory);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadDir.resolve(fileName);

        Files.copy(file.getInputStream(), filePath);
        return filePath.toString();
    }

    @GetMapping("/name")
    public ResponseEntity<String> getOperatorName(HttpSession session) {
        String operatorName = (String) session.getAttribute("loggedInOperator");

        if (operatorName != null) {
            return ResponseEntity.ok(operatorName);
        } else {
            return ResponseEntity.status(401).body("Not logged in");
        }
    }

    @GetMapping("/profile")
    public OperatorDTO getOperatorProfile(HttpSession session) {
        System.out.println("Session ID: " + session.getId());
        System.out.println("LoggedInOperatorId: " + session.getAttribute("loggedInOperatorId"));
        Long operatorId = (Long) session.getAttribute("loggedInOperatorId");
        if (operatorId == null) {
            throw new IllegalStateException("Operator not logged in");
        }
        Operator operator = operatorService.getOperatorById(operatorId);
        if (operator == null) {
            throw new IllegalStateException("Operator not found");
        }

        OperatorDTO dto = new OperatorDTO(
                operator.getFullName(),
                operator.getMobileNo(),
                operator.getVehicleRegistrationNumber(),
                operator.getDrivinglicenceNo(),
                operator.getEmail(),
                operator.getAlternateMobileNo()
        );

        System.out.println("Returning OperatorDTO: " + dto);
        System.out.println("Full Name: " + dto.getFullName());
        System.out.println("Mobile No: " + dto.getMobileNo());
        System.out.println("Vehicle Reg No: " + dto.getVehicleRegistrationNumber());
        System.out.println("Driving Licence No: " + dto.getDrivinglicenceNo());
        System.out.println("Email: " + dto.getEmail());
        System.out.println("Alternate Mobile No: " + dto.getAlternateMobileNo());

        return dto;
    }

        
    
    @PutMapping("/profile")
    public OperatorDTO updateOperatorProfile(@RequestBody OperatorDTO operatorDTO, HttpSession session) {
        Long operatorId = (Long) session.getAttribute("loggedInOperatorId");
        if (operatorId == null) {
            throw new IllegalStateException("Unauthorized update attempt");
        }
        Operator updatedOperator = operatorService.updateOperator(operatorId, operatorDTO);
        return new OperatorDTO(
                updatedOperator.getFullName(),
                updatedOperator.getMobileNo(),
                updatedOperator.getVehicleRegistrationNumber(),
                updatedOperator.getDrivinglicenceNo(),
                updatedOperator.getEmail(),
                updatedOperator.getAlternateMobileNo()
        );
    }
}
