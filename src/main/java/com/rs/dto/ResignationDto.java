package com.rs.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
@Data
public class ResignationDto {
		private Long id;
		private String dateOfApplying;
		private String reason;
		private String mobileNo;
		private String status;

		@JsonBackReference
		private EmployeeDto employee; // Reference to parent employee
}