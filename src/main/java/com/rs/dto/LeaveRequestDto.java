package com.rs.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
public class LeaveRequestDto {
	private Long id;
	private String startDate;
	private String endDate;
	private String reason;
	private String mobileNo;
	private String status;
	
	@JsonBackReference
	private EmployeeDto employee;
	

}
