package com.example.quick.quick.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "agent_reg")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_id")
    private Long agentId;

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

    @NotBlank(message = "Company Name is required")
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @NotBlank(message = "City is required")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "Aadhar No is required")
    @Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}", message = "Aadhar No must follow the format 0000-0000-0000")
    @Column(name = "aadhar_no", nullable = false, unique = true)
    private String aadharNo;

    @NotBlank(message = "PAN No is required")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "PAN No must follow the format AAAAA0000A")
    @Column(name = "pan_no", nullable = false, unique = true)
    private String panNo;

    @Column(name = "aadhar_file_path", nullable = false)
    private String aadharFilePath;

    @Column(name = "pan_file_path", nullable = false)
    private String panFilePath;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "alternate_mobile_no")
    private String alternateMobileNo;

	
	

	public Long getAgentId() {
		return agentId;
	}

	public String getAlternateMobileNo() {
		return alternateMobileNo;
	}

	public void setAlternateMobileNo(String alternateMobileNo) {
		this.alternateMobileNo = alternateMobileNo;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getAadharFilePath() {
		return aadharFilePath;
	}

	public void setAadharFilePath(String aadharFilePath) {
		this.aadharFilePath = aadharFilePath;
	}

	public String getPanFilePath() {
		return panFilePath;
	}

	public void setPanFilePath(String panFilePath) {
		this.panFilePath = panFilePath;
	}
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

