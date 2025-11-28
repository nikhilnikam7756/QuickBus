package com.example.quick.quick.service;


import com.example.quick.quick.dto.AgentDTO;
import com.example.quick.quick.dto.LoginRequestDTO;
import com.example.quick.quick.dto.LoginResponseDTO;
import com.example.quick.quick.entity.Agent;
import com.example.quick.quick.repository.AgentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Optional;


import java.io.File;
import java.io.IOException;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;
    
    
    

    // Base directory where the files will be stored
    private static final String BASE_DIR = "C:/Users/user/OneDrive/Desktop/Project_directory/Agents/";

    public Agent registerAgent(Agent agent, MultipartFile aadharFile, MultipartFile panFile) throws IOException {
        // Step 1: Save the agent in the database
        Agent savedAgent = agentRepository.save(agent);
        Long agentId = savedAgent.getAgentId(); // Retrieve the auto-generated ID

        // Step 2: Define the file paths
        String aadharFilePath = BASE_DIR + "aadhar/" + agentId + "_aadhar.pdf";
        String panFilePath = BASE_DIR + "pan/" + agentId + "_pan.pdf";

        // Step 3: Save files to the filesystem
        saveFile(aadharFile, aadharFilePath);
        saveFile(panFile, panFilePath);

        return savedAgent;
    }

    private void saveFile(MultipartFile file, String path) throws IOException {
        File directory = new File(path).getParentFile(); // Ensure the directory exists
        if (!directory.exists()) {
            directory.mkdirs();
        }
        file.transferTo(new File(path)); // Save the file
    }
    
    
 //  Add this method for agent login
    public LoginResponseDTO authenticateAgent(LoginRequestDTO loginRequestDTO, HttpSession session) {
        Optional<Agent> agentOptional = agentRepository.findByEmailOrMobileNo(
                loginRequestDTO.getEmail(), loginRequestDTO.getMobileNo());

        if (agentOptional.isPresent()) {
            Agent agent = agentOptional.get();

            if (agent.getPassword().equals(loginRequestDTO.getPassword())) {
                // ✅ Store agent name in session
                session.setAttribute("loggedInAgentName", agent.getFullName());
                session.setAttribute("loggedInAgentId", agent.getAgentId());
                return new LoginResponseDTO(true, "Login successful!", agent.getFullName(), agent.getAgentId(), null);
            } else {
                return new LoginResponseDTO(false, "Invalid password.");
            }
        } else {
            return new LoginResponseDTO(false, "Agent not found.");
        }
    }

    public Agent getAgentById(Long agentId) {
        System.out.println("Fetching agent with ID: " + agentId);
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new IllegalArgumentException("Agent not found"));
        System.out.println("Agent found: " + agent);
        return agent;
    }
    
    public Agent updateAgent(Long id, AgentDTO agentDTO) {
        Agent agent = getAgentById(id);
        agent.setFullName(agentDTO.getFullName());
        agent.setMobileNo(agentDTO.getMobileNo());
        agent.setCompanyName(agentDTO.getCompanyName());
        agent.setCity(agentDTO.getCity());
        agent.setAadharNo(agentDTO.getAadharNo());
        agent.setPanNo(agentDTO.getPanNo());
        agent.setEmail(agentDTO.getEmail());
        agent.setAlternateMobileNo(agentDTO.getAlternateMobileNo());
        return agentRepository.save(agent);
    }
    
}