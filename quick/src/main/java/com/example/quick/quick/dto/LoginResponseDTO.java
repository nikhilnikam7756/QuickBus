package com.example.quick.quick.dto;


public class LoginResponseDTO {
    private boolean success;
    private String message;
    private String fullName;
    private Long agentId;
    private Long id; 
    

 // ✅ Constructor with full name
    public LoginResponseDTO(boolean success, String message, String fullName, Long agentId, Long id) {
        this.success = success;
        this.message = message;
        this.fullName = fullName;
        this.agentId = agentId;
        this.id = id;

        
    }
    
 // ✅ Constructor without full name (for failed logins)
    public LoginResponseDTO(boolean success, String message) {
        this.success = success;	
        this.message = message;
        this.fullName = null; // Default to null
        this.agentId = null; // Default to null
       

    }

   

	public boolean isSuccess() {
        return success;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFullName() { // ✅ Fixed method name capitalization
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getAgentId() { // ✅ Added getter for agentId
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

	
}

