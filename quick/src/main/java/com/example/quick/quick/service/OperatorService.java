package com.example.quick.quick.service;

import com.example.quick.quick.dto.LoginRequestDTO;
import com.example.quick.quick.dto.LoginResponseDTO;
import com.example.quick.quick.dto.OperatorDTO;
import com.example.quick.quick.entity.Agent;
import com.example.quick.quick.entity.Operator;
import com.example.quick.quick.repository.AgentRepository;
import com.example.quick.quick.repository.OperatorRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class OperatorService {

    private static final String BASE_DIR = "C:/Users/user/OneDrive/Desktop/Project_directory/Operators/";

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private AgentRepository agentRepository;

    public Operator registerOperator(Operator operator, MultipartFile drivingLicenceFile, Long agentId) throws IOException {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        operator.setAgent(agent);
        Operator savedOperator = operatorRepository.save(operator);
        Long operatorId = savedOperator.getId();

        String licenceFilePath = BASE_DIR + "licence/" + operatorId + "_licence.pdf";
        saveFile(drivingLicenceFile, licenceFilePath);
        savedOperator.setDrivingLicenceFilePath(licenceFilePath);

        return operatorRepository.save(savedOperator);
    }

    private void saveFile(MultipartFile file, String path) throws IOException {
        File directory = new File(path).getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        file.transferTo(new File(path));
    }

    public LoginResponseDTO authenticateOperator(LoginRequestDTO loginRequestDTO, HttpSession session) {
        Optional<Operator> operatorOptional = operatorRepository.findByEmailOrMobileNo(
                loginRequestDTO.getEmail(), loginRequestDTO.getMobileNo());

        if (operatorOptional.isPresent()) {
            Operator operator = operatorOptional.get();	

            if (operator.getPassword().equals(loginRequestDTO.getPassword())) {
                session.setAttribute("loggedInOperatorName", operator.getFullName());
                session.setAttribute("loggedInOperatorId", operator.getId());
                System.out.println("LoggedInOperatorId set: " + operator.getId());

                
                return new LoginResponseDTO(true, "Login successful!", operator.getFullName(), null, operator.getId());
            } else {
                return new LoginResponseDTO(false, "Invalid password.");
            }
        } else {
            return new LoginResponseDTO(false, "Operator not found.");
        }
    }

    public List<OperatorDTO> getOperatorsByAgent(Long agentId) {
        List<Operator> operators = operatorRepository.findByAgentAgentId(agentId);
        return operators.stream().map(op -> new OperatorDTO(
                op.getFullName(),
                op.getMobileNo(),
                op.getVehicleRegistrationNumber(),
                op.getDrivinglicenceNo(), null, null
        )).collect(Collectors.toList());
    }

    public Operator getOperatorById(Long id) {
        System.out.println("Fetching operator with ID: " + id);
        Operator operator = operatorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Operator not found"));
        System.out.println("Operator found: " + operator);
        return operator;
    }


  
    public Operator updateOperator(Long id, OperatorDTO operatorDTO) {
        Operator operator = getOperatorById(id);
        operator.setFullName(operatorDTO.getFullName());
        operator.setMobileNo(operatorDTO.getMobileNo());
        operator.setVehicleRegistrationNumber(operatorDTO.getVehicleRegistrationNumber());
        operator.setDrivinglicenceNo(operatorDTO.getDrivinglicenceNo());
        operator.setEmail(operatorDTO.getEmail());
        operator.setAlternateMobileNo(operatorDTO.getAlternateMobileNo());
        return operatorRepository.save(operator);
    }
}
