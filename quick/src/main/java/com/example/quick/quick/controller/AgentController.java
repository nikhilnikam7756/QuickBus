package com.example.quick.quick.controller;

import com.example.quick.quick.dto.AgentDTO;
import com.example.quick.quick.dto.LoginRequestDTO;
import com.example.quick.quick.dto.LoginResponseDTO;
import com.example.quick.quick.dto.OperatorDTO;
import com.example.quick.quick.entity.Agent;
import com.example.quick.quick.entity.Operator;
import com.example.quick.quick.service.AgentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;



@RestController
@RequestMapping("/api/agents")
@CrossOrigin
public class AgentController {

    @Autowired
    private AgentService agentService;

 // Register Agent
    @PostMapping("/register")
    public ResponseEntity<?> registerAgent(
            @Valid @ModelAttribute Agent agent,
            @RequestParam("aadharFile") MultipartFile aadharFile,
            @RequestParam("panFile") MultipartFile panFile) {
        try {
            // Validate file inputs
            if (aadharFile.isEmpty()) {
                return ResponseEntity.badRequest().body("Aadhar file is required.");
            }
            if (panFile.isEmpty()) {
                return ResponseEntity.badRequest().body("PAN file is required.");
            }

            // Save Aadhar file and generate file path
            String aadharFilePath = saveFile(aadharFile, "uploads/aadhar");

            // Save PAN file and generate file path
            String panFilePath = saveFile(panFile, "uploads/pan");

            // Set file paths to agent object
            agent.setAadharFilePath(aadharFilePath);
            agent.setPanFilePath(panFilePath);

            // Delegate to the service layer to save the agent
            Agent registeredAgent = agentService.registerAgent(agent, aadharFile, panFile);

            return ResponseEntity.ok("Agent registered successfully ");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    
 // Login Agent
    
    @PostMapping("/login")
    public ModelAndView loginAgent(
            @RequestParam String emailOrMobile,
            @RequestParam String password,
            HttpSession session) {
        try {
            // Determine if the input is an email or mobile number
            boolean isEmail = emailOrMobile.contains("@");
            String email = isEmail ? emailOrMobile : null;
            String mobileNo = isEmail ? null : emailOrMobile;

            // Create DTO object
            LoginRequestDTO loginRequestDTO = new LoginRequestDTO(email, mobileNo, password);

            // Authenticate agent
            LoginResponseDTO response = agentService.authenticateAgent(loginRequestDTO, session);

            if (response.isSuccess()) {
                // ✅ Store agent's name in session
                session.setAttribute("loggedInAgent", response.getFullName());
                session.setAttribute("loggedInAgentId", response.getAgentId());

                // ✅ Redirect to dashboard
                return new ModelAndView("redirect:/Agent/agentDashboard/agentDashboard.html");
            } else {
                // ❌ Redirect to login with error
                ModelAndView modelAndView = new ModelAndView("redirect:/Agent/AgentLogin/agentlogin.html");
                modelAndView.addObject("error", "Invalid credentials. Please try again.");
                return modelAndView;
            }
        } catch (Exception e) {
            // ❌ Redirect to login with error
            ModelAndView modelAndView = new ModelAndView("redirect:/Agent/AgentLogin/agentlogin.html");
            modelAndView.addObject("error", "Error: " + e.getMessage());
            return modelAndView;
        }
    }


    
    
    
    
 // Helper method to save files
    private String saveFile(MultipartFile file, String directory) throws Exception {
        // Create the directory if it does not exist
        Path uploadDir = Paths.get(directory);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // Generate a unique file name to avoid overwrites
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadDir.resolve(fileName);

        // Save the file to the specified path
        Files.copy(file.getInputStream(), filePath);

        // Return the file path as a string
        return filePath.toString();
    }
    
    
    @GetMapping("/name")
    public ResponseEntity<String> getAgentName(HttpSession session) {
        String agentName = (String) session.getAttribute("loggedInAgent");

        if (agentName != null) {
            return ResponseEntity.ok(agentName);
        } else {
            return ResponseEntity.status(401).body("Not logged in");
        }
    }
    
    
    @GetMapping("/profile")
    public AgentDTO getAgentProfile(HttpSession session) {
        System.out.println("Session ID: " + session.getId());
        System.out.println("LoggedInAgentId: " + session.getAttribute("loggedInAgentId"));
        Long agentId = (Long) session.getAttribute("loggedInAgentId");
        if (agentId == null) {
            throw new IllegalStateException("Agent not logged in");
        }
        Agent agent = agentService.getAgentById(agentId);
        if (agent == null) {
            throw new IllegalStateException("Agent not found");
        }

        AgentDTO dto = new AgentDTO(
                agent.getAgentId(),
                agent.getFullName(),
                agent.getMobileNo(),
                agent.getCompanyName(),
                agent.getCity(),
                agent.getAadharNo(),
                agent.getPanNo(),
                agent.getEmail(),
                agent.getAlternateMobileNo()

        );

        System.out.println("Returning AgentDTO: " + dto);
        System.out.println("Full Name: " + dto.getFullName());
        System.out.println("Mobile No: " + dto.getMobileNo());
        System.out.println("Company Name: " + dto.getCompanyName());
        System.out.println("City: " + dto.getCity());
        System.out.println("Aadhar No: " + dto.getAadharNo());
        System.out.println("PAN No: " + dto.getPanNo());
        System.out.println("Email: " + dto.getEmail());

        return dto;
    }
    
    
    
    @PutMapping("/profile")
    public AgentDTO updateAgentProfile(@RequestBody AgentDTO agentDTO, HttpSession session) {
    	
        Long agentId = (Long) session.getAttribute("loggedInAgentId");
        if (agentId == null) {
            throw new IllegalStateException("Unauthorized update attempt");
        }
        Agent updatedAgent = agentService.updateAgent(agentId, agentDTO);
        return new AgentDTO(
                updatedAgent.getAgentId(),
                updatedAgent.getFullName(),
                updatedAgent.getMobileNo(),
                updatedAgent.getCompanyName(),
                updatedAgent.getCity(),
                updatedAgent.getAadharNo(),
                updatedAgent.getPanNo(),
                updatedAgent.getEmail(),
                updatedAgent.getAlternateMobileNo()
        );
    }


}



