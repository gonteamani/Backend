package com.rs.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rs.dto.EmployeeDto;
import com.rs.dto.LeaveRequestDto;

public interface EmployeeService {


	public ResponseEntity<?> getById(Long id);

	public ResponseEntity<String> deleteById(Long id);

	public ResponseEntity<?> updateById(long id, EmployeeDto data);

	public ResponseEntity<String> add(EmployeeDto data);

	public ResponseEntity<List<EmployeeDto>> getAll();

	ResponseEntity<String> add(LeaveRequestDto data);	
	
}

