package com.example.quick.quick.dto;

public class AgentDTO {
	
	 private Long agentId;
	    private String fullName;
	    private String mobileNo;
	    private String companyName;
	    private String city;
	    private String aadharNo;
	    private String panNo;
	    private String email;
	    private String alternateMobileNo;
	    

	    public AgentDTO(Long agentId, String fullName, String mobileNo, String companyName, String city, String aadharNo, String panNo, String email, String alternateMobileNo) {
	        this.agentId = agentId;
	        this.fullName = fullName;
	        this.mobileNo = mobileNo;
	        this.companyName = companyName;
	        this.city = city;
	        this.aadharNo = aadharNo;
	        this.panNo = panNo;	      
	        this.email = email;
	        this.alternateMobileNo = alternateMobileNo;
	    }


		public Long getAgentId() {
			return agentId;
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
