package com.example.quick.quick.dto;

public class OperatorDTO {

    private String fullName;
    private String mobileNo;
    private String vehicleRegistrationNumber;
    private String drivinglicenceNo;
    private String email;
    private String alternateMobileNo;

    public OperatorDTO(String fullName, String mobileNo, String vehicleRegistrationNumber, String drivinglicenceNo, String email, String alternateMobileNo) {
        this.fullName = fullName;
        this.mobileNo = mobileNo;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        this.drivinglicenceNo = drivinglicenceNo;
        this.email = email;
        this.alternateMobileNo = alternateMobileNo;
      
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

    public String getAlternateMobileNo() {
        return alternateMobileNo;
    }

    public void setAlternateMobileNo(String alternateMobileNo) {
        this.alternateMobileNo = alternateMobileNo;
    }
} 
