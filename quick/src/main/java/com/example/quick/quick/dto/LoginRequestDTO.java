package com.example.quick.quick.dto;

public class LoginRequestDTO {
    private String email;
    private String mobileNo;
    private String password;
    
 // ✅ Default constructor (required for Spring Boot)
    public LoginRequestDTO() {}
    
    public LoginRequestDTO(String email, String mobileNo, String password) {
        this.email = email;
        this.mobileNo = mobileNo;
        this.password = password;
    }
    
 // ✅ Default constructor (required for Spring Boot)
    

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }
   

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for mobileNo
    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
