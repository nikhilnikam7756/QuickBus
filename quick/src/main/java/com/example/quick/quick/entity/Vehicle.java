package com.example.quick.quick.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;


import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;


    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "operator_id", referencedColumnName = "id", nullable = false)
    private Operator operator;

    private Date insuranceFrom;
    private Date insuranceTo;
    private String insuranceNumber;
    private String insuranceDocumentPath;

    private Date taxFrom;
    private Date taxTo;
    private String taxDocumentPath;

    private String chassisNumber;
    private String rcDocumentPath;
    
    
    
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	public Date getInsuranceFrom() {
		return insuranceFrom;
	}
	public void setInsuranceFrom(Date insuranceFrom) {
		this.insuranceFrom = insuranceFrom;
	}
	public Date getInsuranceTo() {
		return insuranceTo;
	}
	public void setInsuranceTo(Date insuranceTo) {
		this.insuranceTo = insuranceTo;
	}
	public String getInsuranceNumber() {
		return insuranceNumber;
	}
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	public String getInsuranceDocumentPath() {
		return insuranceDocumentPath;
	}
	public void setInsuranceDocumentPath(String insuranceDocumentPath) {
		this.insuranceDocumentPath = insuranceDocumentPath;
	}
	public Date getTaxFrom() {
		return taxFrom;
	}
	public void setTaxFrom(Date taxFrom) {
		this.taxFrom = taxFrom;
	}
	public Date getTaxTo() {
		return taxTo;
	}
	public void setTaxTo(Date taxTo) {
		this.taxTo = taxTo;
	}
	public String getTaxDocumentPath() {
		return taxDocumentPath;
	}
	public void setTaxDocumentPath(String taxDocumentPath) {
		this.taxDocumentPath = taxDocumentPath;
	}
	public String getChassisNumber() {
		return chassisNumber;
	}
	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}
	public String getRcDocumentPath() {
		return rcDocumentPath;
	}
	public void setRcDocumentPath(String rcDocumentPath) {
		this.rcDocumentPath = rcDocumentPath;
	}
    
    
    
}

