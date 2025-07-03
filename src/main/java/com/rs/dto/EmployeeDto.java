package com.rs.dto;

import java.util.List;

import lombok.Data;

@Data
public class EmployeeDto {
	private Long id;
	private String fullName;
	private String mobileNo;
	private String emailId;
	private String designation;
	private String empId;
	private String role;
	private String status;
    
	private List<LeaveRequestDto> leaveRequests;
}
