package com.example.quick.quick.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "operator")
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full Name is required")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank(message = "Mobile No is required")
    @Pattern(regexp = "\\d{10}", message = "Mobile No must be 10 digits")
    @Column(name = "mobile_no", nullable = false, unique = true)
    private String mobileNo;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "City is required")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "Vehicle Registration Number is required")
    @Column(name = "vehicle_registration_number", nullable = false, unique = true)
    private String vehicleRegistrationNumber;

    @NotBlank(message = "Driving Licence No is required")
    @Column(name = "driving_licence", nullable = false, unique = true)
    private String drivinglicenceNo;

    @Column(nullable = false)
    private String drivingLicenceFilePath;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    @Column(name = "alternate_mobile_no")
    private String alternateMobileNo;
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}

	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}

	public String getDrivinglicenceNo() {
		return drivinglicenceNo;
	}

	public void setDrivinglicenceNo(String drivinglicenceNo) {
		this.drivinglicenceNo = drivinglicenceNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public String getAlternateMobileNo() {
		return alternateMobileNo;
	}

	public void setAlternateMobileNo(String alternateMobileNo) {
		this.alternateMobileNo = alternateMobileNo;
	}

    private String insuranceNumber;
    private String insuranceDocumentPath;

    public String getDrivingLicenceFilePath() {
		return drivingLicenceFilePath;
	}

	public void setDrivingLicenceFilePath(String drivingLicenceFilePath) {
		this.drivingLicenceFilePath = drivingLicenceFilePath;
	}

	
    
}
